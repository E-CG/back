package co.udea.ssmu.api.model.jpa.dto;

import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import co.udea.ssmu.api.model.jpa.model.User;
import co.udea.ssmu.api.utils.common.StrategyUserTypeEnum;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@ToString
public class StrategyDTO{
    private Long idStrategy;

    @NotEmpty(message = "Falta el nombre de la estrategia")
    private String name;

    @NotEmpty(message = "Falta una descripción")
    private String description;

    @NotNull(message = "Falta la fecha de inicio")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @NotNull(message = "Falta la fecha de finalización")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    @Min(value = 0)
    @Max(value = 100)
    private Integer discountPercentage;

    @Min(value = 0)
    @Max(value = 50000)
    private Integer discountValue;

    @Min(value = 5000)
    @Max(value = 100000)
    private Integer minValue;

    @Min(value = 5000)
    @Max(value = 100000)
    private Integer maxDiscount;

    @NotNull(message = "Falta el tipo de usuario")
    private StrategyUserTypeEnum userType;

    private Boolean isActive;

    @NotEmpty(message = "Falta la ciudad")
    private String city;

    private List<User> users;
}