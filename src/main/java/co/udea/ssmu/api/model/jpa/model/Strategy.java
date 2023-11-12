package co.udea.ssmu.api.model.jpa.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "estrategia", schema = "esteban")
public class Strategy implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_estrategia", length = 3, nullable = false, unique = true, updatable = false)
    private Long idStrategy;

    @Column(name = "nombre", nullable = false, length = 50)
    private String name;

    @Column(name = "descripcion", nullable = false, length = 50)
    private String description;

    @Column(name = "porcentaje_descuento", nullable = false, length = 3)
    private int discountPercentage;

    @Column(name = "estado", nullable = false, length = 1)
    private Boolean isActive;

    @Column(name = "ciudad", nullable = false, length = 50)
    private String city;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "valor_descuento", nullable = true, length = 5)
    private int discountValue;

    @Column(name = "valor_minimo", nullable = true, length = 5)
    private int minValue;

    @Column(name = "descuento_maximo", nullable = false, length = 5)
    private int maxDiscount;

    @Column(name = "tipo_usuario", nullable = false, length = 20)
    private String userType;

    @ManyToMany(mappedBy = "strategies")
    private List<User> users;

}