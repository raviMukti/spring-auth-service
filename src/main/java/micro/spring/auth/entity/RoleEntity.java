package micro.spring.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "role")
public class RoleEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50, unique = true)
    private String name;

    public RoleEntity(String name)
    {
        this.name = name;
    }

    public RoleEntity(Integer id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return this.name;
    }

}
