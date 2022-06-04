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

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.Transaction;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.TransactionIntent;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.User;
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
    public ResponseEntity<?> showUser(@PathVariable Long id){
        Transaction transaction = transactionService.findById(id);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @PostMapping("/transactions")
    public ResponseEntity<?> create(@Valid @RequestBody TransactionIntent transaction, 
                                    @Valid @RequestBody User user) {
                                    //la anotacion RequestBody es necesaria en user?


        Transaction transactionN = transactionService.save(transaction, user);
        Map<String, Object> response = new HashMap<>();

        response.put("message", "The transaction has been succefully created");
        response.put("Transaction: ", transactionN);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/transactions/accept")
    public void accept(Long id) {
        transactionService.acceptTransaction(id);
    }
    
    @PostMapping("/transactions/cancel")
    public void cancel(Long id) {
        transactionService.cancel(id);
    }
}
