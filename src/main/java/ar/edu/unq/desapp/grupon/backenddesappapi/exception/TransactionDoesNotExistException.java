package ar.edu.unq.desapp.grupon.backenddesappapi.exception;

public class TransactionDoesNotExistException extends RuntimeException {
    Long transactionId;

    public TransactionDoesNotExistException(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }
}