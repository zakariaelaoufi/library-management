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
		EmprunteOperation EmpruntDAO = new EmprunteOperation();
		ArrayList<EmpruntRelance> listEmpruntRelance = EmpruntDAO.getAllLatnessEmprunt();
	%>
	
	<!DOCTYPE html>
	<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Consulter Exemplaire</title>
	<script src="https://cdn.tailwindcss.com"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<link rel="stylesheet" href="https://cdn.datatables.net/2.0.2/css/dataTables.dataTables.css" />
	<script src="https://cdn.datatables.net/2.0.2/js/dataTables.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script type="text/javascript">
			$(document).ready( function () {
			    $('#myTable').dataTable();
			} );
		</script>
	<style>
    .dt-type-numeric {
        text-align: left !important; /* Use !important to override any conflicting styles */
    }
    .dt-type-date {
    	text-align: left !important;
    }
	</style>
	</head>
	<body>
	<div class="flex h-screen">
		<%@include file="SideBarBibliothecaire.jspf"%>
		<div class="w-screen">
		<%@include file="topBar.jspf"%>
		<% String msg=request.getParameter("msg");
		if("valid".equals(msg)) {
		%>
		<div class="mx-auto my-8 bg-emerald-100 w-2/3 py-8 rounded-2xl  shadow-lg shadow-emerald-100/50">
		<p class="text-xl block text-center text-emerald-950 font-semibold">L'email de relance est envoyée avec success</p>
		</div>
		<%}%>
		<% if("allvalid".equals(msg)) { %>
		<div class="mx-auto my-8 bg-emerald-100 w-2/3 py-8 rounded-2xl shadow-lg shadow-emerald-100/50">
		<p class="text-xl block text-center text-emerald-950 font-semibold">Tout les Emails de relance sont evoyées</p>
		</div>
		<%} %>
		<% if("invalid".equals(msg)) { %>
		<div class="mx-auto my-8 bg-red-100 w-2/3 py-8 rounded-2xl shadow-lg shadow-emerald-100/50">
		<p class="text-xl block text-center text-ered-950 font-semibold">Il y a un erreur</p>
		</div>
		<%} %>
		<div class="sticky overflow-y-auto mx-auto my-10 drop-shadow-xl w-4/5">
		
		<a id="deleteAllrecords" href="#" onclick="confirmSubmitForm()">
			<button class="bg-teal-700 border-2 border-teal-700 hover:bg-transparent hover:text-teal-700 text-white font-bold py-2 px-4 rounded">
				Relancer Tout
			</button>
	    </a>
	    <div class="py-3">
	    <table id="myTable" class="text-[15px] text-left text-gray-900 overflow-hidden rounded-lg">
	        <thead class="text-[13px] text-white uppercase bg-emerald-700 dark:text-white">
	            <tr>
	            	<th scope="col" class="px-6 py-4"><input type="checkbox" name="" id="select_all_boxes"></th>
	                <th scope="col" class="px-6 py-4">
	                    Identifiant
	                </th>
	                <th scope="col" class="px-6 py-4">
	                    Nom
	                </th>
	                <th scope="col" class="px-6 py-4">
	                    Prenom
	                </th>
	                <th scope="col" class="px-6 py-4">
	                    Exemplaire
	                </th>
	                <th>
	                Date Prevue Retoure
	                </th>
	                <th scope="col" class="px-6 py-4">
	                	Actions
	                </th>
	            </tr>
	        </thead>
	        <tbody>
	        <% for(EmpruntRelance emp : listEmpruntRelance) { %>
	            <tr class="bg-emerald-100 border-b dark:border-white">
	                <td class="px-6 py-4"><input type="checkbox"  class="checkBox" name="idEmprunt" id="singleBox" value="<%=emp.getId() %>"></td>
	                <th scope="row" class="px-6 py-4 text-black whitespace-nowrap">
	                    <%=emp.getId_emprunteur()%>
	                </th>
	                <td class="px-6 py-4">
	                    <%=emp.getNom() %>
	                </td>
	                <td class="px-6 py-4">
	                    <%=emp.getPrenom() %>
	                </td>
	                <td class="px-6 py-4">
	                    <%=emp.getNomExemplaire() %>
	                </td>
	                <td class="px-6 py-4"> 
	                	<%=emp.getDate_prevue_retour() %>
	                </td>
	                <td>
					<a href="./emprunte/envoyerrelance?idEmprunte=<%=emp.getId()%>">
						<button  onclick="confirmSubmitForm()" class="bg-teal-700 border-2 border-teal-700 hover:bg-transparent text-white hover:text-teal-700 font-semibold py-2 px-4 m-2 rounded flex items-center">
						    <p class="mr-1">Relancer</p>
						    <svg class="h-5 w-auto object-contain" fill="none" viewBox="0 0 24 24" stroke="currentColor">
						        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z"/>
						    </svg>
						</button>
					</a>
	                </td>
	            </tr>
	            <%} %>
	        </tbody>
	    </table>
	   	</div>
		</div>
		</div>
		</div>
	
	<script type="text/javascript">
		$(function(e) {
			$("#select_all_boxes").click(function() {
				$('.checkBox').prop('checked',$(this).prop('checked'));
			});
		});
	</script>
	
	<script>
    function sendRelanceRequest() {
        var selectedIds = [];
        $('input[name="idEmprunt"]:checked').each(function() {
            selectedIds.push($(this).val());
        });

        var url;
        if (selectedIds.length === 1) {
            url = './emprunte/envoyerrelance?idEmprunte=' + selectedIds[0];
        } else if (selectedIds.length > 1) {
            url = './emprunte/envoyerrelance';
            for (var i = 0; i < selectedIds.length; i++) {
                if (i === 0) {
                    url += '?idEmprunte=' + selectedIds[i];
                } else {
                    url += '&idEmprunte=' + selectedIds[i];
                }
            }
        }

        window.location.href = url;
    }
    
    function confirmSubmitForm() {
        Swal.fire({
            text: "Voulez vous vraiment envoyé un email de relance?",
            icon: "question",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Oui, relancé!"
        }).then((result) => {
            if (result.isConfirmed) {
                // Submit the form
                sendRelanceRequest()
            }
        });
    }
</script>
	</body>
	</html>