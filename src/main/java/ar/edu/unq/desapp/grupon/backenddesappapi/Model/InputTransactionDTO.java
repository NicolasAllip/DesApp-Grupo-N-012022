package ar.edu.unq.desapp.grupon.backenddesappapi.Model;

public class InputTransactionDTO {

    private String cryptoactive;
    private Float amount;
    private Float prize;
    private Float prizePesos;
    private User user;
    private Operation operation;
    private Float reputation = user.getReputation();
    private Integer sendAddress;

    if(transaction.getOperation() == Operation.VENTA) {
        transaction.setSendAddress(user.getCvu())
    } else {
        transaction.setSendAddress(user.getSendAddress())
    }

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

    public String getUser() {
        return userNam;
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
}
