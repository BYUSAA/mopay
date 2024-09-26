package mopay.mopay.customer;
import mopay.mopay.customer.CustomerEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByPhoneNumber(String phoneNumber);
}


