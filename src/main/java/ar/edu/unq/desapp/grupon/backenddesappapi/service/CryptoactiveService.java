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

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.Cryptoactive;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.CryptoactiveName;
import ar.edu.unq.desapp.grupon.backenddesappapi.persistence.ICryptoactiveDao;

@Service
public class CryptoactiveService implements ICryptoactiveService {
    
    @Autowired
    private ICryptoactiveDao cryptoactiveDao;
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
    public List<Cryptoactive> findAll(){
        return (List<Cryptoactive>) cryptoactiveDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Cryptoactive findById(Long id) {
        return cryptoactiveDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Cryptoactive save(CryptoactiveName name, Float price) {
        Cryptoactive cryptoactive = Cryptoactive.builder()
                .name(name)
                .price(price)
                .date(LocalDateTime.now())
                .build();

        return cryptoactiveDao.save(cryptoactive);
    }

    @Transactional
    @Override
    public Cryptoactive update(Long id, Float price) {
        Cryptoactive cryptoactive = cryptoactiveDao.findById(id).orElse(null);
        // TODO: validar null
        cryptoactive.setPrice(price);
        return cryptoactiveDao.save(cryptoactive);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        cryptoactiveDao.deleteById(id);
    }

    @Transactional
    @Override
    public List<Cryptoactive> getAllCryptos() {
        BinanceCryptoDTO[] binanceCryptoDTOS = getPriceForCryptoRestclient.getBatchCryptoPrice(AVAILABLE_CRYPTOS);
        List<Cryptoactive> cryptoactiveList = new ArrayList<>();

        Arrays.stream(binanceCryptoDTOS).forEach(bcrypto -> cryptoactiveList.add(binanceToModelCrypto(bcrypto)));

        return cryptoactiveList;
    }

    private Cryptoactive binanceToModelCrypto(BinanceCryptoDTO binanceCryptoDTO) {
        // TODO: crear CryptoLog
        Cryptoactive cryptoactive = Cryptoactive.builder()
                .name(CryptoactiveName.valueOf(binanceCryptoDTO.getSymbol()))
                .price(Float.valueOf(binanceCryptoDTO.getPrice()))
                .date(LocalDateTime.now())
                .build();
        return cryptoactiveDao.save(cryptoactive);
    }
}