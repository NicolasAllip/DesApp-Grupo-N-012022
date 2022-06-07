package ar.edu.unq.desapp.grupon.backenddesappapi.restclient.dto;

import java.util.Objects;

public class BinanceCryptoDTO {

    private String symbol;
    private String price;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinanceCryptoDTO that = (BinanceCryptoDTO) o;
        return Objects.equals(symbol, that.symbol) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, price);
    }

    public static BinanceCryptoDTOBuilder binance() {
        return new BinanceCryptoDTOBuilder();
    }

    public static final class BinanceCryptoDTOBuilder {
        private BinanceCryptoDTO binanceCryptoDTO;

        public static BinanceCryptoDTOBuilder aBinanceCryptoDTO() {
            return new BinanceCryptoDTOBuilder();
        }

        public BinanceCryptoDTOBuilder symbol(String symbol) {
            binanceCryptoDTO.setSymbol(symbol);
            return this;
        }

        public BinanceCryptoDTOBuilder price(String price) {
            binanceCryptoDTO.setPrice(price);
            return this;
        }

        public BinanceCryptoDTO build() {
            return binanceCryptoDTO;
        }
    }
}
