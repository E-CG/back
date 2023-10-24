package co.udea.ssmu.api.model.jpa.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class StrategyDTO {
    // Atributos de una estrategia
    private int idStrategy;

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
    private int discountValue;

    @Min(value = 5000, message = "El valor mínimo debe ser al menos 5000")
    @Max(value = 100000, message = "El valor mínimo no puede ser mayor a 100,000")
    private int minValue;

    @Min(value = 5000, message = "El descuento máximo debe ser al menos 5000")
    @Max(value = 100000, message = "El descuento máximo no puede ser mayor a 100,000")
    private int maxDiscount;

    private String isActive;

    @NotEmpty(message = "Falta la ciudad")
    private String city;

    private CouponDTO codeCoupon;

    // Constructor
    public StrategyDTO() {

    }

    // Getter y Setter
    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(int maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdStrategy() {
        return idStrategy;
    }

    public void setIdStrategy(int idStrategy) {
        this.idStrategy = idStrategy;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public CouponDTO getCodeCoupon() {
        return codeCoupon;
    }

    public void setCodeCoupon(CouponDTO codeCoupon) {
        this.codeCoupon = codeCoupon;
    }

    public int getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(int discountValue) {
        this.discountValue = discountValue;
    }
}