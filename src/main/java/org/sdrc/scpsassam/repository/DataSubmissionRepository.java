package org.sdrc.scpsassam.repository;

import java.util.List;

import org.sdrc.scpsassam.domain.DataSubmission;
import org.sdrc.scpsassam.domain.Facility;

public interface DataSubmissionRepository {

	public DataSubmission save(DataSubmission dataSubmission);

	public DataSubmission findByDataSubmissionId(int dataSubmissionId);

	public DataSubmission findByFacilityAndDataEnteredForMonthAndDataEnteredForYear(Facility facilty, int month, int year);
	
	public List<DataSubmission> findTop6ByFacilityOrderBySubmissionDateDesc(Facility facilty);

	public List<DataSubmission> findAllByDataEnteredForMonthAndDataEnteredForYearAndNoOfTimeDataEditedGreaterThanZero(Integer month, Integer year);

	public List<String> findByDistinctDataEnteredForMonthAndDataEnteredForYear();

	
}
