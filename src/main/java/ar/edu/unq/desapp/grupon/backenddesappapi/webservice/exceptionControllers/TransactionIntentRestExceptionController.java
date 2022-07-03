package ar.edu.unq.desapp.grupon.backenddesappapi.webservice.exceptionControllers;

import ar.edu.unq.desapp.grupon.backenddesappapi.exception.TransactionIntentDoesNotExistException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class TransactionIntentRestExceptionController extends RestExceptionController {

    @ExceptionHandler(TransactionIntentDoesNotExistException.class)
    public ResponseEntity<Map<String, Object>> handleTransactionInentDoesNotExistExceptions(TransactionIntentDoesNotExistException e) {
        Map<String, Object> response = new HashMap<>();

        response.put("error", e.getMessage());
        response.put("message", "User " + e.getTransactionId() + " does not exist in the db");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}