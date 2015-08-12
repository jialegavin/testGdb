package com.njyb.gbdbas.util.translate;
import java.util.List;
import com.njyb.gbdbas.util.translate.TransResult;

/**
 * 百度翻译
 * @author 洪皓
 * 2015-04-09
 * @version 标准版
 */
public class BaiduTrans {

	private String from;
	private String to;
	private List<TransResult> trans_result;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public List<TransResult> getTrans_result() {
		return trans_result;
	}

	public void setTrans_result(List<TransResult> trans_result) {
		this.trans_result = trans_result;
	}

}
