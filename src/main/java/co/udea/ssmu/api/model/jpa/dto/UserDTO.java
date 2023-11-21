package co.udea.ssmu.api.model.jpa.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    @NotNull
    private long document;

    @NotNull
    private String name;

    @NotNull
    private String lastName;

    @NotNull
    private String clasificacion;
}
