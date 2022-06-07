package ar.edu.unq.desapp.grupon.backenddesappapi.persistence;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.Cryptoactive;
import ar.edu.unq.desapp.grupon.backenddesappapi.Model.CryptoactiveName;
public interface ICryptoactiveDao extends CrudRepository<Cryptoactive,CryptoactiveName> {
}