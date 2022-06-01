package ar.edu.unq.desapp.grupon.backenddesappapi.service;

import java.util.List;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.CryptoactiveLog;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.CryptoactiveName;

public interface ICryptoactiveLogService {
    
    public List<CryptoactiveLog> findAll();

    public CryptoactiveLog findById(Long id);

    public CryptoactiveLog save(CryptoactiveName name, Float price);

    public CryptoactiveLog update(Long id, Float price);

    public void delete(Long id);

    List<CryptoactiveLog> getAllCryptos();
}