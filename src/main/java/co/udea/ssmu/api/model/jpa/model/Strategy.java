package co.udea.ssmu.api.model.jpa.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "estrategia", schema = "esteban")
public class Strategy implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_estrategia", length = 3, nullable = false, unique = true, updatable = false)
    private long idStrategy;

    @Column(name = "nombre", nullable = false, length = 50)
    private @NonNull String name;

    @Column(name = "descripcion", nullable = false, length = 50)
    private @NonNull String description;

    @Column(name = "porcentaje_descuento", nullable = false, length = 3)
    private int discountPercentage;

    @Column(name = "estado", nullable = false, length = 1)
    private Boolean isActive;

    @Column(name = "ciudad", nullable = false, length = 50)
    private @NonNull String city;

    @Column(name = "fecha_inicio", nullable = false)
    private @NonNull LocalDate startDate;

    @Column(name = "fecha_fin", nullable = false)
    private @NonNull LocalDate endDate;

    @Column(name = "valor_descuento", nullable = true, length = 5)
    private int discountValue;

    @Column(name = "valor_minimo", nullable = true, length = 5)
    private int minValue;

    @Column(name = "descuento_maximo", nullable = false, length = 5)
    private int maxDiscount;

    //
    @ManyToMany(mappedBy = "strategies")
    private List<User> users;
}