package ar.edu.unq.desapp.grupon.backenddesappapi.webservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unq.desapp.grupon.backenddesappapi.webservice.dto.CreateTransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.Transaction;
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

    @GetMapping("/transactions/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Transaction transaction = transactionService.findById(id);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    //@PostMapping("/transactions")
    @RequestMapping(value = "/transactions", method = RequestMethod.POST)
    public ResponseEntity<?> create(@Valid @RequestBody CreateTransactionDTO createTransactionDTO) {

        Transaction transactionN = transactionService.save(createTransactionDTO);
        Map<String, Object> response = new HashMap<>();

        response.put("message", "The transaction has been succefully created");
        response.put("Transaction: ", transactionN);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //@PostMapping("/transactions/accept/{id}")
    @RequestMapping(value = "/transactions/accept/{id}", method = RequestMethod.POST)
    public void accept(@PathVariable Long id) {
        transactionService.acceptTransaction(id);
    }
    
    //@PostMapping("/transactions/cancel/{id}")
    @RequestMapping(value = "/transactions/cancel/{id}", method = RequestMethod.POST)
    public void cancel(@PathVariable Long id) {
        transactionService.cancel(id);
    }

    @GetMapping("/transactions/get-active-transactions")
    public List<Transaction> getAllActive() { return transactionService.findActiveTransactions(); }
}