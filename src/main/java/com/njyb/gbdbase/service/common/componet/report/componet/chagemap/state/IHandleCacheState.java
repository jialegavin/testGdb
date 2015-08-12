package com.njyb.gbdbase.service.common.componet.report.componet.chagemap.state;

import java.util.List;
import java.util.Map;

import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;

public interface IHandleCacheState {

	public abstract void handleCacheState(String type,
			Map<String, List<DataReportSumModel>> rmp);

}