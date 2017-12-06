package org.sdrc.scpsassam.repository;

import java.util.List;

import org.sdrc.scpsassam.domain.Indicator;
import org.sdrc.scpsassam.domain.IndicatorUnitSubgroup;
import org.sdrc.scpsassam.domain.Subgroup;
import org.sdrc.scpsassam.domain.Unit;

public interface IndicatorUnitSubgroupRepository {

	public IndicatorUnitSubgroup save(IndicatorUnitSubgroup ius);

	public IndicatorUnitSubgroup findByIndicatorUnitSubgroupId(int id);
	
	public IndicatorUnitSubgroup findByIndicatorAndUnitAndSubgroup(Indicator indicator,Unit unit,Subgroup subgroup);


	public List<IndicatorUnitSubgroup> findAll();
	
	public List<IndicatorUnitSubgroup> findAllByOrderByIndicatorUnitSubgroupIdAsc();
	
	
	
	

}
