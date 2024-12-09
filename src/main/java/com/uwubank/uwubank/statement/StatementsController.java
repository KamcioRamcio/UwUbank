package com.uwubank.uwubank.statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.uwubank.uwubank.BasicController;

@RestController
@RequestMapping("/api/statements")
public class StatementsController extends BasicController {

    private final StatementsService statementsService;

    @Autowired
    public StatementsController(StatementsService statementsService) {
        this.statementsService = statementsService;
    }

    @GetMapping("/{accountId}")
    public Statements getStatements(@PathVariable Long accountId) {
        return statementsService.getStatements(accountId);
    }
}