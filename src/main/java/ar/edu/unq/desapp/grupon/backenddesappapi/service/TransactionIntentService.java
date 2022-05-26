package ar.edu.unq.desapp.grupon.backenddesappapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.Cryptoactive;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.TransactionIntent;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.User;
import ar.edu.unq.desapp.grupon.backenddesappapi.persistence.ITransactionIntentDao;

@Service
public class TransactionIntentService implements ITransactionIntentService {
    
    @Autowired
    private ITransactionIntentDao transactionDao;
    
    @Transactional(readOnly = true)
    @Override
    public List<TransactionIntent> findAll(){
        return (List<TransactionIntent>) transactionDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public TransactionIntent findById(Long id) {
        return transactionDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public TransactionIntent save(Cryptoactive cryptoactive, Float amount, User user) {
        TransactionIntent transaction = TransactionIntent.builder()
                .cryptoactive(cryptoactive)
                .amount(amount)
                .user(user)
                .build();

        return transactionDao.save(transaction);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        transactionDao.deleteById(id);
    }
    
}