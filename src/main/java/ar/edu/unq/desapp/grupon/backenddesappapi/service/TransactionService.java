package ar.edu.unq.desapp.grupon.backenddesappapi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.*;
import ar.edu.unq.desapp.grupon.backenddesappapi.exception.TransactionDoesNotExistException;
import ar.edu.unq.desapp.grupon.backenddesappapi.webservice.dto.CreateTransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupon.backenddesappapi.persistence.ITransactionDao;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    private ITransactionDao transactionDao;

    @Autowired
    @Lazy
    private ITransactionIntentService transactionIntentService;

    @Autowired
    @Lazy
    private IUserService userService;

    @Autowired
    @Lazy
    private ICryptoactiveService criptoService;

    @Transactional(readOnly = true)
    @Override
    public List<Transaction> findAll(){
        return (List<Transaction>) transactionDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Transaction findById(Long id) {
        Transaction transaction = transactionDao.findById(id).orElse(null);

        if (transaction == null) {
            throw new TransactionDoesNotExistException(id);
        }

        return transaction;
    }


    @Transactional
    @Override
    public Transaction save(CreateTransactionDTO createTransactionDTO) {
        TransactionIntent transactionIntent = transactionIntentService.findById(createTransactionDTO.getTransactionIntentId());
        User user = userService.findById(createTransactionDTO.getUserId());

        LocalDateTime now = LocalDateTime.now();
        Transaction transaction = new Transaction(transactionIntent, user, TransactionState.PENDING, now, now);

        return transactionDao.save(transaction);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        transactionDao.deleteById(id);
    }


    @Transactional
    @Override
    public void acceptTransaction(Long id){

        Transaction transaction = transactionDao.findById(id).orElse(null);
        if (transaction == null) {
            throw new TransactionDoesNotExistException(id);
        }

        User senderUser   = transaction.getTransactionIntent().getUser();
        User receiverUser = transaction.getUser();

        LocalDateTime transactionDate = transaction.getTransactionIntent().getDate();


        Float realPrice        = transaction.getCryptoactive().getPrice();
        Float transactionPrice = transaction.getPrize();
        Float transactionOffer = transaction.getOffer();

        if(transaction.getState() != TransactionState.PENDING) {
            if(validatePriceChangedTooMuch(transactionPrice, realPrice) || validatePriceChangedTooMuch(transactionOffer, realPrice)) {
                cancelByPrize(id);
            } else {
                if(transactionDate.isAfter(LocalDateTime.now().minusMinutes(30))) {
                    senderUser.increaseReputationBy(10L);
                    receiverUser.increaseReputationBy(10L);
                } else {
                    senderUser.increaseReputationBy(5L);
                    receiverUser.increaseReputationBy(5L);
                }
                senderUser.increaseOperationAmount();
                receiverUser.increaseOperationAmount();
                if (transaction.getOperation() == Operation.BUY) {
                    transaction.setState(TransactionState.TRANSFERED);
                } else {
                    transaction.setState(TransactionState.RECEIVED);
                }
            }
        }

        transaction.setLastUpdated(LocalDateTime.now());
        transactionDao.save(transaction);
    }

    private Boolean validatePriceChangedTooMuch(Float actualPrice, Float realPrice) {
        Float pricePlus = realPrice + ((realPrice * 5) / 100);
        Float priceMinus = realPrice - ((realPrice * 5) / 100);

        return (actualPrice > pricePlus || actualPrice < priceMinus);
    }

    @Transactional
    @Override
    public void cancel(Long id){
        Transaction transaction  = transactionDao.findById(id).orElse(null);
        if (transaction == null) {
            throw new TransactionDoesNotExistException(id);
        }
        User senderUser   = transaction.getTransactionIntent().getUser();
        User receiverUser = transaction.getUser();
        transaction.setState(TransactionState.CANCELED);
        senderUser.lowerReputationBy(20L);
        receiverUser.lowerReputationBy(20L);
        transaction.setLastUpdated(LocalDateTime.now());

        transactionDao.save(transaction);
    }
    
    @Transactional
    private void cancelByPrize(Long id){
        Transaction transaction  = transactionDao.findById(id).orElse(null);
        if (transaction == null) {
            throw new TransactionDoesNotExistException(id);
        }
        transaction.setState(TransactionState.CANCELED);
        transaction.setLastUpdated(LocalDateTime.now());

        transactionDao.save(transaction);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Transaction> findActiveTransactions(){
        List<Transaction> transactions = (List<Transaction>) transactionDao.findAll();
        return transactions.stream().filter(transaction -> transaction.getState() == TransactionState.PENDING).collect(Collectors.toList());
    }
}