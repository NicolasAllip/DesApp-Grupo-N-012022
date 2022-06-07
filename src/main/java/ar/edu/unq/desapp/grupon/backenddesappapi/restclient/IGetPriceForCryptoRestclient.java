package ar.edu.unq.desapp.grupon.backenddesappapi.restclient;

import ar.edu.unq.desapp.grupon.backenddesappapi.restclient.dto.BinanceCryptoDTO;

import java.util.List;

public interface IGetPriceForCryptoRestclient {
    BinanceCryptoDTO getCryptoPrice(String cryptoSymbol);
    BinanceCryptoDTO[] getBatchCryptoPrice(List<String> cryptoSymbolList);
}
