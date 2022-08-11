package micro.spring.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRequest {

    @NotNull @Email @Length(min = 5, max = 50)
    private String email;

    @NotNull @Length(min = 5, max = 10)
    private String password;
}
