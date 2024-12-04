package com.uwubank.uwubank.transfer;


import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public Transfer createTransfer(@RequestBody Transfer transfer) {
        return transferService.executeTransfer(transfer);
    }

    @PostMapping("/own")
    public OwnTransfer createOwnTransfer(@RequestBody OwnTransfer ownTransfer) {
        return transferService.executeOwnTransfer(ownTransfer);
    }

    @GetMapping("/details")
    public List<TransferDetailsDTO> getTransferDetailsByCustomerId(@RequestParam Long customerId) {
        return transferService.getTransferDetailsByCustomerId(customerId);
    }

    @GetMapping("/customer/{customerId}")
    public List<Transfer> getAllTransfersByCustomerId(@PathVariable Long customerId) {
        return transferService.getAllTransfersByCustomerId(customerId);
    }

    @GetMapping("/customer/own/{customerId}")
    public List<OwnTransfer> getAllOwnTransfersByCustomerId(@PathVariable Long customerId) {
        return transferService.getAllOwnTransfersByCustomerId(customerId);
    }

}
