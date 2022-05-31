package ar.edu.unq.desapp.grupon.backenddesappapi.persistence;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.User;

public interface IUserDao extends CrudRepository<User,Long> {
    
}