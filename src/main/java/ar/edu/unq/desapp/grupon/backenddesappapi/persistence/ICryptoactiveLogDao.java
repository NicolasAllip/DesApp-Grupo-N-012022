package ar.edu.unq.desapp.grupon.backenddesappapi.persistence;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.CryptoactiveLog;
public interface ICryptoactiveLogDao extends CrudRepository<CryptoactiveLog,Long> {
}