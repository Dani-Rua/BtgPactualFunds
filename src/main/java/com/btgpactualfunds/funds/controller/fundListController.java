package com.btgpactualfunds.funds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btgpactualfunds.funds.entities.Fund;
import com.btgpactualfunds.funds.services.FundService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class FundListController {

    @Autowired
    private FundService fundService;

    @GetMapping("/funds")
    public ResponseEntity<List<Fund>> getFunds() {
        return ResponseEntity.ok(fundService.getAllFunds());
    }

}
