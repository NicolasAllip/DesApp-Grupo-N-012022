package ar.edu.unq.desapp.grupon.backenddesappapi.Model;

import Transaction.Operation;
import java.util.Objects;
import java.util.DateTime;


public class InputTransactionDTO() {

    private String cryptoactive;
    private Float amount;
    private Float prize;
    private Float prizePesos;
    private String userName;
    private Operation operation;
    private Float reputation;
    private Integer sendAddress;

    public String getCryptoactive() {
        return cryptoactive;
    }
    public void setCryptoactive(String cryptoactive) {
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

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
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

    """
    public void acceptTransaction(){}
    public void cancel(){}
    """
}
