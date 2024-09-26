package mopay.mopay.admin;

import mopay.mopay.customer.CustomerEntity;
import mopay.mopay.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mopay.mopay.admin.AdminRepository;
import mopay.mopay.customer.CustomerRepository;

import mopay.mopay.admin.AdminEntity;
import mopay.mopay.customer.CustomerEntity;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // Method to get the admin profile, assuming there's only one admin
    public AdminEntity getAdminProfile() {
        return adminRepository.findById(1L).orElseThrow(() -> new RuntimeException("Admin not found"));
    }

    @Transactional
    public String sendMoney(String phoneNumber, Double amount, String bank) {
        // Validate the amount (ensure it's positive)
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        // Fetch customer using phone number
        CustomerEntity customer = customerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Fetch admin profile
        AdminEntity admin = getAdminProfile();

        // Check if admin has sufficient balance
        if (admin.getBalance() < amount) {
            return "Insufficient balance!";
        }

        // Deduct the amount from admin and add to customer
        admin.setBalance(admin.getBalance() - amount);
        customer.setBalance(customer.getBalance() + amount);
        customer.setBank(bank); // Update the bank for the customer

        // Save changes to both the admin and customer entities
        adminRepository.save(admin);
        customerRepository.save(customer);

        return "Money sent to " + customer.getName() + " (" + customer.getPhoneNumber() + ") via " + bank;
    }
}
