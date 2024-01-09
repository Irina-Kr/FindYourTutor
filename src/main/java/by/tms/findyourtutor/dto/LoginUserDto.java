package by.tms.findyourtutor.dto;

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
    private AbstractEntity abstractEntity;

    public LoginUserDto(){

    }
    public LoginUserDto(AbstractEntity abstractEntity){
        this.abstractEntity = abstractEntity;
    }
    @NotEmpty
    @NotBlank
    Long id = abstractEntity.getId();

    @NotEmpty
    @NotBlank
    private String username;

    @NotEmpty
    @NotBlank
    @Range(min = 4,max = 16)
    private String password;



}
