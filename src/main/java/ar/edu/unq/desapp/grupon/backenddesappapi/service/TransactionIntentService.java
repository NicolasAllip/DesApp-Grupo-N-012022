package ar.edu.unq.desapp.grupon.backenddesappapi.service;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.*;
import ar.edu.unq.desapp.grupon.backenddesappapi.exception.TransactionDoesNotExistException;
import ar.edu.unq.desapp.grupon.backenddesappapi.restclient.IGetDolarConversionValueRestclient;
import ar.edu.unq.desapp.grupon.backenddesappapi.service.dto.ActiveTransactionDTO;
import ar.edu.unq.desapp.grupon.backenddesappapi.webservice.dto.TransactionIntentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupon.backenddesappapi.persistence.ITransactionIntentDao;

@Service
public class TransactionIntentService implements ITransactionIntentService {
    
    @Autowired
    private ITransactionIntentDao transactionIntentDao;

    @Autowired
    @Lazy
    private ICryptoactiveService cryptoactiveService;

    @Autowired
    @Lazy
    private IUserService userService;

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
        TransactionIntent transaction = transactionIntentDao.findById(id).orElse(null);

        if (transaction == null) {
            throw new TransactionDoesNotExistException(id);
        }

        return transaction;
    }


    @Transactional
    @Override
    public TransactionIntent save(TransactionIntentDTO transactionIntentDTO) {
        User user = userService.findById(transactionIntentDTO.getUserId());
        Cryptoactive cryptoactive = cryptoactiveService.findByName(transactionIntentDTO.getCryptoactiveName());

        pesosConversion = Float.parseFloat(getDolarConversionValueRestclient.getOfficialDolarValue().getCompra().replace(',', '.'));

        TransactionIntent transaction = new TransactionIntent(cryptoactive, transactionIntentDTO.getAmount(), transactionIntentDTO.getOffer(), cryptoactive.getPrice() * pesosConversion, user, transactionIntentDTO.getOperation());

        return transactionIntentDao.save(transaction);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        transactionIntentDao.deleteById(id);
    }
    
}