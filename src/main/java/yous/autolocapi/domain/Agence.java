package yous.autolocapi.domain;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agence {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idagence;

    private String nom;
    private String ville;
    private String adresse;
    private String telephone;
    @OneToMany(mappedBy = "agence", cascade = CascadeType.ALL)
    private Set<Vehicule> vehicules;

    @OneToMany(mappedBy = "agence", cascade = CascadeType.ALL)
    private Set<Employe> employes;
}
