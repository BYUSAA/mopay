package mopay.mopay.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mopay.mopay.customer.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Use POST and @RequestBody to accept JSON
    @PostMapping("/balance")
    public ResponseEntity<Double> getBalance(@RequestBody BalanceRequestDto balanceRequest) {
        // Use the phoneNumber and pin from the request body
        return ResponseEntity.ok(customerService.getCustomerBalance(balanceRequest.getPhoneNumber(), balanceRequest.getPin()));
    }
}




