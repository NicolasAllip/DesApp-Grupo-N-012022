package ar.edu.unq.desapp.grupon.backenddesappapi.service.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class DatedPriceDTO {
    private Float price;
    private LocalDateTime date;

    public DatedPriceDTO(Float price, LocalDateTime date) {
        this.price = price;
        this.date = date;
    }

    public DatedPriceDTO() {}

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatedPriceDTO that = (DatedPriceDTO) o;
        return Objects.equals(price, that.price) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, date);
    }

    public static DatedPriceDTOBuilder builder() {
        return new DatedPriceDTOBuilder();
    }

    public static final class DatedPriceDTOBuilder {
        private DatedPriceDTO datedPriceDTO;

        private DatedPriceDTOBuilder() {
            datedPriceDTO = new DatedPriceDTO();
        }

        public DatedPriceDTOBuilder price(Float price) {
            datedPriceDTO.setPrice(price);
            return this;
        }

        public DatedPriceDTOBuilder date(LocalDateTime date) {
            datedPriceDTO.setDate(date);
            return this;
        }

        public DatedPriceDTO build() {
            return datedPriceDTO;
        }
    }
}
