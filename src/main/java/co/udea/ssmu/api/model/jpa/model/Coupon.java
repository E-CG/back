package co.udea.ssmu.api.model.jpa.model;

import java.io.Serializable;
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
    @Column(name = "codigo", nullable = false, unique = true, updatable = true, length = 9)
    private String code;

    @NotNull
    @Column(name = "estado_cupon", nullable = false, length = 20)
    private String status;

    @NotNull
    @Column(name = "cantidad_disponible", nullable = false, length = 3, updatable = true)
    private int amount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_estrategia")
    private Strategy strategy;
}