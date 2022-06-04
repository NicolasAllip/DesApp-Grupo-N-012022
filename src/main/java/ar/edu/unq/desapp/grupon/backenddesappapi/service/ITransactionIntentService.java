package ar.edu.unq.desapp.grupon.backenddesappapi.service;

import java.util.List;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.*;

public interface ITransactionIntentService {
    
    public List<ActiveTransactionDTO> findAll();

    public TransactionIntent findById(Long id);

    public TransactionIntent save(Cryptoactive cryptoactive, Float amount, User user, Operation operation);

    public void delete(Long id);
    
}