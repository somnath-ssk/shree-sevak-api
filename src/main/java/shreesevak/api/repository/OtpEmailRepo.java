package shreesevak.api.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
 
import shreesevak.api.model.OtpEmail;
 
import java.time.LocalDateTime;
import java.util.List;
 
 
public interface OtpEmailRepo extends JpaRepository<OtpEmail, Integer> {
	OtpEmail findByOtp(int otp);
	OtpEmail findByEmail(String email);
    void deleteAll();
}