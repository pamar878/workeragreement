package gt.com.tigo.workeragreementrenewmanagement.models.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gt.com.tigo.workeragreementrenewmanagement.entities.TblParameters;

@Repository
public interface ParametersRepository extends CrudRepository<TblParameters,Integer> {
	List<TblParameters> findAll();
}
