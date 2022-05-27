package ar.edu.unq.desapp.grupon.backenddesappapi.service;

import java.time.LocalDateTime;
import java.util.List;

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
        cryptoactive.setPrice(price);
        return cryptoactiveDao.save(cryptoactive);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        cryptoactiveDao.deleteById(id);
    }
    
}