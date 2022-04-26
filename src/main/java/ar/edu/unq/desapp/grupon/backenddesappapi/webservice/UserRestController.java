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
        
        User user = null;
        Map<String, Object> response = new HashMap<>();
        
        try {
            user = userService.findById(id);
            
        } catch (DataAccessException e) {
            response.put("message", "Error querying bd");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if (user == null){
            response.put("message", "User ID: ".concat(id.toString().concat(" not exist in the bd")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody NewUserDTO newUserDTO){

        User userN = null;
        Map<String, Object> response = new HashMap<>();

        try {
            userN = userService.save(newUserDTO);
        } catch (DataAccessException e) {
            response.put("message", "Error to save the user in the bd");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "The user has been created succefully");
        response.put("User: ", userN);
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED );
    }
}
