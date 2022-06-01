package ar.edu.unq.desapp.grupon.backenddesappapi.service;

import java.util.List;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.CryptoactiveLog;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.Operation;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.TransactionIntent;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.User;

public interface ITransactionIntentService {
    
    public List<ActiveTransactionDTO> findAll();

    public TransactionIntent findById(Long id);

    public TransactionIntent save(CryptoactiveLog cryptoactive, Float amount, User user, Operation operation);

    public void delete(Long id);
    
}