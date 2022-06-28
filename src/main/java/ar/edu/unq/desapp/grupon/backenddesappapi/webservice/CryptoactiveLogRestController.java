package ar.edu.unq.desapp.grupon.backenddesappapi.webservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unq.desapp.grupon.backenddesappapi.service.dto.CryptoactiveHistoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.CryptoactiveLog;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.CryptoactiveName;
import ar.edu.unq.desapp.grupon.backenddesappapi.service.ICryptoactiveLogService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CryptoactiveLogRestController {
    
    @Autowired
    private ICryptoactiveLogService cryptoactiveLogService;

    @GetMapping("/cryptoactive-log")
    public List<CryptoactiveLog> index(){
        return cryptoactiveLogService.findAll();
    }

    @GetMapping("/cryptoactive-log/{id}")
    public ResponseEntity<?> showCryptoactive(@PathVariable Long id){
        CryptoactiveLog cryptoactive = cryptoactiveLogService.findById(id);
        return new ResponseEntity<>(cryptoactive, HttpStatus.OK);
    }

    @PostMapping("/cryptoactive-log")
    public ResponseEntity<?> create(@Valid @RequestBody CryptoactiveName name) {

        String URL = "https://api1.binance.com/api/v3/ticker/price?symbol=" + name;

        RestTemplate restTemplate = new RestTemplate();

        Map cripto = restTemplate.getForObject(URL, HashMap.class);

        CryptoactiveLog cryptoactiveN = cryptoactiveLogService.save(name, Float.parseFloat(cripto.get("price").toString()));
        Map<String, Object> response = new HashMap<>();

        response.put("message", "The cryptoactive has been succefully created");
        response.put("cryptoactive: ", cryptoactiveN);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/cryptoactive-log/get-all")
    public List<CryptoactiveLog> getAllCryptos(){
        return cryptoactiveLogService.getAllCryptos();
    }

    @GetMapping("/cryptoactive-log/get-24hr-history/{cryptoName}")
    public CryptoactiveHistoryDTO getCryptoactive24HourHistory(@PathVariable String cryptoName){
        return cryptoactiveLogService.getCryptoactive24HourHistory(CryptoactiveName.valueOf(cryptoName));
    }
}