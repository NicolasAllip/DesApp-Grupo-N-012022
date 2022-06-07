package ar.edu.unq.desapp.grupon.backenddesappapi.webservice.dto;

import java.util.Objects;

public class CreateTransactionDTO {
    private Long transactionIntentId;
    private Long userId;

    public Long getTransactionIntentId() {
        return transactionIntentId;
    }

    public void setTransactionIntentId(Long transactionIntentId) {
        this.transactionIntentId = transactionIntentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateTransactionDTO that = (CreateTransactionDTO) o;
        return Objects.equals(transactionIntentId, that.transactionIntentId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionIntentId, userId);
    }

    public static CreateTransactionDTOBuilder builder() {
        return new CreateTransactionDTOBuilder();
    }

    public static final class CreateTransactionDTOBuilder {
        private CreateTransactionDTO createTransactionDTO;

        private CreateTransactionDTOBuilder() {
            createTransactionDTO = new CreateTransactionDTO();
        }

        public CreateTransactionDTOBuilder transactionIntentId(Long transactionIntentId) {
            createTransactionDTO.setTransactionIntentId(transactionIntentId);
            return this;
        }

        public CreateTransactionDTOBuilder userId(Long userId) {
            createTransactionDTO.setUserId(userId);
            return this;
        }

        public CreateTransactionDTO build() {
            return createTransactionDTO;
        }
    }
}
