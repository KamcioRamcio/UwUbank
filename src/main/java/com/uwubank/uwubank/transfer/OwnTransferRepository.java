package com.uwubank.uwubank.transfer;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnTransferRepository extends CrudRepository<OwnTransfer, Long> {
    List<OwnTransfer> findAllByToAccountId(Long accountId);
    List<OwnTransfer> findAllByFromAccountId(Long accountId);
}
