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
		
		load();
		
		$('#save').on('click', function(){
			$.ajax({
					method: "POST",
				  	url: "./rest",
				  	dataType: "json",
				  	contentType: "application/json",
				  	data: JSON.stringify({
				  		"id": $('#id').attr("data-id"),
				  		"vorname": $('#vorname').val(),
				  		"nachname": $('#nachname').val()
				  	}),
				}).done(function(data) {
				  console.log(data);
				  $('#save').attr('data-id', data.id);
				  load();
				});
		});
		
		function load(){
			$.ajax({
				  url: "./rest/persons",
				  dataType: "json"
				}).done(function(data) {
				  console.log(data);
				  $('#personen').empty();
				  $(data).each(function(i, p){
						$('#personen').append('<tr><td>'+p.vorname+'</td><td>'+p.nachname+'</td></tr>');
					});
				});
			};
	});
</script>
</head>
<body>
Vorname<input type="text" id="vorname"><br>
Nachname<input type="text" id="nachname"><br>
<button id="save">speichern</button>
<table>
  <tr>
    <th>Vorname</th>
    <th>Nachname</th>
    <th>Unternehmen</th>
  </tr>
  <tbody id="personen"></tbody>
</table>
<a href="/server/rest/personxlsx">Export</a>
</body>
</html>