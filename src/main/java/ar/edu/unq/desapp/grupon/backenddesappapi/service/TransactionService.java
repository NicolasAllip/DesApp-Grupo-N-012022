package ar.edu.unq.desapp.grupon.backenddesappapi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.TransactionIntent;
import ar.edu.unq.desapp.grupon.backenddesappapi.webservice.dto.CreateTransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.Transaction;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.TransactionState;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.User;
import ar.edu.unq.desapp.grupon.backenddesappapi.persistence.ITransactionDao;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    private ITransactionDao transactionDao;

    @Autowired
    private ITransactionIntentService transactionIntentService;

    @Autowired
    private IUserService userService;

    @Transactional(readOnly = true)
    @Override
    public List<Transaction> findAll(){
        return (List<Transaction>) transactionDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Transaction findById(Long id) {
        Transaction transaction = userDao.findById(id).orElse(null);

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
        Transaction transaction = Transaction.builder()
                .transaction(transactionIntent)
                .user(user)
                .state(TransactionState.PENDING)
                .dateCreated(now)
                .lastUpdated(now)
                .build();

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
        if(transaction != null) { // TODO: hacer un not found para la transaccion

            User senderUser   = transaction.getTransaction().getUser();
            User receiverUser = transaction.getUser();

            LocalDateTime transactionDate = transaction.getTransaction().getDate();

            Float realPrice        = transaction.getCryptoactive().getPrice();
            Float pricePlusP       = realPrice + ((realPrice * 5) / 100);
            Float priceMinusP      = realPrice - ((realPrice * 5) / 100);
            Float transactionPrice = transaction.getPrize();

            if(transaction.getState() != TransactionState.PENDING) {
                if(transactionPrice > pricePlusP || transactionPrice < priceMinusP) {
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
                    transaction.setState(TransactionState.COMPLETED);
                }
            }

            transaction.setLastUpdated(LocalDateTime.now());
            transactionDao.save(transaction);
        }
    }

    @Transactional
    @Override
    public void cancel(Long id){
        Transaction transaction  = transactionDao.findById(id).orElse(null);
        // TODO: hacer un not found para la transaccion
        User senderUser   = transaction.getTransaction().getUser();
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
        // TODO: hacer un not found para la transaccion
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