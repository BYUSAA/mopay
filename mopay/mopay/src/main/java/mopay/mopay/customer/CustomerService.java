package mopay.mopay.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mopay.mopay.customer.CustomerRepository;
import mopay.mopay.customer.CustomerEntity;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Double getCustomerBalance(String phoneNumber, String pin) {
        // Fetch the customer using phoneNumber and validate the pin
        CustomerEntity customer = customerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Validate pin
        if (!customer.getPin().equals(pin)) {
            throw new RuntimeException("Invalid PIN");
        }

        // Return the balance
        return customer.getBalance();
    }
}


