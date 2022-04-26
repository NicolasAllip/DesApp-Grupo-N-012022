package ar.edu.unq.desapp.grupon.backenddesappapi.unit.service;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.NewUserDTO;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.User;
import ar.edu.unq.desapp.grupon.backenddesappapi.persistence.IUserDao;
import ar.edu.unq.desapp.grupon.backenddesappapi.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private IUserDao iUserDaoMock;

    @InjectMocks
    private UserService userService;

    @Test
    public void getAllUsers_returnsUserList() {
        // arrange
        User user1 = User.builder()
                .id(1L)
                .build();
        User user2 = User.builder()
                .id(2L)
                .build();

        List<User> expectedUserList = new ArrayList<>();
        expectedUserList.add(user1);
        expectedUserList.add(user2);

        when(iUserDaoMock.findAll()).thenReturn(expectedUserList);

        // act
        List<User> actualUserList = userService.findAll();

        // assert
        verify(iUserDaoMock, atLeastOnce()).findAll();

        Assertions.assertEquals(2, actualUserList.size());
        Assertions.assertTrue(actualUserList.contains(user1));
        Assertions.assertTrue(actualUserList.contains(user2));
    }

    @Test
    public void getUserById_returnsUser() {
        // arrange
        User expectedUser = User.builder()
                .id(1L)
                .build();

        when(iUserDaoMock.findById(1L)).thenReturn(java.util.Optional.ofNullable(expectedUser));

        // act
        User actualUser = userService.findById(1L);

        // assert
        verify(iUserDaoMock, atLeastOnce()).findById(1L);

        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    public void getUserByIdWhenUserDoesntExist_returnsNull() {
        // arrange
        //when(iUserDaoMock.findById(1L)).thenReturn(null);

        // act
        User actualUser = userService.findById(1L);

        // assert
        verify(iUserDaoMock, atLeastOnce()).findById(1L);

        Assertions.assertNull(actualUser);
    }

    @Test
    public void saveUser_transformsDtoIntoModelObject() {
        // arrange
        NewUserDTO newUserDTO = NewUserDTO.builder()
                .name("TestName")
                .surname("TestSurname")
                .email("testemail@gmail.com")
                .address("TestAddress 123")
                .password("TestPassw0rd!")
                .cvu("0123456789012345678901")
                .walletAddress("01234567")
                .build();

        User expectedUser = User.builder()
                .name(newUserDTO.getName())
                .surname(newUserDTO.getSurname())
                .email(newUserDTO.getEmail())
                .address(newUserDTO.getAddress())
                .password(newUserDTO.getPassword())
                .cvu(newUserDTO.getCvu())
                .reputation(0.0f)
                .walletAddress(newUserDTO.getWalletAddress())
                .build();

        // act
        userService.save(newUserDTO);

        // assert
        verify(iUserDaoMock, atLeastOnce()).save(expectedUser);
    }
}
