<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>  
<h2>Stores Retrieved.......</h2>
<body>  
<ol>
		<c:forEach var="sd" items="${details}">
		
			<li>
			<ul>
			<li>Merchant name : ${sd.merchantname}</li>
			<li>Open time : ${sd.opentime}</li>
			<li>Close time : ${sd.closetime}</li>
			<li>Address : ${sd.address}</li>
			<li>Range of home delivery : ${sd.rangeofhomedelivery} KM</li>
			<li>Distance (from here) : ${sd.distance} KM</li>
			<li>Category : ${sd.category}</li>
			</ul>
			</li>
			
		</c:forEach>
	</ol>
</body>  
</html>  