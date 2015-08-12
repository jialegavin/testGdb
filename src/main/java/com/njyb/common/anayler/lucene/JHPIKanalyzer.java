package com.njyb.common.anayler.lucene;

import java.io.Reader;
/**
 * 自定义分词器
 * @author 贾红平
 *
 */
public class JHPIKanalyzer extends IKAnalyzer {
	@Override
	public boolean useSmart() {
		return super.useSmart();
	}

	@Override
	public void setUseSmart(boolean useSmart) {
		super.setUseSmart(useSmart);
	}

	@Override
	protected TokenStreamComponents createComponents(String fieldName, Reader in) {
		return super.createComponents(fieldName, in);
	}

	@Override
	protected Reader initReader(String fieldName, Reader reader) {
		return super.initReader(fieldName, reader);
	}

	@Override
	public int getPositionIncrementGap(String fieldName) {
		return super.getPositionIncrementGap(fieldName);
	}

	@Override
	public int getOffsetGap(String fieldName) {
		return super.getOffsetGap(fieldName);
	}

	@Override
	public void close() {
		super.close();
	}

}
