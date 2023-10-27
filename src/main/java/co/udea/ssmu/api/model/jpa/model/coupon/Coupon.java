package co.udea.ssmu.api.model.jpa.model.coupon;

import co.udea.ssmu.api.model.jpa.model.strategy.Strategy;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "cupon")
public class Coupon {
    @Id
    @Column(name = "codigo", insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String code;

    @NotNull
    @Column(name = "estado_cupon")
    private String status;

    @NotNull
    @Column(name = "cantidad_disponible")
    private int amount;

    @ManyToOne
    @JoinColumn(name = "id_estrategia", nullable = false)
    private Strategy strategy;
}