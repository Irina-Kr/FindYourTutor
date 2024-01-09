package by.tms.findyourtutor.service;

import by.tms.findyourtutor.configuration.UserPrincipal;
import by.tms.findyourtutor.entity.Role;
import by.tms.findyourtutor.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private  final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    public User save(User user){
        user.getPassword();
        user.getAuthorities().add(Role.STUDENT);
        user.getAuthorities().add(Role.TUTOR);
        user.getAuthorities().add(Role.ADMIN);
        return userRepository.save(user);
    }
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    public List<User> findAll(){
    return userRepository.findAll();
}
    public void remove(User user){
        userRepository.delete(user);
    }
    public void removeById(Long id){
        if(id != null){
            userRepository.deleteById(id);
        }
    }
    public void update(User user) {
        if (user!= null){
            userRepository.save(user);
        }
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username).orElseThrow();

        UserPrincipal userPrincipal = UserPrincipal.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();

        return userPrincipal;

    }

    public void assignRoleToUser(User user, Role role){
        user.getAuthorities().add(role);


}
}