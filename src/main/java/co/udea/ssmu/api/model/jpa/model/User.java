package co.udea.ssmu.api.model.jpa.model;

import java.util.List;
import co.udea.ssmu.api.model.jpa.model.Strategy;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "usuario", schema = "esteban")
public class User {
    @Id
    @Column(name = "cedula")
    private long document;

    @Column(name = "nombre")
    private @NonNull String name;
    
    @Column(name = "apellido")
    private @NonNull String lastName;
    
    @Column(name = "clasificacion")
    private @NonNull String userType;

    @ManyToMany
    @JoinTable(
        name = "estrategia_usuario",
        joinColumns = @JoinColumn(name = "cedula"),
        inverseJoinColumns = @JoinColumn(name = "id_estrategia")
    )
    private List<Strategy> strategies;
    // Al usuario se le aplica la lista de estrategias
}
