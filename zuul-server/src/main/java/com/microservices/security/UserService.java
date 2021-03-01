package com.microservices.security;


import com.microservices.auth.beans.UserDetails;
import com.microservices.exception.SecurityServiceException;
import com.microservices.model.Role;
import com.microservices.model.User;
import com.microservices.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if (user == null || user.getRole() == null || user.getRole().isEmpty()) {
            throw new SecurityServiceException("Invalid username or password.", HttpStatus.UNAUTHORIZED);
        }
        String [] authorities = new String[user.getRole().size()];
        int count=0;
        for (Role role : user.getRole()) {
            authorities[count] = "ROLE_"+role.getRole();
            count++;
        }
        return new UserDetails(user.getUsername(),
                user.getPassword(),
                user.getActive(),
                user.isLocked(),
                user.isExpired(),
                user.isEnabled(),
                authorities);
    }
}
