package ar.edu.unq.desapp.grupon.backenddesappapi.webservice.dto;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.CryptoactiveName;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.Operation;

import java.util.Objects;

public class TransactionIntentDTO {
    Cryptoactive cryptoactiveName;
    Float amount;
    Float offer;
    Long userId;
    Operation operation;

    public CryptoactiveName getCryptoactiveName() {
        return cryptoactiveName;
    }

    public void setCryptoactiveName(CryptoactiveName cryptoactiveName) {
        this.cryptoactiveName = cryptoactiveName;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionIntentDTO that = (TransactionIntentDTO) o;
        return cryptoactiveName == that.cryptoactiveName && Objects.equals(amount, that.amount) && Objects.equals(offer, that.offer) && Objects.equals(userId, that.userId) && operation == that.operation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cryptoactiveName, amount, offer, userId, operation);
    }

    public static TransactionIntentDTOBuilder builder() {
        return new TransactionIntentDTOBuilder();
    }

    public static final class TransactionIntentDTOBuilder {
        private TransactionIntentDTO transactionIntentDTO;

        public TransactionIntentDTOBuilder cryptoactiveName(CryptoactiveName cryptoactiveName) {
            transactionIntentDTO.setCryptoactiveName(cryptoactiveName);
            return this;
        }

        public TransactionIntentDTOBuilder amount(Float amount) {
            transactionIntentDTO.setAmount(amount);
            return this;
        }

        public TransactionIntentDTOBuilder offer(Float offer) {
            transactionIntentDTO.setOffer(offer);
            return this;
        }

        public TransactionIntentDTOBuilder userId(Long userId) {
            transactionIntentDTO.setUserId(userId);
            return this;
        }

        public TransactionIntentDTOBuilder operation(Operation operation) {
            transactionIntentDTO.setOperation(operation);
            return this;
        }

        public TransactionIntentDTO build() {
            return transactionIntentDTO;
        }
    }
}