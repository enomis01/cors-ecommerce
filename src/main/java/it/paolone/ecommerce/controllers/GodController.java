package it.paolone.ecommerce.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import it.paolone.ecommerce.dto.UserRegistrationDTO;
import it.paolone.ecommerce.exceptions.UserAndCustomerEmailMismatchException;
import it.paolone.ecommerce.services.UserInfoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth/god")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5500")
public class GodController {

    private final UserInfoService userInfoService;

    @PostMapping("/new_admin")
    @PreAuthorize("hasAuthority('ROLE_GOD')")
    public ResponseEntity<UserRegistrationDTO> registerNewAdmin(@RequestBody UserRegistrationDTO data)
            throws ResponseStatusException, UserAndCustomerEmailMismatchException {

        if (data != null) {

            data.setRoles("ROLE_ADMIN");

            if (!data.getRoles().contains("ROLE_ADMIN"))
                return ResponseEntity.badRequest().build();

            return ResponseEntity.ok(userInfoService.addUser(data));

        }

        return ResponseEntity.noContent().build();

    }

    @GetMapping("/deleteUser/{email}")
    @PreAuthorize("hasAuthority('ROLE_GOD')")
    public ResponseEntity<Boolean> deleteUserByEmail(@PathVariable String email) {
        if (email != null) {
            int responseData = userInfoService.deleteUser(email);

            if (responseData == 0)
                return ResponseEntity.ok(true);

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

}
