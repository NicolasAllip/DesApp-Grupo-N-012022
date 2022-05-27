package ar.edu.unq.desapp.grupon.backenddesappapi.Model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transaction_intents")
public class TransactionIntent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Float id;
    private Cryptoactive cryptoactive;
    private Float amount;
    private Float prize = cryptoactive.getPrice();
    private Float prizePesos; //Hay que pegar con la api https://www.dolarsi.com/api/api.php?type=valoresprincipales
    private User user;
    private Operation operation;
    private LocalDateTime date = LocalDateTime.now();

    public Float getId() {
        return id;
    }

    public void setId(Float id) {
        this.id = id;
    }

    public Cryptoactive getCryptoactive() {
        return cryptoactive;
    }

    public void setCryptoactive(Cryptoactive cryptoactive) {
        this.cryptoactive = cryptoactive;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
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
        return Objects.equals(cryptoactive, that.cryptoactive) && Objects.equals(amount, that.amount) && Objects.equals(prize, that.prize) && Objects.equals(prizePesos, that.prizePesos) && Objects.equals(user, that.user) && operation == that.operation;
    }

    public static TransactionIntentBuilder builder() {
        return new TransactionIntentBuilder();
    }

    public static final class TransactionIntentBuilder {
        private TransactionIntent transaction;

        private TransactionIntentBuilder() {
            transaction = new TransactionIntent();
        }
        public TransactionIntentBuilder id(Float id) {
            transaction.setId(id);
            return this;
        }

        public TransactionIntentBuilder cryptoactive(Cryptoactive cryptoactive) {
            transaction.setCryptoactive(cryptoactive);
            return this;
        }

        public TransactionIntentBuilder amount(Float amount) {
            transaction.setAmount(amount);
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