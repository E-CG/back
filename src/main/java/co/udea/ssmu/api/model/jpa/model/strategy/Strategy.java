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
    @Column(name = "id_estrategia")
    private int idStrategy;

    @NotNull
    @Column(name = "nombre")
    private String name;

    @NotNull
    @Column(name = "descripcion")
    private String description;

    @NotNull
    @Column(name = "porcentaje_descuento")
    private int discountPercentage;

    @NotNull
    @Column(name = "estado")
    private String isActive;

    @NotNull
    @Column(name = "ciudad")
    private String city;

    @Column(name = "fecha_inicio")
    private LocalDate startDate;

    @Column(name = "fecha_fin")
    private LocalDate endDate;

    @NotNull
    @Column(name = "valor_descuento")
    private int discountValue;

    @NotNull
    @Column(name = "valor_minimo")
    private int minValue;

    @NotNull
    @Column(name = "descuento_maximo")
    private int maxDiscount;

    @OneToMany(mappedBy = "strategy")
    private List<Coupon> coupon;

    public Strategy() {
    }

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

    public List<Coupon> getCoupon() {
        return coupon;
    }

    public void setCoupon(List<Coupon> coupon) {
        this.coupon = coupon;
    }
}