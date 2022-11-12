package com.tupercav;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud-chek")
public class FraudCheckHistoryController {

    private final FraudCheckHistoryService fraudCheckHistoryService;

    public FraudCheckHistoryController(FraudCheckHistoryService fraudCheckHistoryService) {
        this.fraudCheckHistoryService = fraudCheckHistoryService;
    }

    @GetMapping(path = {"customerId"})
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId) {
        boolean fraudulentCustomer = fraudCheckHistoryService.isFraudulentCustomer(customerId);
        return new FraudCheckResponse(fraudulentCustomer);
    }

}
