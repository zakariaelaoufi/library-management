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
		<%@include file="SideBarBibliothecaire.jspf"%>
		<div class="w-screen ">
		<%@include file="topBar.jspf"%>
		<div class="ml-28 mt-8">
		<h1 class="text-3xl font-bold">Hello, Admin</h1>
		</div>
		<div class="w-2/3 mx-auto my-10 p-6 mb-40 mt-6">
		<div class="flex-col">
		<div class="flex shadow-lg mx-auto w-full mb-8">
		            <img class="w-auto h-36 object-contain px-4 m-auto" src="late.png" alt="">
		            <div class="p-8 my-auto">
		            	<% String color = null;
		            	if(total == 0)
		            		color = "text-emerald-600";	
		            	else
		            		color = "text-red-600";
		            	%>
		                <h1 class="text-2xl font-semibold <%=color %>"><%=total %> Personnes en Retard</h1>
		                <p class="text-base text-gray-400 mt-2">
		                    Envoyer un email de relance à ces personnes
		                </p>
		                <a href="./EnvoyerRelance.jsp">
			                <button class="border-2 text-white bg-emerald-900 border-emerald-900 hover:bg-transparent hover:text-emerald-900 font-bold mt-4 w-3/4 py-2 px-8 rounded">
							  Traiter Retard
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