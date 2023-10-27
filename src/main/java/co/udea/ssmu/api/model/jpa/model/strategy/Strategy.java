package co.udea.ssmu.api.model.jpa.model.strategy;

import java.io.Serializable;
import java.time.LocalDate;
import jakarta.persistence.*;
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

    @Column(name = "nombre")
    private @NonNull String name;

    @Column(name = "descripcion")
    private @NonNull String description;

    @Column(name = "porcentaje_descuento")
    private int discountPercentage;

    @Column(name = "estado")
    private @NonNull Boolean isActive;

    @Column(name = "ciudad")
    private @NonNull String city;

    @Column(name = "fecha_inicio")
    private @NonNull LocalDate startDate;

    @Column(name = "fecha_fin")
    private @NonNull LocalDate endDate;

    @Column(name = "valor_descuento")
    private int discountValue;

    @Column(name = "valor_minimo", nullable = false)
    private int minValue;

    @Column(name = "descuento_maximo")
    private int maxDiscount;
}