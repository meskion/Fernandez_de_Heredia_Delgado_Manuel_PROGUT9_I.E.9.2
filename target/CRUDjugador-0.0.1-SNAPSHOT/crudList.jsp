

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<!-- <link rel="stylesheet" type="text/css" href="css/main.css" /> -->
<style type="text/css">
<%@ include file="css/main.css"%>
</style>

<script>
//Script que muestra el formulario escondido para editar socios
function showForm(id){
	form = document.getElementById("FORM"+id);
	display = document.getElementById("DISP"+id);

	form.className = "";
	display.className = "hidden";

}
//Script que cancela una edición.
function hideForm(id){
	form = document.getElementById("FORM"+id);
	display = document.getElementById("DISP"+id);

	display.className = "";
	form.className = "hidden";
}


</script>

<meta charset="ISO-8859-1">
<title>Socios Baloncesto</title>
</head>
<body>

	<div class="header">
		<h1>Club de Baloncesto</h1>
		<span class="error"><c:out value="${error}"></c:out></span>
	</div>
	<div>
		<table>
		<tr>
				<th>ID</th>
				<th>Nombre</th>
				<th>Edad</th>
				<th>Estatura</th>
				<th>Localidad</th>
			</tr>
			<tr>
				<form action="insert" method="post">
				<td><input type="text" name="nSocio"></td>
				<td><input type="text" name="nombre" ></td>
				<td><input type="text" name="edad"></td>
				<td><input type="text" name="estatura"></td>
				<td><input type="text" name="localidad"></td>
				<td><button class="newButton" type="submit" value="test">añadir</button></td>
				</form>
			</tr>
			

			<c:forEach var="socio" items="${listSocio}">
				<tr id="DISP<c:out value='${socio.nSocio}' />">
					<td><c:out value="${socio.nSocio}" /></td>
					<td><c:out value="${socio.nombre}" /></td>
					<td><c:out value="${socio.edad}" /></td>
					<td><c:out value="${socio.estatura}" /></td>
					<td><c:out value="${socio.localidad}" /></td>
					<td><button class="modButton" type="button" onclick="showForm(<c:out value='${socio.nSocio}' />)">modificar</button></td>
					<td><a href="delete?id=<c:out value='${socio.nSocio}' />"><button class="delButton" type="button">borrar</button></a></td>
					
				</tr>
				<tr class="hidden" id="FORM<c:out value='${socio.nSocio}' />">
				<form action="update" method="post">
				<td><c:out value="${socio.nSocio}" /></td>
				<td><input type="text" value="<c:out value='${socio.nombre}' />" name="nombre" ></td>
				<td><input type="text" value="<c:out value='${socio.edad}' />" name="edad"></td>
				<td><input type="text" value="<c:out value='${socio.estatura}' />" name="estatura"></td>
				<td><input type="text" value="<c:out value='${socio.localidad}' />" name="localidad"></td>
				<td><button class="newButton" type="submit" value="test">confirmar</button></td>
				<td><button class="cancelButton" onclick="hideForm(<c:out value='${socio.nSocio}' />)" type="button">cancelar</button></td>
				</form>
			</tr>
				
			</c:forEach>
		</table>
	</div>
</body>

</html>