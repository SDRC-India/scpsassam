/**
 * 
 */
package org.sdrc.scpsassam.repository.springdata;

import org.sdrc.scpsassam.domain.UserAreaMapping;
import org.sdrc.scpsassam.repository.UserAreaMappingRepository;
import org.springframework.data.repository.RepositoryDefinition;



/**
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */

@RepositoryDefinition(domainClass=UserAreaMapping.class,idClass=Integer.class)
public interface SpringDataUserAreaMappingRepository extends UserAreaMappingRepository{

}
