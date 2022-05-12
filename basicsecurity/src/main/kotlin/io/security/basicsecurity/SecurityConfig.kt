package io.security.basicsecurity

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
@EnableWebSecurity
class SecurityConfig(val userDetailsService: UserDetailsService) : WebSecurityConfigurerAdapter() {

    val log: Logger = LoggerFactory.getLogger(SecurityConfig::class.java)

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .anyRequest().authenticated()

        /**
         * LOGIN
         */
        http.formLogin()
            .defaultSuccessUrl("/")
            .failureUrl("/login")
            .usernameParameter("userId")
            .passwordParameter("password")
            .loginProcessingUrl("/login_process")
            .successHandler { _, response, authentication ->
                run {
                    println("authentication: ${authentication.name}")
                    response.sendRedirect("/")
                }
            }.failureHandler { _, response, exception ->
                run {
                    println("exception: ${exception.message}")
                    response.sendRedirect("/login")
                }
            }.permitAll()

        /**
         * LOGOUT
         */
        http.logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login")
            .addLogoutHandler { request, _, _ ->
                run {
                    log.info("processing logout Handler")
                    val session = request.session
                    session.invalidate()
                }
            }.logoutSuccessHandler { _: HttpServletRequest, response: HttpServletResponse, _: Authentication ->
                log.info("processing logout Success Handler")
                response.sendRedirect("/login")
            }.deleteCookies("remember-me")

        /**
         * Remember Me
         */
        http.rememberMe()
            .rememberMeParameter("remember") // default: remember-me
            .tokenValiditySeconds(3600) // Default: 14 Days
            .alwaysRemember(false) // if true, it always remembers even if remember me is not active
            .userDetailsService(userDetailsService)
    }

}
