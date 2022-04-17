package MainPackage.GlobalExceptionHandler;

import MainPackage.GlobalExceptionHandler.CustomExceptions.CustomInvalidInputException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomInvalidInputException.class)
    public ResponseEntity<String> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        if (ex instanceof CustomInvalidInputException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            CustomInvalidInputException exception = (CustomInvalidInputException) ex;

            return handleCustomInvalidException(exception, headers, status, request);
        } else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, null, headers, status, request);
        }
    }

    private ResponseEntity<String> handleCustomInvalidException(CustomInvalidInputException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errors = ex.getMessage();
        return handleExceptionInternal(ex, errors, headers, status, request);
    }

    private ResponseEntity<String> handleExceptionInternal(Exception ex, String body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }
}
