package com.njyb.gbdbase.controller.paypal;
import java.io.IOException;
import java.io.InputStream;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.service.paypal.IPayService;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import com.paypal.base.rest.PayPalResource;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;


@Controller
public class TransactionController {
	
	private static final Logger LOGGER = Logger
			.getLogger(TransactionController.class);
	
	private String clientID="AWNmrqSAZUOIaXpDB6XyM-_-QYLObFZD5rkKaTTWWcMOhGRSgulDLcJaEysdJ-2RK6I6I9milzkK6ZRF";
	private String clientSecret="EJqfq1FKth02hTPG122CjndgW4N7uJcx9XuIhEP1ioZWBUGteGZnuJi1MtSKeGQTMZZR5B093WCjL-0s";
	@Autowired
	private IPayService iUserService;

	@RequestMapping (value = "/creditCardTransaction")
    public void beginTransaction(HttpServletRequest request, HttpServletResponse response)
    throws Exception{
		
		LOGGER.info("before createPayment");
		Payment payment=createPayment(request,response);
		if(payment!=null)
		{
			try{
			//	LOGGER.info("afterCreatePayment");
			if(payment.getState().equals("approved")){
	//		LOGGER.info("payment create successfully");
				UserModel user=(UserModel)request.getSession().getAttribute("user");
				String loginName=user.getLoginName();
				int model=iUserService.checkTransactionModel(payment);
				iUserService.recordUserCharge(payment, loginName,model);
				iUserService.updateUserCharge(payment, loginName,model);
				response.getWriter().write("1");
			}
			}catch(Exception e){
				response.getWriter().write("0");
			}
			
		}
}
@RequestMapping(value="/saveMoneyAndCheckUser",method=RequestMethod.POST)
public void saveMoneyAndCheckUser(HttpServletRequest request,HttpServletResponse response, String money) throws IOException{
	UserModel user=(UserModel) request.getSession().getAttribute("user");
	String moneyNumber=null;
	if(money.equals("1")){
		moneyNumber="359";
	}else{
		if(money.equals("2")){
			moneyNumber="959";
		}else{
			if(money.equals("3")){
				moneyNumber="1759";
			}else{
				if(money.equals("4")) moneyNumber="3159";
			}
		}
	}
	request.getSession().setAttribute("moneyAmmount", moneyNumber);
	if(user==null||user.getLoginName()==null||user.getLoginName().equals("")){
		response.getWriter().write("0");//it means that the user do not exist we need user name to be typed in. 
	}else{
		response.getWriter().write("1");
	}
}

@RequestMapping (value="/gavin3")//method using paypal account to pay
public void transactionThroughPayPal(HttpServletRequest request, HttpServletResponse response) throws PayPalRESTException, IOException{
	Map<String, String> sdkConfig = new HashMap<String, String>();
	sdkConfig.put("mode", "sandbox");

	String accessToken = new OAuthTokenCredential(clientID, clientSecret,sdkConfig).getAccessToken();
	APIContext apiContext = new APIContext(accessToken);
	apiContext.setConfigurationMap(sdkConfig);
	
	Amount amount = new Amount();
	amount.setCurrency("USD");
	amount.setTotal("12");

	Transaction transaction = new Transaction();
	transaction.setDescription("TransactionsForInforvellor");
	transaction.setAmount(amount);

	List<Transaction> transactions = new ArrayList<Transaction>();
	transactions.add(transaction);

	Payer payer = new Payer();
	payer.setPaymentMethod("paypal");

	Payment payment = new Payment();
	payment.setIntent("sale");
	payment.setPayer(payer);
	payment.setTransactions(transactions);
	RedirectUrls redirectUrls = new RedirectUrls();
	redirectUrls.setCancelUrl("https://devtools-paypal.com/guide/pay_paypal?cancel=true");
	redirectUrls.setReturnUrl("https://www.inforvellor.com/index.jsp");
	payment.setRedirectUrls(redirectUrls);
	
	Payment createdPayment = payment.create(apiContext);
	
	Iterator<Links> links = createdPayment.getLinks().iterator();
	while (links.hasNext()) {
		Links link = links.next();
		if (link.getRel().equalsIgnoreCase("approval_url")) {
			//request.setAttribute("redirectURL", link.getHref());
			response.sendRedirect(link.getHref());
		}
	}
	//String link=createdPayment.getRedirectUrls();
	//System.out.print(link);
}

@RequestMapping(value="/successTransaction" ,params={"paymentId","token","payerId"} )//method to after transaction successfully.

public void successTransaction(HttpServletRequest request,HttpServletResponse response,String paymentId,String token,String payerId){

}

public Payment createPayment(HttpServletRequest req, HttpServletResponse resp) throws IOException{
	//LOGGER.info("begin transaction");
	String billAddress1=req.getParameter("billing-address-line1");
	String billAddress2=req.getParameter("billing-address-line2");
	String city=req.getParameter("city");
	String state=req.getParameter("state");
	String zip=req.getParameter("zip");
	String country=req.getParameter("country");
	String billFirstName=req.getParameter("billing-name1");
	String billLastName=req.getParameter("billing-name2");
	String phone=req.getParameter("phone-number");
	String cardType=req.getParameter("visa");
	String firstName=req.getParameter("card-holder-firstname");
	String lastName=req.getParameter("card-holder-lastname");
	String cardNumber=req.getParameter("card-number");
	String expireMonth=req.getParameter("expiry-month");
	String expireYear=req.getParameter("expiry-year");
	String cvv=req.getParameter("cvv");
	String moneyAmmount=(String) req.getSession().getAttribute("moneyAmmount");
	//LOGGER.info(lastName);
	Map<String, String> sdkConfig = new HashMap<String, String>();
	sdkConfig.put("mode", "sandbox");
	Address billingAddress = new Address();
	//billingAddress.setCity("Johnstown");
//	billingAddress.setCountryCode("US");
	//billingAddress.setLine1("52 N Main ST");
//	billingAddress.setPostalCode("43210");
//	billingAddress.setState("OH");
	billingAddress.setCity(city);
	billingAddress.setCountryCode("US");
	billingAddress.setLine1(billAddress1);
	billingAddress.setPostalCode(zip);
	billingAddress.setState(state);
	CreditCard creditCard = new CreditCard();
	creditCard.setBillingAddress(billingAddress);
	//creditCard.setCvv2(111);
	//creditCard.setExpireMonth(5);
	//creditCard.setExpireYear(2020);
	//creditCard.setFirstName("Joe");
	//creditCard.setLastName("Shopper");
	//creditCard.setNumber("4032034879726365");
//	creditCard.setType("visa");
	creditCard.setType(cardType);
//	LOGGER.info(cvv);
	//creditCard.setCvv2(Integer.parseInt(cvv));
	creditCard.setCvv2(Integer.parseInt(cvv));
	creditCard.setExpireMonth(Integer.parseInt(expireMonth));
	creditCard.setExpireYear(Integer.parseInt(expireYear));
	creditCard.setFirstName(firstName);
	creditCard.setLastName(lastName);
	creditCard.setNumber(cardNumber);
	Details details = new Details();
	details.setShipping("0");
	details.setSubtotal("0");
	details.setTax("0");
	Amount amount = new Amount();
	amount.setCurrency("USD");
	amount.setTotal(moneyAmmount);
	amount.setDetails(details);
	Transaction transaction = new Transaction();
	transaction.setAmount(amount);
	transaction
			.setDescription("TransactionsForInforvellor");
	List<Transaction> transactions = new ArrayList<Transaction>();
	transactions.add(transaction);
	FundingInstrument fundingInstrument = new FundingInstrument();
	fundingInstrument.setCreditCard(creditCard);
	List<FundingInstrument> fundingInstrumentList = new ArrayList<FundingInstrument>();
	fundingInstrumentList.add(fundingInstrument);
	Payer payer = new Payer();
	payer.setFundingInstruments(fundingInstrumentList);
	payer.setPaymentMethod("credit_card");
	Payment payment = new Payment();
	payment.setIntent("sale");
	payment.setPayer(payer);
	payment.setTransactions(transactions);
	Payment createdPayment = null;
	try{
		
		String accessToken = new OAuthTokenCredential(clientID, clientSecret,sdkConfig).getAccessToken();
		//String accessToken = GenerateAccessToken.getAccessToken();
		APIContext apiContext = new APIContext(accessToken);
		apiContext.setConfigurationMap(sdkConfig);

		createdPayment = payment.create(apiContext);
	
	
	} catch (PayPalRESTException e) {
		resp.getWriter().print("0");
		return null;
	}
	
	

	
	return createdPayment;
	
}

	
	
	
}
