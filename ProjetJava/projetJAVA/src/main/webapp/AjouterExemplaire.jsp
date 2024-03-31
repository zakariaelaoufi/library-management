<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ajouter Exemplaire</title>
<script src="https://cdn.tailwindcss.com"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/habibmhamadi/multi-select-tag@2.0.1/dist/css/multi-select-tag.css">
<script src="https://cdn.jsdelivr.net/gh/habibmhamadi/multi-select-tag@2.0.1/dist/js/multi-select-tag.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>

		<div class="flex">
		<%@include file="SideBarAssistant.jspf"%>
		<div class=" w-screen">
		<%@include file="topBar.jspf"%>
		<% String msg=request.getParameter("msg");
		if("valid".equals(msg)) {
		%>
		<div class="mx-auto my-8 bg-emerald-100 w-2/3 py-8 rounded-2xl  shadow-lg shadow-emerald-100/50">
		<p class="text-xl block text-center text-emerald-950 font-semibold">Ajouté Avec Succes</p>
		</div>
		<%}%>
		<% if("invalid".equals(msg)) { %>
		<div class="mx-auto my-8 bg-red-100 w-2/3 py-8 rounded-2xl shadow-lg shadow-emerald-100/50">
		<p class="text-xl block text-center text-ered-950 font-semibold">Il y a un erreur</p>
		</div>
		<%} %>
		<div class="w-2/3 mx-auto p-6 my-10 shadow-md bg-slate-100 rounded-md">
		<form action="../exemplaire/insert" method="post" onsubmit="return validateForm()">
		<h1 class="text-3xl block text-center font-semibold">Ajouter un Exemplaire</h1>
		<hr class="mt-3">
		<div class="mt-3">
                <label for="livreISBN[]" class="block text-base mb-2">Choisir ISBN du Exeemplaire</label>
				<select name="livreISBN" id="livreISBN[]">
				    <c:forEach var="isbn" items="${ISBN}">
				    	<option value="${isbn}">${isbn}</option>
				    </c:forEach>
				</select>
		</div>
        <div class="mt-3">
                <label for="etats[]" class="block text-base mb-2">Choisir ISBN du Exeemplaire</label>
				<select name="etats" id="etats[]" >
				    <option value="1">Neuf</option>
				    <option value="2">Bon état</option>
				    <option value="3">Partiellement abimé</option>
				    <option value="4">Très abimé</option>
				</select>
        </div> 
        <div class="mt-3">
			<label for="numInventaire" class="block text-base mb-2">Numéro d'Inventaire</label>
        	<input type="text" id="numInventaire" name="numInventaire" class="border w-full text-base px-2 py-2 text-gray-900 rounded-lg focus:outline-none focus:ring-0 focus:border-green-600" placeholder="Enter Numéro d'Inventaire..." />
        	<span id="numInventaireError" class="text-red-600"></span>
        </div> 
        <hr class="mt-6">
        <div class="mt-5 text-center">		
			<button type="submit" class="border-2 border-green-700 bg-green-700 text-white py-2 w-1/2 rounded-md hover:bg-transparent hover:text-green-700 font-semibold">&nbsp;&nbsp;Ajouter Exemplaire</button>
        </div>   
		</form>
		</div>
		
		</div>
		</div>


		<script>
		    new MultiSelectTag('livreISBN[]', {
		        rounded: true,    // default true
		        shadow: true,      // default false
		        placeholder: 'Search',  // default Search...
		        tagColor: {
		            textColor: '#327b2c',
		            borderColor: '#92e681',
		            bgColor: '#eaffe6',
		        },
		        onChange: function(values) {
		            console.log(values)
		        }
		    })
		</script>
		<script>
		    new MultiSelectTag('etats[]', {
		        rounded: true,    // default true
		        shadow: true,      // default false
		        placeholder: 'Search',  // default Search...
		        tagColor: {
		            textColor: '#327b2c',
		            borderColor: '#92e681',
		            bgColor: '#eaffe6',
		        },
		        onChange: function(values) {
		            console.log(values)
		        }
		    })
		</script>
		
		<script>
	    function validateForm() {
		    document.getElementById('numInventaireError').textContent = '';
		
		    var numInventaireInput = document.getElementById('numInventaire');
		    var numInventaire = numInventaireInput.value.trim();
		
		    var isValid = true;
		
		    checkNumInventaireExists(numInventaire, function(numInventaireExists) {
		        if (numInventaireExists) {
		            document.getElementById('numInventaireError').textContent = 'numInventaire already exists';
		            numInventaireInput.style.borderColor = 'red';
		            isValid = false; 
		        }
		
		        if (isValid) {
		                confirmSubmitForm(document.forms[0]);
		            }
		    });
		
		    return false; 
		    }
	
	
	    function checkNumInventaireExists(numInventaire, callback) {
	        var xhr = new XMLHttpRequest();
	        xhr.open('GET', 'checkNumInventaire?numInventaire=' + numInventaire, true);
	
	        xhr.onreadystatechange = function() {
	            if (xhr.readyState === 4 && xhr.status === 200) {
	                var response = xhr.responseText;
	                if (response === 'exists') {
	                    // ISBN exists
	                    callback(true);
	                } else {
	                    // ISBN doesn't exist
	                    callback(false);
	                }
	            }
	        };
	
	        xhr.send();
	    }
	</script>
		
		<script type="text/javascript">
		    function confirmSubmitForm(form) {
		        Swal.fire({
		            text: "Voulez vous vraiment ajouté ce exemplaire?",
		            icon: "question",
		            showCancelButton: true,
		            confirmButtonColor: "#3085d6",
		            cancelButtonColor: "#d33",
		            confirmButtonText: "Oui, Ajouté!"
		        }).then((result) => {
		            if (result.isConfirmed) {
		                // Submit the form
		                form.submit();
		            }
		        });
		}
</script>
</body>
</html>