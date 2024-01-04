package by.tms.findyourtutor.configuration;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public abstract class UserPrincipal implements UserDetails {

    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private String photo;
    private String lessonTime;
    private String price;
    private String language;

    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return (Collection<? extends GrantedAuthority>) this.roles;
    }
    @Override
    public String getPassword(){return password;}

    @Override
    public String getUsername(){return username;}


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




}

