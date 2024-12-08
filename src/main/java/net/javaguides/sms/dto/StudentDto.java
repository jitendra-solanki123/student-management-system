package net.javaguides.sms.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    private Long id;
    @NotEmpty(message="Student first name should not be empty")
    private String firstName;
    @NotEmpty(message="Student last name should not be empty")
    private String lastName;
    @NotEmpty(message="Student email should not be empty")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
