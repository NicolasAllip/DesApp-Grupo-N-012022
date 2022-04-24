package ar.edu.unq.desapp.grupon.backenddesappapi.service;

import java.util.List;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.User;

public interface IUserService {
    
    public List<User> findAll();

    public User findById(Long id);

    public User save(User user);

    public void delete(Long id);
    
}
