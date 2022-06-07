package ar.edu.unq.desapp.grupon.backenddesappapi.webservice.dto;

import java.util.Objects;

public class CryptosBetweenTwoDatesInput {
    private String dateFrom;
    private String dateTo;

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CryptosBetweenTwoDatesInput that = (CryptosBetweenTwoDatesInput) o;
        return Objects.equals(dateFrom, that.dateFrom) && Objects.equals(dateTo, that.dateTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateFrom, dateTo);
    }

    public static CryptosBetweenTwoDatesInputBuilder builder() {
        return new CryptosBetweenTwoDatesInputBuilder();
    }

    public static final class CryptosBetweenTwoDatesInputBuilder {
        private CryptosBetweenTwoDatesInput cryptosBetweenTwoDatesInput;

        private CryptosBetweenTwoDatesInputBuilder() {
            cryptosBetweenTwoDatesInput = new CryptosBetweenTwoDatesInput();
        }

        public CryptosBetweenTwoDatesInputBuilder dateFrom(String dateFrom) {
            cryptosBetweenTwoDatesInput.setDateFrom(dateFrom);
            return this;
        }

        public CryptosBetweenTwoDatesInputBuilder dateTo(String dateTo) {
            cryptosBetweenTwoDatesInput.setDateTo(dateTo);
            return this;
        }

        public CryptosBetweenTwoDatesInput build() {
            return cryptosBetweenTwoDatesInput;
        }
    }
}
