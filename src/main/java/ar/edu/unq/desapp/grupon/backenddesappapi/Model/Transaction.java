package ar.edu.unq.desapp.grupon.backenddesappapi.Model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Float id;
    @Column(nullable = false)
    private TransasctionIntent transaction;
    private Cryptoactive cryptoactive = transaction.getCryptoactive();
    private Float amount = transaction.getAmount();
    private Float prize = transaction.getPrize();
    private Float prizePesos = transaction.getPrizePesos();
    private User user;
    private Operation operation = transaction.getOperation();
    //private State state;

    public TransasctionIntent getTransaction() {
        return transaction;
    }

    public void setTransaction(TransasctionIntent transaction) {
        this.transaction = transaction;
    }

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

    public Float getReputation() {
        return reputation;
    }
    public void setReputation(Float reputation) {
        this.reputation = reputation;
    }

    public Integer getSendAddress() {
        return sendAddress;
    }
    public void setSendAddress(Integer sendAddress) {
        this.sendAddress = sendAddress;
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
        Transaction that = (Transaction) o;
        return Objects.equals(cryptoactive, that.cryptoactive) && Objects.equals(amount, that.amount) && Objects.equals(prize, that.prize) && Objects.equals(prizePesos, that.prizePesos) && Objects.equals(user, that.user) && operation == that.operation;
    }

    public static TransactionBuilder builder() {
        return new TransactionBuilder();
    }

    public static final class TransactionBuilder {
        private Transaction transaction;

        private TransactionBuilder() {
            transaction = new Transaction();
        }
        public TransactionBuilder id(Float id) {
            transaction.setId(id);
            return this;
        }

        public TransactionBuilder transaction(TransasctionIntent transaction) {
            transaction.setTransaction(transaction);
            return this;
        }

        public TransactionBuilder cryptoactive(Cryptoactive cryptoactive) {
            transaction.setCryptoactive(cryptoactive);
            return this;
        }

        public TransactionBuilder amount(Float amount) {
            transaction.setAmount(amount);
            return this;
        }

        public TransactionBuilder prize(Float prize) {
            transaction.setPrize(prize);
            return this;
        }

        public TransactionBuilder prizePesos(Float prizePesos) {
            transaction.setPrizePesos(prizePesos);
            return this;
        }

        public TransactionBuilder user(User user) {
            transaction.setUser(user);
            return this;
        }

        public TransactionBuilder operation(Operation operation) {
            transaction.setOperation(operation);
            return this;
        }

        public TransactionBuilder reputation(Operation operation) {
            transaction.setReputation(operation);
            return this;
        }

        public TransactionBuilder sendAddress(Operation operation) {
            transaction.setSendAddress(operation);
            return this;
        }

        public TransactionBuilder date(Operation operation) {
            transaction.setDate(operation);
            return this;
        }

        public Transaction build() {
            return transaction;
        }
    }
}
