package it.paolone.ecommerce.controllers;

import org.springframework.web.bind.annotation.*;

import it.paolone.ecommerce.services.JwtService;
import it.paolone.ecommerce.services.OrderDetailsService;
import it.paolone.ecommerce.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import it.paolone.ecommerce.configuration.filter.AuthRequest;
import it.paolone.ecommerce.dto.OrderDetailsDTO;
import it.paolone.ecommerce.dto.UserRegistrationDTO;
import it.paolone.ecommerce.exceptions.CustomerNotRegisteredException;
import it.paolone.ecommerce.exceptions.ProductNotInDatabaseException;
import it.paolone.ecommerce.exceptions.ProductQuantityException;
import it.paolone.ecommerce.exceptions.UserAndCustomerEmailMismatchException;

@RestController
@CrossOrigin(origins = "http://localhost:5500")
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserInfoService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private OrderDetailsService orderDetailsService;

    @PostMapping("/add_new_user")
    public UserRegistrationDTO addNewUser(@RequestBody UserRegistrationDTO user)
            throws UserAndCustomerEmailMismatchException {
        user.setRoles("ROLE_USER");
        return this.userService.addUser(user);
    }

    @PostMapping("/generate_token")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getEmail());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    @PostMapping("/user/orders/save_new")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<OrderDetailsDTO> saveOrderData(@RequestBody OrderDetailsDTO in)
            throws ProductQuantityException, CustomerNotRegisteredException, ProductNotInDatabaseException {
        if (in != null) {
            OrderDetailsDTO data = orderDetailsService.saveNewOrder(in);
            if (data != null) {
                return ResponseEntity.ok(data);
            } else {
                return ResponseEntity.internalServerError().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
