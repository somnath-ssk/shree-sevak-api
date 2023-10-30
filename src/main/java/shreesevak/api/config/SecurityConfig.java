package shreesevak.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import shreesevak.api.security.JwtAuthenticationEntryPoint;
import shreesevak.api.security.JwtAuthenticationFilter;
import shreesevak.api.services.UserService;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
   
	
	@Autowired
    private JwtAuthenticationEntryPoint point;
	
    @Autowired
    private JwtAuthenticationFilter filter;
    
    @Autowired 
    private UserDetailsService userDetailsService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	
    	// Configuration
    	//STATELESS - not storing anything on a servor
//    	"/api/role/**","/api/user/**","/api/member/**","/api/baithak/**","/api/location/**","/api/schedular/**",
    	http.csrf(csrf->csrf.disable()).cors(corp->corp.disable()).authorizeHttpRequests(auth->auth
    			.requestMatchers("/auth/login","/api/user/**")
    			.permitAll().requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
    			.anyRequest().authenticated()
    			).exceptionHandling(ex->ex.authenticationEntryPoint(point)).sessionManagement(session->
    			session.sessionCreationPolicy(SessionCreationPolicy.STATELESS ));
    	
    	http.addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);
    	
    	return http.build();
	
    }
    
    @Bean
    public DaoAuthenticationProvider doDaoAuthenticationProvider() {
    	DaoAuthenticationProvider provider =new DaoAuthenticationProvider();
    	provider.setUserDetailsService(userDetailsService);
    	provider.setPasswordEncoder(passwordEncoder);
    	return provider;
    	
    	
    	
    }
}
