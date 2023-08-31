package shreesevak.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import shreesevak.api.security.JwtAuthenticationEntryPoint;
import shreesevak.api.security.JwtAuthenticationFilter;

public class SecurityConfig {
   
	
	@Autowired
    private JwtAuthenticationEntryPoint point;
	
    @Autowired
    private JwtAuthenticationFilter filter;
    
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	
    	// Configuration
    	
    	http.csrf(csrf->csrf.disable()).cors(corp->corp.disable()).authorizeHttpRequests(auth->auth.requestMatchers("/api/**").authenticated().requestMatchers("/auth/login")
    			.permitAll()
    			.anyRequest().authenticated()).exceptionHandling(ex->ex.authenticationEntryPoint(point)).sessionManagement(session->
    			session.sessionCreationPolicy(SessionCreationPolicy.STATELESS ));
    	
    	http.addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);
    	
    	return http.build();
	
    }
}
