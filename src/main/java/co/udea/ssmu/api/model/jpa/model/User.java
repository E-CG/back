package co.udea.ssmu.api.model.jpa.model;

import java.io.Serializable;
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
public class User implements Serializable{
    @Id
    @Column(name = "cedula")
    private long document;

    @Column(name = "nombre")
    private @NonNull String name;
    
    @Column(name = "apellido")
    private @NonNull String lastName;
    
    @Column(name = "clasificacion")
    private @NonNull String userType;
}
