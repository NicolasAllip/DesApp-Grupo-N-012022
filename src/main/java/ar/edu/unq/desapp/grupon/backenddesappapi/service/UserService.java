package ar.edu.unq.desapp.grupon.backenddesappapi.service;

import java.util.List;

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

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userDao.findById(id).orElse(null);
    }
    
    @Override
    @Transactional
    public User save(User user) {
        return userDao.save(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userDao.deleteById(id);
    }
}
