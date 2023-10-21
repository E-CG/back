package co.udea.ssmu.api.model.jpa.model.strategy;

import java.time.LocalDate;
import java.util.List;
import co.udea.ssmu.api.model.jpa.model.coupon.Coupon;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
    @Column(name = "porcentaje_descuento")
    private int discountPercentage;

    @NotNull
    @Column(name = "isActive")
    private String isActive;

    @NotNull
    @Column(name = "city")
    private String city;

    @NotNull
    @Column(name = "startDate")
    private LocalDate startDate;

    @NotNull
    @Column(name = "endDate")
    private LocalDate endDate;

    @NotNull
    @Column(name = "discount_value")
    private int discountValue;

    @NotNull
    @Column(name = "min_value")
    private int minValue;

    @NotNull
    @Column(name = "max_discount")
    private int maxDiscount;

    @OneToMany(mappedBy = "estrategia", fetch = FetchType.LAZY)
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

    public int getDiscountPercentage() {
        return discountPercentage;

    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

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

    public int getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(int discountValue) {
        this.discountValue = discountValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(int maxDiscount) {
        this.maxDiscount = maxDiscount;
    }
}