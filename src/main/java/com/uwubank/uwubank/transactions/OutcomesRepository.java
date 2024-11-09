package com.uwubank.uwubank.transactions;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OutcomesRepository extends CrudRepository<Outcomes, Long> {
    List<Outcomes> findAllByAccountId(Long accountId);
}
