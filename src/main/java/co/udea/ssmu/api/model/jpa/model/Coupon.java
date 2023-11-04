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
<<<<<<< HEAD:src/main/java/co/udea/ssmu/api/model/jpa/model/Coupon.java
=======
    @Column(name = "id_cupon")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_coupon;

>>>>>>> 03ef6cdca11fa684e2aec62710d7d97a90bbd006:src/main/java/co/udea/ssmu/api/model/jpa/model/coupon/Coupon.java
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