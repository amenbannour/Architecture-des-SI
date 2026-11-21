
package yous.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import yous.autolocapi.domain.Vehicule;
import yous.autolocapi.domain.Reservation;

import yous.autolocapi.repository.VehiculeRepository;
import yous.autolocapi.repository.ReservationRepository;
import yous.autolocapi.repository.AgenceRepository;
import yous.autolocapi.repository.ClientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JPQLServiceImpl implements IJPQLService {

    private final VehiculeRepository vehiculeRepository;
    private final ReservationRepository reservationRepository;
    private final AgenceRepository agenceRepository;
    private final ClientRepository clientRepository;

    // EXERCICE 1
    @Override
    public List<Vehicule> getVehiculesByAgence(Long idAgence) {
        return vehiculeRepository.getVehiculesByAgence(idAgence);
    }

    // EXERCICE 2
    @Override
    public List<Reservation> getReservationsByClient(Long idClient) {
        return reservationRepository.getReservationsByClient(idClient);
    }

    // EXERCICE 3
    @Override
    public List<Object[]> countVehiculesByAgence() {
        return agenceRepository.countVehiculesByAgence();
    }

    // EXERCICE 4
    @Override
    public List<Object[]> countReservationsByClient() {
        return clientRepository.countReservationsByClient();
    }
}
