package com.uwubank.uwubank.transfer;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransferRepository extends CrudRepository<Transfer, Long> {
    List<Transfer> findAllBySenderAccountId(Long accountId);
    List<Transfer> findAllByReceiverAccountId(Long accountId);

    @Query("SELECT t.transfer_id, t.customer_id AS transfer_customer_id, t.amount AS transfer_amount, t.date AS transfer_date, " +
            "ot.own_transfer_id, ot.customer_id AS own_transfer_customer_id, ot.amount AS own_transfer_amount, ot.date AS own_transfer_date, " +
            "i.income_id, i.customer_id AS income_customer_id, i.amount AS income_amount, i.date AS income_date, " +
            "o.outcome_id, o.customer_id AS outcome_customer_id, o.amount AS outcome_amount, o.date AS outcome_date " +
            "FROM transfers t " +
            "LEFT JOIN own_transfers ot ON t.customer_id = ot.customer_id " +
            "LEFT JOIN incomes i ON t.customer_id = i.customer_id " +
            "LEFT JOIN outcomes o ON t.customer_id = o.customer_id " +
            "WHERE t.customer_id = :customerId")
    List<TransferDetailsDTO> findTransferDetailsByCustomerId(Long customerId);

    @Query("SELECT t.* FROM transfers t WHERE t.customer_id = :customerId")
    List<Transfer> findAllByCustomerId(Long customerId);
}
