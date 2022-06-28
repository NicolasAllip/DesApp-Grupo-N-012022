package ar.edu.unq.desapp.grupon.backenddesappapi.service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import ar.edu.unq.desapp.grupon.backenddesappapi.service.dto.CryptoactiveHistoryDTO;
import ar.edu.unq.desapp.grupon.backenddesappapi.service.dto.DatedPriceDTO;
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

    @Transactional
    @Override
    public CryptoactiveHistoryDTO getCryptoactive24HourHistory(CryptoactiveName cryptoactiveName) {
        List<CryptoactiveLog> logList = (List<CryptoactiveLog>) cryptoactiveLogDao.findAll();
        logList = logList.stream().filter(
                cryptoactiveLog ->
                        cryptoactiveLog.getName() == cryptoactiveName
                        && cryptoactiveLog.getDate().isAfter(LocalDateTime.now().minusHours(24)))
                .collect(Collectors.toList());

        List<DatedPriceDTO> history = logList.stream().map(
                        cryptoactiveLog -> new DatedPriceDTO(cryptoactiveLog.getPrice(), cryptoactiveLog.getDate())
                ).collect(Collectors.toList());

        return new CryptoactiveHistoryDTO(cryptoactiveName, history);
    }
}