package micro.spring.auth.repository;

import micro.spring.auth.entity.RoleEntity;
import micro.spring.auth.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RoleRepositoryTest {

    @Autowired private RoleRepository roleRepository;

    @Test
    @Disabled
    void testCreateRoles()
    {
        RoleEntity admin = new RoleEntity("ROLE_ADMIN");
        RoleEntity editor = new RoleEntity("ROLE_EDITOR");
        RoleEntity customer = new RoleEntity("ROLE_CUSTOMER");

        roleRepository.saveAll(List.of(admin, editor, customer));

        long count = roleRepository.count();
        Assertions.assertEquals(3, count);
    }
}
