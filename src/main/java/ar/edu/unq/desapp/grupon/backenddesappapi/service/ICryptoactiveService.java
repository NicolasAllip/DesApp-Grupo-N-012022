package ar.edu.unq.desapp.grupon.backenddesappapi.service;

import java.time.LocalDate;
import java.util.List;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.Cryptoactive;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.CryptoactiveName;
import ar.edu.unq.desapp.grupon.backenddesappapi.webservice.dto.CryptosBetweenTwoDatesInput;
import ar.edu.unq.desapp.grupon.backenddesappapi.webservice.dto.CryptosBetweenTwoDatesOutput;

public interface ICryptoactiveService {
    
    public List<Cryptoactive> findAll();

    public Cryptoactive findByName(CryptoactiveName name);

    public List<Cryptoactive> findAllValues();

    public Cryptoactive findValueByName(String name);

    public Cryptoactive save(CryptoactiveName name, Float price);

    public void delete(CryptoactiveName name);

    List<Cryptoactive> getAllCryptos();

    List<Cryptoactive> updateAllCryptos();

    CryptosBetweenTwoDatesOutput getOperatedCryptosInRange(CryptosBetweenTwoDatesInput cryptosBetweenTwoDatesInput);
}