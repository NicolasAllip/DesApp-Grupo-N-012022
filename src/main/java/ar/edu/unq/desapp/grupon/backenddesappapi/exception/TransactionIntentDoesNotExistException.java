package ar.edu.unq.desapp.grupon.backenddesappapi.exception;

public class TransactionIntentDoesNotExistException extends RuntimeException {
    Long transactionId;

    public TransactionIntentDoesNotExistException(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }
}
