package by.tms.findyourtutor.dto;

import by.tms.findyourtutor.configuration.UserPrincipal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@ToString
public class LoginUserDto {
    private UserPrincipal userPrincipal;

    public LoginUserDto(){

    }
    public LoginUserDto(UserPrincipal userPrincipal){
        this.userPrincipal = userPrincipal;
    }
    @NotEmpty
    @NotBlank
    Long id ;

    @NotEmpty
    @NotBlank
    private String username;

    @NotEmpty
    @NotBlank
    @Range(min = 4,max = 16)
    private String password;



}
