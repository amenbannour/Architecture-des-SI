
package yous.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import yous.autolocapi.domain.Agence;
import yous.autolocapi.domain.Employe;

import yous.autolocapi.repository.AgenceRepository;
import yous.autolocapi.repository.EmployeRepository;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements IEmployeService {

    private final EmployeRepository employeRepository;
    private final AgenceRepository agenceRepository;

    // Recuperer tous les employes
    @Override
    public List<Employe> retrieveAllEmployes() {
        return StreamSupport.stream(
                employeRepository.findAll().spliterator(),
                false
        ).toList();
    }

    // Ajouter un employe
    @Override
    public Employe addEmploye(Employe e) {
        return employeRepository.save(e);
    }

    // Modifier un employe
    @Override
    public Employe updateEmploye(Employe e) {
        return employeRepository.save(e);
    }

    // Rechercher un employe
    @Override
    public Employe retrieveEmploye(Long idEmploye) {
        return employeRepository.findById(idEmploye)
                .orElse(null);
    }

    // Supprimer un employe
    @Override
    public void removeEmploye(Long idEmploye) {
        employeRepository.deleteById(idEmploye);
    }

    // Ajouter plusieurs employes
    @Override
    public List<Employe> addEmployes(List<Employe> employes) {
        return StreamSupport.stream(
                employeRepository.saveAll(employes).spliterator(),
                false
        ).toList();
    }

    // SCENARIO 1 : Desaffecter un employe
    @Override
    @Transactional
    public Employe desaffecterEmploye(Long idEmploye) {

        Employe employe = employeRepository.findById(idEmploye)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Employe introuvable : " + idEmploye
                        )
                );

        // Retirer le lien avec l'ancienne agence
        employe.setAgence(null);

        // Enregistrer la modification
        return employeRepository.save(employe);
    }

    // SCENARIO 1 : Affecter un employe a une agence
    @Override
    @Transactional
    public Employe affecterEmploye(
            Long idEmploye,
            Long idAgence) {

        Employe employe = employeRepository.findById(idEmploye)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Employe introuvable : " + idEmploye
                        )
                );

        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Agence introuvable : " + idAgence
                        )
                );

        // Verifier si l'employe est deja affecte
        if (employe.getAgence() != null) {
            throw new IllegalStateException(
                    "L'employe est deja affecte. " +
                            "Il faut d'abord le desaffecter."
            );
        }

        // Affecter l'employe a la nouvelle agence
        employe.setAgence(agence);

        // Enregistrer la nouvelle affectation
        return employeRepository.save(employe);
    }
}
