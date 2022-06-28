package ar.edu.unq.desapp.grupon.backenddesappapi.Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name="transaction_intents")
public class TransactionIntent implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private CryptoactiveName cryptoactive;
    private Float amount;
    private Float offer;
    private Float prize;
    private Float prizePesos;
    @ManyToOne
    private User user;
    private Operation operation;
    private LocalDateTime date;


    public TransactionIntent(Cryptoactive cryptoactive, Float amount, Float offer, Float prizePesos, User user, Operation operation) {
        this.cryptoactive = cryptoactive;
        this.amount = amount;
        this.offer = offer;
        this.prize = cryptoactive.getPrice();
        this.prizePesos = prizePesos;
        this.user = user;
        this.operation = operation;
        this.date = LocalDateTime.now();
    }

    public TransactionIntent() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CryptoactiveName getCryptoactive() {
        return cryptoactive;
    }

    public void setCryptoactive(CryptoactiveName cryptoactive) {
        this.cryptoactive = cryptoactive;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Float getOffer() {
        return offer;
    }

    public void setOffer(Float offer) {
        this.offer = offer;
    }

    public Float getPrize() {
        return prize;
    }

    public void setPrize(Float prize) {
        this.prize = prize;
    }

    public Float getPrizePesos() {
        return prizePesos;
    }

    public void setPrizePesos(Float prizePesos) {
        this.prizePesos = prizePesos;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
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
        TransactionIntent that = (TransactionIntent) o;
        return Objects.equals(id, that.id) && Objects.equals(cryptoactive, that.cryptoactive) && Objects.equals(amount, that.amount) && Objects.equals(offer, that.offer) && Objects.equals(prize, that.prize) && Objects.equals(prizePesos, that.prizePesos) && Objects.equals(user, that.user) && operation == that.operation && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cryptoactive, amount, offer, prize, prizePesos, user, operation, date);
    }

    public static TransactionIntentBuilder builder() {
        return new TransactionIntentBuilder();
    }

    public static final class TransactionIntentBuilder {
        private TransactionIntent transaction;

        private TransactionIntentBuilder() {
            transaction = new TransactionIntent();
        }
        public TransactionIntentBuilder id(Long id) {
            transaction.setId(id);
            return this;
        }

        public TransactionIntentBuilder cryptoactive(CryptoactiveName cryptoactive) {
            transaction.setCryptoactive(cryptoactive);
            return this;
        }

        public TransactionIntentBuilder amount(Float amount) {
            transaction.setAmount(amount);
            return this;
        }

        public TransactionIntentBuilder offer(Float offer) {
            transaction.setOffer(offer);
            return this;
        }

        public TransactionIntentBuilder prize(Float prize) {
            transaction.setPrize(prize);
            return this;
        }

        public TransactionIntentBuilder prizePesos(Float prizePesos) {
            transaction.setPrizePesos(prizePesos);
            return this;
        }

        public TransactionIntentBuilder user(User user) {
            transaction.setUser(user);
            return this;
        }

        public TransactionIntentBuilder operation(Operation operation) {
            transaction.setOperation(operation);
            return this;
        }

        public TransactionIntent build() {
            return transaction;
        }
    }

}