package com.tistory.shanepark.coresecurity.security.configs

import com.tistory.shanepark.coresecurity.security.filter.AjaxLoginProcessingFilter
import com.tistory.shanepark.coresecurity.security.handler.AjaxAuthenticationFailureHandler
import com.tistory.shanepark.coresecurity.security.handler.AjaxAuthenticationSuccessHandler
import com.tistory.shanepark.coresecurity.security.provider.AjaxAuthenticationProvider
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
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@Order(0)
class AjaxSecurityConfig(
    private val userDetailService: UserDetailsService,
    private val passwordEncoder: PasswordEncoder,
) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.authenticationProvider(ajaxAuthenticationProvider())
    }

    @Bean
    fun ajaxAuthenticationProvider(): AuthenticationProvider? {
        return AjaxAuthenticationProvider(userDetailService, passwordEncoder)
    }

    override fun configure(http: HttpSecurity) {
        http
            .antMatcher("/api/**")
            .authorizeRequests()
            .anyRequest().authenticated().and()

            .addFilterBefore(ajaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter::class.java)
            .csrf().disable()
    }

    @Bean
    fun ajaxLoginProcessingFilter(): AjaxLoginProcessingFilter {
        val ajaxLoginProcessingFilter = AjaxLoginProcessingFilter()
        ajaxLoginProcessingFilter.setAuthenticationManager(authenticationManagerBean())
        ajaxLoginProcessingFilter.setAuthenticationSuccessHandler(ajaxAuthenticationSuccessHandler())
        ajaxLoginProcessingFilter.setAuthenticationFailureHandler(ajaxAuthenticationFailureHandler())
        return ajaxLoginProcessingFilter
    }

    @Bean
    fun ajaxAuthenticationSuccessHandler(): AuthenticationSuccessHandler {
        return AjaxAuthenticationSuccessHandler()
    }

    @Bean
    fun ajaxAuthenticationFailureHandler(): AuthenticationFailureHandler {
        return AjaxAuthenticationFailureHandler()
    }
}
