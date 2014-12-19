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
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<title>TravelDeals</title>
</head>

<div class="container travel travel-screen">
	<div class="site">
		<h1>
			<img align="left" src="static/images/logo.png" class="logo" /><span>MyTrip</span>
		</h1>
	</div>
	<div class="row top-section">
		<div class="col span_9">
			<h1 class="heading">Travel Deals</h1>
		</div>
	</div>

	<div class="load-overlay">
		<p class="load-overlay-container">
			<span>Please Wait</span>
		</p>
	</div>

	<div class="row">
		<div class="col span_8">
			<div class="travelData hotelData"></div>
			<div class="row show-more">
				<div class="col span_12">
					<span class="refresh show-more-span hide"><span></span>Show
						more Hotels</span><span class="no-show-more">There are no more
						hotels to show</span>
				</div>
			</div>
		</div>
		<div class="col span_4 sidebar">
			<h4 class="bb">Filter</h4>
			<div class="travelData hotelFilter">
				<div class="row title active">
					<div class="col span_12">
						<p>
							City<span class="city"></span>
						</p>
					</div>
				</div>
				<div class="row desc open">
					<div class="col span_12">
						<a class="clearFilter">Clear Filter</a> <input id="cityFilter"
							name="city" type="text" placeholder="city" autocomplete="off">
					</div>
				</div>
				<div class="row title active">
					<div class="col span_12">
						<p>
							Rating<span></span>
						</p>
					</div>
				</div>
				<div class="row desc open">
					<div class="col span_12 sliderContainer">
						<a class="clearFilter">Clear Filter</a>
					<div data-role="rangeslider" data-track-theme="b" data-theme="a">
                        <label for="range-3a"></label>
                        <input type="range" name="range-3a" id="range-3a" min="0" step=0.1 max="5" value="0">
                        <label for="range-3b"></label>
                        <input type="range" name="range-3b" id="range-3b" min="0" step=0.1 max="5" value="5">
                    </div>
					</div>
				</div>
				<div class="row title active">
					<div class="col span_12">
						<p>
							Price<span class="price"></span>
						</p>
					</div>
				</div>
				<div class="row desc open">
					<div class="col span_12">
						<a class="clearFilter">Clear Filter</a> <input id="priceFilter"
							name="price" type="text" placeholder="upto value"
							autocomplete="off" />
					</div>
				</div>
				<div class="row title active">
					<div class="col span_12">
						<p>
							Latitude<span class="latitude"></span>
						</p>
					</div>
				</div>
				<div class="row desc open">
					<div class="col span_12 sliderContainer">
						<a class="clearFilter">Clear Filter</a>
						<div data-role="rangeslider" data-track-theme="b" data-theme="a">
                        <label for="range-3a"></label>
                        <input type="range" name="range-3a" id="range-3a" min="-85" step=0.01 max="85" value="-85">
                        <label for="range-3b"></label>
                        <input type="range" name="range-3b" id="range-3b" min="-85" step=0.01 max="85" value="85">
                    </div>
					</div>
				</div>
				<div class="row title active">
					<div class="col span_12">
						<p>
							Longitude<span class="longitude"></span>
						</p>
					</div>
				</div>
				<div class="row desc open">
					<div class="col span_12 sliderContainer">
						<a class="clearFilter">Clear Filter</a>
						<div data-role="rangeslider" data-track-theme="b" data-theme="a">
                        <label for="range-3a"></label>
                        <input type="range" name="range-3a" id="range-3a" min="-180" step=0.01 max="180" value="-180">
                        <label for="range-3b"></label>
                        <input type="range" name="range-3b" id="range-3b" min="-180" step=0.01 max="180" value="180">
                    </div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script id="HotelHandle" type="text/x-handlebars-template">
    <div class="row title">
            <div class="col span_12">
            <p class="mobileHide loc">{{destination}} : {{streetAddress}}</p>
              <p class="mobileHide hotelId">{{hotelId}}</p>
            <h4>
              <span class="hotelName">{{name}}</span>
            </h4>
             </div>
     </div>
     <div class="row desc">
            <div class="col span_10">
             <p></p>
            </div>
     <div class="row">
        <div class="col span_12">
            <div class="tasks">
            <div class="division">
                <div class="left">
                    <p class="head">Latitude : <span>{{latitude}}</span></p>
                    <p class ="head">Longitude : <span> {{longitude}}</span></p>
                </div> 
                <div class="middle">
                    <p class="head">starRating : <span>{{starRating}}</span></p>
                    <p class="head">guestRating : <span>{{guestRating}}</span></p>  
                </div>
                <div class="right">
                    <p class="head">totalRate : <span>{{totalRate}} {{currency}}</span></p>
                    <p class="head"> taxesAndFees : <span>{{taxesAndFees}} {{currency}}</span></p>
                </div>
              </div>
            <div class="hotelimage">
            <a target="_blank" href={{dealDeepLink}}>
            <img src={{imageUrl}} height="25%"></img>
            </a>
            <p class="head">Description : <span> {{description}}</span></p>
            </div>
            </div>
        </div>
      </div>
     </div>
           
</script>