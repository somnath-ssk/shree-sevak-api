package shreesevak.api.exceptions;

import lombok.Data;

@Data
public class ResourceAllReadyExist extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String resourceName;
	
	public ResourceAllReadyExist(String resourceName) {
		super( String.format("%s all ready exist",resourceName));
		this.resourceName = resourceName;
	}
    
  
}
