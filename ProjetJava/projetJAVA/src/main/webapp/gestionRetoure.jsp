<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.sql.*" %>
<%@ page import="DAO.*" %>
<%@ page import="Models.*" %>
<%@ page import="Models.Livre" %>
<%@ page import="jakarta.servlet.ServletException" %>
<%@ page import="jakarta.servlet.http.*" %> 
<%@ page import="java.util.ArrayList" %> 
<%
	EmprunteOperation EmpruntDAO = new EmprunteOperation();
	ArrayList<String> listExmNotAvailable = EmpruntDAO.getNotAvailableExemplaire();
	ArrayList<String> listNullRetoure = EmpruntDAO.RetoureIsNull();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Engister Retoure</title>
<script src="https://cdn.tailwindcss.com"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/habibmhamadi/multi-select-tag@2.0.1/dist/css/multi-select-tag.css">
<script src="https://cdn.jsdelivr.net/gh/habibmhamadi/multi-select-tag@2.0.1/dist/js/multi-select-tag.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>

		<div class="flex">
		<%@include file="SideBarBibliothecaire.jspf"%>
		<div class=" w-screen">
		<%@include file="topBar.jspf"%>
		<% String msg=request.getParameter("msg");
		if("valid".equals(msg)) {
		%>
		<div class="mx-auto my-8 bg-emerald-100 w-2/3 py-8 rounded-2xl  shadow-lg shadow-emerald-100/50">
		<p class="text-xl block text-center text-emerald-950 font-semibold">Le retourne est engistrée</p>
		</div>
		<%}%>
		<% if("invalid".equals(msg)) { %>
		<div class="mx-auto my-8 bg-red-100 w-2/3 py-8 rounded-2xl shadow-lg shadow-emerald-100/50">
		<p class="text-xl block text-center text-ered-950 font-semibold">Il y a un erreur</p>
		</div>
		<%} %>
		
		<div class="w-2/3 mx-auto p-6 my-10 shadow-md bg-slate-100 rounded-md">
		<form action="./emprunte/retoureExemplaire" method="post">
		<h1 class="text-3xl block text-center font-semibold">Engistrer retoure d'exemplaire</h1>
		<hr class="mt-3">
		<div class="mt-3">
			<label for="idEmprunteur[]" class="block text-base mb-2">ID Emprunteur</label>
			<select name="idEmprunteur" id="idEmprunteur[]">
				    <% for(String nullRet : listNullRetoure) { %>
				    	<option value="<%=nullRet %>">
				    		<%=nullRet %>
				    	</option>
				    <%} %>
				</select>
        </div> 
		<div class="mt-3">
                <label for="NumeroInventaire[]" class="block text-base mb-2">Choisir Numero Inventaire du Exeemplaire</label>
				<select name="NumeroInventaire" id="NumeroInventaire[]">
				    <% for(String numInv : listExmNotAvailable) { %>
				    	<option value="<%=numInv %>">
				    		<%=numInv %>
				    	</option>
				    <%} %>
				</select>
		</div>
        <div class="mt-3">
                <label for="etats[]" class="block text-base mb-2">Choisir Etat du Exemplaire</label>
				<select name="etats" id="etats[]" >
				    <option value="1">Neuf</option>
				    <option value="2">Bon état</option>
				    <option value="3">Partiellement abimé</option>
				    <option value="4">Très abimé</option>
				</select>
        </div> 
        <div class="mt-3">
			<label for="dateRetoure" class="block text-base mb-2">Date Retoure</label>
        	<input type="date" id="dateRetoure" name="dateRetoure" class="border w-full text-base px-2 py-2 text-gray-900 rounded-lg focus:outline-none focus:ring-0 focus:border-green-600" />
        </div> 
        <hr class="mt-6">
        <div class="mt-5 text-center">		
			<button type="button" onclick="confirmSubmitForm(this.form)" class="border-2 border-green-700 bg-green-700 text-white py-2 w-1/2 rounded-md hover:bg-transparent hover:text-green-700 font-semibold">&nbsp;&nbsp;Engistré Retoure</button>
        </div>   
		</form>
		</div>
		
		</div>
		</div>

		<script>
		    new MultiSelectTag('idEmprunteur[]', {
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
		    new MultiSelectTag('NumeroInventaire[]', {
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
		
		<script type="text/javascript">
    function confirmSubmitForm(form) {
        Swal.fire({
            text: "Voulez vous vraiment engistré ce retoure?",
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