package ar.edu.unq.desapp.grupon.backenddesappapi.unit.controller;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.Transaction;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.TransactionState;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.User;
import ar.edu.unq.desapp.grupon.backenddesappapi.service.ITransactionService;
import ar.edu.unq.desapp.grupon.backenddesappapi.webservice.TransactionRestController;
import ar.edu.unq.desapp.grupon.backenddesappapi.webservice.dto.CreateTransactionDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionRestControllerTest {

    @Mock
    private ITransactionService transactionServiceMock;
    @InjectMocks
    private TransactionRestController transactionRestController;

    @Test
    public void getAllTransactions_returnTransactions() {
        // arrange
        Transaction transaction1 = Transaction.builder()
                .build();
        Transaction transaction2 = Transaction.builder()
                .build();

        List<Transaction> expectedTransactionList = new ArrayList<>();
        expectedTransactionList.add(transaction1);
        expectedTransactionList.add(transaction2);

        when(transactionServiceMock.findAll()).thenReturn(expectedTransactionList);

        // act
        List<Transaction> actualTransactionList = transactionRestController.index();

        // assert
        verify(transactionServiceMock, atLeastOnce()).findAll();

        Assertions.assertEquals(2, actualTransactionList.size());
        Assertions.assertTrue(actualTransactionList.contains(transaction1));
        Assertions.assertTrue(actualTransactionList.contains(transaction2));
    }

    @Test
    public void getTransactionById_gotTransaction() {
        // arrange
        Long expectedId = 1L;
        Transaction expectedTransaction = Transaction.builder()
                .id(expectedId)
                .build();

        // act
        when(transactionServiceMock.findById(1L)).thenReturn(expectedTransaction);

        // assert
        ResponseEntity<?> response = transactionRestController.findById(1L);

        verify(transactionServiceMock, atLeastOnce()).findById(expectedId);

        Assertions.assertEquals(expectedTransaction, response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void whenCreateTransaction_returnCreated() {
        // arrange
        CreateTransactionDTO payload = CreateTransactionDTO.builder()
                .transactionIntentId(1L)
                .userId(1L)
                .build();
        Transaction expectedTransaction = Transaction.builder()
                .id(1L)
                .build();

        // act
        when(transactionServiceMock.save(payload)).thenReturn(expectedTransaction);

        // assert
        ResponseEntity<?> response = transactionRestController.create(payload);

        verify(transactionServiceMock, atLeastOnce()).save(payload);

        Assertions.assertEquals(expectedTransaction, response.getBody());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void acceptTransaction() {
        Long id = 1L;
        transactionRestController.accept(id);
        verify(transactionServiceMock, atLeastOnce()).acceptTransaction(id);
    }

    @Test
    public void cancelTransaction() {
        Long id = 1L;
        transactionRestController.cancel(id);
        verify(transactionServiceMock, atLeastOnce()).cancel(id);
    }

    @Test
    public void getAllActiveTransactions_returnTransactions() {
        // arrange
        Transaction transaction1 = Transaction.builder()
                .id(1L)
                .state(TransactionState.PENDING)
                .build();
        Transaction transaction2 = Transaction.builder()
                .id(2L)
                .state(TransactionState.PENDING)
                .build();

        List<Transaction> expectedTransactionList = new ArrayList<>();
        expectedTransactionList.add(transaction1);
        expectedTransactionList.add(transaction2);

        when(transactionServiceMock.findActiveTransactions()).thenReturn(expectedTransactionList);

        // act
        List<Transaction> actualUserList = transactionRestController.getAllActive();

        // assert
        verify(transactionServiceMock, atLeastOnce()).findActiveTransactions();

        Assertions.assertEquals(2, actualUserList.size());
        Assertions.assertTrue(actualUserList.contains(transaction1));
        Assertions.assertTrue(actualUserList.contains(transaction2));
    }
}
