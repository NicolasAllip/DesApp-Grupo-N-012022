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
    private ICryptoactiveLogService cryptoactiveLogService;
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
    public Cryptoactive findByName(CryptoactiveName name) {
        return cryptoactiveDao.findById(name).orElse(null);
    }

    @Transactional
    @Override
    public Cryptoactive save(CryptoactiveName name, Float price) {
        Cryptoactive cryptoactive = cryptoactiveDao.findById(name).orElse(Cryptoactive.builder()
        .name(name)
        .build());
        cryptoactive.setPrice(price);
        return cryptoactiveDao.save(cryptoactive);
    }

    @Transactional
    @Override
    public void delete(CryptoactiveName name) {
        cryptoactiveDao.deleteById(name);
    }

    @Transactional
    @Override
    public List<Cryptoactive> getAllCryptos() {
        return (List<Cryptoactive>) cryptoactiveDao.findAll();
    }

    @Transactional
    @Override
    public List<Cryptoactive> updateAllCryptos() {
        BinanceCryptoDTO[] binanceCryptoDTOS = getPriceForCryptoRestclient.getBatchCryptoPrice(AVAILABLE_CRYPTOS);
        List<Cryptoactive> cryptoactiveList = new ArrayList<>();

        Arrays.stream(binanceCryptoDTOS).forEach(bcrypto -> {
            Cryptoactive crypto = binanceToModelCrypto(bcrypto);
            cryptoactiveList.add(crypto);
            cryptoactiveLogService.save(crypto.getName(), crypto.getPrice());
        });

        return cryptoactiveList;
    }

    private Cryptoactive binanceToModelCrypto(BinanceCryptoDTO binanceCryptoDTO) {
        Cryptoactive cryptoactive = Cryptoactive.builder()
                .name(CryptoactiveName.valueOf(binanceCryptoDTO.getSymbol()))
                .price(Float.valueOf(binanceCryptoDTO.getPrice()))
                .build();
        return cryptoactiveDao.save(cryptoactive);
    }
}