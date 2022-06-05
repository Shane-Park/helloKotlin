package com.tistory.shanepark.coresecurity.security.configs

import com.tistory.shanepark.coresecurity.security.common.FormAuthenticationDetailsSource
import com.tistory.shanepark.coresecurity.security.factory.UrlResourcesMapFactoryBean
import com.tistory.shanepark.coresecurity.security.handler.CustomAccessDeniedHandler
import com.tistory.shanepark.coresecurity.security.handler.CustomAuthenticationFailureHandler
import com.tistory.shanepark.coresecurity.security.handler.CustomAuthenticationSuccessHandler
import com.tistory.shanepark.coresecurity.security.metadatasource.UrlFilterInvocationSecurityMetadataSource
import com.tistory.shanepark.coresecurity.security.provider.FormAuthenticationProvider
import com.tistory.shanepark.coresecurity.service.SecurityResourceService
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.access.AccessDecisionManager
import org.springframework.security.access.vote.AffirmativeBased
import org.springframework.security.access.vote.RoleVoter
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor

@Configuration
@EnableWebSecurity
@Order(1)
class SecurityConfig(
    private val userDetailService: UserDetailsService,
    private val formWebAuthenticationDetailsSource: FormAuthenticationDetailsSource,
    private val formAuthenticationSuccessHandler: CustomAuthenticationSuccessHandler,
    private val formAuthenticationFailureHandler: CustomAuthenticationFailureHandler,
    private val securityResourceService: SecurityResourceService,
) : WebSecurityConfigurerAdapter() {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun configure(web: WebSecurity) {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations())
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
//        auth.userDetailsService(userDetailService)
        auth.authenticationProvider(authenticationProvider())

        val password = passwordEncoder().encode("1234")
//        auth.inMemoryAuthentication().withUser("user").password(password).roles("USER")
//        auth.inMemoryAuthentication().withUser("manager").password(password).roles("MANAGER")
//        auth.inMemoryAuthentication().withUser("admin").password(password).roles("ADMIN")
    }

    private fun authenticationProvider(): AuthenticationProvider? {
        return FormAuthenticationProvider(userDetailService, passwordEncoder())
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

    override fun configure(http: HttpSecurity) {
        log.info("SecurityConfig.kt configure")
        http
            .authorizeRequests()
            .antMatchers("/", "/user/login/**", "/login*").permitAll()
            .antMatchers("/mypage").hasRole("USER")
            .antMatchers("/messages").hasRole("MANAGER")
            .antMatchers("/config").hasRole("ADMIN")
            .antMatchers("/**").permitAll()
            .anyRequest().authenticated()

            .and()
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login_proc")
            .authenticationDetailsSource(formWebAuthenticationDetailsSource)
            .defaultSuccessUrl("/")
            .successHandler(formAuthenticationSuccessHandler)
            .failureHandler(formAuthenticationFailureHandler)
            .permitAll()

            .and()
            .exceptionHandling()
            .accessDeniedHandler(accessDeniedHandler())

            .and()
            .addFilterBefore(customFilterSecurityInterceptor(), FilterSecurityInterceptor::class.java)

    }

    @Bean
    fun accessDeniedHandler(): AccessDeniedHandler? {
        return CustomAccessDeniedHandler("/denied")
    }

    @Bean
    fun urlFilterInvocationSecurityMetadataSource(): FilterInvocationSecurityMetadataSource? {
        return UrlFilterInvocationSecurityMetadataSource(
            urlResourcesMapFactoryBean().getObject(),
            securityResourceService
        )
    }

    private fun urlResourcesMapFactoryBean(): UrlResourcesMapFactoryBean {
        return UrlResourcesMapFactoryBean(securityResourceService)
    }


    private fun affirmativeBased(): AccessDecisionManager? {
        return AffirmativeBased(getAccessDecisionVoters())
    }

    private fun getAccessDecisionVoters(): List<RoleVoter> {
        return listOf(RoleVoter())
    }

    @Bean
    fun customFilterSecurityInterceptor(): FilterSecurityInterceptor {
        val filterSecurityInterceptor = FilterSecurityInterceptor()
        filterSecurityInterceptor.securityMetadataSource = urlFilterInvocationSecurityMetadataSource();
        filterSecurityInterceptor.accessDecisionManager = affirmativeBased();
        filterSecurityInterceptor.authenticationManager = authenticationManagerBean()
        return filterSecurityInterceptor;
    }


}
