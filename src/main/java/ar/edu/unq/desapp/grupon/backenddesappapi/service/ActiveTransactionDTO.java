package ar.edu.unq.desapp.grupon.backenddesappapi.service;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.Cryptoactive;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.User;
//import net.bytebuddy.dynamic.TypeResolutionStrategy.Active;

import java.time.LocalDateTime;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.CryptoactiveLog;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.TransactionIntent;

public class ActiveTransactionDTO {

    private Cryptoactive cryptoactive;
    private LocalDateTime date;
    private Float amount;
    private Float prize;
    private Float prizePesos;
    private User user;
    private Integer operations = user.getOperationAmount();
    private Float reputation = user.getReputation();

    public ActiveTransactionDTO(TransactionIntent transaction) {
        cryptoactive = transaction.getCryptoactive();
        date = transaction.getDate();
        amount = transaction.getAmount();
        prize = transaction.getPrize();
        prizePesos = transaction.getPrizePesos();
        user = transaction.getUser();

    }

    public Cryptoactive getCryptoactive() {
        return cryptoactive;
    }
    public void setCryptoactive(Cryptoactive cryptoactive) {
        this.cryptoactive = cryptoactive;
    }

    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
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
        return this.user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Integer getOperations() {
        return operations;
    }
    public void setOperations(Integer operations) {
        this.operations = operations;
    }

    public Float getReputation() {
        return reputation;
    }
    public void setReputation(Float reputation) {
        this.reputation = reputation;
    }
}