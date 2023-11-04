package co.udea.ssmu.api.model.jpa.model;

import java.io.Serializable;
import java.time.LocalDate;
<<<<<<< HEAD:src/main/java/co/udea/ssmu/api/model/jpa/model/Strategy.java
import java.util.List;
import co.udea.ssmu.api.model.jpa.model.User;
=======
>>>>>>> 03ef6cdca11fa684e2aec62710d7d97a90bbd006:src/main/java/co/udea/ssmu/api/model/jpa/model/strategy/Strategy.java
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
    @Column(name = "id_estrategia")
    private long idStrategy;

    @Column(name = "nombre")
    private @NonNull String name;

    @Column(name = "descripcion")
    private @NonNull String description;

    @Column(name = "porcentaje_descuento", nullable = true)
    private int discountPercentage;

    @Column(name = "estado")
    private Boolean isActive;

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
<<<<<<< HEAD:src/main/java/co/udea/ssmu/api/model/jpa/model/Strategy.java

    //
    @ManyToMany(mappedBy = "strategies")
    private List<User> users;
=======
>>>>>>> 03ef6cdca11fa684e2aec62710d7d97a90bbd006:src/main/java/co/udea/ssmu/api/model/jpa/model/strategy/Strategy.java
}