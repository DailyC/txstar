package com.dailyc.txstar.developer.platform.ops

import de.codecentric.boot.admin.server.config.AdminServerProperties
import de.codecentric.boot.admin.server.config.EnableAdminServer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler

@SpringBootApplication
@EnableAdminServer
@EnableWebSecurity
class DeveloperPlatformOpsApplication

fun main(args: Array<String>) {
    runApplication<DeveloperPlatformOpsApplication>(*args)
}

@Configuration
class SecuritySecureConfig(adminServerProperties: AdminServerProperties): WebSecurityConfigurerAdapter() {
    var  adminContextPath:String = adminServerProperties.contextPath

    @Throws
    override fun configure(http: HttpSecurity){
            val successHandler = SavedRequestAwareAuthenticationSuccessHandler()
            successHandler.setTargetUrlParameter("redirectTo")
            http.authorizeRequests()
                    .antMatchers("$adminContextPath/assets/**").permitAll()
                    .antMatchers("$adminContextPath/login").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin().loginPage("$adminContextPath/login").successHandler(successHandler).and()
                    .logout().logoutUrl("$adminContextPath/logout").and()
                    .httpBasic().and()
                    .csrf().disable()
    }
}

