package com.cfmaddressbook.demo.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	 
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailServiceImpl();
    }
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
        	.antMatchers("/api/register/**").permitAll()
	        .antMatchers("/").hasAnyAuthority("USER", "ADMIN")
	        .antMatchers("/api/**").hasAnyAuthority("ADMIN", "USER")
	        .antMatchers("/manager/api/**").hasAuthority("ADMIN")
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/api/login").permitAll()
            .defaultSuccessUrl("/")
            .and()
            .logout()
            .logoutSuccessUrl("/api/login")
            .permitAll()
            ;
    }
}