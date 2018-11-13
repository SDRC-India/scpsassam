package org.sdrc.scpsassam.service;

import org.sdrc.scpsassam.domain.Agency;


public interface AggregationService {

	public boolean startAggregation();

	public boolean createIndex(Agency agency, int year, int month);

	public boolean aggregateDataByAgency(Agency agency, int year, int month);

}
