
package yous.autolocapi.service;

import yous.autolocapi.domain.Vehicule;
import yous.autolocapi.domain.Reservation;

import java.util.List;

public interface IJPQLService {

    List<Vehicule> getVehiculesByAgence(Long idAgence);

    List<Reservation> getReservationsByClient(Long idClient);

    List<Object[]> countVehiculesByAgence();

    List<Object[]> countReservationsByClient();
}
