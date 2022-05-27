package ar.edu.unq.desapp.grupon.backenddesappapi.webservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
    public ResponseEntity<?> showCryptoactive(@PathVariable Long id){
        Cryptoactive cryptoactive = cryptoactiveService.findById(id);
        return new ResponseEntity<>(cryptoactive, HttpStatus.OK);
    }

    @PostMapping("/cryptoactives")
    public ResponseEntity<?> create(@Valid @RequestBody CryptoactiveName name) {

        String URL = "https://api1.binance.com/api/v3/ticker/price?symbol=" + name;

        RestTemplate restTemplate = new RestTemplate();

        Map cripto = restTemplate.getForObject(URL, HashMap.class);

        List<Cryptoactive> allCriptos = cryptoactiveService.findAll();
        boolean flag = false;
        Integer i = 0;
        Long id = 0L;
        Cryptoactive cryptoactiveN;
        while(!flag || i >= allCriptos.size()) {
            flag = flag || (allCriptos.get(i).getName() == name);
            i += 1;
            id = allCriptos.get(i).getId();
        }
        if(flag) {
            cryptoactiveN = cryptoactiveService.update(id, Float.parseFloat(cripto.get("price").toString()));
        } else {
            cryptoactiveN = cryptoactiveService.save(name, Float.parseFloat(cripto.get("price").toString()));
        }
        Map<String, Object> response = new HashMap<>();

        response.put("message", "The transaction intent has been succefully created");
        response.put("transactionIntent: ", cryptoactiveN);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}