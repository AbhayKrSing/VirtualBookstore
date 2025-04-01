package com.Novel.VirtualBookStore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
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
    					"/api/user/login",  //so that everyone can accessed
    					"/api/user/logout",
    	                "/api/cart_items", 
    	                "/h2-console/**",      
    	                "/public/**"// Any sub-paths
    	            ).permitAll()
    			.requestMatchers(HttpMethod.GET, "/api/user","/api/user/email/**","/api/user/paginated","/api/user/count").hasRole("ADMIN")
    			.requestMatchers(HttpMethod.DELETE,"/api/user/**").hasRole("ADMIN")
                .anyRequest().authenticated())
//    	   .formLogin(form -> form
//                .loginPage("/login")  //If you go to this route it will give login page[custom login page]
//                .permitAll()
//            )
//    	   .formLogin(Customizer.withDefaults())  //for default login page
//    	    .httpBasic(Customizer.withDefaults())  //implement basic auth [auth in which we have send credentials in header].
    	    .formLogin(form->form.disable())   //disable form login
    	    .httpBasic(Customizer.withDefaults()) // Keep basic auth as fallback
    	    .logout(logout -> logout
                    .logoutUrl("/api/user/logout")   //when this api will hit spring automatically handle this.[post request hai]
                    .logoutSuccessHandler((request, response, authentication) -> {
                        response.setStatus(HttpStatus.OK.value());
                        response.getWriter().write("Successfully logout");
                        response.getWriter().flush();
                    })
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll()
                )
                .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Sessions created when needed
                    .maximumSessions(1)
                    .maxSessionsPreventsLogin(false)
                )
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**","/public/**","/api/user","/api/user/login","/api/user/logout")
            )
            .headers(headers -> headers.disable()
            ).userDetailsService(customUserDetailsService);
            
    	
    	return http.build();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}