package yous.autolocapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import yous.autolocapi.domain.Client;

@Repository

public interface ClientRepository extends CrudRepository <Client,Long>{



}
