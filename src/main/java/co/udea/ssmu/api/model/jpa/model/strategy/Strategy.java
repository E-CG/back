package co.udea.ssmu.api.model.jpa.model.strategy;

import java.time.LocalDate;
import java.util.List;
import co.udea.ssmu.api.model.jpa.model.coupon.Coupon;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "estrategia")
public class Strategy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_strategy")
    private int idStrategy;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @OneToMany
    @Column(name = "code")
    private String code;

    @NotNull
    @Column(name = "porcentaje_descuento")
    private int discountPercentage;

    @NotNull
    @Column(name = "status")
    private String status;

    @NotNull
    @Column(name = "city")
    private String city;

    @NotNull
    @Column(name = "startDate")
    private LocalDate startDate;

    @NotNull
    @Column(name = "endDate")
    private LocalDate endDate;

    @OneToMany(mappedBy = "strategy")
    private List<Coupon> coupons;

    public int getIdStrategy() {
        return idStrategy;
    }

    public void setIdStrategy(int idStrategy) {
        this.idStrategy = idStrategy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }
}