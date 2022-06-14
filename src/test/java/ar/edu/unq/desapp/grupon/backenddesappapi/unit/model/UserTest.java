package ar.edu.unq.desapp.grupon.backenddesappapi.unit.model;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = User.builder()
                .id(1L)
                .reputation(50.0F)
                .operationAmount(1)
                .build();
    }

    @Test
    public void increaseRep() {
        testUser.increaseReputationBy(10L);
        Assertions.assertEquals(60.0F, testUser.getReputation());
    }

    @Test
    public void lowerRep() {
        testUser.lowerReputationBy(10L);
        Assertions.assertEquals(40.0F, testUser.getReputation());
    }

    @Test
    public void increaseOperations() {
        testUser.increaseOperationAmount();
        testUser.increaseOperationAmount();
        Assertions.assertEquals(3, testUser.getOperationAmount());
    }
}
