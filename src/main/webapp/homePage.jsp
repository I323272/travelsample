<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="static/js/handlebars-v1.3.0.js"></script>
<script type="text/javascript" src="/static/js/homePage.js"></script>
<link href="static/css/homePage.css" type="text/css" rel="stylesheet" />
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

	<div class="row">
		<div class="col span_8">
			<div class="travelData"></div>
		</div>
		<div class="col span_12">
			<span class="refresh show-more-span">Show more
				products </span><span class="no-show-more">There are no more
				products to show</span>
		</div>
	</div>
</div>

<script id="HotelHandle" type="text/x-handlebars-template">
    <div class="row title">
      <h4>
            <div class="col span_12">
              <span class="hotelName">{{name}}</span>
             </div>
            <div class="col span_10">
              <span>{{country}} : {{streetAddress}}</span>
            </div>
            <div class="col span_10 hide">
              <span class="latitude">Latitude :{{latitude}}</span><span class="longitude"> Longitude:{{longitude}}</span>
                <span class="rating">Rating :{{starRating}}</span>
            </div>
      </h4>
          </div>
           
</script>