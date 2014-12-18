
var payload={};
payload.offset="8";
payload.pageNo = "0";

		$(document).ready(function(){
			getHotels();
			 setTimeout(function() {
 				   $('.load-overlay').hide();
 		           }, 300);
			 
			 $('.show-more').find("span").click(function(){
					payload.pageNo=payload.pageNo+1;
			        $('.load-overlay').show();
			        var myVar = setTimeout(function(){
			          getHotels();
			        },500);
			 });
			
		});
		
		function getHotels() {
			$.ajax({
				type : "POST",
				url : "/hotelsList",
				data : payload,
				cache:false,
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
									"streetAddress" : array[i].streetAddress,
									"hotelId": array[i].hotelId,
									"guestRating" :array[i].guestRating
					         
					        };
					        if(hotelRowHTMLCompiled)
					        	$('.travel-screen .traveldata').append(hotelRowHTMLCompiled(context));
					        
					      });
					      $('.container .no-show-more').hide();
						
					} 
					
					else {
						 $('.show-more-span').hide();
						 $('.container .no-show-more').show();
					}
				},
				error : function(error) {
					$('.show-more-span').hide()
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
					"hotelId":hotelJson[i].hotelId,
					"name" : name,
					"language" : language,
					"country" : country,
					"latitude" : hotelJson[i].latitude,
					"longitude" : hotelJson[i].longitude,
					"starRating" : hotelJson[i].starRating,
					"guestRating" : hotelJson[i].guestRating,
					"streetAddress" : hotelJson[i].streetAddress
				};
				hotel_json.push(hotelObj);

			}
			return hotel_json;
		}
		
		
		$(".show-more .show-more-span").click(function(){
				payload.pageNo=payload.pageNo+1;
		        $('.load-overlay').show();
		        var myVar = setTimeout(function(){
		          getHotels();
		        },500);
});
