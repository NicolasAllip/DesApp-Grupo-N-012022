package ar.edu.unq.desapp.grupon.backenddesappapi.service;

import java.util.List;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.Cryptoactive;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.CryptoactiveName;

public interface ICryptoactiveService {
    
    public List<Cryptoactive> findAll();

    public Cryptoactive findById(Long id);

    public Cryptoactive save(CryptoactiveName name, Float price);

    public Cryptoactive update(Long id, Float price);

    public void delete(Long id);
    
}