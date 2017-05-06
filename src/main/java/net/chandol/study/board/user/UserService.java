package net.chandol.study.board.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService implements UserDetailsService {
    @Autowired UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return userRepository.getByUsername(username);
        } catch (NullPointerException e) {
            throw new UsernameNotFoundException("유저를 찾을 수 없습니다.", e);
        }
    }

    public User createUser(UserCreateRequest request) {
        User user = new User(request.getUsername(), request.getEmail(),
                request.getPassword(), Arrays.asList("ROLE_ADMIN"));

        userRepository.save(user);
        return user;
    }
}
