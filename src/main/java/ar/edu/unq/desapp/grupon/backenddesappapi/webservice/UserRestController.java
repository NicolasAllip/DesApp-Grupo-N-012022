package ar.edu.unq.desapp.grupon.backenddesappapi.webservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.Role;
import ar.edu.unq.desapp.grupon.backenddesappapi.service.dto.NewUserDTO;
import ar.edu.unq.desapp.grupon.backenddesappapi.service.dto.UserCredentialsDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
//import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import ar.edu.unq.desapp.grupon.backenddesappapi.Mapper.UserViewMapper;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.User;
import ar.edu.unq.desapp.grupon.backenddesappapi.service.IUserService;
import java.time.Instant;
import org.springframework.security.core.GrantedAuthority;
import static java.util.stream.Collectors.joining;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import lombok.RequiredArgsConstructor;

import javax.annotation.security.RolesAllowed;

import ar.edu.unq.desapp.grupon.backenddesappapi.service.dto.NewUserDTO;
import org.springframework.http.HttpStatus;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.User;
import ar.edu.unq.desapp.grupon.backenddesappapi.service.IUserService;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserRestController {

    //@Autowired
    private final AuthenticationManager authenticationManager;
    //@Autowired
    private final JwtEncoder jwtEncoder;
    //@Autowired
    //private final UserViewMapper userViewMapper;
    //@Autowired
    private final IUserService userService;

    @Lazy
    public UserRestController(AuthenticationManager authenticationManager, JwtEncoder jwtEncoder, /*UserViewMapper userViewMapper,*/ IUserService userService) {
      this.authenticationManager = authenticationManager;
      this.jwtEncoder = jwtEncoder;
      //this.userViewMapper = userViewMapper;
      this.userService = userService;
    }

    /*@Bean
    public UserViewMapper getUserViewMapper(UserViewMapper userViewMapper) {
        return userViewMapper;
    }*/

    @GetMapping("/users")
    public List<User> index(){
        return userService.findAll();
    }

    /*@GetMapping("/users/admin")
    @RolesAllowed(Role.ADMIN)
    public String esAdmin() {
        return "es admin";
    }*/


    @GetMapping("/users/{id}")
    public ResponseEntity<?> showUser(@PathVariable Long id){
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //@PostMapping("/users/register")
    @RequestMapping(value = "/users/register", method = RequestMethod.POST)
    public ResponseEntity<?> create(@Valid @RequestBody NewUserDTO newUserDTO){

        User userN = userService.save(newUserDTO);
        Map<String, Object> response = new HashMap<>();

        response.put("message", "The user has been succefully created");
        response.put("User: ", userN);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //@PostMapping("/users/login")
    @RequestMapping(value = "/users/login", method = RequestMethod.POST)
    public ResponseEntity<User> login(@RequestBody @Valid UserCredentialsDTO userCredentialsDTO) {
        try {
          Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(userCredentialsDTO.getEmail(), userCredentialsDTO.getPassword()));
    
          User user = (User) authentication.getPrincipal();
    
          Instant now = Instant.now();
          long expiry = 36000L;
    
          String scope = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(joining(" "));
    
            JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("example.io")
            .issuedAt(now)
            .expiresAt(now.plusSeconds(expiry))
            .subject(String.format("%s,%s", user.getId(), user.getEmail()))
            .claim("roles", scope)
            .build();
    
          String token = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    
          return ResponseEntity.ok()
            .header(HttpHeaders.AUTHORIZATION, token)
            .body(user);
        } catch (BadCredentialsException ex) {
          return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
