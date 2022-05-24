package com.tistory.shanepark.coresecurity.security.configs

import com.tistory.shanepark.coresecurity.security.common.FormAuthenticationDetailsSource
import com.tistory.shanepark.coresecurity.security.provider.CustomAuthenticationProvider
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val userDetailService: UserDetailsService,
    private val authenticationDetailsSource: FormAuthenticationDetailsSource,
) : WebSecurityConfigurerAdapter() {

    override fun configure(web: WebSecurity) {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations())
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
//        auth.userDetailsService(userDetailService)
        auth.authenticationProvider(authenticationProvider())

        val password = passwordEncoder().encode("1234")
        auth.inMemoryAuthentication().withUser("user").password(password).roles("USER")
        auth.inMemoryAuthentication().withUser("manager").password(password).roles("MANAGER")
        auth.inMemoryAuthentication().withUser("admin").password(password).roles("ADMIN")
    }

    private fun authenticationProvider(): AuthenticationProvider? {
        return CustomAuthenticationProvider(userDetailService, passwordEncoder())
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
            .antMatchers("/", "/users").permitAll()
            .antMatchers("/mypage").hasRole("USER")
            .antMatchers("/messages").hasRole("MANAGER")
            .antMatchers("/config").hasRole("ADMIN")
            .anyRequest().authenticated().and()

            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login_proc")
            .authenticationDetailsSource(authenticationDetailsSource)
            .defaultSuccessUrl("/")
            .permitAll()
    }
}
