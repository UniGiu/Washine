package uni.washine.application.data;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SamplePersonRepository
        extends
            JpaRepository<SamplePerson, Long>,
            JpaSpecificationExecutor<SamplePerson> {
	
	// Aggiungi una query personalizzata per recuperare gli utenti appartenenti a una comunità
    List<SamplePerson> findByCommunityId(String communityId);

}
