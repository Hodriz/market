package com.market.borghi.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun userDetailsService(): UserDetailsService{
        val user = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("123")
            .roles("ADMIN")
            .build()
        return InMemoryUserDetailsManager(user)
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { requests ->
                requests
                    .requestMatchers("/graphql").permitAll() // Libera o GraphiQL (para desenvolvimento)
                    .anyRequest().authenticated() // Todas as outras requisições exigem autenticação
            }
            .formLogin { form -> form.disable() } // Desabilita login por formulário HTML
            .httpBasic { } // Habilita autenticação básica (login/senha via headers HTTP)
            .csrf { csrf -> csrf.disable() } // Desabilita CSRF para APIs (cuidado em produção!)
        return http.build()
    }
}