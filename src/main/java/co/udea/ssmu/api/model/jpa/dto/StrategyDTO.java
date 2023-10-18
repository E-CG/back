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
    @JsonFormat(pattern = "yyyy-MM-dd:HH:mm:ss")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd:HH:mm:ss")
    private LocalDate endDate;
    @Min(1)
    @Max(100)
    private int discountPercentage;
    @Min(0)
    @Max(50000)
    private int discountValue;
    private int minValue;
    private int maxDiscount;
    private Boolean estado;
    @NotEmpty(message = "Falta la ciudad")
    private String city;

    // Constructor
    public StrategyDTO() {

    }

    // Getter y Setter
    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getCiudad() {
        return city;
    }

    public void setCiudad(String city) {
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

    public int getIdEstrategy() {
        return idStrategy;
    }

    public void setIdEstrategy(int idStrategy) {
        this.idStrategy = idStrategy;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}