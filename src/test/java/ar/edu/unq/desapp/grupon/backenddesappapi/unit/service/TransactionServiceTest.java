package ar.edu.unq.desapp.grupon.backenddesappapi.unit.service;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.*;
import ar.edu.unq.desapp.grupon.backenddesappapi.exception.TransactionDoesNotExistException;
import ar.edu.unq.desapp.grupon.backenddesappapi.persistence.ITransactionDao;
import ar.edu.unq.desapp.grupon.backenddesappapi.service.ITransactionIntentService;
import ar.edu.unq.desapp.grupon.backenddesappapi.service.IUserService;
import ar.edu.unq.desapp.grupon.backenddesappapi.service.TransactionService;
import ar.edu.unq.desapp.grupon.backenddesappapi.webservice.dto.CreateTransactionDTO;
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
public class TransactionServiceTest {
    @Mock
    private ITransactionDao transactionDao;
    @Mock
    private ITransactionIntentService transactionIntentService;
    @Mock
    private IUserService userService;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    public void getAllTransactions_returnsTransactionList() {
        // arrange
        Transaction transaction1 = Transaction.builder()
                .id(1L)
                .build();
        Transaction transaction2 = Transaction.builder()
                .id(2L)
                .build();

        List<Transaction> expectedTransactionList = new ArrayList<>();
        expectedTransactionList.add(transaction1);
        expectedTransactionList.add(transaction2);

        when(transactionDao.findAll()).thenReturn(expectedTransactionList);

        // act
        List<Transaction> actualTransactionList = transactionService.findAll();

        // assert
        verify(transactionDao, atLeastOnce()).findAll();

        Assertions.assertEquals(2, actualTransactionList.size());
        Assertions.assertTrue(actualTransactionList.contains(transaction1));
        Assertions.assertTrue(actualTransactionList.contains(transaction2));
    }

    @Test
    public void getTransactionById_returnsTransaction() {
        // arrange
        Transaction expectedTransaction = Transaction.builder()
                .id(1L)
                .build();

        when(transactionDao.findById(1L)).thenReturn(java.util.Optional.ofNullable(expectedTransaction));

        // act
        Transaction actualTransaction = transactionService.findById(1L);

        // assert
        verify(transactionDao, atLeastOnce()).findById(1L);

        Assertions.assertEquals(expectedTransaction, actualTransaction);
    }

    @Test
    public void getTransactionByIdWhenTransactionDoesntExist_returnsNull() {
        // arrange

        // act & assert
        Assertions.assertThrows(
                TransactionDoesNotExistException.class,
                () -> transactionService.findById(1L)
        );

        verify(transactionDao, atLeastOnce()).findById(1L);
    }

    @Test
    public void saveSellTransactionFromIntent_returnTransaction() {
        // arrange
        CreateTransactionDTO createTransactionDTO = CreateTransactionDTO.builder()
                .transactionIntentId(1L)
                .userId(2L)
                .build();
        TransactionIntent transactionIntent = TransactionIntent.builder()
                .id(1L)
                .operation(Operation.SELL)
                .build();
        User user = User.builder()
                .id(2L)
                .cvu("1234567890")
                .build();

        Transaction expectedTransaction = Transaction.builder()
                .transaction(transactionIntent)
                .user(user)
                .state(TransactionState.PENDING)
                .sendAddress(user.getCvu())
                .build();

        when(transactionIntentService.findById(1L)).thenReturn(transactionIntent);
        when(userService.findById(2L)).thenReturn(user);
        when(transactionDao.save(any())).thenReturn(expectedTransaction);

        Transaction actualTransaction = transactionService.save(createTransactionDTO);

        Assertions.assertEquals(TransactionState.PENDING, actualTransaction.getState());
        Assertions.assertEquals(transactionIntent, actualTransaction.getTransactionIntent());
        Assertions.assertEquals(user, actualTransaction.getUser());
        Assertions.assertEquals(user.getCvu(), actualTransaction.getSendAddress());
    }

    @Test
    public void saveBuyTransactionFromIntent_returnTransaction() {
        // arrange
        CreateTransactionDTO createTransactionDTO = CreateTransactionDTO.builder()
                .transactionIntentId(1L)
                .userId(2L)
                .build();
        TransactionIntent transactionIntent = TransactionIntent.builder()
                .id(1L)
                .operation(Operation.BUY)
                .build();
        User user = User.builder()
                .id(2L)
                .walletAddress("address")
                .build();

        Transaction expectedTransaction = Transaction.builder()
                .transaction(transactionIntent)
                .user(user)
                .state(TransactionState.PENDING)
                .sendAddress(user.getWalletAddress())
                .build();

        when(transactionIntentService.findById(1L)).thenReturn(transactionIntent);
        when(userService.findById(2L)).thenReturn(user);
        when(transactionDao.save(any())).thenReturn(expectedTransaction);

        Transaction actualTransaction = transactionService.save(createTransactionDTO);

        Assertions.assertEquals(TransactionState.PENDING, actualTransaction.getState());
        Assertions.assertEquals(transactionIntent, actualTransaction.getTransactionIntent());
        Assertions.assertEquals(user, actualTransaction.getUser());
        Assertions.assertEquals(user.getWalletAddress(), actualTransaction.getSendAddress());
    }

    @Test
    public void deleteTransaction() {
        transactionService.delete(1L);
        verify(transactionDao).deleteById(1L);
    }

    // TODO: tests de accept
    // TODO: tests de cancel
    // TODO: tests de cancel by prize

    @Test
    public void findActiveTransactions() {
        // arrange
        Transaction transaction1 = Transaction.builder()
                .id(1L)
                .state(TransactionState.PENDING)
                .build();
        Transaction transaction2 = Transaction.builder()
                .id(2L)
                .state(TransactionState.CANCELED)
                .build();
        Transaction transaction3 = Transaction.builder()
                .id(2L)
                .state(TransactionState.COMPLETED)
                .build();

        List<Transaction> expectedTransactionList = new ArrayList<>();
        expectedTransactionList.add(transaction1);
        expectedTransactionList.add(transaction2);
        expectedTransactionList.add(transaction3);

        when(transactionDao.findAll()).thenReturn(expectedTransactionList);

        // act
        List<Transaction> actualTransactionList = transactionService.findActiveTransactions();

        // assert
        verify(transactionDao, atLeastOnce()).findAll();

        Assertions.assertEquals(1, actualTransactionList.size());
        Assertions.assertTrue(actualTransactionList.contains(transaction1));
        Assertions.assertFalse(actualTransactionList.contains(transaction2));
        Assertions.assertFalse(actualTransactionList.contains(transaction3));
    }
}
