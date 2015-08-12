package com.njyb.service.paypal;

import com.paypal.api.payments.Payment;

/**
 * The interface used to operate some data when users making transaction.
 * @author hy_ho_000
 *
 */
public interface IPayService {
	/**
	 * change database of user status
	 */
	boolean updateUserCharge(Payment payment, String loginName,int model) throws Exception;
	/**
	 * used to save the charge information of user
	 * @param username
	 * @param method
	 */
	
	public boolean recordUserCharge(Payment payment, String loginName,int model);
	public int checkTransactionModel(Payment payment);


}
