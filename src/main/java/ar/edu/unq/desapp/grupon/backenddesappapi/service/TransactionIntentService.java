package ar.edu.unq.desapp.grupon.backenddesappapi.service;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.*;
import ar.edu.unq.desapp.grupon.backenddesappapi.restclient.IGetDolarConversionValueRestclient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupon.backenddesappapi.persistence.ITransactionIntentDao;

@Service
public class TransactionIntentService implements ITransactionIntentService {
    
    @Autowired
    private ITransactionIntentDao transactionIntentDao;

    @Autowired
    private IGetDolarConversionValueRestclient getDolarConversionValueRestclient;
    private Float pesosConversion;

    @Transactional(readOnly = true)
    @Override
    public List<ActiveTransactionDTO> findAll(){
        List<TransactionIntent> transactionIntents = (List<TransactionIntent>) transactionIntentDao.findAll();
        List<ActiveTransactionDTO> ret = new ArrayList();
        for(TransactionIntent transaction : transactionIntents) {
            ret.add(new ActiveTransactionDTO(transaction));
        }
        return ret;
    }

    @Transactional(readOnly = true)
    @Override
    public TransactionIntent findById(Long id) {
        return transactionIntentDao.findById(id).orElse(null);
    }

    @Transactional
    @Override // TODO: debatir si deberiamos mandar objetos enteros o solo IDs
    public TransactionIntent save(Cryptoactive cryptoactive, Float amount, User user, Operation operation) {
        Float pesosConversion = Float.parseFloat(getDolarConversionValueRestclient.getOfficialDolarValue().getCompra());

        TransactionIntent transaction = TransactionIntent.builder()
                .cryptoactive(cryptoactive)
                .amount(amount)
                .user(user)
                .prizePesos(cryptoactive.getPrice() * pesosConversion)
                .operation(operation)
                .build();

        return transactionIntentDao.save(transaction);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        transactionIntentDao.deleteById(id);
    }
    
}