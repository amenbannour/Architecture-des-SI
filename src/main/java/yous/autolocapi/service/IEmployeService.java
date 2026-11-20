
package yous.autolocapi.service;

import yous.autolocapi.domain.Employe;
import java.util.List;

public interface IEmployeService {

    // CRUD existant
    List<Employe> retrieveAllEmployes();

    Employe addEmploye(Employe e);

    Employe updateEmploye(Employe e);

    Employe retrieveEmploye(Long idEmploye);

    void removeEmploye(Long idEmploye);

    List<Employe> addEmployes(List<Employe> employes);

    // Nouvelles methodes : scenario 1
    Employe desaffecterEmploye(Long idEmploye);

    Employe affecterEmploye(Long idEmploye, Long idAgence);
}
