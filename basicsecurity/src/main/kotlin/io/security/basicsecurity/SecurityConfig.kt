package io.security.basicsecurity

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.savedrequest.HttpSessionRequestCache
import org.springframework.security.web.savedrequest.RequestCache
import org.springframework.security.web.savedrequest.SavedRequest
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
@EnableWebSecurity
class SecurityConfig(val userDetailsService: UserDetailsService) : WebSecurityConfigurerAdapter() {

    val log: Logger = LoggerFactory.getLogger(SecurityConfig::class.java)

    override fun configure(http: HttpSecurity) {

        /**
         * Authorization. Role hierarchy not set yet
         * antMatchers should start from narrow range to wider range
         */
        http.authorizeRequests()
            .antMatchers("/login").permitAll()
//            .antMatchers("/user").hasRole("USER") // ADM or SYS can't access here!
            .antMatchers("/user").access("hasRole('USER') or hasRole('ADMIN') or hasRole('SYS')")
            .antMatchers("/admin/pay").hasRole("ADM") // SYS or USER can't access here!
            .antMatchers("/admin/**").access("hasRole('ADMIN') or hasRole('SYS')") // ADMIN or SYS can access here
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
            .successHandler { request, response, authentication ->
                run {
                    println("authentication: ${authentication.name}")
                    val requestCache: RequestCache = HttpSessionRequestCache()
                    val savedRequest: SavedRequest = requestCache.getRequest(request, response)
                    val redirectUrl = savedRequest.redirectUrl
                    response.sendRedirect(redirectUrl)
                }
            }
//            .failureHandler { _, response, exception ->
//                run {
//                    println("exception: ${exception.message}")
//                    response.sendRedirect("/login")
//                }
//            }
            .permitAll()

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

        /**
         * Limit maximum session
         */
        http.sessionManagement()
            .maximumSessions(3) // maximum session count
            .maxSessionsPreventsLogin(true) // block login when exceeds maximum sessions. default : false which expire old session
//            .expiredUrl("/expired") // page to move when the session is expired

        /**
         * Session protect
         */
        http.sessionManagement()
            .sessionFixation()
//            .none() // if none attackers can inject their session to user and when the user login the attacker passes authentication
            .changeSessionId() // Default is changeSessionId. Other options: none / migrateSession / newSession
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Always / If_Required (default) / Never : Not make but if exists use it / Stateless : Spring security do not make session and never use even if exists (JST)

        /**
         * Exception Handling
         */
        http.exceptionHandling()
//            .authenticationEntryPoint { request, response, authException ->
//                response.sendRedirect("/login")
//            }
            .accessDeniedHandler(AccessDeniedHandler { request, response, accessDeniedException ->
                response.sendRedirect("/denied")
            })

        /**
         * CSRF
         */
        http.csrf() // default enabled.
//            .disable()

    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        /**
         * Create users
         */
        auth.inMemoryAuthentication().withUser("user").password("{noop}1234").roles("USER")
        auth.inMemoryAuthentication().withUser("sys").password("{noop}1234").roles("SYS")
        auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN")
    }

}

@Configuration
@Order(1)
class SecurityConfig2 : WebSecurityConfigurerAdapter(){
    override fun configure(http: HttpSecurity) {
        http.antMatcher("/admin/**")
            .authorizeRequests()
            .anyRequest().authenticated()
            .and().httpBasic()
    }
}

@Configuration
@Order(2)
class SecurityConfig3 : WebSecurityConfigurerAdapter(){
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .anyRequest().permitAll()
            .and()
            .formLogin()
    }
}
