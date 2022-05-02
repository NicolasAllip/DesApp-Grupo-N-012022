package ar.edu.unq.desapp.grupon.backenddesappapi.service;

import java.util.List;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.InputTransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.Transaction;
import ar.edu.unq.desapp.grupon.backenddesappapi.persistence.ITransactionDao;

@Service
public class TransactionService implements ITransactionService {
    
    @Autowired
    private ITransactionDao transactionDao;
    
    @Transactional(readOnly = true)
    @Override
    public List<Transaction> findAll(){
        return (List<Transaction>) transactionDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Transaction findById(Long id) {
        return transactionDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Transaction save(TransactionIntent transactionIntent, User user) {
        Transaction transaction = Transaction.builder()
                .transaction(transactionIntent)
                .user(user)
                .state(PENDING)
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
        val transaction  = transactionDao.findById(id);
        val senderUser   = transaction.getTransaction().getUser();
        val receiverUser = transaction.getUser();
        val transactionDate = transaction.getTransaction().getDate();
        if(transactionDate.isAfter(LocalDateTime.now().minusMinutes(30))) {
            senderUser.increaseReputationBy(10);
            receiverUser.increaseReputationBy(10);
        } else {
            senderUser.increaseReputationBy(5);
            receiverUser.increaseReputationBy(5);
        }
    }

    @Transactional
    @Override
    public void cancel(Long id){
        val transaction  = transactionDao.findById(id);
        val senderUser   = transaction.getTransaction().getUser();
        val receiverUser = transaction.getUser();
        transaction.setState(TransactionState.CANCELED);
        senderUser.lowerReputationBy(20);
        receiverUser.lowerReputationBy(20);
    }
    @Transactional
    @Override
    public void cancelByPrize(Long id){
        val transaction  = transactionDao.findById(id);
        transaction.setState(TransactionState.CANCELED);
    }
    */
}