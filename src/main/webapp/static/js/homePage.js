
var offset="8";
var pageNo = "0";
var filter=false;
var formData;
var values;
var params;
		$(document).ready(function(){
			values=new Array();
			params=new Array();
			formData=$('#deals :input').serializeArray(); 
			$.each(formData, function(index,element){
		           values.push(element.value);
		           params.push(element.name);
		        });
			$('.show-more-span').hide();
			$('.container .no-show-more').show();
			 getHotels();
			 setTimeout(function() {
 				   $('.load-overlay').hide();
 		           }, 300);
			 
			 $('.show-more .show-more-span').click(function(){
					pageNo=parseInt(pageNo)+1;
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
			   
			   $('input[type="text"]').on('input',function(event) {
				   values=new Array();
				   params=new Array();
						formData=$('#deals :input').serializeArray(); 
						$.each(formData, function(index,element){
					           values.push(element.value);
					           params.push(element.name);
					        });	
						
						$.each(formData, function(){
				           if(this.value!=undefined && this.value!=null && this.value!='')
				            	{
				            	 filter=true;
				            	 return false;
				            	}
				        });			   		
				   		pageNo = "0";
				   		$('.load-overlay').show();
				        var myVar = setTimeout(function(){
				          getHotels();
				        },500);
			    });
			
		});
		
		function getHotels() {
			if (pageNo==0) {
				$('.travel-screen .hotelData').html('');
			}
			$.ajax({
				type : "POST",
				url : "/filter",
				data : {"pageNo":pageNo ,"offset":offset,"filter":filter,"formDataValues":values,"formDataParams":params},
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
									"destination" : array[i].destination,
									"latitude" : array[i].latitude,
									"longitude" : array[i].longitude,
									"starRating" : array[i].starRating,
									"streetAddress" : array[i].streetAddress,
									"hotelId": array[i].hotelId,
									"guestRating" :array[i].guestRating,
									"totalRate":array[i].totalRate,
									"taxesAndFees":array[i].taxesAndFees,
									"imageUrl":array[i].imageUrl,
									"description":array[i].description,
									"currency":array[i].currency,
									"dealDeepLink":array[i].dealDeepLink,
									"city":array[i].city
					         
					        };
					        if(hotelRowHTMLCompiled)
					        	$('.travel-screen .hotelData').append(hotelRowHTMLCompiled(context));
					        
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
					$('.show-more-span').hide();
					$('.container .no-show-more').show();
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
				var name = '', language = '', country = '',dealDeepLink='#';
				if (hotelJson[i].hasOwnProperty('name')) {
					name = hotelJson[i].name;
				}
				if (hotelJson[i].hasOwnProperty('language')) {
					language = hotelJson[i].language;
				}
				
				if(hotelJson[i].hasOwnProperty('dealDeepLink')) {
					dealDeepLink=decodeURIComponent(hotelJson[i].dealDeepLink);
				}
				
				hotelObj = {
					"hotelId":hotelJson[i].hotelId,
					"name" : name,
					"language" : language,
					"destination" : hotelJson[i].destination,
					"latitude" : hotelJson[i].latitude,
					"longitude" : hotelJson[i].longitude,
					"starRating" : hotelJson[i].starRating,
					"guestRating" : hotelJson[i].guestRating,
					"streetAddress" : hotelJson[i].streetAddress,
					"totalRate": hotelJson[i].totalRate,
					"taxesAndFees" : hotelJson[i].taxesAndFees,
					"imageUrl":hotelJson[i].imageUrl,
					"description":hotelJson[i].description,
					"currency":hotelJson[i].currency,
					"dealDeepLink":dealDeepLink,
					"city":hotelJson[i].city
				};
				hotel_json.push(hotelObj);

			}
			return hotel_json;
		}