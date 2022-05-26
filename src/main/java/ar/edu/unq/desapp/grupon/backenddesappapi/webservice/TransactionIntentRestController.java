package ar.edu.unq.desapp.grupon.backenddesappapi.webservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.Cryptoactive;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.TransactionIntent;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.User;
import ar.edu.unq.desapp.grupon.backenddesappapi.service.ITransactionIntentService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class TransactionIntentRestController {
    
    @Autowired
    private ITransactionIntentService transactionService;

    @GetMapping("/transactionIntents")
    public List<TransactionIntent> index(){
        return transactionService.findAll();
    }

    @GetMapping("/transactionIntents/{id}")
    public ResponseEntity<?> showTransaction(@PathVariable Long id){
        TransactionIntent transaction = transactionService.findById(id);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @PostMapping("/transactionIntents")
    public ResponseEntity<?> create(@Valid @RequestBody Cryptoactive cryptoactive, 
                                    @Valid @RequestBody Float amount, 
                                    @Valid @RequestBody User user) {
                                    //la anotacion RequestBody es necesaria en user?

        TransactionIntent transactionN = transactionService.save(cryptoactive, amount, user);
        Map<String, Object> response = new HashMap<>();

        response.put("message", "The transaction intent has been succefully created");
        response.put("transactionIntent: ", transactionN);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}