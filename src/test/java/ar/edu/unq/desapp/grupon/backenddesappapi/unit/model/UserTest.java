package ar.edu.unq.desapp.grupon.backenddesappapi.unit.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.User;

public class UserTest {

    @Test
    public void setUserName(){
        String name = "name";
        User user = User.builder()
        .name(name)
        .build();

        assertEquals(user.getName(), name);
    }
    
    @Test
    public void setUserSurname(){
        String surname = "surname";
        User user = User.builder()
        .surname(surname)
        .build();

        assertEquals(user.getSurname(), surname);
    }

    @Test
    public void setUserEmail(){
        String email = "email@gmail.com";
        User user = User.builder()
        .email(email)
        .build();

        assertEquals(user.getEmail(), email);
    }

    @Test
    public void setUserAddress(){
        String address = "address";
        User user = User.builder()
        .address(address)
        .build();
        
        assertEquals(user.getAddress(), address);
    }

    @Test
    public void setUserPassword(){
        String pass = "pass1234";
        User user = User.builder()
        .password(pass)
        .build();

        assertEquals(user.getPassword(), pass);
    }

    @Test
    public void setUserCvu(){
        String cvu = "123456123456";
        User user = User.builder()
        .cvu(cvu)
        .build();

        assertEquals(user.getCvu(), cvu);
    }
    
    @Test
    public void setUserReputation(){
        Float rep = 4F;
        User user = User.builder()
        .reputation(rep)
        .build();

        assertEquals(user.getReputation(), rep);
    }

}
