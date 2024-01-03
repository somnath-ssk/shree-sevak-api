package shreesevak.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shreesevak.api.model.JwtRequest;
import shreesevak.api.model.JwtResponse;
import shreesevak.api.model.User;
import shreesevak.api.repository.UserRepo;
import shreesevak.api.security.JwtHelper;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private JwtHelper helper;

	private Logger logger = LoggerFactory.getLogger(AuthController.class);

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody JwtRequest request) {
	    this.doAuthenticate(request.getEmail(), request.getPassword());
	    User user = this.userRepo.findByEmailId(request.getEmail());

	    if (user.getStatus().equals("1")) {
	        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
	        String token = this.helper.generateToken(userDetails);

	        User user1 = this.userRepo.findByEmailId(request.getEmail());
	        JwtResponse response = JwtResponse.builder()
	                .username(userDetails.getUsername())
	                .jwtToken(token)
	                .user(user1)
	                .build();

	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
	        // User is inactive and not authorized
	        return new ResponseEntity<>("User is inactive", HttpStatus.UNAUTHORIZED);
	    }
	}

	private void doAuthenticate(String email, String password) {

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
		try {
			manager.authenticate(authentication);

		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(" Invalid Username or Password  !!");
		}

	}

	@ExceptionHandler(BadCredentialsException.class)
	public String exceptionHandler() {
		return "Credentials Invalid !!";
	}
}
