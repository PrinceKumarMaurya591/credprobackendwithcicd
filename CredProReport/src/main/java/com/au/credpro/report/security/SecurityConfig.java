//package com.au.credpro.report.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig  {
//
//	 @Autowired
//	    private JwtAuthenticationEntryPoint point;
//	    @Autowired
//	    private JwtRequestFilter filter;
//
//	    @Bean
//	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//	        http.csrf(csrf -> csrf.disable())
//	                .cors(cors->cors.disable())
//	                .authorizeRequests(auth->auth
//	                .antMatchers("/health")
//	                .authenticated()
//	                .antMatchers("/health","/credpro/checkuseroradmin/*")
//	                .permitAll()	                
//	                .anyRequest()
//	                .authenticated())
//	                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
//	                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//	        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
//	        return http.build();
//	        
//	     
//	    }
//}