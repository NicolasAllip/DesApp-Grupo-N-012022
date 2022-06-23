package ar.edu.unq.desapp.grupon.backenddesappapi.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.Transaction;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.TransactionState;
import ar.edu.unq.desapp.grupon.backenddesappapi.restclient.IGetPriceForCryptoRestclient;
import ar.edu.unq.desapp.grupon.backenddesappapi.restclient.dto.BinanceCryptoDTO;
import ar.edu.unq.desapp.grupon.backenddesappapi.webservice.dto.CryptosBetweenTwoDatesInput;
import ar.edu.unq.desapp.grupon.backenddesappapi.webservice.dto.CryptosBetweenTwoDatesOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.Cryptoactive;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.CryptoactiveName;
import ar.edu.unq.desapp.grupon.backenddesappapi.persistence.ICryptoactiveDao;

@Service
@EnableScheduling
public class CryptoactiveService implements ICryptoactiveService {
    
    @Autowired
    Jedis jedis = new Jedis("localhost");
    @Autowired
    private ICryptoactiveDao cryptoactiveDao;
    @Autowired
    private ICryptoactiveLogService cryptoactiveLogService;
    @Autowired
    @Lazy
    private ITransactionService transactionService;
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

    @Transactional(readOnly = true)
    @Override
    public List<String> findAllValues(){
        ArrayList<String> ret = new ArrayList<String>();
        for (String criptoName : AVAILABLE_CRYPTOS) {
            ret.append(this.findValueByName(criptoName));
        }
        return (List<String>) ret;
    }

    @Transactional(readOnly = true)
    @Override
    public String findValueByName(String name) {
        return jedis.get(name);
    }

    @Transactional
    @Override
    public Cryptoactive save(CryptoactiveName name, Float price) {
        Cryptoactive cryptoactive = cryptoactiveDao.findById(name).orElse(new Cryptoactive(name, null));

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
    @Scheduled(cron = "* */10 * * * *")
    public List<Cryptoactive> updateAllCryptos() {
        BinanceCryptoDTO[] binanceCryptoDTOS = getPriceForCryptoRestclient.getBatchCryptoPrice(AVAILABLE_CRYPTOS);
        List<Cryptoactive> cryptoactiveList = new ArrayList<>();

        Arrays.stream(binanceCryptoDTOS).forEach(bcrypto -> {
            Cryptoactive crypto = binanceToModelCrypto(bcrypto);
            cryptoactiveList.add(crypto);
            cryptoactiveLogService.save(crypto.getName(), crypto.getPrice());
            jedis.set(crypto.getName().name(), Float.toString(crypto.getPrice()));
        });

        return cryptoactiveList;
    }

    private Cryptoactive binanceToModelCrypto(BinanceCryptoDTO binanceCryptoDTO) {
        Cryptoactive cryptoactive = new Cryptoactive(
                CryptoactiveName.valueOf(binanceCryptoDTO.getSymbol()),
                Float.valueOf(binanceCryptoDTO.getPrice())
        );
        return cryptoactiveDao.save(cryptoactive);
    }

    @Override
    public CryptosBetweenTwoDatesOutput getOperatedCryptosInRange(CryptosBetweenTwoDatesInput cryptosBetweenTwoDatesInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateStart = LocalDate.parse(cryptosBetweenTwoDatesInput.getDateStart(), formatter);
        LocalDate dateEnd = LocalDate.parse(cryptosBetweenTwoDatesInput.getDateEnd(), formatter);

        Map<CryptoactiveName, Float> operatedCryptoactives = new HashMap<>();

        List<Transaction> transactions = transactionService.findAll()
                .stream().filter(
                        transaction -> transaction.getState() == TransactionState.COMPLETED && dateIsInRange(transaction.getLastUpdated(), dateStart, dateEnd)
                ).collect(Collectors.toList());

        for (Transaction transaction : transactions) {
            CryptoactiveName cryptoactiveName = transaction.getCryptoactive().getName();
            Float amount = transaction.getAmount();
            if (operatedCryptoactives.containsKey(cryptoactiveName)) {
                Float newAmount = amount + operatedCryptoactives.get(cryptoactiveName);
                operatedCryptoactives.put(cryptoactiveName, newAmount);
            } else {
                operatedCryptoactives.put(cryptoactiveName, amount);
            }
        }

        return CryptosBetweenTwoDatesOutput.builder()
                .range(cryptosBetweenTwoDatesInput)
                .operatedCryptoactives(operatedCryptoactives)
                .build();
    }

    private Boolean dateIsInRange(LocalDateTime date, LocalDate dateFrom, LocalDate dateTo) {
        return date.isAfter(ChronoLocalDateTime.from(dateFrom)) && date.isBefore(ChronoLocalDateTime.from(dateTo));
    }
}