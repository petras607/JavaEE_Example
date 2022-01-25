<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Pobaw sie</title>
	</head>
	<body>
		<jsp:useBean id="Pieniazek" class="pieniazek.clnt.PieniazekBean" scope="session"></jsp:useBean>
		<jsp:setProperty property="radios" name="Pieniazek"/>
		<jsp:getProperty property="wynik" name="Pieniazek"/>
		
		<img src=<jsp:getProperty property="image" name="Pieniazek"/> />
		<img src=<jsp:getProperty property="modifiedFileName" name="Pieniazek"/> />
		<br/>
		
		Wybierz nowy plik:<br />
		<form action="UploadServlet" method="post" enctype="multipart/form-data">
        	<input type="file" name="file" size="50" />
        	<br />
			<input type="radio" name="radios" value="negat">Negatyw<br>
    		<input type="radio" name="radios" value="horiz">Obrot w poziomie<br>
    		<input type="radio" name="radios" value="verti">Obrot w pionie<br>
			<input type="radio" name="radios" value="oldify">Zmien na czarno-bialy<br>
			<input type="submit" value="Wyslij i zobacz zmiany"/>
		</form>
	</body>
</html>