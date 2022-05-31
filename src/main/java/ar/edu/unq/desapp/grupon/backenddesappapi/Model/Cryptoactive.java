package ar.edu.unq.desapp.grupon.backenddesappapi.Model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="crypto_actives")
public class Cryptoactive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private CryptoactiveName name;
    private Float price;
    private LocalDateTime date;

    public Long getId() {
        return id;
    }

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