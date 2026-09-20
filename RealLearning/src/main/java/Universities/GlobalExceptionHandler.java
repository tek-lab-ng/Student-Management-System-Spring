package Universities;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
