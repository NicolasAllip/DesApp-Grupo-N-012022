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

    @Override
    @Transactional(readOnly = true)
    public Transaction findById(Long id) {
        return transactionDao.findById(id).orElse(null);
    }
    
    @Override
    @Transactional
    public Transaction save(TransactionIntent transactionIntent, User user) {
        Transaction transaction = Transaction.builder()
                .transaction(transactionIntent)
                .user(user)
                .build();

        return transactionDao.save(transaction);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        transactionDao.deleteById(id);
    }

    /*
    public void acceptTransaction(){}
    public void cancel(){}
    public void cancelByPrize(){}
    */
}