package com.backend.flyquest.Controller;


import com.backend.flyquest.Model.Account;
import com.backend.flyquest.Payload.RegisterRequest;
import com.backend.flyquest.Security.AccountDetailService;
import com.backend.flyquest.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    @Autowired
    private AccountService accountService;
    @PostMapping("/register")
    public ResponseEntity<?> registerNewAccount(@RequestBody RegisterRequest registerRequest) {
        try {
            int flag = accountService.saveAccountToDatabase(registerRequest);
            if (flag != 0) {
                return ResponseEntity.badRequest().body(flag);
            } else {
                return ResponseEntity.ok(registerRequest);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Account Creation Failure for some reason...");
        }
    }
}