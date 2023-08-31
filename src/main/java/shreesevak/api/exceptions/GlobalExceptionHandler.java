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
import org.springframework.web.bind.annotation.RestControllerAdvice;

import shreesevak.api.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse>resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
	  String message=ex.getMessage();
	  ApiResponse apiResponse =new ApiResponse(message, false);
	  return new  ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
		
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
   
	
		

}