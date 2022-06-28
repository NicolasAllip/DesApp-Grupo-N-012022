package ar.edu.unq.desapp.grupon.backenddesappapi.service.dto;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.CryptoactiveName;

import java.util.List;
import java.util.Objects;

public class CryptoactiveHistoryDTO {
    private CryptoactiveName cryptoactiveName;
    private List<DatedPriceDTO> history;

    public CryptoactiveHistoryDTO(CryptoactiveName cryptoactiveName, List<DatedPriceDTO> history) {
        this.cryptoactiveName = cryptoactiveName;
        this.history = history;
    }

    public CryptoactiveHistoryDTO() {}

    public CryptoactiveName getCryptoactiveName() {
        return cryptoactiveName;
    }

    public void setCryptoactiveName(CryptoactiveName cryptoactiveName) {
        this.cryptoactiveName = cryptoactiveName;
    }

    public List<DatedPriceDTO> getHistory() {
        return history;
    }

    public void setHistory(List<DatedPriceDTO> history) {
        this.history = history;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CryptoactiveHistoryDTO that = (CryptoactiveHistoryDTO) o;
        return cryptoactiveName == that.cryptoactiveName && Objects.equals(history, that.history);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cryptoactiveName, history);
    }

    public static CryptoactiveHistoryDTOBuilder builder() {
        return new CryptoactiveHistoryDTOBuilder();
    }

    public static final class CryptoactiveHistoryDTOBuilder {
        private CryptoactiveHistoryDTO cryptoactiveHistoryDTO;

        private CryptoactiveHistoryDTOBuilder() {
            cryptoactiveHistoryDTO = new CryptoactiveHistoryDTO();
        }

        public CryptoactiveHistoryDTOBuilder cryptoactiveName(CryptoactiveName cryptoactiveName) {
            cryptoactiveHistoryDTO.setCryptoactiveName(cryptoactiveName);
            return this;
        }

        public CryptoactiveHistoryDTOBuilder history(List<DatedPriceDTO> history) {
            cryptoactiveHistoryDTO.setHistory(history);
            return this;
        }

        public CryptoactiveHistoryDTO build() {
            return cryptoactiveHistoryDTO;
        }
    }
}
