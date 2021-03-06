package ar.edu.unq.desapp.grupon.backenddesappapi.webservice.exceptionControllers;

import ar.edu.unq.desapp.grupon.backenddesappapi.exception.UserDoesNotExistException;
import ar.edu.unq.desapp.grupon.backenddesappapi.webservice.exceptionControllers.RestExceptionController;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class UserRestExceptionController extends RestExceptionController {

    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<Map<String, Object>> handleUserDoesNotExistExceptions(UserDoesNotExistException e) {
        Map<String, Object> response = new HashMap<>();

        response.put("error", e.getMessage());
        response.put("message", "User " + e.getUserId() + " does not exist in the db");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}