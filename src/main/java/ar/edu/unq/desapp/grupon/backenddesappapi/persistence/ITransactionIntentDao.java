package ar.edu.unq.desapp.grupon.backenddesappapi.persistence;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.TransactionIntent;
public interface ITransactionIntentDao extends CrudRepository<TransactionIntent,Long> {
}