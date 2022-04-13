package ar.edu.unq.desapp.grupon.backenddesappapi.Model;

import java.util.Objects;
import java.util.DateTime;

public enum CryptoactiveName {
        ALICEUSDT,
        MATICUSDT,
        AXSUSDT,
        AAVEUSDT,
        ATOMUSDT,
        NEOUSDT,
        DOTUSDT,
        ETHUSDT,
        CAKEUSDT,
        BTCUSDT,
        BNBUSDT,
        ADAUSDT,
        TRXUSDT,
        AUDIOUSDT
}

public class Cryptoactive() {
    private CryptoactiveName name;
    private Float price;
    private DateTime date;

    public CryptoactiveName getName() {
        return name;
    }

    public void setName(CryptoactiveName name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }


}