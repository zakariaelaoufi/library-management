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
<meta charset="ISO-8859-1">
<title>Consulter Exemplaire</title>
<script src="https://cdn.tailwindcss.com"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdn.datatables.net/2.0.2/css/dataTables.dataTables.css" />
<script src="https://cdn.datatables.net/2.0.2/js/dataTables.js"></script>
<script type="text/javascript">
		$(document).ready( function () {
		    $('#myTable').dataTable();
		} );
	</script>
</head>
<body>

<div class="flex h-screen">
	<%@include file="SideBarAssistant.jspf"%>
	<div class="w-screen">
	<%@include file="topBar.jspf"%>
	<div class="sticky overflow-y-auto mx-auto my-10 drop-shadow-xl w-4/5">
	<%	ExemplaireOperation ExemplaireDAO = new ExemplaireOperation();  
		ArrayList<Exemplaire> lexp = ExemplaireDAO.getAllExemplaire();
	%>
	<a href="AjouterExemplaire.jsp">
		<button  class="bg-green-700 border-2 border-green-700 hover:bg-transparent hover:text-green-700 my-3 text-white font-bold py-2 px-4 rounded">
			Ajouter Exemplaire
		</button>
    </a>
    
    <div class="py-4">
    <table id="myTable" class="text-[15px] text-left text-gray-900 overflow-hidden rounded-lg">
        <thead class="text-[13px] text-white uppercase bg-emerald-700 dark:text-white">
            <tr>
                <th scope="col" class="px-6 w-44 py-4">
                    ISBN
                </th>
                <th scope="col" class="px-6 w-52 py-4">
                    N d'Inventaire
                </th>
                <th scope="col" class="px-6 w-44 py-4">
                    Etat
                </th>
                <th scope="col" class="px-6 py-4">
                	Actions
                </th>
            </tr>
        </thead>
        <tbody>
            <% for(Exemplaire exemplaire : lexp) { %>
            <tr class="bg-emerald-100 border-b dark:border-white">
                <td class="px-6 py-4"><%= exemplaire.getIsbn() %></td>
                <th class="px-6 py-4"><%= exemplaire.getNumeroInventaire() %></th>
                <td class="px-6 py-4"><%= exemplaire.getEtat() %> </td>
                   <th class="px-6 py-4">                    
                    <!-- Boutons d'actions -->
                    
                    <% if(!exemplaire.getEtat().equals("Neuf")) { %>
                        <% if(!exemplaire.getEtat().equals("En réparation")) { %>
                          <a href="./exemplaire/Reparation?numeroInventaire=<%= exemplaire.getNumeroInventaire() %>">
								<button class="bg-blue-600 border-2 border-blue-600 hover:bg-transparent text-white hover:text-blue-600 font-semibold py-2 px-4 m-2 rounded">
								 	 Envoyer en reparation
								</button>
						  </a>
                  <% } %>
                         <a href="./exemplaire/destroy?numeroInventaire=<%= exemplaire.getNumeroInventaire() %>">
							<button class="bg-red-600 border-2 border-red-600 hover:bg-transparent text-white  hover:text-red-600 font-semibold m-2 py-2 px-3 rounded">
							 	 Detruire
							</button>
						 </a>
                     <% } else { %>
                      <p class="m-2"> Aucune action nécessaire pour le moment</p>   
                    <% } %>
                </th>
            </tr>
            <% } %>
        </tbody>
    </table>
    </div>
	</div>
	</div>
	</div>

</body>
</html>