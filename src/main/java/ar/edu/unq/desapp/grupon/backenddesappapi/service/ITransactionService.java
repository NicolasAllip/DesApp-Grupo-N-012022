package ar.edu.unq.desapp.grupon.backenddesappapi.service;

import java.util.List;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.InputTransactionDTO;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.OutputTransactionDTO;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.Transaction;

public interface ITransactionService {
    
    public List<Transaction> findAll();

    public Transaction findById(Long id);

    public Transaction saveInputTransaction(InputTransactionDTO inputTransactionDTO);

    public Transaction saveOutputTransaction(OutputTransactionDTO outputTransactionDTO);

    public void delete(Long id);
    
}