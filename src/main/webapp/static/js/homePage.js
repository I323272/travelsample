		
		$(document).ready(function(){
			getHotels();
			 setTimeout(function() {
 				   $('.load-overlay').hide();
 		           }, 300);
			
		});
		
		function getHotels() {
			$.ajax({
				dataType : "json",
				type : 'POST',
				url : "/hotelsList",
				data : '',
				contentType : "application/json; charset=utf-8",
				success : function(data) {
					if (data != undefined && data != null && data != '') {
						
						var array= generateHotelJson(data),hotelRowHTML,hotelRowHTMLCompiled ;
						hotelRowHTML = $('#HotelHandle').html();
						    
					      if(hotelRowHTML)
					    	  hotelRowHTMLCompiled = Handlebars.compile(hotelRowHTML);

					   
					      $.each(array, function(i){
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
					      $('.container .no-show-more').hide();
						
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
				if (hotelJson[i].hasOwnProperty('country')) {
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
				hotel_json.push(hotelObj);

			}
			return hotel_json;
		}
