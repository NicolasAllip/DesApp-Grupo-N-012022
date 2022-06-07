package ar.edu.unq.desapp.grupon.backenddesappapi.service;

import java.util.List;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.Transaction;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.TransactionIntent;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.User;
import ar.edu.unq.desapp.grupon.backenddesappapi.webservice.dto.CreateTransactionDTO;

public interface ITransactionService {
    
    public List<Transaction> findAll();

    public Transaction findById(Long id);

    public Transaction save(CreateTransactionDTO createTransactionDTO);

    public void delete(Long id);

    public void acceptTransaction(Long id);

    public void cancel(Long id);

    public List<Transaction> findActiveTransactions();

}