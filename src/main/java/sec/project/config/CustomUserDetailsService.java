package sec.project.config;

import java.util.Arrays;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sec.project.domain.Account;
import sec.project.repository.AccountRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private AccountRepository accountRepository;
    

    @PostConstruct
    public void init() {
        Account account = new Account();
        account.setUsername("admin");
        account.setPassword("admin");
        accountRepository.save(account);
        
        account = new Account();
        account.setUsername("user1");
        account.setPassword("user1");
        accountRepository.save(account);
        
        account = new Account();
        account.setUsername("user2");
        account.setPassword("user2");
        accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        
        if (account == null) {
            throw new UsernameNotFoundException("No such user: " + username);
        }
        
        if (username.equals("admin")) {
            return new org.springframework.security.core.userdetails.User(
            account.getUsername(),
            account.getPassword(),
            true,
            true,
            true,
            true,
            Arrays.asList(new SimpleGrantedAuthority("ADMIN")));
        } else {
            return new org.springframework.security.core.userdetails.User(
            account.getUsername(),
            account.getPassword(),
            true,
            true,
            true,
            true,
            Arrays.asList(new SimpleGrantedAuthority("USER")));  
        }
    }
}
