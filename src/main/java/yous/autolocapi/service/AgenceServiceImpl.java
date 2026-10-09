
package yous.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import yous.autolocapi.domain.Agence;
import yous.autolocapi.repository.AgenceRepository;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class AgenceServiceImpl implements IAgenceService {

    private final AgenceRepository agenceRepository;

    // READ : Recuperer toutes les agences
    @Override
    public List<Agence> retrieveAllAgences() {
        return StreamSupport.stream(
                agenceRepository.findAll().spliterator(),
                false
        ).toList();
    }

    // CREATE : Ajouter une agence
    @Override
    public Agence addAgence(Agence a) {
        a.setIdagence(null);
        return agenceRepository.save(a);
    }

    // UPDATE : Modifier une agence
    @Override
    public Agence updateAgence(Agence a) {

        if (a.getIdagence() == null ||
                !agenceRepository.existsById(a.getIdagence())) {
            throw new IllegalArgumentException(
                    "Agence inexistante"
            );
        }

        return agenceRepository.save(a);
    }

    // READ : Recuperer une agence par ID
    @Override
    public Agence retrieveAgence(Long idAgence) {
        return agenceRepository.findById(idAgence)
                .orElse(null);
    }

    // DELETE : Supprimer une agence
    @Override
    @Transactional
    public void removeAgence(Long idAgence) {

        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Agence inexistante"
                        )
                );

        if (!agence.getVehicules().isEmpty() ||
                !agence.getEmployes().isEmpty()) {
            throw new IllegalStateException(
                    "Impossible de supprimer une agence " +
                            "contenant des vehicules ou employes"
            );
        }

        agenceRepository.delete(agence);
    }

    // CREATE : Ajouter plusieurs agences
    @Override
    public List<Agence> addAgences(List<Agence> agences) {

        agences.forEach(a -> a.setIdagence(null));

        return StreamSupport.stream(
                agenceRepository.saveAll(agences)
                        .spliterator(),
                false
        ).toList();
    }
}
