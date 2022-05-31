package ar.edu.unq.desapp.grupon.backenddesappapi.unit.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.Cryptoactive;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.CryptoactiveName;

public class CriptoavtiveTest {

    @Test
    public void getCriptoactiveName(){
        CryptoactiveName cryptoName = CryptoactiveName.ALICEUSDT;
        Cryptoactive criptoActive = Cryptoactive.builder()
        .name(cryptoName)
        .build();

        assertEquals(criptoActive.getName(), cryptoName);
    }

    @Test
    public void getCriptoactivePrice(){
        Float price = 5F;
        Cryptoactive criptoActive = Cryptoactive.builder()
        .price(price)
        .build();

        assertEquals(criptoActive.getPrice(), price);
    }

    @Test
    public void getCriptoactiveLocalDate(){
        LocalDateTime localDateTime = LocalDateTime.now();
        Cryptoactive criptoActive = Cryptoactive.builder()
        .date(localDateTime)
        .build();

        assertEquals(criptoActive.getDate(), localDateTime);
    }
}
