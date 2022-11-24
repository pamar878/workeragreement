package gt.com.tigo.workeragreementrenewmanagement.models.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gt.com.tigo.workeragreementrenewmanagement.entities.TblPlatform;

@Repository
public interface PlatformRepository extends CrudRepository<TblPlatform,Integer>{
	
	@Query("SELECT c FROM TblPlatform c WHERE c.name = ?1")
	Optional<TblPlatform> findPlatformByName(String name);

}
