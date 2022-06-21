package ar.edu.unq.desapp.grupon.backenddesappapi.restclient;

import ar.edu.unq.desapp.grupon.backenddesappapi.restclient.dto.DolarsiDolarValueContainerDTO;
import ar.edu.unq.desapp.grupon.backenddesappapi.restclient.dto.DolarsiDolarValueDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
public class GetDolarConversionValueRestclient implements IGetDolarConversionValueRestclient {
    @Override
    public DolarsiDolarValueDTO getOfficialDolarValue() {
        RestTemplate restTemplate = new RestTemplate();
        DolarsiDolarValueContainerDTO[] allValues = restTemplate.getForObject("https://www.dolarsi.com/api/api.php?type=valoresprincipales" , DolarsiDolarValueContainerDTO[].class);
        return Arrays.stream(allValues)
                .filter(dolarsiDolarValueContainerDTO -> dolarsiDolarValueContainerDTO.getCasa().getNombre() == "Dolar Oficial")
                .findFirst().get().getCasa();
    }
}
