package org.sdrc.scpsassam.repository;

import org.sdrc.scpsassam.domain.PublishHistory;

public interface PublishHistoryRepository {
	
	PublishHistory findByDataBeingPublishedForMonthAndDataBeingPublishedForYear(Integer month,Integer year);

	PublishHistory save(PublishHistory history);

}
