	<%@ page language="java" contentType="text/html; charset=UTF-8"
	    pageEncoding="UTF-8"%>
	<%@ page import="java.io.IOException" %>
	<%@ page import="java.sql.*" %>
	<%@ page import="DAO.*" %>
	<%@ page import="Models.*" %>
	<%@ page import="Models.Livre" %>
	<%@ page import="jakarta.servlet.ServletException" %>
	<%@ page import="jakarta.servlet.http.*" %> 
	<%@ page import="java.util.ArrayList" %> 
	
	<%
		String isbn = request.getParameter("isbn");
		String idEmprunteur = request.getParameter("id");
		if (isbn == null) {
	        isbn = "";
	    }
	    if (idEmprunteur == null) {
	        idEmprunteur = "";
	    }
		EmprunteOperation EmpruntDAO = new EmprunteOperation();
		ArrayList<EmpruntRelance> listEmpruntRelance = EmpruntDAO.getAllLatnessEmprunt();
		ArrayList<Exemplaire> listExemplaire = EmpruntDAO.getAllAvailableExemplaire(isbn);
	%>
	
	<!DOCTYPE html>
	<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Emprunté Livre</title>
	<script src="https://cdn.tailwindcss.com"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<style type="text/css">
		input:checked + label {
		border-color: #065f46;
		box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05);
		}
	</style>
	</head>
	<body>
	<div class="flex h-screen">
		<%@include file="SideBarBibliothecaire.jspf"%>
		<div class="w-screen">
		<%@include file="topBar.jspf"%>
		<% String msge = request.getParameter("msge");
		    if (msge != null && !msge.isEmpty()) { %>
		    <div class="mx-auto my-8 bg-red-100 w-2/3 py-8 rounded-2xl shadow-lg shadow-emerald-100/50">
		        <p class="text-xl block text-center text-ered-950 font-semibold"><%=msge%></p>
		    </div>
		<% } %>
		<% String msg=request.getParameter("msg");
			String err=request.getParameter("err");
		if("toomuch".equals(err)) { %>
		<div class="mx-auto my-8 bg-amber-100 w-2/3 py-8 rounded-2xl shadow-lg shadow-amber-100/50">
		<p class="text-xl block text-center text-ered-950 font-semibold">Vous disposez de plus de deux exemplaires avec vous actuellement.</p>
		</div>
		<%} %>
		<%if("notavailable".equals(err)) {
		%>
		<div class="mx-auto my-8 bg-amber-100 w-2/3 py-8 rounded-2xl  shadow-lg shadow-amber-100/50">
		<p class="text-xl block text-center text-emerald-950 font-semibold">Aucun exemplaire n'est disponible actuellement pour ce livre.</p>
		</div>
		<%}%>
		<%if("valid".equals(msg)) {
		%>
		<div class="mx-auto my-8 bg-emerald-100 w-2/3 py-8 rounded-2xl  shadow-lg shadow-emerald-100/50">
		<p class="text-xl block text-center text-emerald-950 font-semibold">Vous pouvez emprunter ce livre.</p>
		</div>
		<%}%>
		<%if("allvalid".equals(msg)) {
		%>
		<div class="mx-auto my-8 bg-emerald-100 w-2/3 py-8 rounded-2xl  shadow-lg shadow-emerald-100/50">
		<p class="text-xl block text-center text-emerald-950 font-semibold">L'emprunt a été effectué avec succès.</p>
		</div>
		<%}%>
		<div class="sticky overflow-y-auto mx-auto my-10 drop-shadow-xl w-4/5">
		<div class="w-2/3 mx-auto my-10 p-6 mb-16 mt-6 shadow-2xl bg-slate-100 rounded-md"  id="searchForm">
			<h1 class="text-3xl block text-center font-semibold">Chercher Disponibilité</h1>
			<hr class="mt-3">
			<form action="./emprunte/checkAvailability" method="post">
            	<div class="mt-3">
					<label class="block text-base mb-2">Donnez ISBN</label>
					<input type="text" value="<%=isbn %>" name="isbnSearch" placeholder="Entrer l'ISBN de Livre" class="border w-full text-base px-2 py-2 text-gray-900 rounded-lg focus:outline-none focus:ring-0 focus:border-green-600">
				</div>
				<div class="mt-3">
					<label class="block text-base mb-2">Donnez CIN</label>
					<input type="text" value="<%=idEmprunteur %>" name="CINSearch" placeholder="Entrer le CIN de L'Emprunteur" class="border w-full text-base px-2 py-2 text-gray-900 rounded-lg focus:outline-none focus:ring-0 focus:border-green-600">
				</div>
				<div class="mt-5 text-center">
					<button class="my-2 border-2 border-blue-700 bg-blue-700 text-white py-2 w-1/2 rounded-md hover:bg-transparent hover:text-blue-700 font-semibold shadow-lg shadow-blue-100/100">Chercher Disponibilité</button>
					<button class="border-2 my-2 border-black bg-black text-white py-2 w-1/2 rounded-md hover:bg-transparent hover:text-black font-semibold shadow-lg" id="nextButton">Suivant</button>
				</div>
			</form>
		</div>
		<div class="w-2/3 mx-auto my-10 p-6 mb-16 mt-6 shadow-2xl bg-slate-100 rounded-md" id="EmpruntForm" style="display: none;"	>
			<h1 class="text-3xl block text-center font-semibold">Emprunter Exemplaire</h1>
			<hr class="mt-3">
			<form action="./emprunte/ajouteEmprunte" method="post">
				<div class="mt-3">
					<label class="block text-base mb-2">Donnez CIN</label>
					<input type="text" name="CIN" placeholder="Entrer le CIN de L'Emprunteur" value="<%=idEmprunteur %>" class="border w-full text-base px-2 py-2 text-gray-900 rounded-lg focus:outline-none focus:ring-0 focus:border-emerald-800">
				</div>
				<div class="mt-3">
					<label class="block text-base mb-2">Donnez ISBN</label>
					<input type="text" name="ISBN" placeholder="Entrer l'ISBN de Livre" value="<%=isbn %>" class="border w-full text-base px-2 py-2 text-gray-900 rounded-lg focus:outline-none focus:ring-0 focus:border-emerald-800">
				</div>
				<div class="mt-3">
					<label class="block text-base mb-2">Choisir Exemplaire</label>
					<%for(Exemplaire laex : listExemplaire) { %>
				    <div class="mt-3">
				    	<input class="hidden" value="<%= laex.getNumeroInventaire() %>" id="radio_<%= laex.getNumeroInventaire() %>" type="radio" name="numIventaire">				       
						<label class="flex flex-row p-4 border-2 border-gray-400 cursor-pointer rounded-xl" for="radio_<%= laex.getNumeroInventaire() %>">
				            <span class="flex flex-col ">
					            <span class="text-xs font-semibold uppercase"><%=laex.getIsbn() %></span>
					            <span class="text-xl font-bold mt-2"><%=laex.getNumeroInventaire() %></span>
				            </span>
				            <% if(laex.getIdEtat()==1) { %>
				                <span class="text-base font-medium my-auto ml-auto">Neuf</span>
				            <%} else if(laex.getIdEtat()==2) {  %>
				                <span class="text-base my-auto ml-auto">Bon état</span>
				            <%} else if(laex.getIdEtat()==3) { %>
				                <span class="text-base my-auto ml-auto">Partiellement abimé</span>
				            <%} else if(laex.getIdEtat()==4) { %>
				                <span class="text-base my-auto ml-auto">Très abimé</span>
				            <% } %>
				        </label>
				    </div>
				<% } %>
				</div>
				<div class="mt-5 text-center">
					<a href="#">
					<button onclick="confirmSubmitForm(this.form)" class="border-2 my-2 border-green-700 bg-green-700 text-white py-2 w-1/2 rounded-md hover:bg-transparent hover:text-green-700 font-semibold shadow-lg shadow-red-100/100">
					Emprunté Livre
					</button>
					</a>
					<button class="border-2 my-2 border-black bg-black text-white py-2 w-1/2 rounded-md hover:bg-transparent hover:text-black font-semibold shadow-lg" id="backButton">
					Back
					</button>
				</div>
			</form>	
		</div>
		
		</div>
		</div>
		</div>
	
	<script>
	
    function confirmSubmitForm(form) {
    	event.preventDefault();
		        Swal.fire({
		            text: "Voulez vous vraiment Emprunté ce Livre?",
		            icon: "question",
		            showCancelButton: true,
		            confirmButtonColor: "#3085d6",
		            cancelButtonColor: "#d33",
		            confirmButtonText: "Oui, Emprunté!"
		        }).then((result) => {
		            if (result.isConfirmed) {
		                // Submit the form
		                form.submit();
		            }
		        });
		}
	    $(document).ready(function() {
	        // Check if msg is set to "valid" in the URL
	        var urlParams = new URLSearchParams(window.location.search);
	        var msg = urlParams.get('msg');
	
	        // Enable the suivant button if msg is "valid"
	        if (msg === "valid") {
	            $('#nextButton').prop('disabled', false);
	            
	        } else {
	        	$('#nextButton').removeClass('border-black bg-black hover:bg-transparent hover:text-black').addClass('border-gray-400 bg-gray-400 text-white');
	            $('#nextButton').prop('disabled', true);
	        }
	
	        // Handle click event for the "Suivant" button
	        $('#nextButton').click(function(e) {
	            // Prevent form submission
	            e.preventDefault();
	
	            // Hide the first form
	            $('#searchForm').hide();
	
	            // Show the second form
	            $('#EmpruntForm').show();
	        });
	
	        // Handle click event for the "Back" button
	        $('#backButton').click(function(e) {
	            // Prevent form submission
	            e.preventDefault();
	
	            // Hide the second form
	            $('#EmpruntForm').hide();
	
	            // Show the first form
	            $('#searchForm').show();
	        });
	    });
	</script>



	</body>
	</html>