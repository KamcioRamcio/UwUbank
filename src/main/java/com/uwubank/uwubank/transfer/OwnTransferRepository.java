package com.uwubank.uwubank.transfer;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnTransferRepository extends CrudRepository<OwnTransfer, Long> {
    List<OwnTransfer> findAllByToAccountId(Long accountId);
    List<OwnTransfer> findAllByFromAccountId(Long accountId);

    @Query("SELECT t.* FROM own_transfers t WHERE t.customer_id = :customerId")
    List<OwnTransfer> findAllByCustomerId(Long customerId);
}
