package shreesevak.api.controller;
 
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import shreesevak.api.model.OtpEmail;
import shreesevak.api.model.User;
import shreesevak.api.payloads.ApiResponse;
import shreesevak.api.payloads.UserDto;
import shreesevak.api.payloads.UserFrontEndData;
import shreesevak.api.repository.OtpEmailRepo;
import shreesevak.api.repository.RoleRepo;
import shreesevak.api.repository.UserRepo;
import shreesevak.api.services.OtpEmailService;
import shreesevak.api.services.RoleService;
import shreesevak.api.services.UserService;
import shreesevak.api.services.impl.EmailSender;

 
@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class UserController {
	public String globalEmail;
	public int globalOtp;
	Random random =new Random(10000);
	@Autowired
	private OtpEmailService otpEmailService;
	@Autowired
	private EmailSender emailService;
	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	@Autowired
	private OtpEmailRepo otpEmailRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RoleService roleService;
	 //POST -create User
	@Autowired
	private RoleRepo roleRepo;
	@PostMapping("/signup") 
	public ResponseEntity<UserDto> createUser(@RequestBody UserFrontEndData frontEndData ){
		UserDto createUserDto=this.userService.createUser(frontEndData);
		return  new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}
	// Assigning role to all ready present user  
	@PutMapping("/{userId}/assignroles")
	public ResponseEntity<UserDto> assignUserRole(@PathVariable Integer userId,@RequestBody List<Integer> roleId){
		UserDto createdUserDto=this.userService.assignUserRole(userId,roleId);
		return new ResponseEntity<UserDto>(createdUserDto,HttpStatus.CREATED);
	}
	//PUT -update user 
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserFrontEndData userFrontEndData ,@PathVariable Integer userId){
	
		UserDto updateduserDto =userService.updateUser(userFrontEndData, userId);
		return ResponseEntity.ok(updateduserDto);
	}
	//DELETE  delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
		  System.out.println("deletUser method controler");
          userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted succesfully ",true),HttpStatus.OK) ;
	}

	//GET -user get
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
		UserDto respuserDto=userService.getUserById(userId);
		return ResponseEntity.ok(respuserDto);

	}
	@GetMapping("/username/{username}")
	public ResponseEntity<UserDto> getUserByUserName(@PathVariable String username){
		UserDto respuserDto=userService.getUserByUserName(username);
		return ResponseEntity.ok(respuserDto);

	}
	@GetMapping("/user_list")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	@GetMapping("/status/{status}")
	public ResponseEntity<List<UserDto>> getUserListByStatus(@PathVariable String status){
		return ResponseEntity.ok(this.userService.getAllActiveUsers(status));
	}

	//for forgot password

	@RequestMapping("/forgot")
	public String opemEmailForm() {
		return "forgot_email_form";
	}

 
	@PostMapping("/forgot")
	public String sendOTP(@RequestParam("email") String email,HttpSession session) {
	    globalEmail = email;
	    System.out.println(globalEmail);
	    System.out.println("Email " + email);
	User user  =  this.userRepo.findByEmailId(email);
	if(user ==null) {
//		throw new ResourceNotFoundException("Email is not prsent in Databse", "not found", email);
		return "mailNotExist";
	}
 
	    int otp = random.nextInt(99999);
	    System.out.println("OTP " + otp);


	    String subject = "Password reset otp: ";
	    String message = "OTP " + otp;
	    String to = email;

	  boolean flag =this.emailService.sendOtpEmail(subject, message, to);
	  System.out.println("flag "+flag);
System.out.println(subject);
System.out.println(message);
System.out.println(to);
session.setAttribute("myotp", otp);
System.out.println("myotps "+otp);
 
 
	  if(flag)
	  {
		 OtpEmail otpEmail =new OtpEmail();
		 otpEmail.setEmail(email);		    
		 otpEmail.setOtp(otp);
		 OtpEmail otpEmail2 =this.otpEmailRepo.save(otpEmail);
		  System.out.println("email from db "+otpEmail2.getEmail());
 
//		  OtpEmail otpEmail2 = this.otpEmailService.saveEmailOtp(email,otp);
//		  System.out.println("get session "+session.getAttribute("email"));
//		  System.out.println("session otp "+session.getAttribute("myotp"));
		  return "verify_otp";
	  }else
	  {
		  session.setAttribute("message","check your email id");
		  return "forgot_email_form";
	  }

//	    try {
//	    	this.emailService.sendOtpEmail(subject, message, to);
//	        // Email sent successfully
//	        Map<String, String> response = new HashMap<>();
//	        response.put("message", "OTP sent successfully");
//	        return ResponseEntity.ok(response);
//	    } catch (MessagingException e) {
//	        e.printStackTrace();
//	        // Handle the exception, e.g., log or return an error response
//	        Map<String, String> response = new HashMap<>();
//	        response.put("message", "Failed to send OTP");
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//	    }
	}
 
	// verify otp
 
	  @PostMapping("/verify-otp")
	  public String verifyOTP(@RequestParam("otp")int otp, HttpSession session)
	  {
		  System.out.println("otp from frontend "+otp);	
		  OtpEmail otpEmail= this.otpEmailRepo.findByOtp(otp);
		  Integer myotp = otpEmail.getOtp();	
		  System.out.println("email fron db "+ otpEmail.getOtp());
		  String email = otpEmail.getEmail();
		  System.out.println(email);
		  if(myotp == otp)
		  {
			  // password change form
			 User user= this.userRepo.findByEmailId(email.toLowerCase());
			  System.out.println(user);
			 if(user==null) 
			 {
			 session.setAttribute("message","user does not exit");
			 System.out.println("member not present");
			 }else
			 {
				 System.out.println("member is present");
				 session.setAttribute("message","user alredy exit");
			 }
			 System.out.println("both otp are same");
			  return "password_change_form";
		  }else
		  {
			  System.out.println("both otp are diffeent");
			  session.setAttribute("message","You have enter wrong otp");
			  return "verify_otp";
		  }
	  }

 
		// Password change
 
		  @PostMapping("/change-password")
		  public String changePassword(@RequestParam("confirmPassword")String confirmPassword, HttpSession session)
		  {
			  System.out.println("confirm password "+confirmPassword);		 
			  System.out.println("globalEmail"+globalEmail);
 
			  User user =this.userRepo.findByEmailId(globalEmail);
			  user.setPassword(this.bcrypt.encode(confirmPassword));
			  this.userRepo.save(user);
			  otpEmailRepo.deleteAll();
			  return "password Successfully changed";
		  }
//	@PutMapping("/{userId}/location/{locId}")
//	public ResponseEntity<User> assignLocationToUser(@PathVariable Integer locId,@PathVariable Integer userId){
//		
//		User userLoc=userService.assignLocationToUser(locId, userId);
//		
//		
//		return ResponseEntity.ok(userLoc);
//		
//		
//	}
 
	
}