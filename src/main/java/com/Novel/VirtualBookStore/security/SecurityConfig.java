package com.Novel.VirtualBookStore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration         //to tell spring that this is configuration class
@EnableWebSecurity     //to use security feature of spring security
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {  //extends WebSecurityConfigurerAdapter @deprecated
        
    	http.authorizeHttpRequests(auth->auth
    			.requestMatchers(
    	                "/api/cart_items", 
    	                "/h2-console/**", 
    	                "/register",       
    	                "/public/**"// Any sub-paths
    	            ).permitAll()
                .anyRequest().authenticated())
//    	   .formLogin(form -> form
//                .loginPage("/login")  //If you go to this route it will give login page[custom login page]
//                .permitAll()
//            )
//    	   .formLogin(Customizer.withDefaults())  //for default login page
    	    .httpBasic(Customizer.withDefaults())  //implement basic auth [auth in which we have send credentials in header].
            .logout(logout -> logout
                .permitAll()
            )
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**","/public/**")
            )
            .headers(headers -> headers.disable()
            ).userDetailsService(customUserDetailsService);
            
    	
    	return http.build();
    }
}