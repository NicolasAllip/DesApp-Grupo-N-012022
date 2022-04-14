package ar.edu.unq.desapp.grupon.backenddesappapi.Model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Cryptoactive {
    private CryptoactiveName name;
    private Float price;
    private LocalDateTime date;

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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cryptoactive that = (Cryptoactive) o;
        return name == that.name && Objects.equals(price, that.price) && Objects.equals(date, that.date);
    }

    public static CryptoactiveBuilder builder() {
        return new CryptoactiveBuilder();
    }

    public static final class CryptoactiveBuilder {
        private Cryptoactive cryptoactive;

        private CryptoactiveBuilder() {
            cryptoactive = new Cryptoactive();
        }

        public CryptoactiveBuilder name(CryptoactiveName name) {
            cryptoactive.setName(name);
            return this;
        }

        public CryptoactiveBuilder price(Float price) {
            cryptoactive.setPrice(price);
            return this;
        }

        public CryptoactiveBuilder date(LocalDateTime date) {
            cryptoactive.setDate(date);
            return this;
        }

        public Cryptoactive build() {
            return cryptoactive;
        }
    }
}