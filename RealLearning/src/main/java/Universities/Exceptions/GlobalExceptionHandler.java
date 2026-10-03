package Universities.Exceptions;

import Universities.DTO.ExceptionResponse;
import Universities.ValidationErrorResponse;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();

        String finalErrorMessage = "Validation failed";
        Map<String, String> totalResultError = new HashMap<>();

        for(ObjectError err : errors){

            String message = err.getDefaultMessage();

            if(err instanceof FieldError fieldError) {
                String fieldName = fieldError.getField();

                totalResultError.put(fieldName, message);

            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ValidationErrorResponse(finalErrorMessage, totalResultError));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        String message = ex.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(message));

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String message = ex.getMessage();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(message));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionResponse> handMethodArgumentTypeViolation(MethodArgumentTypeMismatchException ex) {
        String message = "Invalid value '" + ex.getValue() + "' for parameter '" + ex.getName() + "'. Expected a " + ex.getRequiredType().getSimpleName();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(message));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadableException (HttpMessageNotReadableException ex){
        Throwable cause = ex.getCause();
        String message = null;
        if(cause instanceof JsonMappingException jsonMappingException){
            List<JsonMappingException.Reference> listOfErrored = jsonMappingException.getPath();
            message  =  "Invalid value for field  " + listOfErrored.get(0).getFieldName() + " . Please provide the correct data type.";
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(message));
    }

}
