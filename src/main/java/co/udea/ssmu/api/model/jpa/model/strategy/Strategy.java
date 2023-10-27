package co.udea.ssmu.api.model.jpa.model.strategy;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import co.udea.ssmu.api.model.jpa.model.coupon.Coupon;
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
@Table(name = "estrategia")
public class Strategy implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_estrategia")
    private long idStrategy;

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
}