package ar.edu.unq.desapp.grupon.backenddesappapi.service;

import java.util.List;


import ar.edu.unq.desapp.grupon.backenddesappapi.Model.User;
import ar.edu.unq.desapp.grupon.backenddesappapi.service.NewUserDTO;

public interface IUserService {
    
    public List<User> findAll();

    public User findById(Long id);

    public User save(NewUserDTO newUserDTO);

    public void delete(Long id);
    
}
