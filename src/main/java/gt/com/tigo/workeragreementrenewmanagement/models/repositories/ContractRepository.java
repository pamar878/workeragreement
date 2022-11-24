package gt.com.tigo.workeragreementrenewmanagement.models.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gt.com.tigo.workeragreementrenewmanagement.entities.TblContract;

@Repository
public interface ContractRepository extends CrudRepository<TblContract,Integer>{
	
	@Query("SELECT c FROM TblContract c WHERE c.idStatus = ?1")
	List<TblContract> findByStatusId(int idStatus);
	
	@Query("SELECT c FROM TblContract c WHERE c.idHorus = ?1")
	Optional<TblContract> findByHorusId(String horusId);

}
