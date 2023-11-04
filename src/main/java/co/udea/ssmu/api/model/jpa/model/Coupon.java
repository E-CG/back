package co.udea.ssmu.api.model.jpa.model;

import java.io.Serializable;

import co.udea.ssmu.api.model.jpa.model.Strategy;
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
@Table(name = "cupon", schema = "esteban")
public class Coupon implements Serializable {
    @Id
    @Column(name = "codigo", nullable = false, unique = true)
    private String code;

    @NotNull
    @Column(name = "estado_cupon")
    private String status;

    @NotNull
    @Column(name = "cantidad_disponible")
    private int amount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_estrategia")
    private Strategy strategy;
}