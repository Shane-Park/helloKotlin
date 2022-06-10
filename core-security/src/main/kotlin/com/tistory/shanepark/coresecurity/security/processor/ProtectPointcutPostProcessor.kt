package com.tistory.shanepark.coresecurity.security.processor

import org.aspectj.weaver.tools.PointcutExpression
import org.aspectj.weaver.tools.PointcutParser
import org.aspectj.weaver.tools.PointcutPrimitive
import org.slf4j.LoggerFactory
import org.springframework.beans.BeansException
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.security.access.ConfigAttribute
import org.springframework.security.access.method.MapBasedMethodSecurityMetadataSource
import org.springframework.util.Assert
import org.springframework.util.StringUtils
import java.lang.reflect.Method

class ProtectPointcutPostProcessor(
    private val mapBasedMethodSecurityMetadataSource: MapBasedMethodSecurityMetadataSource,
) : BeanPostProcessor {

    private val pointcutMap: MutableMap<String?, List<ConfigAttribute>?> = LinkedHashMap()
    private val pointCutExpressions: MutableSet<PointcutExpression> = LinkedHashSet()
    private var parser: PointcutParser? = null
    private val processedBeans: MutableSet<String> = HashSet()
    private val log = LoggerFactory.getLogger(javaClass)

    init {
        val supportedPrimitives: MutableSet<PointcutPrimitive> = HashSet(3)
        supportedPrimitives.add(PointcutPrimitive.EXECUTION)
        supportedPrimitives.add(PointcutPrimitive.ARGS)
        supportedPrimitives.add(PointcutPrimitive.REFERENCE)
        parser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(
            supportedPrimitives)
    }

    @Throws(BeansException::class)
    override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any? {
        if (processedBeans.contains(beanName)) {
            return bean
        }
        synchronized(processedBeans) {
            if (processedBeans.contains(beanName)) {
                return bean
            }
            val methods: Array<Method>
            methods = try {
                bean.javaClass.methods
            } catch (e: Exception) {
                throw IllegalStateException(e.message)
            }
            for (method in methods) {
                for (expression in pointCutExpressions) {
                    if (attemptMatch(bean.javaClass, method, expression, beanName)) {
                        break
                    }
                }
            }
            processedBeans.add(beanName)
        }
        return bean
    }

    /**
     * 설정클래스에서 람다 형식으로 선언된 빈이 존재할 경우 에러가 발생하여 스프링 빈과 동일한 클래스를 생성하여 약간 수정함
     * 아직 AspectJ 라이브러리에서 Fix 하지 못한 것으로 판단되지만 다른 원인이 존재하는지 계속 살펴보도록 함
     */
    private fun attemptMatch(
        targetClass: Class<*>,
        method: Method,
        expression: PointcutExpression,
        beanName: String,
    ): Boolean {
        var matches: Boolean
        try {
            matches = expression.matchesMethodExecution(method).alwaysMatches()
            if (matches) {
                val attr = pointcutMap[expression.pointcutExpression]
                if (log.isDebugEnabled()) {
                    log.debug("AspectJ pointcut expression '"
                            + expression.pointcutExpression + "' matches target class '"
                            + targetClass.name + "' (bean ID '" + beanName
                            + "') for method '" + method
                            + "'; registering security configuration attribute '" + attr
                            + "'")
                }
                mapBasedMethodSecurityMetadataSource!!.addSecureMethod(targetClass, method, attr)
            }
            return matches
        } catch (e: Exception) {
            matches = false
        }
        return matches
    }

    fun setPointcutMap(map: Map<String, List<ConfigAttribute>>) {
        Assert.notEmpty(map, "configAttributes cannot be empty")
        for (expression in map.keys) {
            val value = map[expression]
            addPointcut(expression, value)
        }
    }

    private fun addPointcut(pointcutExpression: String?, definition: List<ConfigAttribute>?) {
        var pointcutExpression = pointcutExpression
        Assert.hasText(pointcutExpression, "An AspectJ pointcut expression is required")
        Assert.notNull(definition, "A List of ConfigAttributes is required")
        pointcutExpression = replaceBooleanOperators(pointcutExpression)
        pointcutMap[pointcutExpression] = definition
        pointCutExpressions.add(parser!!.parsePointcutExpression(pointcutExpression))
        if (log.isDebugEnabled()) {
            log.debug("AspectJ pointcut expression '" + pointcutExpression
                    + "' registered for security configuration attribute '" + definition
                    + "'")
        }
    }

    private fun replaceBooleanOperators(pcExpr: String?): String? {
        var pcExpr = pcExpr
        pcExpr = StringUtils.replace(pcExpr!!, " and ", " && ")
        pcExpr = StringUtils.replace(pcExpr, " or ", " || ")
        pcExpr = StringUtils.replace(pcExpr, " not ", " ! ")
        return pcExpr
    }

    companion object {
        fun postProcessAfterInitialization(bean: Any?, beanName: String?): Any? {
            return bean
        }
    }
}
