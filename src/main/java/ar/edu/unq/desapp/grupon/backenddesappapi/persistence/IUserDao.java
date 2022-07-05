package ar.edu.unq.desapp.grupon.backenddesappapi.persistence;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.User;

import java.util.Optional;

public interface IUserDao extends CrudRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
