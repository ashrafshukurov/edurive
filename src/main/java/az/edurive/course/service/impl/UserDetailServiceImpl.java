package az.edurive.course.service.impl;

import az.edurive.course.exception.NotFoundException;
import az.edurive.course.model.entity.User;
import az.edurive.course.repository.UserRepository;
import az.edurive.course.security.UserPrincipal.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String gmail) throws UsernameNotFoundException {
        Optional<User> userOptional=userRepository.findByGmail(gmail);
        Set<GrantedAuthority> authorities=new HashSet<>();
        if(userOptional.isPresent()){
            User user=userOptional.get();
            UserPrincipal userPrincipal=new UserPrincipal();
            userPrincipal.setGmail(user.getGmail());
            userPrincipal.setPassword(user.getPassword());
            authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRoleType().name()));
            userPrincipal.setAuthorities(authorities);
            return userPrincipal;
        }else{
            throw new NotFoundException(gmail+" not found");
        }
    }
}
