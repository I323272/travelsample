
var payload={};
payload.offset="8";
payload.pageNo = "0";

		$(document).ready(function(){
			$('.show-more-span').hide();
			$('.container .no-show-more').show();
			getHotels();
			 setTimeout(function() {
 				   $('.load-overlay').hide();
 		           }, 300);
			 
			 $('.show-more').find("span").click(function(){
					payload.pageNo=parseInt(payload.pageNo)+1;
			        $('.load-overlay').show();
			        var myVar = setTimeout(function(){
			          getHotels();
			        },500);
			 });
			 
			   $('.travelData').on('click','.row.title',function() {

                   if ( $(this).hasClass('active') ) {
                                   $(this).removeClass('active');
                   } else {
                                 $(this).addClass('active');                                 
                   }
                   
                   $(this).next('.row.desc').toggleClass('open');
                 });
			
		});
		
		function getHotels() {
			$.ajax({
				type : "POST",
				url : "/hotelsList",
				data : payload,
				cache:false,
				success : function(data) {
					if (data.paginatedHotelList != undefined && data.paginatedHotelList != null && data.paginatedHotelList != '') {
						
						var array= generateHotelJson(data.paginatedHotelList),hotelRowHTML,hotelRowHTMLCompiled ;
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
									"guestRating" :array[i].guestRating,
									"totalRate":array[i].totalRate,
									"taxesAndFees":array[i].taxesAndFees,
									"imageUrl":array[i].imageUrl,
									"description":array[i].descripion
					         
					        };
					        if(hotelRowHTMLCompiled)
					        	$('.travel-screen .traveldata').append(hotelRowHTMLCompiled(context));
					        
					      });
					      
					      if(data.moreResults) {
						      $('.show-more-span').show();
						      $('.container .no-show-more').hide();
					      }
					      else {
					      $('.container .no-show-more').show();
					      $('.show-more-span').hide();
					      }
						
					} 
					
					else {
						 $('.show-more-span').hide();
						 $('.container .no-show-more').show();
					}
				},
				error : function(error) {
					$('.show-more-span').hide()
					console.log(error);
				},
				complete: function(){
			      	  setTimeout(function() {
			    			   $('.load-overlay').hide();
			    	          }, 300);
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
					"streetAddress" : hotelJson[i].streetAddress,
					"totalRate": hotelJson[i].totalRate,
					"taxesAndFees" : hotelJson[i].taxesAndFees,
					"imageUrl":hotelJson[i].imageUrl,
					"description":hotelJson[i].description
				};
				hotel_json.push(hotelObj);

			}
			return hotel_json;
		}
