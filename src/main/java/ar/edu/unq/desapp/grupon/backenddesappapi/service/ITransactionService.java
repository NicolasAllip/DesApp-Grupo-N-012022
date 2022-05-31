package ar.edu.unq.desapp.grupon.backenddesappapi.service;

import java.util.List;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.Transaction;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.TransactionIntent;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.User;

public interface ITransactionService {
    
    public List<Transaction> findAll();

    public Transaction findById(Long id);

    public Transaction save(TransactionIntent transactionIntent, User user);

    public void delete(Long id);

    public void acceptTransaction(Long id);

    public void cancel(Long id);

}