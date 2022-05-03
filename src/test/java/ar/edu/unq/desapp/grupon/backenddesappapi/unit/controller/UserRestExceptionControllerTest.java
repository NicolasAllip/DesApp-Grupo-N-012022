package ar.edu.unq.desapp.grupon.backenddesappapi.unit.controller;

import ar.edu.unq.desapp.grupon.backenddesappapi.exception.UserDoesNotExistException;
import ar.edu.unq.desapp.grupon.backenddesappapi.webservice.UserRestExceptionController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class UserRestExceptionControllerTest {

    private UserRestExceptionController userRestExceptionController = new UserRestExceptionController();

    @Test
    public void handleDataAccessException() {
        UserDoesNotExistException exception = new UserDoesNotExistException(1L);

        Map<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("error", exception.getMessage());
        expectedResponse.put("message", "User " + exception.getUserId() + " does not exist in the db");

        ResponseEntity<Map<String, Object>> responseEntity = userRestExceptionController.handleUserDoesNotExistExceptions(exception);

        Assertions.assertEquals(expectedResponse, responseEntity.getBody());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
