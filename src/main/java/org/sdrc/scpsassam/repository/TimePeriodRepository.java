package org.sdrc.scpsassam.repository;

import java.util.List;

import org.sdrc.scpsassam.domain.Agency;
import org.sdrc.scpsassam.domain.Timeperiod;

public interface TimePeriodRepository {

	public Timeperiod findByTimePeriod(String timePeriod);

	public Timeperiod save(Timeperiod timeperiod);

	public List<Timeperiod> findBySource_Nid(Integer iusNid, Integer sourceNid);

	public Timeperiod findByTimeperiodId(int timePeriod);

	public List<Timeperiod> findBySource_NidForPublicView(Integer iusNid, Integer sourceNid);

	List<Timeperiod> findAll();

	public List<Timeperiod> findTimePeriodsPresentForDataOfMyAgencyAllPublishedAndUnPublished(int agencyId);
	
	

}
