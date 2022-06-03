package ar.edu.unq.desapp.grupon.backenddesappapi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    private ICryptoactiveLogDao cryptoactiveDao;
    @Autowired
    private IGetPriceForCryptoRestclient getPriceForCryptoRestclient;

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
        return (List<CryptoactiveLog>) cryptoactiveDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public CryptoactiveLog findById(Long id) {
        return cryptoactiveDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public CryptoactiveLog save(CryptoactiveName name, Float price) {
        CryptoactiveLog cryptoactive = CryptoactiveLog.builder()
                .name(name)
                .price(price)
                .date(LocalDateTime.now())
                .build();

        return cryptoactiveDao.save(cryptoactive);
    }

    @Transactional
    @Override
    public CryptoactiveLog update(Long id, Float price) {
    //public void update(Long id, Float price) {
        CryptoactiveLog cryptoactive = cryptoactiveDao.findById(id).orElse(null);
        if(cryptoactive != null) {
            cryptoactive.setPrice(price);
            return cryptoactiveDao.save(cryptoactive);    
        }
    }

    @Transactional
    @Override
    public void delete(Long id) {
        cryptoactiveDao.deleteById(id);
    }

    @Transactional
    @Override
    public List<CryptoactiveLog> getAllCryptos() {
        BinanceCryptoDTO[] binanceCryptoDTOS = getPriceForCryptoRestclient.getBatchCryptoPrice(AVAILABLE_CRYPTOS);
        List<CryptoactiveLog> cryptoactiveList = new ArrayList<>();

        Arrays.stream(binanceCryptoDTOS).forEach(bcrypto -> cryptoactiveList.add(binanceToModelCrypto(bcrypto)));

        return cryptoactiveList;
    }

    private CryptoactiveLog binanceToModelCrypto(BinanceCryptoDTO binanceCryptoDTO) {
        CryptoactiveLog cryptoactive = CryptoactiveLog.builder()
                .name(CryptoactiveName.valueOf(binanceCryptoDTO.getSymbol()))
                .price(Float.valueOf(binanceCryptoDTO.getPrice()))
                .date(LocalDateTime.now())
                .build();
        return cryptoactiveDao.save(cryptoactive);
    }
}