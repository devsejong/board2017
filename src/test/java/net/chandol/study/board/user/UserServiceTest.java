package net.chandol.study.board.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired UserService userService;

    @Test
    public void createAndFindUserTest() throws Exception {
        // given
        UserCreateRequest request = new UserCreateRequest("username", "email", "password");

        // when
        userService.createUser(request);

        // then
        User user = userService.loadUserByUsername("username");
        assertThat(user.getId()).hasNoNullFieldsOrProperties();
        assertThat(user.getUsername()).isEqualTo("username");
        assertThat(user.getEmail()).isEqualTo("email");
        assertThat(user.getPassword()).isEqualTo("password");
    }

}