var s, homePage = {

	triggerActions : function(clicked) {
		
		$(document).ready(function(){
			getHotels();
			 setTimeout(function() {
 				   $('.load-overlay').hide();
 		           }, 300);
			
		});
		
		function getHotels() {
			$.ajax({
				dataType : "json",
				type : 'GET',
				url : "http://localhost:8080/hotelsList",
				data : '',
				contentType : "application/json; charset=utf-8",
				success : function(data) {
					if (data != undefined && data != null && data != '') {
						
						var array= generateHotelJson(data),hotelRowHTML,hotelRowHTMLCompiled ;
						hotelRowHTML = $('#HotelHandle').html();
						    
					      if(hotelRowHTML)
					    	  hotelRowHTMLCompiled = Handlebars.compile(hotelRowHTML);

					   
					      $.each(array, function(){
					        var context = {					        		
					        		"name" : array[i].name,
									"language" : array[i].language,
									"country" : array[i].country,
									"latitude" : array[i].latitude,
									"longitude" : array[i].longitude,
									"starRating" : array[i].starRating,
									"streetAddress" : array[i].streetAddress
					         
					        };
					        if(hotelRowHTMLCompiled)
					        	$('.travel-screen .traveldata').append(hotelRowHTMLCompiled(context));
					        
					      });
						
						
					} 
					
					else {
						console.log('No Hotels available');
						
					}
				},
				error : function(error) {
					console.log(error);
				}
			});
		}
		
		function generateHotelJson(hotelJson) {
			var hotelObj;
			var hotel_json = [];

			for ( var i in hotelJson) {
				var name = '', language = '', country = '';
				if (hotelJson[i].hasOwnProperty('name')) {
					name = hotelJson[i].name;
				}
				if (hotelJson[i].hasOwnProperty('language')) {
					language = hotelJson[i].language;
				}
				if (orderJson[i].hasOwnProperty('country')) {
					country = hotelJson[i].country;
				}
				hotelObj = {
					"name" : name,
					"language" : language,
					"country" : country,
					"latitude" : hotelJson[i].latitude,
					"longitude" : hotelJson[i].longitude,
					"starRating" : hotelJson[i].starRating,
					"streetAddress" : hotelJson[i].streetAddress
				};
				hotel_json.push(orderObj);

			}
			return hotel_json;
		}

	},

	init : function(clicked) {
		this.s = this.settings;
		this.triggerActions(clicked);
	}

};