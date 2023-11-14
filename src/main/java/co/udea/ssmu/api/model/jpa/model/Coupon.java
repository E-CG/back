package co.udea.ssmu.api.model.jpa.model;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Getter
@Setter
@RequiredArgsConstructor
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
    @Column(name = "cantidad_disponible", nullable = false, length = 3, updatable = false)
    private Integer amountAvalaible;

    @NotNull
    @Column(name = "cantidad_creada", nullable = false, updatable = false)
    private Integer amountCreated;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_estrategia")
    private Strategy strategy;
}