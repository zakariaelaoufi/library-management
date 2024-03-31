<%@ page language="java" contentType="text/html; charset=UTF-8"
	    pageEncoding="UTF-8"%>
	<%@ page import="java.io.IOException" %>
	<%@ page import="java.sql.*" %>
	<%@ page import="DAO.*" %>
	<%@ page import="Models.*" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ page import="jakarta.servlet.ServletException" %>
	<%@ page import="jakarta.servlet.http.*" %> 
	<%@ page import="java.util.ArrayList" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List des livres</title>
<script src="https://cdn.tailwindcss.com"></script>
<script src="https://cdn.tailwindcss.com"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdn.datatables.net/2.0.2/css/dataTables.dataTables.css" />
<script src="https://cdn.datatables.net/2.0.2/js/dataTables.js"></script>
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
	
	<%	LivreOperation LivreDAO = new LivreOperation();  
		ArrayList<Livre> livres = LivreDAO.getAllLivre();
	%>
	<div class="flex h-screen">
	<%@include file="SideBarAssistant.jspf"%>
	<div class="w-screen">
	<%@include file="topBar.jspf"%>
	<!-- affichage du livre -->
	<div class="sticky overflow-y-auto mx-auto my-10 drop-shadow-xl w-4/5">
	<a href="./AjouterLivre.jsp">
	<button  class="bg-green-700 border-2 border-green-700 hover:bg-transparent hover:text-green-700 my-3 text-white font-bold py-2 px-4 rounded">
		Ajouter Livre
	</button>
    </a>
    <div class="py-4">
    <table id="myTable" class="text-[15px] text-left text-gray-900 rounded-lg">
        <thead class="text-[13px] text-white uppercase bg-emerald-700 dark:text-white">
            <tr>
                <th scope="col" class="px-6 py-4">
                    Livre
                </th>
                <th scope="col" class="px-6 py-4">
                    ISBN
                </th>
                <th scope="col" class="px-6 py-4">
                    Autheur
                </th>
                <th scope="col" class="px-6 py-4">
                    Date Publication
                </th>
                <th scope="col" class="px-6 py-4">
                	Actions
                </th>
            </tr>
        </thead>
        <tbody>
        <%for(Livre livre : livres) { %>
            <tr class="bg-emerald-100 border-b dark:border-white">
                <th scope="row" class="px-6 py-4 text-black whitespace-nowrap">
                    <%=livre.getTitre() %>
                </th>
                <td class="px-6 py-4">
                    <%=livre.getISBN() %>
                </td>
                <td class="px-6 py-4">
                    <% 
				        for (int i = 0; i < livre.getListAuteurs().size(); i++) {
				            out.print(livre.getListAuteurs().get(i));
				            if (i < livre.getListAuteurs().size() - 1) {
				                out.print(", ");
				            }
				        }
				    %>
                </td>
                <td class="px-6 py-4">
                   <%=livre.getDateEdition() %>
                </td>
                <td>
				<a href="./ModifierLivre.jsp?isbn=<%=livre.getISBN() %>">
					<button class="bg-blue-600 border-2 border-blue-600 hover:bg-transparent text-white hover:text-blue-600 font-semibold py-2 px-4 m-2 rounded">
					 	 Modifier
					</button>
				</a>
				<a href="../livre/delete?isbn=<%=livre.getISBN() %>">
				<button class="bg-red-600 border-2 border-red-600 hover:bg-transparent text-white  hover:text-red-600 font-semibold my-2 py-2 px-4 rounded">
				 	 Supprimer
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




</body>
</html>   