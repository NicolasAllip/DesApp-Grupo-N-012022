package ar.edu.unq.desapp.grupon.backenddesappapi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.Cryptoactive;
import ar.edu.unq.desapp.grupon.backenddesappapi.restclient.IGetPriceForCryptoRestclient;
import ar.edu.unq.desapp.grupon.backenddesappapi.restclient.dto.BinanceCryptoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.CryptoactiveLog;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.CryptoactiveName;
import ar.edu.unq.desapp.grupon.backenddesappapi.persistence.ICryptoactiveLogDao;

@Service
public class CryptoactiveLogService implements ICryptoactiveLogService {
    
    @Autowired
    private ICryptoactiveLogDao cryptoactiveLogDao;

    private static List<String> AVAILABLE_CRYPTOS = new ArrayList<>();
    static {
        AVAILABLE_CRYPTOS.add("ALICEUSDT");
        AVAILABLE_CRYPTOS.add("MATICUSDT");
        AVAILABLE_CRYPTOS.add("AXSUSDT");
        AVAILABLE_CRYPTOS.add("AAVEUSDT");
        AVAILABLE_CRYPTOS.add("ATOMUSDT");
        AVAILABLE_CRYPTOS.add("NEOUSDT");
        AVAILABLE_CRYPTOS.add("DOTUSDT");
        AVAILABLE_CRYPTOS.add("ETHUSDT");
        AVAILABLE_CRYPTOS.add("CAKEUSDT");
        AVAILABLE_CRYPTOS.add("BTCUSDT");
        AVAILABLE_CRYPTOS.add("BNBUSDT");
        AVAILABLE_CRYPTOS.add("ADAUSDT");
        AVAILABLE_CRYPTOS.add("TRXUSDT");
        AVAILABLE_CRYPTOS.add("AUDIOUSDT");
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<CryptoactiveLog> findAll(){
        return (List<CryptoactiveLog>) cryptoactiveLogDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public CryptoactiveLog findById(Long id) {
        return cryptoactiveLogDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public CryptoactiveLog save(CryptoactiveName name, Float price) {
        return cryptoactiveLogDao.save(new CryptoactiveLog(name, price, LocalDateTime.now()));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        cryptoactiveLogDao.deleteById(id);
    }

    @Transactional
    @Override
    public List<CryptoactiveLog> getAllCryptos() {
        return (List<CryptoactiveLog>) cryptoactiveLogDao.findAll();
    }
}