package ar.edu.unq.desapp.grupon.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupon.backenddesappapi.exception.UserDoesNotExistException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class UserRestExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String, Object> response = new HashMap<>();

        response.put("error", "MethodArgumentNotValidException");
        response.put("message", e.getFieldError().getDefaultMessage());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Map<String, Object>> handleDataAccessExceptions(DataAccessException e) {
        Map<String, Object> response = new HashMap<>();

        response.put("message", "Error querying db");
        response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<Map<String, Object>> handleUserDoesNotExistExceptions(UserDoesNotExistException e) {
        Map<String, Object> response = new HashMap<>();

        response.put("error", e.getMessage());
        response.put("message", "User " + e.getUserId() + " does not exist in the db");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}