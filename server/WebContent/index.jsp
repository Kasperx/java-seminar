<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$.ajax({
			  url: "./rest/persons",
			  dataType: "json"
			}).done(function(data) {
			  console.log(data);
			  
			  $(data).each(function(i, p){
					$('#personen').append('<tr><td>'+p.vorname+'</td><td>'+p.nachname+'</td></tr>');
				});
			});
	});
</script>
</head>
<body>
<%=1+1%>
<table>
  <tr>
    <th>Vorname</th>
    <th>Nachname</th>
    <th>Unternehmen</th>
  </tr>
  <tbody id="personen"></tbody>
</table>
</body>
</html>