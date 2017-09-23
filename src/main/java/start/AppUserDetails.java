package start;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import start.entities.AppUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class AppUserDetails extends AppUser implements UserDetails {

    private static final long serialVersionUID = 1L;

    public AppUserDetails(AppUser AppUser){
        super(AppUser);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles=new ArrayList<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority("ROLE_"));
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return super.getHandle();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }
}
