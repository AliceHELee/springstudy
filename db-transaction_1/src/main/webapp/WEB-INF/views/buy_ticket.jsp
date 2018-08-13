<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>buy_ticket</title>
</head>
<body>
<p>카드 결제</p>

<form action="buy_ticket_card">
	고객 아이디 : <input type="text" name="consumerId" > <br />
	티켓 구매수 : <input type="text" name="amount" > <br />
	<input type="submit" value="구매" > <br />
</form>

</body>
</html>
