package ar.edu.unq.desapp.grupon.backenddesappapi.webservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.NewUserDTO;
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

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.User;
import ar.edu.unq.desapp.grupon.backenddesappapi.service.IUserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserRestController {
    
    @Autowired
    private IUserService userService;

    @GetMapping("/users")
    public List<User> index(){
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> showUser(@PathVariable Long id){
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<?> create(@Valid @RequestBody NewUserDTO newUserDTO){

        User userN = userService.save(newUserDTO);
        Map<String, Object> response = new HashMap<>();

        response.put("mensaje", "The user has been created succefully");
        response.put("User: ", userN);
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED );
    }
}
