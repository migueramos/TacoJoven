package tacos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tacos.dao.UserRepository;
import tacos.domain.UserInf;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

    private UserRepository repository;

   @Autowired
    public UserRepositoryUserDetailsService(UserRepository userRepo){
        this.repository = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInf user = repository.findByUsername(username);
        if (user != null){
            return user;
        }
        throw new UsernameNotFoundException("User '" + username + "' not found");
    }
}
