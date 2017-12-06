package org.sdrc.scpsassam.service;

import org.sdrc.scpsassam.domain.Agency;
import org.sdrc.scpsassam.util.FactsheetObject;

public interface FactsheetService {

	Object getPrefetchData(int agencyId);

	Object getFactSheetData(FactsheetObject factsheetObject,Agency agency);
}
