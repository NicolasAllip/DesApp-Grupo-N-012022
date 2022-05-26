package ar.edu.unq.desapp.grupon.backenddesappapi.service;

import java.util.List;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.Cryptoactive;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.TransactionIntent;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.User;

public interface ITransactionIntentService {
    
    public List<TransactionIntent> findAll();

    public TransactionIntent findById(Long id);

    public TransactionIntent save(Cryptoactive cryptoactive, Float amount, User user);

    public void delete(Long id);
    
}