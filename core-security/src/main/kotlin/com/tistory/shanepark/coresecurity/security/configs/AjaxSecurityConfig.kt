package com.tistory.shanepark.coresecurity.security.configs

import com.tistory.shanepark.coresecurity.security.common.AjaxLoginAuthenticationEntryPoint
import com.tistory.shanepark.coresecurity.security.handler.AjaxAccessDeniedHandler
import com.tistory.shanepark.coresecurity.security.handler.AjaxAuthenticationFailureHandler
import com.tistory.shanepark.coresecurity.security.handler.AjaxAuthenticationSuccessHandler
import com.tistory.shanepark.coresecurity.security.provider.AjaxAuthenticationProvider
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

@Configuration
@EnableWebSecurity
@Order(0)
class AjaxSecurityConfig(
    private val userDetailService: UserDetailsService,
    private val passwordEncoder: PasswordEncoder,
) : WebSecurityConfigurerAdapter() {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.authenticationProvider(ajaxAuthenticationProvider())
    }

    @Bean
    fun ajaxAuthenticationProvider(): AuthenticationProvider? {
        return AjaxAuthenticationProvider(userDetailService, passwordEncoder)
    }

    override fun configure(http: HttpSecurity) {
        log.info("AjaxSecurityConfig.kt configure")

        http
            .antMatcher("/api/**")
            .authorizeRequests()
            .antMatchers("/api/messages").hasRole("MANAGER")
            .antMatchers("/api/login").permitAll()
            .anyRequest().authenticated()

            .and()
            .exceptionHandling()
            .authenticationEntryPoint(AjaxLoginAuthenticationEntryPoint())
            .accessDeniedHandler(ajaxAccessDeniedHandler())

            .and()
//            .addFilterBefore(ajaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter::class.java)
//            .csrf().disable()

        customConfigurerAjax(http);

    }

    private fun customConfigurerAjax(http: HttpSecurity) {
        http
            .apply(AjaxLoginConfigurer(ajaxAuthenticationSuccessHandler(),
                ajaxAuthenticationFailureHandler(),
                authenticationManagerBean()))
            .loginProcessingUrl("/api/login")
    }

    private fun ajaxAccessDeniedHandler(): AccessDeniedHandler {
        return AjaxAccessDeniedHandler()
    }

//    @Bean
//    fun ajaxLoginProcessingFilter(): AjaxLoginProcessingFilter {
//        val ajaxLoginProcessingFilter = AjaxLoginProcessingFilter()
//        ajaxLoginProcessingFilter.setAuthenticationManager(authenticationManagerBean())
//        ajaxLoginProcessingFilter.setAuthenticationSuccessHandler(ajaxAuthenticationSuccessHandler())
//        ajaxLoginProcessingFilter.setAuthenticationFailureHandler(ajaxAuthenticationFailureHandler())
//        return ajaxLoginProcessingFilter
//    }

    @Bean
    fun ajaxAuthenticationSuccessHandler(): AuthenticationSuccessHandler {
        return AjaxAuthenticationSuccessHandler()
    }

    @Bean
    fun ajaxAuthenticationFailureHandler(): AuthenticationFailureHandler {
        return AjaxAuthenticationFailureHandler()
    }
}
