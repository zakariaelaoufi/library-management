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
	EmprunteOperation EmpruntDAO = new EmprunteOperation();;
	int total = EmpruntDAO.getNumberOfLatness();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body>

<div class="flex">
		<%@include file="SideBarAssistant.jspf"%>
		<div class="w-screen ">
		<%@include file="topBar.jspf"%>
		<div class="ml-28 mt-8">
		<h1 class="text-3xl font-bold">Hello, Admin</h1>
		</div>
		<div class="w-2/3 mx-auto my-10 p-6 mb-40 mt-6">
		<div class="flex-col">
		<div class="flex shadow-lg mx-auto w-full mb-8">
		            <img class="w-auto h-36 object-contain px-4 m-auto" src="borrow.png" alt="">
		            <div class="p-8 my-auto">
		                <h1 class="text-2xl font-semibold text-gray-800">Emprunter des livres</h1>
		                <p class="text-base text-gray-400 mt-2">
		                    Emprunter les livres pour les étudiants&nbsp;
		                </p>
		                <button class="border-2 text-white bg-emerald-900 border-emerald-900 hover:bg-transparent hover:text-emerald-900 font-bold mt-4 w-3/4 py-2 px-8 rounded">
						  Emprunter Livre
						</button>
		            </div>
		</div>
		<div class="flex shadow-lg mx-auto w-full mb-8">
		            <img class="w-auto h-36 object-contain px-4 m-auto" src="book.png" alt="">
		            <div class="p-8 my-auto">
		                <h1 class="text-2xl font-semibold text-gray-800">Consulter les Livres</h1>
		                <p class="text-base text-gray-400 mt-2">
		                    Consulter les livres pour les étudiant
		                </p>
		                <button class="border-2 text-white bg-emerald-900 border-emerald-900 hover:bg-transparent hover:text-emerald-900 font-bold mt-4 w-3/4 py-2 px-8 rounded">
						  Consulter Livre
						</button>
		            </div>
		</div>
		<div class="flex shadow-lg mx-auto w-full mb-8">
		            <img class="w-auto h-36 object-contain px-4 m-auto" src="file.png" alt="">
		            <div class="p-8 my-auto">
		                <h1 class="text-2xl font-semibold text-gray-800">Consulter Exemplaires</h1>
		                <p class="text-base text-gray-400 mt-2">
		                    Consulter les exemplaires pour les étudiant
		                </p>
		                <a href="./EnvoyerRelance.jsp">
		                	<button class="border-2 text-white bg-emerald-900 border-emerald-900 hover:bg-transparent hover:text-emerald-900 font-bold mt-4 w-3/4 py-2 px-8 rounded">
							  Consulter Exemplaire
							</button>
		                </a>
		            </div>
		</div>
		</div>
		</div>
		</div>
		</div>

</body>
</html>