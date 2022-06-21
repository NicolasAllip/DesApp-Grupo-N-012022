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
@Table(name="crypto_actives_log")
public class CryptoactiveLog implements Serializable{
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CryptoactiveLog that = (CryptoactiveLog) o;
        return Objects.equals(id, that.id) && name == that.name && Objects.equals(price, that.price) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, date);
    }

    public static CryptoactiveBuilder builder() {
        return new CryptoactiveBuilder();
    }

    public static final class CryptoactiveBuilder {
        private CryptoactiveLog cryptoactive;

        private CryptoactiveBuilder() {
            cryptoactive = new CryptoactiveLog();
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

        public CryptoactiveLog build() {
            return cryptoactive;
        }
    }
}