package ar.edu.unq.desapp.grupon.backenddesappapi.Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="crypto_actives")
public class Cryptoactive implements Serializable{
    @Id
    private CryptoactiveName name;
    private Float price;

    public Cryptoactive(CryptoactiveName name, Float price) {
        this.name = name;
        this.price = price;
    }

    public Cryptoactive() {}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cryptoactive that = (Cryptoactive) o;
        return name == that.name && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
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

        public Cryptoactive build() {
            return cryptoactive;
        }
    }
}