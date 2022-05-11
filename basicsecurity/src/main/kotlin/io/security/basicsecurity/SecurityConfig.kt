package io.security.basicsecurity

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .anyRequest().authenticated()
        http
            .formLogin()
//            .loginPage("/loginPage")
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

    }
}
