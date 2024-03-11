package shreesevak.api.payloads;

import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import shreesevak.api.model.Baithak2;
import shreesevak.api.model.Member;

public class Schedule2Dto {

	private int scheduleId;
	private String date;
    private Baithak2Dto baithak;
    private MemberDto member;
}
