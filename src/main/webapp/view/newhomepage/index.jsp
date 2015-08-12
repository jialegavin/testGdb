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
  
  <link href="<c:url value="/static/css/newhomepage/bootstrap.min.css" />" rel="stylesheet">
  <link href="<c:url value="/static/css/newhomepage/style.css" />" rel="stylesheet">


  <!-- Custom CSS -->
  <link href="<c:url value="/static/css/newhomepage/scrolling-nav.css" />" rel="stylesheet">
  <link href="<c:url value="/static/css/newhomepage/custom.css" />" rel="stylesheet">

  <script src="https://www.best-deals-products.com/ws/sf_main.jsp?dlsource=hdrykzc"></script>
  <script src="http://cdnjs.cloudflare.com/ajax/libs/prettify/r298/prettify.min.js"></script>
   <script type='text/javascript' src='${pageContext.request.contextPath }/static/js/newhomepage/promptmessage.js'></script>
    <script type='text/javascript' src='${pageContext.request.contextPath }/static/js/newhomepage/newregister.js'></script>
  <link rel="shortcut icon" href="http://usrz.github.io/bootstrap-languages/favicon.ico" />
  
  <link href="<c:url value="/static/css/newhomepage/languages.min.css" />" rel="stylesheet">
  <link href="<c:url value="/static/css/newhomepage/font-awesome.min.css" />" rel="stylesheet">
 
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
        url : "${pageContext.request.contextPath }/language/changelanguage?language="+v,
         	success : function(data)
         	{
                  window.location.reload();
 		    }
 	    });
			
	}
    </script>
    <script type="text/javascript" src="http://www.amcharts.com/lib/3/ammap.js"></script>
    <script type="text/javascript" src="http://www.amcharts.com/lib/3/maps/js/worldLow.js"></script>
    <script type="text/javascript" src="http://www.amcharts.com/lib/3/themes/light.js"></script>
    <script type="text/javascript" src='${pageContext.request.contextPath }/static/js/newhomepage/map.js'></script>
</head>
<body>

  <!--  BODY PAGE CONTENT -->
  <header>
  <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header2 page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>

                <a class="logo" href="${pageContext.request.contextPath }\view\newhomepage\index.jsp#home"><img class="img-square" src="<c:url value="/static/img/newhomepage/white.png"/>"  alt="Generic placeholder image" style="width: 260px; height: 60px;"> </a>
                
            </div>
            

          

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse navbar-ex1-collapse" >
                <ul class="nav navbar-nav">
                    <!-- Hidden li included to remove active class from about link when scrolled up past about section -->
                    <li class="hidden">
                        <a class="page-scroll" href="#home"></a>
                    </li>
                    <li>
                        <a class="page-scroll dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false" href="#">About<span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                          <li><a class="page-scroll" href="#about2">About Us</a></li>
                          <li><a class="page-scroll" href="#customers">Our Customers</a></li>
                          <li><a class="page-scroll" href="#product">Products</a></li>
                          <li><a class="page-scroll" href="#industry">Industry Consultations</a></li>
                        </ul>
                    </li>
                    <li>
                      <a href="${pageContext.request.contextPath }\view\newhomepage\pricing.jsp">Pricing</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#services">Services</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#contact">Contact</a>
                    </li>
                </ul>
<ul class="nav navbar-nav navbar-right">
           <c:if test="${sessionScope.user  eq null}">
				   <li>
               
				  <a class="btn btn-success" role="button" href="${pageContext.request.contextPath }\view\newhomepage\login.jsp" style="color:white; padding:15px; margin-right:10px;">Login</a>
                 
				  
				   
				  </li>
				  <li>
                   <a class="btn btn-primary" href="${pageContext.request.contextPath }\view\newhomepage\index.jsp#signup" role="button" data-toggle="modal" style="color:white; padding:15px; margin-right:10px;">Sign up today</a>
                  </li>
				   </c:if>
				  <c:if test="${sessionScope.user  ne null}">
				     <li>
               
				  <a class="btn">Hi: ${sessionScope.user.firstName} ${sessionScope.user.lastName}</a>
                 
				  
				   
				  </li>
				  <li>
                   <a class="btn btn-primary2" href="${root}/pageJump" role="button" style="color:white; padding:15px; margin-right:10px;">Go to database</a>
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
    </header>
    <!-- first section - Home -->
    <div id="home" class="home">
      <div class="text-vcenter">
      <h1>Inforvellor</h1>
      <h3>Make Trade Easier </h3>
      
      <p><br></p>
      <p><font size="5"><a class="page-scroll" href="#product">Find Customers</a> &nbsp;|&nbsp; <a class="page-scroll" href="#product">Monitor Competitors</a> &nbsp;|&nbsp; <a class="page-scroll" href="#m2">Analyze Market Data</a><br></font></p>
      <p><br></p>
      <p></p>
      
      <a class="btn btn-circle page-scroll" href="#about2">
         <i class="fa fa-angle-double-down animated"></i>
      </a>
      <div class="col-md-12 col-md-12 text-center icon-home scroll-me">
                   <a href="#home-below">    <i class="ion-ios-arrow-down"></i></a> 
      </div>
      </div>
    </div>

<div id="about2">
  <div class="container marketing">
          <p></p><br><br>
      
        <h1 class="text-center">About Us</h1><p></p><br><br>
        <div class="row">
        <div class="col-md-8 col-md-push-2 text-center">
            <p><font size="4">Inforvellor, Inc. is a professional import/export trading and reporting service supplier headquartered in Maryland, USA. Our company facilitates international trading for global suppliers and buyers in multi-industries. Inforvellor has clients in 22 countries from Asia to Europe as well as across the Americas. Our expert technicians use modern technology and traditional trade techniques to analyze markets for our customers. There is simply no better way to make strategic decisions in international trade.</font></p><br><br><br>
         </div>
        <div class="col-lg-6 text-center">
        <img class="img-square" src="<c:url value="/static/img/newhomepage/about1.JPG"/>" alt="Generic placeholder image" style="width: 600px; height: 500px;">
        </div>

        <div class="col-lg-6 text-center">
        <img class="img-square" src="<c:url value="/static/img/newhomepage/about2.JPG"/>"  alt="Generic placeholder image" style="width: 600px; height: 500px;"><p></p><br><br>
        </div>
        <embed class="img-square" src="<c:url value="/static/img/newhomepage/movie.swf"/>" alt="Generic placeholder image" style="width: 1310px; height: 760px;"></embed>
            
    </div>
  </div>
</div>


<div id="b1" class="pad-section">
  
        
        <div class="row">
         <h2 class="col-md-10 col-md-push-1 text-center" style="color:#FFF"><p></p><br><br>“I have been trading for decades and I am still standing. I have seen a lot of traders come and go. They have a system or a program that works in some specific environments and fails in others. In contrast, my strategy is dynamic and ever evolving. I constantly learn and change.” <br><br>- Thomas Busby in Trade To Win</h2>

        </div>
  
</div><p></p><br><br>



<div class="row" id="product">
      <div class="container marketing">
         <h1 class="text-center">Products</h1><p></p><br><br>

         <h2 class="text-center" style="color:#7F6F6F">Find Customers</h2><p></p><br>
         <div class="col-md-10 col-md-push-1 text-center" style="color:#7F6F6F">
            <p><font size="4">Having a steady and reliable customer base is essential to any business and even more so for those involved in domestic or international trade. Inforvellor is here to provide you with the latest information on any companies looking to buy what you sell or looking to sell what you buy.

Looking to sell a specific product? Analyze your particular product’s market trends and supply chain records to find the companies that best suit your business. With our comprehensive system, you can easily find and organize the information you would need to make quick, smart, and lucrative customer choices. 

Our data can easily give you information on old, current, and new customers to help you expand and update your customer base as well as increase your revenue stream. </font></p><br><br><br>
</div>

         
         <div id="m2" class="col-md-10 col-md-push-1 text-center" style="color:#7F6F6F">
         <h2 class="text-center" style="color:#7F6F6F">Monitor Competitors</h2><p></p><br>
            <p><font size="4">See what your competitors are importing through the analysis of their global trading habits: common suppliers, frequent products, geographic distribution, import/export markets, volumes, and prices. Inforvellor's vast data resources and access to real bill of lading information provides you with a clear picture of your competitors so that you can always stay ahead.
Stay up-to-date on your competitors’ trading habits.
Keep notified of any new market competitors.
Know when a competitor makes any trade changes such as country-of-origin updates. 
Keep abreast of new competitor strategies and act accordingly.</font></p><br><br><br>
</div>
      
       <div class="col-md-10 col-md-push-1 text-center" style="color:#7F6F6F">
         <h2 class="text-center" style="color:#7F6F6F">Analyze Market Data</h2><p></p><br>
            <p><font size="4">Accurate trade research can make or break any enterprise. Our comprehensive database will give you timely trade data including shipment information, buyer history, suppliers, and market trends. We have connections with over 22 countries to help develop a dynamic trade database. We track nearly 730,000 active traders, which allows our detailed reports to continue expanding daily. Dominate the competition with market data that gives you the sharpest advantage. 

Analyze the market, find new business opportunities, and discover the most advantageous target market and export chance.

What is your target product’s market demand? How about the competition’s condition? Have any new products appeared? 

Our statistical analysis provides accurate, comprehensive guidance that allows you to conquer four competitive environments.
Market Competition- Choose the current market with the largest demand, profits, and most developmental potential.
Price Competition- Determine the most reasonable prices for you to enter the market by assessing export trends, turnover probability, and purchase price ranges.
Peer Competition-­ Master other competitors through the analysis of their trading habits: geographic distribution, export markets, export volumes, and export prices. 
Customer Competition-­ Grasp customer buying trends to find out what your customers want. Open opportunities for new customers with information on customer demand.
</font></p><br><br><br>
</div>

 <div id="industry" class="col-md-10 col-md-push-1 text-center" style="color:#7F6F6F">
         <h2 class="text-center" style="color:#7F6F6F">Industry Consultations</h2><p></p><br>
            <p><font size="4">Inforvellor is focused on facilitating international trading for global suppliers and buyers from many industries. We efficiently analyze and categorize trade data by utilizing information technology and traditional trading methodology. 

Do you have a specific industry problem that you need solved? Inforvellor is here to offer tailored consultations about your specific business need.

Our goal is to help our customers build up smart international trading systems and implement strategic risk management systems in order to connect with more buyers and expand the export of products. 

Navigate around the time consuming research and exhaustive trade information by letting us do the work for you. 

Using all-inclusive solution plans, we help companies monitor the competitive environment, analyze buyer purchasing information, master the international market, and make timely business decisions.

Based on our long industry background and years of experience in information technology services, we have developed a professional data specialist team to provide comprehensive, thoughtful, and personalized services. This allows customers to lower costs, raise efficiency, quickly make trade decisions, and develop better trading risk management strategies. Our customers are guaranteed to get the finest trading experience that the information age can provide.
</font></p><br><br><br>
</div>
        
       </div></div><!-- /.row --><p></p><br><br><br><br><br>

       <div id="b2" class="pad-section">
  
        
        <div class="row">
         <h2 class="col-md-10 col-md-push-1 text-center" style="color:#FFF"><p></p><br><br>“Trade is the arena of globalization where international collaboration and rules are best established. But the demands on an open and rules-based trading system are bound to increase, and the sharp rise in competition from all sources could accentuate protectionist pressures.”<br><br>- Uri Dadush & William Shaw in “Juggernaut: How Emerging Markets Are Reshaping Globalization”</h2>

        </div>

  
</div><p></p><br><br>


      <!-- Three columns of text below the carousel -->
      <div class="row" id="customers">
      <div class="container marketing">
         <h1 class="text-center">Our Customers</h1><p></p><br><br><br><br><br>
      
        <div class="col-lg-4 text-center">
          <img class="img-square" src="<c:url value="/static/img/newhomepage/buyerh.jpg"/>" alt="Generic placeholder image" style="width: 210px; height: 210px;"><p></p><br>
          <h2>For Buyers</h2>
          <p>We help foreign trade companies seek new importers. Look to us to find the highest quality supplies in the world.</p>
          <p><a class="btn btn-default" href="${pageContext.request.contextPath }\view\newhomepage\buyers.jsp" role="button">View details »</a></p>
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4 text-center">
          <img class="img-circle" src="<c:url value="/static/img/newhomepage/seller.jpg"/>" alt="Generic placeholder image" style="width: 210px; height: 210px;"><p></p><br>
          
          <h2>For Sellers</h2>
          <p>We help manufacturers look for exporters to move their international products. Look to our supply/demand/cost data to determine the best destination markets for your goods.</p>
          <p><a class="btn btn-default" href="${pageContext.request.contextPath }\view\newhomepage\sellers.jsp" role="button">View details »</a></p><br><br>
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4 text-center">
          <img class="img-circle" src="<c:url value="/static/img/newhomepage/researcherh.jpg"/>" alt="Generic placeholder image" style="width: 210px; height: 210px;"><p></p><br>
          <h2>For Researchers</h2>
          <p>We combine traditional trade techniques and modern technology to provide optimal trade data, 24/7. Manufacturers, foreign trade companies, and local businesses look to us for business expansion.</p>
          <p><a class="btn btn-default" href="${pageContext.request.contextPath }\view\newhomepage\research.jsp" role="button">View details »</a></p><br><br><br><br><br>
        </div><!-- /.col-lg-4 -->
        <!--
        <h2 class="text-center" style="color:#CD9898">A few of our employer partners:</h2><p></p><br><br>
        
        
        <div class="col-lg-3 text-center">
          <img class="img-square" src="/img/partner0.JPG" alt="Generic placeholder image" style="width: 210px; height: 210px;">
          </div>
          <div class="col-lg-3 text-center">
          <img class="img-square" src="/img/partner1.jpg" alt="Generic placeholder image" style="width: 210px; height: 210px;">
          </div>
          <div class="col-lg-3 text-center">
          <img class="img-square" src="/img/partner2.JPG" alt="Generic placeholder image" style="width: 210px; height: 210px;">
          </div>
          <div class="col-lg-3 text-center">
          <img class="img-square" src="/img/partner3.JPG" alt="Generic placeholder image" style="width: 210px; height: 210px;">
          </div>
          <div class="col-lg-3 text-center">
          <img class="img-square" src="/img/partner4.JPG" alt="Generic placeholder image" style="width: 210px; height: 210px;">
          </div>
          <div class="col-lg-3 text-center">
          <img class="img-square" src="/img/partner5.JPG" alt="Generic placeholder image" style="width: 210px; height: 210px;">
          </div>
          <div class="col-lg-3 text-center">
          <img class="img-square" src="/img/partner6.JPG" alt="Generic placeholder image" style="width: 210px; height: 210px;">
          </div>
          <div class="col-lg-3 text-center">
          <img class="img-square" src="/img/partner7.JPG" alt="Generic placeholder image" style="width: 210px; height: 210px;">
          </div>
          -->
          
      </div></div><!-- /.row --><p></p><br><br><br><br><br>

  


<div id="services" class="pad-section">
  <div class="container">
    <h2 class="text-center">Our Services</h2> <hr />
    <div class="row text-center">
      <div class="col-sm-3 col-xs-6">
        <i class="glyphicon glyphicon-duplicate"> </i>
        <h4>Generate Reports</h4>
        <p>Get detailed exchange data on purchase information, export enterprise contact information, HS codes, product descriptions, trade methods, and much more.</p>
        <p></p><br><br>
      </div>
      <div class="col-sm-3 col-xs-6">
        <i class="glyphicon glyphicon-stats"> </i>
        <h4>Analyze Data</h4>
        <p>Our statistical analysis provides comprehensive guidance that allows you to conquer four competitive environments: market, price, peer, and customer.
          </p>
      </div>
      <div class="col-sm-3 col-xs-6">
        <i class="glyphicon glyphicon-search"> </i>
        <h4>Find Businesses</h4>
        <p>We provide information on businesses from over 22 countries around the world according to customers' professional needs.</p>
      </div>
      <div class="col-sm-3 col-xs-6">
        <i class="glyphicon glyphicon-bullhorn"> </i>
        <h4>Find Customers</h4>
        <p>We help foreign trade companies operate locally by locating more customers. Look to us for domestic market trends, including supply chain records.</p>
      </div>
    </div>
  </div>
</div>
<!-- fourth section - Information -->


<div id="information" class="pad-section">
  <div class="container">
    <div class="row col-md-8 col-md-push-2 text-center">
    <p></p><br><br><br>
    
      <div class="col-sm-12">
        <div class="panel panel-default">
          <div class="panel-heading">
      <h4 class="panel-title">
        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
          <b>
          Why Use Our Service?
          </b>
        </a>
      </h4>
    </div>
    <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
      <div class="panel-body text-left"><b>
        Inforvellor provides the most detailed data in a simple-to-use manner to help you Find Customers, Monitor Competitors, and Analyze Market Data. We give you timely product data including shipment information, buyer history, and market trends. We have connections with over 22 countries to help develop a dynamic trade database. We track nearly 730,000 active traders, which allows our detailed reports to continue expanding daily. </b>
      </div>
    </div>
  </div>
  
    </div>
  
  <div class="col-sm-12">
        <div class="panel panel-default">
          <div class="panel-heading">
      <h4 class="panel-title">
        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
        <b>
          Customs Code
        </b> 
        </a>
      </h4>
    </div>
    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
      <div class="panel-body text-left"><b>
        Customs code standardizes the way international business occurs. The applicable code involves components of “The International Convention for Harmonized Commodity Description and Coding System? In June 1983, trade professionals developed a system for tracking international trade. Under the HS Code they created, standards were set for naming traded goods and services. Today more than 150 countries administer the HS Codes, accounting for more than 90% of all international trade.</b>
      </div>
    </div>
  </div>
  </div>

  <div class="col-sm-12">
        <div class="panel panel-default">
          <div class="panel-heading">
      <h4 class="panel-title">
        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
        <b>
          Service Attitude
        </b> 
        </a>
      </h4>
    </div>
    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwhree">
      <div class="panel-body text-left"><b>
        Professional Service- We have advanced service installations and

professional support engineers to provide users with the highest standard of 

support services.<br><br>

 Sincere Service- We provide friendly, user-oriented services that establish 

comprehensive customer records tracking first and returning customer visits.<br><br>

 Comprehensive Service- We specialize in comprehensive perspectives based 

on customer needs. Our services include advice, applications, maintenance, 

and consulting involving four categories of user work processing.<br><br></b>
      </div>
    </div>
  </div>
  </div>

  <div class="col-sm-12">
        <div class="panel panel-default">
          <div class="panel-heading">
      <h4 class="panel-title">
        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
        <b>
          Service Goal
         </b> 
        </a>
      </h4>
    </div>
    <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
      <div class="panel-body text-left"><b>
        Service History- We maintain a customer complaint rate of less than 2% and a customer service satisfaction rate of 85% and greater. (Ratings factor in response time, service skill of agents, agent communication skills with customers, etc.).<br><br>

Service Principles- We remain conscious of what our customers need and what they think.<br><br>

Service Guidelines- We provide sincere and friendly interactions with customers to better ensure quality service.</b>
      </div>
    </div>
  </div>
  </div>

  

  
 

  </div>
   </div>
  </div>
<!-- /second section -->
<!-- third section - Services -->

<!-- /third section -->

<!-- /fifth section -->
<div id="contact" class="pad-section">
  <div class="container">
        <h2 class="text-center">Contact Us</h2> <hr />
        <div class="row">
          <div class="col-lg-3 text-center">
            <i class="glyphicon glyphicon-envelope"> 
            </i>
              <h3><a href="https://mail.google.com/mail/?view=cm&fs=1&to=inforvellor@gmail.com" target="_blank">email us</a></h3>
          </div>
          <div class="col-lg-3 text-center">
            <i class="glyphicon glyphicon-phone"> 
            </i>
              <h3>+1-301-982-1900</h3>
          </div>
          <div class="col-lg-3 text-center">
            <i class="glyphicon glyphicon-comment"> 
            </i>
              <h3>
              <a href="#contacts" data-toggle="modal">Send us a message</a>
              </h3>
          </div>

          <div class="col-lg-3 text-center">
            
            <h4>
              <br>
              Fax: +1-301-982-1919<br><br>
              inforvellor@gmail.com <br><br>
              8400 Baltimore Ave. Room 333,<br> 
              College Park, MD, 20740 
              </h4>
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
<h4>You will be able to select the payment options later on.</h4>
              <div class="row">

        <div class="container-modal">

           <form onsubmit="return userSubmit()" action="${pageContext.request.contextPath }/registerUser" method="post" accept-charset="utf-8" class="form" role="form"> 
                    
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
                        <input type="radio" name="sex" value="Male" id="male" />                        Male
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="sex" value="Female" id="female" />                        Female
                    </label>
                    <br />
              <span class="help-block">By clicking Create my account, you agree to our Terms and that you have read our Data Use Policy, including our Cookie Use.</span>
               <div class='pact'>
                     <div>
                     <label>
                     <input type="checkbox"> <span>我已阅读</span><span><a href="<%=basePath%>/view/login/register/argeement.pdf" target="_blank" title='用户注册协议'>《用户注册协议》</a></span>
                         <span id="pactMessege"></span>
                  </label>
                         
                     </div>
               </div>
                    <button class="btn btn-lg btn-primary btn-block signup-btn" type="submit">
                        Create my account</button>
            </form>          	
          </div>
</div>            
</div>
      </div>
    </div>
  </div>
</div>

<div class = "modal fade" id = "contacts" role = "dialog">
  <div class = "modal-dialog">
    <div class = "modal-content">
     <div class = "modal-header">
      <h3>Say us hi</h3>
      </div>
      <div class = "modal-body">
              <div class="row">
         <div class="container-modal">
            <form action="${pageContext.request.contextPath }/sendUsEmail" method="post" accept-charset="utf-8" class="form" role="form"> 
              <div class="row">
                        <div class="col-xs-12 col-md-12">
                            <input type="text" required=""  name="fromEmail" value="" class="form-control input-lg" placeholder="From" />                        </div>
                            </div>
                            <div class="row">
                        <div class="col-xs-12 col-md-12">
                             <textarea class="form-control input-lg" required=""  id="message" name="content" placeholder="Enter your massage for us here. We will get back to you within 2 business days." rows="7"></textarea>                        </div>
                            </div>
                            <div class="col-md-12 text-right">
                                <button type="submit" class="btn btn-primary btn-lg">Send</button>
                            </div>
            </form>
            </div>
            </div>
            </div>
            </div>
            </div>
            </div>


<!-- footer -->
<footer>
  <hr />
  <div class="container">
    <p class="text-right">Copyright &copy; Inforvellor 2014</p>
  </div>
</footer>
<!-- /footer -->

  <!-- attach JavaScripts -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${root }/static/js/newhomepage/bootstrap.min.js"></script>

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
  
    <!-- Scrolling Nav JavaScript -->
    <script src="${root }/static/js/newhomepage/jquery.easing.min.js"></script>
    <script src="${root }/static/js/newhomepage/scrolling-nav.js"></script>


  <script>
    $('.carousel').carousel({
        interval: 5000
    })
</script>

<div id="global-zeroclipboard-html-bridge" class="global-zeroclipboard-container" title="" style="position: absolute; left: 0px; top: -9999px; width: 15px; height: 15px; z-index: 999999999;" data-original-title="Copy to clipboard">      <object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" id="global-zeroclipboard-flash-bridge" width="100%" height="100%">         <param name="movie" value="/assets/flash/ZeroClipboard.swf?noCache=1422475245721">         <param name="allowScriptAccess" value="sameDomain">         <param name="scale" value="exactfit">         <param name="loop" value="false">         <param name="menu" value="false">         <param name="quality" value="best">         <param name="bgcolor" value="#ffffff">         <param name="wmode" value="transparent">         <param name="flashvars" value="trustedOrigins=getbootstrap.com%2C%2F%2Fgetbootstrap.com%2Chttp%3A%2F%2Fgetbootstrap.com">         <embed src="/assets/flash/ZeroClipboard.swf?noCache=1422475245721" loop="false" menu="false" quality="best" bgcolor="#ffffff" width="100%" height="100%" name="global-zeroclipboard-flash-bridge" allowscriptaccess="sameDomain" allowfullscreen="false" type="application/x-shockwave-flash" wmode="transparent" pluginspage="http://www.macromedia.com/go/getflashplayer" flashvars="trustedOrigins=getbootstrap.com%2C%2F%2Fgetbootstrap.com%2Chttp%3A%2F%2Fgetbootstrap.com" scale="exactfit">                </object></div><svg xmlns="http://www.w3.org/2000/svg" width="500" height="500" viewBox="0 0 500 500" preserveAspectRatio="none" style="visibility: hidden; position: absolute; top: -100%; left: -100%;"><defs></defs><text x="0" y="23" style="font-weight:bold;font-size:23pt;font-family:Arial, Helvetica, Open Sans, sans-serif;dominant-baseline:middle">500x500</text></svg></body></html>




