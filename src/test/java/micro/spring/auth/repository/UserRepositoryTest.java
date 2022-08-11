package micro.spring.auth.repository;

import micro.spring.auth.entity.UserEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Disabled
    void testCreateUser()
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("12345678");

        UserEntity u = new UserEntity("ravi@email.com", password);
        UserEntity savedU = userRepository.save(u);

        Assertions.assertThat(savedU).isNotNull();
        Assertions.assertThat(savedU.getId()).isGreaterThan(0);
    }

}
