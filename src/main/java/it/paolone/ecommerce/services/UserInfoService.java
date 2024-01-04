package it.paolone.ecommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.paolone.ecommerce.dto.UserRegistrationDTO;
import it.paolone.ecommerce.entities.Customer;
import it.paolone.ecommerce.entities.User;
import it.paolone.ecommerce.exceptions.UserAndCustomerEmailMismatchException;
import it.paolone.ecommerce.repositories.UserInfoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userDetail = repository.findByEmail(username);
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found (" + username + ")"));

    }

    @Transactional
    public int deleteUser(String email) {

        Optional<User> fetchedUser = repository.findByEmail(email);

        if (fetchedUser.isPresent() && !fetchedUser.get().getRoles().contains("ROLE_GOD")) {

            int deletedUser = repository.deleteByEmail(email);
            if (deletedUser > 0) {
                return 0;
            }
        }
        return 1;
    }

    public UserRegistrationDTO addUser(UserRegistrationDTO userRegistrationDTO)
            throws UserAndCustomerEmailMismatchException {

        User newUser = new User();
        Customer newCustomer = new Customer();

        newUser.setPassword(encoder.encode(userRegistrationDTO.getPassword()));
        newUser.setEmail(userRegistrationDTO.getEmail());
        newUser.setRoles(userRegistrationDTO.getRoles());

        newCustomer.setNominative(userRegistrationDTO.getNominative());
        newCustomer.setPhoneNumber(userRegistrationDTO.getPhoneNumber());
        newCustomer.setUser(newUser);

        User newUserDetails = repository.save(newUser);

        Customer newCustomerDetails = customerService.saveCustomer(newCustomer);

        if (!newUserDetails.getEmail().equals(newCustomerDetails.getUser().getEmail()))
            throw new UserAndCustomerEmailMismatchException();

        UserRegistrationDTO registrationDetails = new UserRegistrationDTO();
        registrationDetails.setEmail(newUserDetails.getEmail());
        registrationDetails.setRoles(newUserDetails.getRoles());
        registrationDetails.setNominative(newCustomerDetails.getNominative());
        registrationDetails.setPhoneNumber(newCustomer.getPhoneNumber());

        return registrationDetails;
    }
}
