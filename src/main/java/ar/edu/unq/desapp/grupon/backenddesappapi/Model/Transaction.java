package ar.edu.unq.desapp.grupon.backenddesappapi.Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name="transactions")
public class Transaction implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private TransactionIntent transactionIntent;
    @ManyToOne
    private Cryptoactive cryptoactive;
    private Float amount;
    private Float offer;
    private Float prize;
    private Float prizePesos;
    @ManyToOne
    private User user;
    private Operation operation;
    private TransactionState state;
    private String sendAddress;
    private LocalDateTime dateCreated;
    private LocalDateTime lastUpdated;

    public Transaction(TransactionIntent transactionIntent, User user, TransactionState state, LocalDateTime dateCreated, LocalDateTime lastUpdated) {
        this.transactionIntent = transactionIntent;
        this.cryptoactive = transactionIntent.getCryptoactive();
        this.amount = transactionIntent.getAmount();
        this.offer = transactionIntent.getOffer();
        this.prize = transactionIntent.getPrize();
        this.prizePesos = transactionIntent.getPrizePesos();
        this.user = user;
        this.operation = transactionIntent.getOperation();
        this.state = state;
        determineAddress(user, transactionIntent.getOperation());
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
    }

    public Transaction() {}

    private void determineAddress(User user, Operation operation) {
        switch(operation) {
            case SELL:
                setSendAddress(user.getCvu());
                break;
            case BUY:
                setSendAddress(user.getWalletAddress());
                break;
        }
    }

    public TransactionIntent getTransactionIntent() {
        return transactionIntent;
    }

    public void setTransactionIntent(TransactionIntent transactionIntent) {
        this.transactionIntent = transactionIntent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public TransactionState getState() {
        return state;
    }

    public void setState(TransactionState state) {
        this.state = state;
    }
    
    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) && Objects.equals(transactionIntent, that.transactionIntent) && Objects.equals(cryptoactive, that.cryptoactive) && Objects.equals(amount, that.amount) && Objects.equals(offer, that.offer) && Objects.equals(prize, that.prize) && Objects.equals(prizePesos, that.prizePesos) && Objects.equals(user, that.user) && operation == that.operation && state == that.state && Objects.equals(sendAddress, that.sendAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transactionIntent, cryptoactive, amount, offer, prize, prizePesos, user, operation, state, sendAddress);
    }

    public static TransactionBuilder builder() {
        return new TransactionBuilder();
    }

    public static final class TransactionBuilder {
        private Transaction transaction;

        private TransactionBuilder() {
            transaction = new Transaction();
        }

        public TransactionBuilder id(Long id) {
            transaction.setId(id);
            return this;
        }

        public TransactionBuilder transaction(TransactionIntent transactionIntent) {
            transaction.setTransactionIntent(transactionIntent);
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

        public TransactionBuilder offer(Float offer) {
            transaction.setOffer(offer);
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

        public TransactionBuilder state(TransactionState state) {
            transaction.setState(state);
            return this;
        }

        public TransactionBuilder sendAddress(String sendAddress) {
            transaction.setSendAddress(sendAddress);
            return this;
        }

        public TransactionBuilder dateCreated(LocalDateTime dateCreated) {
            transaction.setDateCreated(dateCreated);
            return this;
        }

        public TransactionBuilder lastUpdated(LocalDateTime lastUpdated) {
            transaction.setLastUpdated(lastUpdated);
            return this;
        }

        public Transaction build() {
            return transaction;
        }
    }
}