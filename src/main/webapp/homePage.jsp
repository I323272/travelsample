<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/static/js/homePage.js"></script>
<link href="static/css/homePage.css" rel="stylesheet"/>
<title>TravelDeals</title>
</head>

<div class="container travel travel-screen">
  <div class="row top-section">
    <div class="col span_9">
      <h1>Travel Deals</h1>
      
    </div>
  </div>
    <div class="load-overlay">
    <p class="load-overlay-container">Please Wait</p>
  </div>
  <div class="travelData">
  </div>
     </div>
      
<script id="HotelHandle" type="text/x-handlebars-template">
    <div class="row title">
            <div class="col span_12">
              <p class="hotelName">{{name}}</p>
             </div>
          </div>
          <div class="row desc">
            <div class="col span_10">
              <p>{{country}} : {{streetAddress}}</p>
            </div>
           
</script>