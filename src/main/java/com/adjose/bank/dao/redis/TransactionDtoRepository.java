package com.adjose.bank.dao.redis;

import com.adjose.bank.dto.TransactionDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDtoRepository extends CrudRepository<TransactionDto, String> {
}
