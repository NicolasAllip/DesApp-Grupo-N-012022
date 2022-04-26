package ar.edu.unq.desapp.grupon.backenddesappapi.service;

import java.util.List;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.InputTransactionDTO;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.OutputTransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.Transaction;
import ar.edu.unq.desapp.grupon.backenddesappapi.persistence.ITransactionDao;

@Service
public class UserService implements ITransactionService {
    
    @Autowired
    private ITransactionDao transactionDao;
    
    @Transactional(readOnly = true)
    @Override
    public List<Transaction> findAll(){
        return (List<Transaction>) transactionDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Transaction findById(Long id) {
        return transactionDao.findById(id).orElse(null);
    }
    
    @Override
    @Transactional
    public Transaction saveInputTransaction(InputTransactionDTO inputTransactionDTO) {
        Transaction transaction = Transaction.builder()
                .cryptoactive(inputTransactionDTO.getCryptoactive())
                .amount(inputTransactionDTO.getAmount())
                .prize(inputTransactionDTO.getPrize())
                .prizePesos(inputTransactionDTO.getPrizePesos())
                .user(inputTransactionDTO.getUserName()))
                .operation(inputTransactionDTO.getOperation())
                .build();

        return transactionDao.save(user);
    }

    @Override
    @Transactional
    public Transaction saveOutputTransaction(InputTransactionDTO outputTransactionDTO) {
        Transaction transaction = Transaction.builder()
                .cryptoactive(outputTransactionDTO.getCryptoactive())
                .amount(outputTransactionDTO.getAmount())
                .prize(outputTransactionDTO.getPrize())
                .prizePesos(outputTransactionDTO.getPrizePesos())
                .user(outputTransactionDTO.getUserName()))
                .operation(outputTransactionDTO.getOperation())
                .build();

        return transactionDao.save(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        transactionDao.deleteById(id);
    }

    /*
    public void acceptTransaction(){}
    public void cancel(){}
    public void cancelByPrize(){}
    */
}