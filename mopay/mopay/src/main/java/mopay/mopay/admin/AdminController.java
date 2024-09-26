package mopay.mopay.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mopay.mopay.admin.AdminService;
import mopay.mopay.admin.AdminEntity;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/profile")
    public ResponseEntity<AdminEntity> getProfile() {
        return ResponseEntity.ok(adminService.getAdminProfile());
    }

    @PostMapping("/send-money")
    public ResponseEntity<String> sendMoney(@RequestBody SendMoneyDto sendMoneyRequest) {
        // Use the data from the request body
        return ResponseEntity.ok(adminService.sendMoney(
                sendMoneyRequest.getPhoneNumber(),
                sendMoneyRequest.getAmount(),
                sendMoneyRequest.getBank()));
    }
}

