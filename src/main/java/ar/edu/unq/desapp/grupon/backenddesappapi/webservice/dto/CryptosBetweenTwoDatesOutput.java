package ar.edu.unq.desapp.grupon.backenddesappapi.webservice.dto;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.CryptoactiveName;

import java.util.Map;
import java.util.Objects;

public class CryptosBetweenTwoDatesOutput {
    CryptosBetweenTwoDatesInput range;
    Map<CryptoactiveName, Float> operatedCryptoactives;

    public CryptosBetweenTwoDatesInput getRange() {
        return range;
    }

    public void setRange(CryptosBetweenTwoDatesInput range) {
        this.range = range;
    }

    public Map<CryptoactiveName, Float> getOperatedCryptoactives() {
        return operatedCryptoactives;
    }

    public void setOperatedCryptoactives(Map<CryptoactiveName, Float> operatedCryptoactives) {
        this.operatedCryptoactives = operatedCryptoactives;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CryptosBetweenTwoDatesOutput that = (CryptosBetweenTwoDatesOutput) o;
        return Objects.equals(range, that.range) && Objects.equals(operatedCryptoactives, that.operatedCryptoactives);
    }

    @Override
    public int hashCode() {
        return Objects.hash(range, operatedCryptoactives);
    }

    public static CryptosBetweenTwoDatesOutputBuilder builder() {
        return new CryptosBetweenTwoDatesOutputBuilder();
    }

    public static final class CryptosBetweenTwoDatesOutputBuilder {
        private CryptosBetweenTwoDatesOutput cryptosBetweenTwoDatesOutput;

        private CryptosBetweenTwoDatesOutputBuilder() {
            cryptosBetweenTwoDatesOutput = new CryptosBetweenTwoDatesOutput();
        }

        public CryptosBetweenTwoDatesOutputBuilder range(CryptosBetweenTwoDatesInput range) {
            cryptosBetweenTwoDatesOutput.setRange(range);
            return this;
        }

        public CryptosBetweenTwoDatesOutputBuilder operatedCryptoactives(Map<CryptoactiveName, Float> operatedCryptoactives) {
            cryptosBetweenTwoDatesOutput.setOperatedCryptoactives(operatedCryptoactives);
            return this;
        }

        public CryptosBetweenTwoDatesOutput build() {
            return cryptosBetweenTwoDatesOutput;
        }
    }
}