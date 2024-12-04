package com.uwubank.uwubank.transactions;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OutcomesRepository extends CrudRepository<Outcomes, Long> {
    List<Outcomes> findAllByAccountId(Long accountId);
    @Query("SELECT t.* FROM outcomes t WHERE t.customer_id = :customerId")
    List<Outcomes> findAllByCustomerId(Long customerId);
}
