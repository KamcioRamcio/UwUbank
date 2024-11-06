package com.uwubank.uwubank.branch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    private final BranchService branchService;

    @Autowired
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping("/{branchId}")
    public Optional<Branch> getBranchWithDetails(@PathVariable Long branchId) {
        return branchService.getBranchWithDetails(branchId);
    }
}