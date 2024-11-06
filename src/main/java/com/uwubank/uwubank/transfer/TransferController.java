package com.uwubank.uwubank.transfer;


import org.springframework.web.bind.annotation.*;


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


}
