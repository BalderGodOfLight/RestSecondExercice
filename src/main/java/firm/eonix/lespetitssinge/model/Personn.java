package firm.eonix.lespetitssinge.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
public class Personn {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;

    @Column(name = "nom")
    public String name;

    @Column(name = "prenom")
    public String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personn personn = (Personn) o;
        return Objects.equals(id, personn.id) && Objects.equals(name, personn.name) && Objects.equals(email, personn.email);
    }


}
