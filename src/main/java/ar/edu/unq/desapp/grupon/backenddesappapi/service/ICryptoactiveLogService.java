package ar.edu.unq.desapp.grupon.backenddesappapi.service;

import java.util.List;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.CryptoactiveLog;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.CryptoactiveName;
import ar.edu.unq.desapp.grupon.backenddesappapi.service.dto.CryptoactiveHistoryDTO;

public interface ICryptoactiveLogService {
    
    public List<CryptoactiveLog> findAll();

    public CryptoactiveLog findById(Long id);

    public CryptoactiveLog save(CryptoactiveName name, Float price);

    public void delete(Long id);

    List<CryptoactiveLog> getAllCryptos();

    CryptoactiveHistoryDTO getCryptoactive24HourHistory(CryptoactiveName cryptoactiveName);
}