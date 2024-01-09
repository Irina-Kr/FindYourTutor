package by.tms.findyourtutor.dto;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class ProfileUserDto {
    @NotEmpty
    @NotBlank
    private String name;

    @NotEmpty
    @NotBlank
    private String surname;

    @NotEmpty
    @NotBlank
    private String username;

    @NotEmpty
    @NotBlank
    private String password;

    @NotEmpty
    @NotBlank
    private String email;

    @NotEmpty
    @NotBlank
    private String photo;

    @NotEmpty
    @NotBlank
    private String lessonTime;

    @NotEmpty
    @NotBlank
    private String price;

    @NotEmpty
    @NotBlank
    private String language;

    @NotEmpty
    @NotBlank
    private String role;

    public ProfileUserDto(){}
}
