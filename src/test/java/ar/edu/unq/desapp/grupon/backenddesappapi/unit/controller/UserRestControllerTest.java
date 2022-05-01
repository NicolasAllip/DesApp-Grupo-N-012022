package ar.edu.unq.desapp.grupon.backenddesappapi.unit.controller;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.User;
import ar.edu.unq.desapp.grupon.backenddesappapi.service.IUserService;
import ar.edu.unq.desapp.grupon.backenddesappapi.webservice.UserRestController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRestControllerTest {

    @Mock
    private IUserService userServiceMock;

    @InjectMocks
    private UserRestController userRestController;

    @Test
    public void getAllUsers_returnUsers() {
        // arrange
        User user1 = User.builder()
                .build();
        User user2 = User.builder()
                .build();

        List<User> expectedUserList = new ArrayList<>();
        expectedUserList.add(user1);
        expectedUserList.add(user2);

        when(userServiceMock.findAll()).thenReturn(expectedUserList);

        // act
        List<User> actualUserList = userRestController.index();

        // assert
        verify(userServiceMock, atLeastOnce()).findAll();

        Assertions.assertEquals(2, actualUserList.size());
        Assertions.assertTrue(actualUserList.contains(user1));
        Assertions.assertTrue(actualUserList.contains(user2));
    }

    @Test
    public void getUserById_gotUser() {
        // arrange
        Long expectedId = 1L;
        User expectedUser = User.builder()
                .id(expectedId)
                .build();

        // act
        when(userServiceMock.findById(1L)).thenReturn(expectedUser);

        // assert
        ResponseEntity<?> response = userRestController.showUser(1L);

        verify(userServiceMock, atLeastOnce()).findById(expectedId);

        Assertions.assertEquals(expectedUser, response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getUserById_throwsErrors() {
        // arrange
        Long expectedId = 1L;

        // act
        //when(userServiceMock.findById(1L)).thenThrow(DataAccessException.class);

        // assert
        ResponseEntity<?> response = userRestController.showUser(1L);

        verify(userServiceMock, atLeastOnce()).findById(expectedId);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
