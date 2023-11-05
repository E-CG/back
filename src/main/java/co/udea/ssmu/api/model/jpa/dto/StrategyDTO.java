package co.udea.ssmu.api.model.jpa.dto;

import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import co.udea.ssmu.api.model.jpa.model.User;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class StrategyDTO{
    private long idStrategy;

    @NotEmpty(message = "Falta el nombre de la estrategia")
    private String name;

    @NotEmpty(message = "Falta una descripción")
    private String description;

    @NotNull(message = "Falta la fecha de inicio")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate startDate;

    @NotNull(message = "Falta la fecha de finalización")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate endDate;

    @Min(value = 1, message = "El porcentaje de descuento debe ser mayor que 0")
    @Max(value = 100, message = "El porcentaje de descuento no puede ser mayor al 100%")
    private int discountPercentage;

    @Min(value = 0, message = "El valor de descuento no puede ser negativo")
    @Max(value = 50000, message = "El valor de descuento no puede ser mayor a 50,000")
    private Integer discountValue;

    @Min(value = 5000, message = "El valor mínimo debe ser al menos 5000")
    @Max(value = 100000, message = "El valor mínimo no puede ser mayor a 100,000")
    private int minValue;

    @Min(value = 5000, message = "El descuento máximo debe ser al menos 5000")
    @Max(value = 100000, message = "El descuento máximo no puede ser mayor a 100,000")
    private int maxDiscount;

    private Boolean isActive;

    @NotEmpty(message = "Falta la ciudad")
    private String city;

    private List<User> users;
}