package micro.spring.auth.repository;

import micro.spring.auth.entity.RoleEntity;
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

    @Test
    @Disabled
    void testCreateUser2()
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("12345678");

        UserEntity u = new UserEntity("editor@email.com", password);
        UserEntity savedU = userRepository.save(u);

        Assertions.assertThat(savedU).isNotNull();
        Assertions.assertThat(savedU.getId()).isGreaterThan(0);
    }

    @Test
    @Disabled
    void testCreateUser3()
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("12345678");

        UserEntity u = new UserEntity("customer@email.com", password);
        UserEntity savedU = userRepository.save(u);

        Assertions.assertThat(savedU).isNotNull();
        Assertions.assertThat(savedU.getId()).isGreaterThan(0);
    }

    @Test
    @Disabled
    void testAssignRoleToUser()
    {
        Integer userId = 1;
        Integer roleId = 1;
        UserEntity user = userRepository.findById(userId).get();
        user.addRole(new RoleEntity(roleId));

        UserEntity updatedUser = userRepository.save(user);
        Assertions.assertThat(updatedUser.getRoleEntities()).hasSize(1);
    }

    @Test
    @Disabled
    void testAssignRoleToUser2()
    {
        Integer userId = 4;
        Integer roleId = 2;
        UserEntity user = userRepository.findById(userId).get();
        user.addRole(new RoleEntity(roleId));

        UserEntity updatedUser = userRepository.save(user);
        Assertions.assertThat(updatedUser.getRoleEntities()).hasSize(1);
    }

    @Test
    @Disabled
    void testAssignRoleToUser3()
    {
        Integer userId = 5;
        Integer roleId = 3;
        UserEntity user = userRepository.findById(userId).get();
        user.addRole(new RoleEntity(roleId));

        UserEntity updatedUser = userRepository.save(user);
        Assertions.assertThat(updatedUser.getRoleEntities()).hasSize(1);
    }

}
