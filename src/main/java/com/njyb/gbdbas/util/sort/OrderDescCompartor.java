package com.njyb.gbdbas.util.sort;
import java.util.Comparator;
import org.apache.commons.beanutils.BeanComparator;

/**
 * 反射自定义比较器
 * @auther honghao
 * 2015-04-27
 *
 */
public class OrderDescCompartor extends BeanComparator {
	public OrderDescCompartor(String property) {
		super(property);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public int compare(Object arg0, Object arg1) {
		return super.compare(arg1, arg0);
	}

	@Override
	public Comparator getComparator() {
		return super.getComparator();
	}

	@Override
	public String getProperty() {
		return super.getProperty();
	}

	@Override
	public void setProperty(String property) {
		super.setProperty(property);
	}
}
