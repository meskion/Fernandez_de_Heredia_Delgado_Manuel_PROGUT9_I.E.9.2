

function updateUser(id){

        row = document.getElementById(id);
        prevState = row.innerHTML;
		modForm = document.createElement("form");
		modForm.setAttribute("action","update");
		modForm.setAttribute("method","post");

		while (row.hasChildNodes()) {  
  			row.removeChild(row.firstChild);
		} 

		row.appendChild(modForm);

		nsocio = document.createElement("td");
        nsocio.appendChild(document.createElement("input"));
        nsocio.firstChild.type = "text";
        nsocio.firstChild.setAttribute("name", "nSocio")

       	nombre = document.createElement("td");
        nombre.appendChild(document.createElement("input"));
		nombre.firstChild.type = "text";
        nombre.firstChild.setAttribute("name", "nombre");

        edad = document.createElement("td");
        edad.appendChild(document.createElement("input"));
        edad.firstChild.type = "text";
        edad.firstChild.setAttribute("name", "edad");

        estatura = document.createElement("td");
        estatura.appendChild(document.createElement("input"));
		estatura.firstChild.type = "text";
        estatura.firstChild.setAttribute("name", "estatura")

        localidad = document.createElement("td");
        localidad.appendChild(document.createElement("input"));
        localidad.firstChild.type = "text";
        localidad.firstChild.setAttribute("name", "localidad")

		modButton = document.createElement("td");
		modButton.appendChild(document.createElement("button"));
		modButton.firstChild.type = "submit";
		modButton.firstChild.className = "modButton";
		modButton.firstChild.textContent = "confimrar";

		cancel = document.createElement("td");
		cancel.appendChild(document.createElement("button"));
		cancel.firstChild.className = "delButton";
		cancel.firstChild.textContent = "cancelar";
		cancel.onclick = function(){
			row.innerHTML = prevState;
		}
		
        
		newtds = [ nsocio, nombre, edad, estatura, localidad, modButton, cancel];

        newtds.forEach(td => {
                modForm.appendChild(td);
        });
}