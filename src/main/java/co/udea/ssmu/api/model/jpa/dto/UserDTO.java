package co.udea.ssmu.api.model.jpa.dto;

import java.util.List;
import javax.validation.constraints.NotNull;
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

    private List<StrategyDTO> strategies;
}
