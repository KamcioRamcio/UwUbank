package com.uwubank.uwubank.transfer;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransferRepository extends CrudRepository<Transfer, Long> {
    List<Transfer> findAllBySenderAccountId(Long accountId);
    List<Transfer> findAllByReceiverAccountId(Long accountId);
}
