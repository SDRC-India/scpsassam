package org.sdrc.scpsassam.repository.springdata;

import org.sdrc.scpsassam.domain.PublishHistory;
import org.sdrc.scpsassam.repository.PublishHistoryRepository;
import org.springframework.data.repository.RepositoryDefinition;


@RepositoryDefinition(domainClass=PublishHistory.class,idClass=Integer.class)
public interface SpringDataPublishHistoryRepository extends PublishHistoryRepository{

}
