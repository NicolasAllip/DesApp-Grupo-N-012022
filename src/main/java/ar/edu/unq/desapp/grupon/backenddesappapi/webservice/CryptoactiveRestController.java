package ar.edu.unq.desapp.grupon.backenddesappapi.webservice;

import java.util.List;

import ar.edu.unq.desapp.grupon.backenddesappapi.webservice.dto.CryptosBetweenTwoDatesInput;
import ar.edu.unq.desapp.grupon.backenddesappapi.webservice.dto.CryptosBetweenTwoDatesOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.Cryptoactive;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.CryptoactiveName;
import ar.edu.unq.desapp.grupon.backenddesappapi.service.ICryptoactiveService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CryptoactiveRestController {
    
    @Autowired
    private ICryptoactiveService cryptoactiveService;

    @GetMapping("/cryptoactives")
    public List<Cryptoactive> index(){
        return cryptoactiveService.findAll();
    }

    @GetMapping("/cryptoactives/{id}")
    public ResponseEntity<?> showCryptoactive(@PathVariable CryptoactiveName name){
        Cryptoactive cryptoactive = cryptoactiveService.findByName(name);
        return new ResponseEntity<>(cryptoactive, HttpStatus.OK);
    }

    /**
    // @PostMapping("/cryptoactives") // TODO: debatamos si tiene sentido hacer esto, vamos a crear cryptoactivos?
    public ResponseEntity<?> create(@Valid @RequestBody CryptoactiveName name) {
        String URL = "https://api1.binance.com/api/v3/ticker/price?symbol=" + name;
        RestTemplate restTemplate = new RestTemplate();
        Map cripto = restTemplate.getForObject(URL, HashMap.class);
        Cryptoactive cryptoactiveN = cryptoactiveService.save(name, Float.parseFloat(cripto.get("price").toString()));
        Map<String, Object> response = new HashMap<>();
        response.put("message", "The cryptoactive has been succefully created");
        response.put("cryptoactive: ", cryptoactiveN);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    } **/

    @GetMapping("/cryptoactives/get-all")
    public List<Cryptoactive> getAllCryptos(){
        return cryptoactiveService.getAllCryptos();
    }

    //@PostMapping("/cryptoactives/update")
    @RequestMapping(value = "/cryptoactives/update", method = RequestMethod.POST)
    public List<Cryptoactive> updateAllCryptos(){
        return cryptoactiveService.updateAllCryptos();
    }

    @GetMapping("/cryptoactives/get-operated-cryptos-in-range")
    public CryptosBetweenTwoDatesOutput getOperatedCryptosInRange(@Valid @RequestBody CryptosBetweenTwoDatesInput cryptosBetweenTwoDatesInput){
        return cryptoactiveService.getOperatedCryptosInRange(cryptosBetweenTwoDatesInput);
    }
}