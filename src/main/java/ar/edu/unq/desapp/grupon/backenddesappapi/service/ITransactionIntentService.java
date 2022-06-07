package ar.edu.unq.desapp.grupon.backenddesappapi.service;

import java.util.List;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.*;
import ar.edu.unq.desapp.grupon.backenddesappapi.webservice.dto.TransactionIntentDTO;

public interface ITransactionIntentService {
    
    public List<ActiveTransactionDTO> findAll();

    public TransactionIntent findById(Long id);

    public TransactionIntent save(TransactionIntentDTO transactionIntentDTO);

    public void delete(Long id);
    
}