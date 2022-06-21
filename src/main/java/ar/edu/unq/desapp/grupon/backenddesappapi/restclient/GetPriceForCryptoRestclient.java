package ar.edu.unq.desapp.grupon.backenddesappapi.restclient;

import ar.edu.unq.desapp.grupon.backenddesappapi.restclient.dto.BinanceCryptoDTO;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

public class GetPriceForCryptoRestclient implements IGetPriceForCryptoRestclient {

    @Override
    public BinanceCryptoDTO getCryptoPrice(String cryptoSymbol) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("https://api1.binance.com/api/v3/ticker/price?symbol=" + cryptoSymbol, BinanceCryptoDTO.class);
    }

    @Override
    public BinanceCryptoDTO[] getBatchCryptoPrice(List<String> cryptoSymbolList) {
        RestTemplate restTemplate = new RestTemplate();
        String cryptoSymbols = cryptoSymbolList.stream()
                .map(n -> '\"' + n + '\"')
                .collect(Collectors.joining(",", "[", "]"));

        return restTemplate.getForObject("https://api1.binance.com/api/v3/ticker/price?symbols=" + cryptoSymbols, BinanceCryptoDTO[].class);
    }
}