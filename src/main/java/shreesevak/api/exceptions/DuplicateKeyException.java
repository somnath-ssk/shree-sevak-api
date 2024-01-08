package shreesevak.api.exceptions;

import lombok.Data;

@Data
public class DuplicateKeyException extends RuntimeException{
	  private int dublicateId;
	  public DuplicateKeyException(String message) {
	        super(message);
	    }

}
