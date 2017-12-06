package org.sdrc.scpsassam.repository.springdata;

import java.util.List;

import org.sdrc.scpsassam.domain.Area;
import org.sdrc.scpsassam.repository.AreaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;


@RepositoryDefinition(domainClass=Area.class,idClass=Integer.class)
public interface SpringDataAreaRepository extends AreaRepository{

	@Query(value="SELECT * FROM area a where a.level=1 UNION SELECT * FROM area b where b.parent_area_id= :stateId ",nativeQuery=true)
	public List<Area> findCountryAndStateByStateId(@Param("stateId")int stateId);

}
