<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <c:set var="root" value="${pageContext.request.contextPath }"/>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="${root}/static/css/newhomepage/bootstrap-theme.css" rel="stylesheet">
	<link href="${root}/static/css/newhomepage/bootstrap.css" rel="stylesheet">

    <link href="${root}/static/css/newhomepage/payment.css" rel="stylesheet">
	<script src="${root}/static/js/newhomepage/jquery-2.1.3.min.js"></script>  
	<script src="${root}/static/js/newhomepage/bootstrap.js"></script>
	<script src="${root}/static/js/newhomepage/bootstrap.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	function promptMessage(m){
		$("#myModalLabel").text('Prompt');
		document.getElementById("warn").style.display="block";
		document.getElementById("prog").style.display="none";
		document.getElementById("clossBtn").style.display="block";
		$("#warn").text(m);
	}
	function clearPrompt(){
		document.getElementById("warn").style.display="none";
		document.getElementById("prog").style.display="block";
		document.getElementById("clossBtn").style.display="none";
		$("#myModalLabel").text('Loading');
		$("#warn").text('');
	}
	function showModal(){
		var billFName = $("#billing-name1").val();
        var billLName = $("#billing-name2").val();
        var billAddr1 = $("#billing-address-line1").val();
        var billAddr2 = $("#billing-address-line2").val();
        var city = $("#city").val();
        var state = $("#state").val();
        var zip = $("#zip").val();
        var country = $("#country").val();
        var phoneNum = $("#phone-number").val();
        var cardNameF = $("#card-holder-firstname").val();
        var cardNameL=$("#card-holder-lastname").val();
        var cardNumber = $("#card-number").val();
        var cardMon = $("#expiry-month").val();
        var cardYear = $("#expiry-year").val();
        var cvv = $("#cvv").val();
        $("#bName").text(billFName+'  '+billLName);
        $("#bAdress1").text(billAddr1);
        $("#bAdress2").text(billAddr2);
        $("#bCity").text(city);
        $("#bState").text(state);
        $("#bZip").text(zip);
        $("#bCardNum").text(cardNumber);
        $("#bExpDate").text(cardMon+'/'+cardYear);
        $("#bCvv").text(cvv);
        $("#bCardName").text(cardNameF+'  ' +cardNameL);
        
        
        
		$('#invoice').modal('show');
		return false;
	}
    function submitPayment() { 
       clearPrompt();
    	$('#invoice').modal('hide');
       $('#myModal').modal('show');
   		
       $.post("${ctx}/gavin2", $("#wholeForm").serialize(),
			function(data){
    	   		if("0"==data){
    	   			promptMessage('Sorry, there is something wrong with your payment information. Please have a check.');
    	   		}else{
    	   			if("1"==data){
    	   			promptMessage('Submit successfully! Thanks for your payment!');
    	   			document.getElementById("loginToSystem").style.display="block";
    	   			}
    	   		}
						  }
					);
       return false;
    }
    function jumpToSystem(){
    	
    	window.location.replace("${ctx}/view/chinese/menu/country.jsp");
    }
</script>
  </head>
  
  <body>
  <div class="col-lg-12 text-center">
  <p></p><br><br>
        <a class="logo" href="${pageContext.request.contextPath }\view\newhomepage\index.jsp#home"><img class="img-square" src="${root}/static/img/newhomepage/INFORVELLOR LOGO.jpg" alt="Generic placeholder image" style="width: 640px; height: 150px;"></a>
        </div><br>
    
<div class="container">
<p></p><br><br>
<div class="span12 panel relative" style="display: block;">
    <form class="myform" id="wholeForm" role="form" onsubmit=" return showModal()">
    
    <fieldset>

     <legend>Billing Address</legend>


     <div class="pad-section col-md-10 col-md-push-1 text-left">

     <div class="form-group">
        <label class="col-sm-12 control-label" for="billing-name1"></label>
        
      </div>

      <div class="form-group">
        <label class="col-sm-2 control-label" for="billing-name1">First name:</label>
        <div class="col-sm-4">
          <input type="text" class="form-control" name="billing-name1" id="billing-name1" required="" placeholder="First Name">
        </div>
      </div>
        
        <div class="form-group">
        <label class="col-sm-2 control-label" for="billing-name2">Last name:</label>
        <div class="col-sm-4" >
          <input type="text" class="form-control" name="billing-name2" id="billing-name2" required="" placeholder="Last Name"><br>
        </div>
      </div>
        
      <div class="form-group">
        <label class="col-sm-3 control-label" for="billing-address-line">Billing address line1:</label>
        <div class="col-sm-9">
          <input type="text" class="form-control" name="billing-address-line1" id="billing-address-line1" required="" placeholder="billing address line1"><br>
        </div>
      </div>
        
      <div class="form-group">
        <label class="col-sm-3 control-label" for="shipping-address-line">Billing address line2:</label>
        <div class="col-sm-9">
          <input type="text" class="form-control" name="billing-address-line2" id="billing-address-line2"  placeholder="billing address line2"><br>
        </div>
      </div>
        
     <div class="form-group">
        <label class="col-sm-1 control-label" for="city">City:</label>
        <div class="col-sm-3">
          <input type="text" class="form-control" name="city" id="city" required="" placeholder="city">
        </div>
      </div>
        
     <div class="form-group">
        <label class="col-sm-1 control-label" for="shipping-address-line">State:</label>
        <div class="col-sm-3">
          <input type="text" class="form-control" name="state" id="state" required="" placeholder="State">
        </div>
      </div>
        
        <div class="form-group">
        <label class="col-sm-1 control-label" for="zip">ZIP:</label>
        <div class="col-sm-3">
          <input type="text" class="form-control" name="zip" id="zip" required="" placeholder="ZIP"><br>
        </div>
      </div>
        
         <div class="form-group">
        <label class="col-sm-1 control-label" for="country">Country:</label>
        <div class="col-sm-3">
          <input type="text" class="form-control" name="country" id="country" required="" placeholder="Country">
        </div>
      </div>
        
         <div class="form-group">
        <label class="col-sm-1 control-label" for="phone-number">Phone#:</label>
        <div class="col-sm-3">
          <input type="text" class="form-control" name="phone-number" id="phone-number" required="" placeholder="Phone number">
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-1 control-label" for="phone-number">Email:</label>
        <div class="col-sm-3">
          <input type="text" class="form-control" name="phone-number" id="phone-number" required="" placeholder="Email">
        </div>
      </div>
     </div>
    </fieldset>
    </form>
</div>
</div>




<div class="container">
<div class="span12 panel relative" style="display: block;">
<form class="myform" id="wholeForm" role="form" onsubmit=" return showModal()">
    <fieldset>
      <legend>Payment</legend>
      <div class="pad-section col-md-10 col-md-push-1 text-left">

     <div class="form-group">
        <label class="col-sm-12 control-label" for="billing-name1"></label>
        
      </div>
      <div class="form-group">
        <label class="col-sm-2 control-label" for="card-holder-name">First Name</label>
        <div class="col-sm-4">
          <input type="text" class="form-control" name="card-holder-firstname" id="card-holder-firstname" required="" placeholder="Card Holder's First Name">
        </div>
         <label class="col-sm-2 control-label" for="card-holder-name">Last Name</label>
        <div class="col-sm-4">
          <input type="text" class="form-control" name="card-holder-lastname" id="card-holder-lastname" required="" placeholder="Card Holder's Last Name"><br>
        </div>
      </div>
      <div class="form-group">
        <label class="col-sm-3 control-label" for="Visa">Credit card type</label>
        <div class="col-sm-3">
          <div class="row">
      <div class="col-xs-9">
              <select class="form-control col-sm-7" name="visa" id="visa" required>
                <option selected disabled hidden value=''>Select</option>
                <option value="1">Visa</option>
                <option value="2">MasterCard</option>
                <option value="3">Discover</option>
                <option value="4">American Express</option>
                
              </select>
            </div>
            </div>
            </div>
            </div>
      <div class="form-group">
        <label class="col-sm-2 control-label" for="card-number">Card Number</label>
        <div class="col-sm-4">
          <input type="text" class="form-control" name="card-number" id="card-number" required="" placeholder="Debit/Credit Card Number"><br>
        </div>
      </div>
      <div class="form-group">
        <label class="col-sm-3 control-label" for="expiry-month">Expiration Date</label>
        <div class="col-sm-9">
          <div class="row">
            <div class="col-xs-3">
              <select class="form-control col-sm-2" name="expiry-month" id="expiry-month" required>
                <option selected disabled hidden value=''>Month</option>
                <option value="1">Jan (01)</option>
                <option value="2">Feb (02)</option>
                <option value="3">Mar (03)</option>
                <option value="4">Apr (04)</option>
                <option value="5">May (05)</option>
                <option value="6">June (06)</option>
                <option value="7">July (07)</option>
                <option value="8">Aug (08)</option>
                <option value="9">Sep (09)</option>
                <option value="10">Oct (10)</option>
                <option value="11">Nov (11)</option>
                <option value="12">Dec (12)</option>
              </select>
            </div>
            <div class="col-xs-3">
              <select class="form-control" name="expiry-year" id="expiry-year" required>
                <option selected disabled hidden value=''>Year</option>
             
                <option value="2015">2015</option>
                <option value="2016">2016</option>
                <option value="2017">2017</option>
                <option value="2018">2018</option>
                <option value="2019">2019</option>
                <option value="2020">2020</option>
                <option value="2021">2021</option>
                <option value="2022">2022</option>
                <option value="2023">2023</option>
              </select>
            </div>
          </div><br>
        </div>
      </div>
      <div class="form-group">
        <label class="col-sm-3 control-label" for="cvv">Card CVV</label>
        <div class="col-sm-2">
          <input type="text" class="form-control" name="cvv" id="cvv" placeholder="Security Code" required="">
        </div>

       
      </div>
     </div>
     </div>

      

      

      <div id="contract" class="pad-section">
  <div class="container">
    <div class="row col-md-8 col-md-push-2 text-center">
    
    
      <div class="col-lg-12 text-center">
        <div class="panel panel-default">
          <div class="panel-heading">
      <h4 class="panel-title">
        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
          <b>
          Customer Agreement
          </b>
        </a>
      </h4>
    </div>
    
    <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
      <div class="panel-body text-left" style="width:698px;height:160px; overflow-y:scroll; border:0px solid;"><b>
        Inforvellor provides the most detailed data in a simple-to-use manner to help you Find Customers, Monitor Competitors, and Analyze Market Data. We give you timely product data including shipment information, buyer history, and market trends. We have connections with over 22 countries to help develop a dynamic trade database. We track nearly 730,000 active traders, which allows our detailed reports to continue expanding daily.
       </b>
      </div>
      
    </div>
  </div>
  </div>
  <div class="checkbox">

  <div class="col-lg-12 text-center">
    <label>
      <input type="checkbox" required="true"> I <b>accept</b> the Customer Agreement 
    </label>
</div>
     </div>
  </div>
 </div>


  <div class="form-group">
  
        <div class="col-sm-offset-3 col-sm-9 text-right">
          <button type="submit" class="btn btn-success">Place your order</button>
          <p></p><br><br>
        </div>
        
      </div>

      

      

      
    </fieldset>
    </form>
  </div>
  


        
    
<div class="modal fade  bs-example-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
       
        <h4 class="modal-title" id="myModalLabel">Loading...</h4>
      </div>
      <div class="modal-body">
		       <div class="progress" id="prog" >
		  			<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
		    			
		  			</div>
				</div>
				<div id = "warn" style="display:none">
				
				</div>
				
      </div>
      <div class="modal-footer" id="clossBtn" style="display:none">
        <button type="button" id="loginToSystem" class="btn btn-primary" data-dismiss="modal" onclick="jumpToSystem()" style="display:none" >Login</button>
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="clearPrompt()">Close</button>

       	
      </div>
      
    </div>
  </div>
</div>
<div class="modal fade bs-example-modal-lg" id="invoice" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
       <div class = "modal-body">
    <div class="row">
    
        <div class="col-lg-12">
            <div class="text-center">
                <i class="fa fa-search-plus pull-left icon"></i>
                <h2>Invoice for purchase</h2>
            </div>
            <hr>
            <div class="row">
                <div class="col-xs-12 col-md-4 col-lg-4 pull-left">
                    <div class="panel panel-default height">
                        <div class="panel-heading">Billing Details</div>
                        <div class="panel-body">
                        <span ><b id="bName"></b> </span>
                        <br>
                        <span id="bAdress1"> </span> 
                         <br>
                         <span id="bAdress2"> </span> 
                         <br>
                         <span id="bCity"></span>  
                         <br>
                         <span id="bState"></span>
                         <br>
                         <span id="bZip"></span>
                       
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-4 col-lg-4">
                    <div class="panel panel-default height">
                        <div class="panel-heading">Payment Information</div>
                        <div class="panel-body">
                        <span ><b>Card Type:</b></span>
                        <span id="bCardKind"> Visa</span>
                        <br>
                         <span ><b>Name:</b></span>
                        <span id="bCardName"> Visa</span>
                        <br>
                        <span ><b>Card Number:</b></span>
                        <span id="bCardNum"></span>
                        <br>
                         <span><b>Exp Date:</b></span>
                        <span id="bExpDate">09/2020</span>
                        <br>
                       <span ><b>Cvv:</b></span>
                        <span id="bCvv"></span>
                        <br>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-4 col-lg-4 pull-right">
                    <div class="panel panel-default height">
                        <div class="panel-heading">Account Information</div>
                        <div class="panel-body">
                            <span><b>Login Email:</b></span>
                          <span>${sessionScope.user.loginName} </span>
                          <br>
                           <span><b>Name:</b></span>
                           <span>${sessionScope.user.firstName }  ${sessionScope.user.lastName }</span>
                           <br>
                           <span><b>Expire Date:</b></span>
                           <span>${sessionScope.user.endTime }</span>
                           <br>
                         
                        </div>
                    </div>
                </div>
              
            </div>
        
    </div>
    </div>
    </div>
    <div class = "modal-body">
    <div class="row">
    
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="text-center"><strong>Order summary</strong></h3>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-condensed">
                            <thead>
                                <tr>
                                    <td><strong>Item Name</strong></td>
                                    <td class="text-center"><strong>Item Price</strong></td>
                                    <td class="text-center"><strong>Item Quantity</strong></td>
                                    <td class="text-right"><strong>Total</strong></td>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Inforvellor Data Service</td>
                                    <td class="text-center">$${sessionScope.moneyAmmount} </td>
                                    <td class="text-center">1</td>
                                    <td class="text-right">$${sessionScope.moneyAmmount} </td>
                                </tr>
                               
                                <tr>
                                    <td class="highrow"></td>
                                    <td class="highrow"></td>
                                    <td class="highrow text-center"><strong>Subtotal</strong></td>
                                    <td class="highrow text-right">$${sessionScope.moneyAmmount} </td>
                                </tr>
                                <tr>
                                    <td class="emptyrow"></td>
                                    <td class="emptyrow"></td>
                                    <td class="emptyrow text-center"><strong>Tax</strong></td>
                                    <td class="emptyrow text-right">$0</td>
                                </tr>
                                <tr>
                                    <td class="emptyrow"><i class="fa fa-barcode iconbig"></i></td>
                                    <td class="emptyrow"></td>
                                    <td class="emptyrow text-center"><strong>Total</strong></td>
                                    <td class="emptyrow text-right">$${sessionScope.moneyAmmount} </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    
                </div>
            </div>
            <div class="col-lg-12 text-center">
            <button type="button" onclick="submitPayment()" class="btn btn-success" >Submit</button>
            <button type="button" data-dismiss="modal" class="btn btn-success">Cancel</button>
        </div>
        
    </div>
    </div>
   </div>

<style>
.height {
    min-height: 200px;
}

.icon {
    font-size: 47px;
    color: #5CB85C;
}

.iconbig {
    font-size: 77px;
    color: #5CB85C;
}

.table > tbody > tr > .emptyrow {
    border-top: none;
}

.table > thead > tr > .emptyrow {
    border-bottom: none;
}

.table > tbody > tr > .highrow {
    border-top: 3px solid;
}
</style>

    </div>
  </div>

  </body>
</html>
