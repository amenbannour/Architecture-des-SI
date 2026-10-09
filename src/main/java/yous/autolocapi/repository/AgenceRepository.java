package yous.autolocapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import yous.autolocapi.domain.Agence;

@Repository
public interface AgenceRepository extends CrudRepository<Agence, Long> {

}