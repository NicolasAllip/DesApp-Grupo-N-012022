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

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.Transaction;
import ar.edu.unq.desapp.grupon.backenddesappapi.service.NewUserDTO;
import ar.edu.unq.desapp.grupon.backenddesappapi.service.ITransactionService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class TransactionRestController {
    
    @Autowired
    private ITransactionService transactionService;

    @GetMapping("/transactions")
    public List<Transaction> index(){
        return transactionService.findAll();
    }

    /*@GetMapping("/transactions/{id}")
    public ResponseEntity<?> showUser(@PathVariable Long id){
        Transaction user = transactionService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/transactions")
    public ResponseEntity<?> create(@Valid @RequestBody NewUserDTO newUserDTO){

        Transaction userN = transactionService.save(newUserDTO);
        Map<String, Object> response = new HashMap<>();

        response.put("message", "The user has been succefully created");
        response.put("User: ", userN);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }*/
}