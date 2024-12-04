package com.uwubank.uwubank.transactions;

import com.uwubank.uwubank.transfer.OwnTransfer;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IncomesRepository extends CrudRepository<Incomes, Long> {
    List<Incomes> findAllByAccountId(Long accountId);
    @Query("SELECT t.* FROM incomes t WHERE t.customer_id = :customerId")
    List<Incomes> findAllByCustomerId(Long customerId);
}
