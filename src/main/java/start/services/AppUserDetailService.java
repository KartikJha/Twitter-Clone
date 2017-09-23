package start.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import start.AppUserDetails;
import start.entities.AppUser;
import start.repos.AppUserRepo;

@Service
public class AppUserDetailService implements UserDetailsService {
    @Autowired
    private AppUserRepo appUserRepo;

    @Override
    public AppUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser AppUser=appUserRepo.getUserByUserName(username);

        if(AppUser == null)
            throw new UsernameNotFoundException("No AppUser with username:"+username);
        else
            return new AppUserDetails(AppUser);
    }
}
