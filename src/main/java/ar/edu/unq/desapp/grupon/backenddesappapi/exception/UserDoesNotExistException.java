package ar.edu.unq.desapp.grupon.backenddesappapi.exception;

public class UserDoesNotExistException extends RuntimeException {
    Long userId;

    public UserDoesNotExistException(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
