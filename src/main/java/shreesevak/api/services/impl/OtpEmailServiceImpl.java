package shreesevak.api.services.impl;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import shreesevak.api.model.OtpEmail;
import shreesevak.api.repository.OtpEmailRepo;
import shreesevak.api.services.OtpEmailService;
 
@Service
public class OtpEmailServiceImpl implements OtpEmailService {
 
	@Autowired
	OtpEmailRepo otpEmailRepo;
 
	@Override
	public OtpEmail saveEmailOtp(String email, Integer otp) {
		OtpEmail otpEmail=new OtpEmail();
		otpEmail.setEmail(email);
		otpEmail.setOtp(otp);
		return this.otpEmailRepo.save(otpEmail);
	}

 
}