package ar.edu.unq.desapp.grupon.backenddesappapi.service;

import java.util.List;


//import ar.edu.unq.desapp.grupon.backenddesappapi.service.dto.NewUserDTO;
import ar.edu.unq.desapp.grupon.backenddesappapi.exception.UserDoesNotExistException;
import ar.edu.unq.desapp.grupon.backenddesappapi.service.dto.NewUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.User;
import ar.edu.unq.desapp.grupon.backenddesappapi.persistence.IUserDao;

@Service
public class UserService implements IUserService {
    
    @Autowired
    private IUserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //@Autowired
    //private UsuarioDetailsService userDetails;
    
    @Transactional(readOnly = true)
    @Override
    public List<User> findAll(){
        return (List<User>) userDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(Long id) {
        User user = userDao.findById(id).orElse(null);

        if (user == null) {
            throw new UserDoesNotExistException(id);
        }

        return user;
    }

    @Transactional
    @Override
    public User save(NewUserDTO newUserDTO) {
        User user = new User(
                newUserDTO.getName(),
                newUserDTO.getSurname(),
                newUserDTO.getEmail(),
                newUserDTO.getAddress(),
                passwordEncoder.encode(newUserDTO.getPassword()),
                newUserDTO.getCvu(),
                0.0f,
                newUserDTO.getWalletAddress()
        );
        //userDetails.addUser(newUserDTO.getName());

        return userDao.save(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userDao.deleteById(id);
    }
}
