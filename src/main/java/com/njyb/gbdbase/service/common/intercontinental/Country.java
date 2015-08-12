package com.njyb.gbdbase.service.common.intercontinental;

import java.io.Serializable;

/**
 * 国家model
 * @author Administrator
 *
 */
public class Country  implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 业务名称
	 */
    private String bizname;
   
    /**
     * 国家名称
     */
    private String countryname;

	public String getBizname() {
		return bizname;
	}
	
	public void setBizname(String bizname) {
		this.bizname = bizname;
	}
	
	public String getCountryname() {
		return countryname;
	}

	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bizname == null) ? 0 : bizname.hashCode());
		result = prime * result
				+ ((countryname == null) ? 0 : countryname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (bizname == null) {
			if (other.bizname != null)
				return false;
		} else if (!bizname.equals(other.bizname))
			return false;
		if (countryname == null) {
			if (other.countryname != null)
				return false;
		} else if (!countryname.equals(other.countryname))
			return false;
		return true;
	}

	
   
}
