package ar.edu.unq.desapp.grupon.backenddesappapi.Model;

import java.util.Objects;

public enum Operation {
    COMPRA,
    VENTA
}

public class Transaction() {
    private Cryptoactive cryptoactive;
    private Float amount;
    private Float prize;
    private Float prizePesos;
    private User user;
    private Operation operation;

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
}
