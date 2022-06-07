package ar.edu.unq.desapp.grupon.backenddesappapi.exception;

public class TransactionDoesNotExistException extends RuntimeException {
    Long transactionId;

    public TransactionDoesNotExistException(Long transactionId) {
        this.userId = userId;
    }

    public Long getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.TransactionId = TransactionId;
    }
}
