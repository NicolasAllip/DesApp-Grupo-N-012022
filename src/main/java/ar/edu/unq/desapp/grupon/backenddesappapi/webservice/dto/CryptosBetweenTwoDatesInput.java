package ar.edu.unq.desapp.grupon.backenddesappapi.webservice.dto;

import java.util.Objects;

public class CryptosBetweenTwoDatesInput {
    private String dateStart;
    private String dateEnd;

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CryptosBetweenTwoDatesInput that = (CryptosBetweenTwoDatesInput) o;
        return Objects.equals(dateStart, that.dateStart) && Objects.equals(dateEnd, that.dateEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateStart, dateEnd);
    }

    public static CryptosBetweenTwoDatesInputBuilder builder() {
        return new CryptosBetweenTwoDatesInputBuilder();
    }

    public static final class CryptosBetweenTwoDatesInputBuilder {
        private CryptosBetweenTwoDatesInput cryptosBetweenTwoDatesInput;

        private CryptosBetweenTwoDatesInputBuilder() {
            cryptosBetweenTwoDatesInput = new CryptosBetweenTwoDatesInput();
        }

        public CryptosBetweenTwoDatesInputBuilder dateStart(String dateStart) {
            cryptosBetweenTwoDatesInput.setDateStart(dateStart);
            return this;
        }

        public CryptosBetweenTwoDatesInputBuilder dateEnd(String dateEnd) {
            cryptosBetweenTwoDatesInput.setDateEnd(dateEnd);
            return this;
        }

        public CryptosBetweenTwoDatesInput build() {
            return cryptosBetweenTwoDatesInput;
        }
    }
}