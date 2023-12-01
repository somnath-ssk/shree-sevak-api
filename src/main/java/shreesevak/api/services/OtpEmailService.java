package shreesevak.api.services;
 
import shreesevak.api.model.OtpEmail;
 
public interface OtpEmailService {
 
	OtpEmail saveEmailOtp(String email,Integer otp);
}