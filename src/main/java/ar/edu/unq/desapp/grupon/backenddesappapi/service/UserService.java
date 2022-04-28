package ar.edu.unq.desapp.grupon.backenddesappapi.service;

import java.util.List;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.NewUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.User;
import ar.edu.unq.desapp.grupon.backenddesappapi.persistence.IUserDao;

@Service
public class UserService implements IUserService {
    
    @Autowired
    private IUserDao userDao;
    
    @Transactional(readOnly = true)
    @Override
    public List<User> findAll(){
        return (List<User>) userDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(Long id) {
        return userDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public User save(NewUserDTO newUserDTO) {
        User user = User.builder()
                .name(newUserDTO.getName())
                .surname(newUserDTO.getSurname())
                .email(newUserDTO.getEmail())
                .address(newUserDTO.getAddress())
                .password(newUserDTO.getPassword())
                .cvu(newUserDTO.getCvu())
                .reputation(0.0f)
                .walletAddress(newUserDTO.getWalletAddress())
                .build();

        return userDao.save(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userDao.deleteById(id);
    }
}
