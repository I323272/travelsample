<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<spring:url value="/catalog/CatalogPage.json" var="getCatalogUrl" />
<script type="text/javascript">
var getCatalogUrl = '${getCatalogUrl}',
    errorVal = '${error}',
    prdCode =' ${productId}'+'_name';
    
</script>
<div class="container catalog catalog-screen">
  <div class="row top-section">
    <div class="col span_9">
      <h1><spring:theme code="catalog.content.heading" text="Lubricants catalog" /></h1>
      
    </div>
  </div>
    <div class="load-overlay">
    <p class="load-overlay-container"><span><spring:theme code="catalog.content.alertmessage.pleasewait" text="Please wait" /></span></p>
  </div>
  <div class="row">
    <div class="col span_8">
      <div class="row mobileControls desktopHide">
        <div class="search-icon"></div>
        <div class="filter-icon"></div>
      </div>
      <h1 class="multi-size"><span class="noOfProds"></span><span><span><span class="mobileHide"> <span class="mobileHide"> <spring:theme code="catalog.content.products" text="Products" /></span></span>
      <span class="desktopHide"> <span class="desktopHide"> <spring:theme code="catalog.content.products" text="Products" /></span></span> <%-- <spring:theme code="catalog.content.products" text="products" /> --%></span></span></span></span></h1>
      <div class="mobileSearch desktopHide">
        <input name="ship-to" type="text" placeholder="Search by name, code, packaging type, etc." class="text shipto-search search-field">
        <div class="position-wrap">
          <ul id="shipto-results" class="address-dd"></ul>
        </div>
      </div>
      <div class="row controls mobileHide">
        <div class="position-wrap">
          <div class="catalog sortBy">
            <p class="sorter"><spring:theme code="catalog.content.sortby" text="Sort by " /><a href="javascript:void(0)"><spring:theme code="catalog.content.materialname" text="Material name" /></a></p>
            <ul class="material-sort">
              <li class="prdSortLi active"><a href="javascript:void(0)"><spring:theme code="catalog.content.materialname" text="Material name" /></a></li>
              <li class="prdSortLi"><a href="javascript:void(0)"><spring:theme code="catalog.content.materialnumber" text="Material Number" /></a></li>
            </ul>
          </div>
        </div>
        <p class="productOffset">
            <spring:theme code="catalog.content.show" text="Show"/>
            <a href="javascript:void(0)" class="active">10</a>
            <a href="javascript:void(0)">20</a>
            <a href="javascript:void(0)">100</a>
            <spring:theme code="catalog.content.products" text="Products" /></p>
      </div>
       <c:if test="${! empty error}">
      <div class="errorMessageContainer">${error}</div>
      </c:if> 
      
      <div class="accordion product product-list"></div>
      <div class="row show-more">
        <div class="col span_12">
          <span class="refresh show-more-span"><span></span><spring:theme code="catalog.content.moreproducts" text="Show more products " /></span><span class="no-show-more"><spring:theme code="catalog.content.nomoreproducts" text="No more products to show" /></span>
        </div>
      </div>
    </div>
    <div class="col span_4 sidebar mobileHide">
     <div class="search-wrap small">
        <input name="ship-to" type="text" placeholder='<spring:theme code="catalog.content.search" text="Search lubricants" />' class="text global-prd-search" maxlength="50">
        <div class="position-wrap">
          <ul id="shipto-results" class="address-dd"></ul>
        </div>
      </div>
      <h4 class="bb"><spring:theme code="catalog.content.filter" text="Filter" /></h4>
      <div class="accordion product Prdfilter">
        <div class="row title active">
          <div class="col span_12">
            <p><spring:theme code="catalog.content.availableshipto" text="Available for ship-to" /><span class="lockedText"> <spring:theme code="catalog.content.filtercurrent" text="(filtered on current order)" /></span></p>
          </div>
        </div>
        <div class="row desc">
          <div class="col span_12">
            <a class="clearFilter"><spring:theme code="catalog.content.clearfilter" text="Clear Filter" /></a>
            <input id="shipToFilter" name="ship-to" type="text" placeholder='<spring:theme code="catalog.content.shipto.placeholder" text="Ship to (Address)" />' class="text shipto-search search-field">
            <div class="position-wrap">
              <ul id="shipto-results" class="address-dd"></ul>
            </div>
          </div>
        </div>
        <!-- <div class="row title active">
          <div class="col span_12">
            <p>Plant code</p>
          </div>
        </div>
        <div class="row desc">
          <div class="col span_12 plant-code-filter"><span class="no-data">No filter to show</span></div>
        </div> -->
        <div class="row title active">
          <div class="col span_12">
            <p><spring:theme code="catalog.content.packtype" text="Packaging type" /><span class="lockedText"> <spring:theme code="catalog.content.filtercurrent" text="(filtered on current order)" /></span></p>
          </div>
        </div>
        <div class="row desc">
            <a class="clearFilter"><spring:theme code="catalog.content.clearfilter" text="Clear Filter" /></a>
          <div class="col span_12 prd-type-filter"><span class="no-data"><spring:theme code="catalog.content.nofilter" text="No filter to show" /></span></div>
        </div>
        <div class="row desc">
          <div class="col span_12 prd-family-filter"><span class="no-data"><spring:theme code="catalog.content.nofilter" text="No filter to show" /></span></div>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="col span_12 order-container hidden original-container"><span class="arrow"></span>
  <form>
    <h2><spring:theme code="catalog.content.createneworder" text="Create new order" /></h2>
    <div class="order-wrapper select-wrapper">
      <label><spring:theme code="catalog.content.add.soldto" text="Sold-to" /></label>
      <div class="select-wrap order-soldTo">
        <select name="select" id="soldToSelect">
        </select>
      </div>
    </div>
    <div class="order-wrapper text-wrapper">
      <p class="error ship-to-error"><spring:theme code="orders.createtemplate.shipto.errormsg" text="Your Ship-to is Unavailable for ordering.  Please contact your Sales Representative." /></p>
      <label><spring:theme code="catalog.content.add.shipto" text="Ship-to" /></label>
      <div class="order-textbox search-wrap">
        <input type="text" name="ship-to" placeholder='<spring:theme code="catalog.content.shipto.add.placeholder" text="Enter ship-to name, number or address" />' data-validation="required" class="text address-search search-field shipto-search shipToForProduct">
        <div class="position-wrap">
          <div id="shipto-results" class="address-dd"></div>
        </div>
      </div>
    </div>
    <div class="order-wrapper product-wrapper">
    
      <label><spring:theme code="catalog.content.add.addproduct" text="Add product" /></label>
      <div class="order-textbox quantity-wrap">
        <h3 class="product-name">BEACON EP5 5W-30 2 x 5.1QT</h3>
        <input type="text" name="quantity" placeholder='<spring:theme code="catalog.content.add.quantity" text="Quantity" />' data-validation="required" class="text quantity-box"><span class="units"></span>
      </div>
      <p class="error qty-max-error"><spring:theme code="" text="The Product quantity cannot be more than 100000." /></p>
    </div>
    <div class="row order-wrapper buttons-wrapper endbuttons">
      <input type="submit" value='<spring:theme code="catalog.content.add.cancel" text="Cancel" />' class="cancel">
      <input type="submit" value='<spring:theme code="catalog.content.add.addtoorder" text="Add To order" />' class="submit-order">
    </div>
  </form>
</div>

<div class="col span_12 addTo-order-container hidden org-container"><span class="arrow"></span>
  <form>
    <h2><spring:theme code="catalog.content.add.addtoorder" text="Add to order" /></h2>
    <div class="order-wrapper product-wrapper">
    
      <div class="order-textbox quantity-wrap">
        <h3 class="product-name">BEACON EP5 5W-30 2 x 5.1QT</h3>
        <input type="text" name="quantity" placeholder='<spring:theme code="catalog.content.add.quantity" text="Quantity" />' data-validation="required" class="text quantity-box"><span class="units"></span>
      </div>
      <p class="error qty-max-error"><spring:theme code="" text="The Product quantity cannot be more than 100000." /></p>
    </div>
    <div class="row order-wrapper buttons-wrapper endbuttons">
      <input type="submit" value='<spring:theme code="catalog.content.add.cancel" text="Cancel" />' class="cancel">
      <input type="submit" value='<spring:theme code="catalog.content.add.addtoorder" text="Add To order" />' class="submit-order">
    </div>
  </form>
</div>
<script id="CatalogPrdHandle" type="text/x-handlebars-template">
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