package com.uwubank.uwubank.transactions;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IncomesRepository extends CrudRepository<Incomes, Long> {
    List<Incomes> findAllByAccountId(Long accountId);
}
