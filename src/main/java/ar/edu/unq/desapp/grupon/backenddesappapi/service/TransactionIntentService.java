package ar.edu.unq.desapp.grupon.backenddesappapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.CryptoactiveLog;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.Operation;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.TransactionIntent;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.User;
import ar.edu.unq.desapp.grupon.backenddesappapi.persistence.ITransactionIntentDao;

@Service
public class TransactionIntentService implements ITransactionIntentService {
    
    @Autowired
    private ITransactionIntentDao transactionDao;
    
    @Transactional(readOnly = true)
    @Override
    public List<ActiveTransactionDTO> findAll(){
        List<TransactionIntent> transactionIntents = (List<TransactionIntent>) transactionDao.findAll();
        List<ActiveTransactionDTO> ret = new ArrayList<ActiveTransactionDTO>();
        for(TransactionIntent transaction : transactionIntents) {
            ret.add(new ActiveTransactionDTO(transaction));
        }
        return ret;
    }

    @Transactional(readOnly = true)
    @Override
    public TransactionIntent findById(Long id) {
        return transactionDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public TransactionIntent save(CryptoactiveLog cryptoactive, Float amount, User user, Operation operation) {
        TransactionIntent transaction = TransactionIntent.builder()
                .cryptoactive(cryptoactive)
                .amount(amount)
                .user(user)
                .operation(operation)
                .build();

        return transactionDao.save(transaction);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        transactionDao.deleteById(id);
    }
    
}