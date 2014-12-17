<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="WEB-INF/js/homePage.js"></script>
<title>TravelDeals</title>
</head>

<div class="container travel travel-screen">
  <div class="row top-section">
    <div class="col span_9">
      <h1>Travel Deals</h1>
      
    </div>
  </div>
    <div class="load-overlay">
    <p class="load-overlay-container">Please Wait</span></p>
  </div>
  <div class="travelData">
  </div>
     </div>
      
<script id="HotelHandle" type="text/x-handlebars-template">
    <div class="row title">
            <div class="col span_12">
              <p class="mobileHide loc" data-tooltip='<spring:theme code="order.tooltip.plant" text="ExxonMobil plant product can be ordered from" />'  data-tooltip-position="left">{{plantCode}} ({{plantName}})</p>
              <p class="mobileHide prdCode" shipToCode="{{Prdcode}}">{{shipToCode}}</p>
              <h4><span class="prdName" data-baseUOm="{{PrdBaseUom}}" data-tooltip='<spring:theme code="tooltip.productDesc" text="Product Name and package style" />' data-tooltip-position="right">{{PrdName}}</span><br/><span><span class="desktopHide">{{Prdcode }}</span>{{PrdBaseUom}} |
               <span class="prodType">{{prodType}}</span><span class="hidden"><span> | </span><span class="pkgType">{{packageType}}</span></span></span></span></h4>
             </div>
          </div>
          <div class="row desc">
            <div class="col span_10">
              <p>{{PrdDescription}}</p>
            </div>
            <div class="row">
              <div class="col span_12">
                <div class="tasks">
                  <div class="left"><a class="msds links-open" href="{{msdsLink}}">MSDS</a><a href="mailto:?subject=MSDS&body={{msdsEmail}}" class="email">Email</a></div>
                  <div class="middle"><a {{pdsLink}} class="links-open" target="_new">PDS</a><a {{pdsEmail}} class="email" >Email</a></div>

                  <!--<div class="left"><span>MSDS</span><a class="msds open" href="{{msdsLink}}">Open</a></div>
                  <div class="middle"><span>PDS</span><a {{pdsLink}} class="open" >Open</a></div>-->
                        <sec:authorize access="hasAnyRole('ROLE_UG_ORDER_ADMIN', 'ROLE_UG_INTERNAL_ORDER_ADMIN')">
                  <div class="right"><a href="javascript:void(0)" class="create"><spring:theme code="catalog.content.createneworder" text="Create new order" /></a></div>
                        </sec:authorize>
                        </div>
                  </div></div></div>
</script>