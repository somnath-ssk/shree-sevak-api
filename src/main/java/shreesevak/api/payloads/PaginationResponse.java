package shreesevak.api.payloads;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import shreesevak.api.model.Member;

@NoArgsConstructor
@Data
public class PaginationResponse {
	
		private List<?> content;
		private int  pageNumber;
		private int  pageSize;
		private long  totoalElement;
		private int  totalPages;
		private boolean lastPage;
		
		
		

}
