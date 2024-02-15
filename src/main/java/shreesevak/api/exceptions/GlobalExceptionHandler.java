package shreesevak.api.exceptions;

import java.util.HashMap;
import java.util.Map;
import java.util.UnknownFormatConversionException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import shreesevak.api.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse>resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
	  String message=ex.getMessage();
	  ApiResponse apiResponse =new ApiResponse(message, false);
	  return new  ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(ResourceAllReadyExist.class)
	public ResponseEntity<ApiResponse>resourceAllReadyExistHandler(ResourceAllReadyExist ex){
		String message=ex.getMessage();
		ApiResponse apiResponse =new ApiResponse(message, false);
		return new  ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		
		Map<String ,String> resp=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
		String fieldName=((FieldError)error).getField();
		String message=error.getDefaultMessage();
		resp.put(fieldName, message);
		}
		);
		return (ResponseEntity<Map<String, String>>) resp;
	}
   
	 @ExceptionHandler(DuplicateKeyException.class)
	    public ResponseEntity<String> handleDuplicateKeyException(DuplicateKeyException ex) {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
	    }
	  @ExceptionHandler(IllegalArgumentException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
	        if (isIncompatibleComparisonException(ex)) {
	        	ApiResponse errorResponse = new ApiResponse( "Invalid comparison: Unable to compare status (expected numeric) with a non-numeric value. "+ex.getMessage(),false);
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	        } else {
	            // Handle other IllegalArgumentExceptions or provide a generic error message
	        	ApiResponse errorResponse = new ApiResponse("Invalid argument: "+ex.getMessage(),false);
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	        }
	    }

	    private boolean isIncompatibleComparisonException(IllegalArgumentException ex) {
	        // Check if the exception message or stack trace contains specific information
	        return ex.getMessage() != null && ex.getMessage().contains("Can't compare test expression");
	    }
		

}