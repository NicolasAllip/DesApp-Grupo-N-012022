package ar.edu.unq.desapp.grupon.backenddesappapi.webservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.*;
import ar.edu.unq.desapp.grupon.backenddesappapi.webservice.dto.TransactionIntentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.edu.unq.desapp.grupon.backenddesappapi.service.dto.ActiveTransactionDTO;
import ar.edu.unq.desapp.grupon.backenddesappapi.service.ITransactionIntentService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class TransactionIntentRestController {
    
    @Autowired
    private ITransactionIntentService transactionService;

    @GetMapping("/transactionIntents")
    public List<ActiveTransactionDTO> index(){
        return transactionService.findAll();
    }

    @GetMapping("/transactionIntents/{id}")
    public ResponseEntity<?> showTransaction(@PathVariable Long id){
        TransactionIntent transaction = transactionService.findById(id);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    //@PostMapping("/transactionIntents")
    @RequestMapping(value = "/transactionIntents", method = RequestMethod.POST)
    public ResponseEntity<?> create(@Valid @RequestBody TransactionIntentDTO transactionIntentDTO) {

        TransactionIntent transactionN = transactionService.save(transactionIntentDTO);
        Map<String, Object> response = new HashMap<>();

        response.put("message", "The transaction intent has been succefully created");
        response.put("transactionIntent: ", transactionN);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}