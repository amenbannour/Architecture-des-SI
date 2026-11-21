
package yous.autolocapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import yous.autolocapi.domain.Vehicule;
import yous.autolocapi.domain.Reservation;
import yous.autolocapi.service.IJPQLService;

import java.util.List;

@RestController
@RequestMapping("/jpql")
@RequiredArgsConstructor
@Tag(
        name = "Atelier JPQL",
        description = "Recherches et comptages avec JPQL"
)
public class JPQLController {

    private final IJPQLService jpqlService;


    @Operation(summary = "Q1 - Vehicules par agence")
    @GetMapping("/vehicules/agence/{idAgence}")
    public List<Vehicule> getVehiculesByAgence(
            @PathVariable Long idAgence) {

        return jpqlService.getVehiculesByAgence(idAgence);
    }


    @Operation(summary = "Q2 - Reservations par client")
    @GetMapping("/reservations/client/{idClient}")
    public List<Reservation> getReservationsByClient(
            @PathVariable Long idClient) {

        return jpqlService.getReservationsByClient(idClient);
    }


    @Operation(summary = "Q3 - Nombre de vehicules par agence")
    @GetMapping("/vehicules/count-by-agence")
    public List<Object[]> countVehiculesByAgence() {

        return jpqlService.countVehiculesByAgence();
    }


    @Operation(summary = "Q4 - Nombre de reservations par client")
    @GetMapping("/reservations/count-by-client")
    public List<Object[]> countReservationsByClient() {

        return jpqlService.countReservationsByClient();
    }
}
