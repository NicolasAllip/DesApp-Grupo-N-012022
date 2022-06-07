package ar.edu.unq.desapp.grupon.backenddesappapi.exception;

public class TransactionIntentDoesNotExistException extends RuntimeException {
    Long transactionId;

    public TransactionIntentDoesNotExistException(Long transactionId) {
        this.userId = userId;
    }

    public Long getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.TransactionId = TransactionId;
    }
}
