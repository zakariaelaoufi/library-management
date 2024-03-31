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
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifier livre</title>
<script src="https://cdn.tailwindcss.com"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/habibmhamadi/multi-select-tag@2.0.1/dist/css/multi-select-tag.css">
<script src="https://cdn.jsdelivr.net/gh/habibmhamadi/multi-select-tag@2.0.1/dist/js/multi-select-tag.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>

		<%
		LivreOperation livreDAO = new LivreOperation();;
		AuteurOperation AuteurDAO = null;
		Connection connection = null;
		Connexion connexion = new Connexion();
        connection = connexion.getConnection();
        
        String idLiv = request.getParameter("isbn");
		Livre livre = livreDAO.getLivre(request.getParameter("isbn"));
		ArrayList<Keyword> keywords = Actions.getidKeyLivre(connection, idLiv);
		ArrayList<Keyword> allkeywords = Actions.getAllKeywords(connection);		
		ArrayList<Auteur> authors = Actions.getidAuteurLivre(connection, idLiv);
		%>


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
		<div class="w-2/3 mx-auto my-10 p-6 mb-16 mt-6 shadow-2xl bg-slate-100 rounded-md">
		<form action="livre/update" method="post">
            <h1 class="text-3xl block text-center font-semibold">Ajouter un livre</h1>
            <hr class="mt-3">
            <div class="mt-3">
                <label for="isbn" class="block text-base mb-2">ISBN</label>
                <input value="<%=livre.getISBN() %>" disabled type="text" id="isbn" class="border w-full text-base px-2 py-2 text-gray-900 rounded-lg focus:outline-none focus:ring-0 focus:border-green-600" placeholder="Enter ISBN..." />
				<input value="<%=livre.getISBN() %>" type="hidden" name="ISBN" id="isbn" class="border w-full text-base px-2 py-2 text-gray-900 rounded-lg focus:outline-none focus:ring-0 focus:border-green-600" placeholder="Enter ISBN..." />
            </div>
            <div class="mt-3">
                <label for="titre" class="block text-base mb-2">Titre</label>
                <input value="<%=livre.getTitre() %>" type="text" name="titre" id="titre" class="border w-full text-base px-2 py-2 text-gray-900 rounded-lg focus:outline-none focus:ring-0 focus:border-green-600" placeholder="Enter Titre..." />
            </div>
            <div class="mt-3">
                <label for="resume" class="block text-base mb-2">Resume</label>
				<textarea id="resume" name="resume" rows="4" class="block p-2.5 w-full text-base border text-gray-900 rounded-lg focus:outline-none focus:ring-0 focus:border-green-600 " placeholder="Donner Resume..."><%=livre.getResume() %></textarea>
            </div>
            <div class="mt-3">
                <label for="autheur" class="block text-base mb-2">Auteur</label>
                <div class="flex">
                <input type="text" id="auteur"  class="border w-full text-base px-2 py-2 text-gray-900 rounded-lg focus:outline-none focus:ring-0 focus:border-green-600" placeholder="Enter Autheur..." />
                <button type="button" onclick="addAuthor()" class="border-2 border-green-700 bg-green-700 text-white ml-4 py-2 w-1/3 rounded-md hover:bg-transparent hover:text-green-700 font-semibold">Ajouter</button>
                </div>
                <div class=flex>
                <select name="auteur[]" class="w-full overflow-auto my-4 py-2 border font-normal italic indent-6 font-medium rounded-lg focus:outline-none focus:ring-0 focus:border-green-600" id="authorList" multiple>
                	<% for(Auteur author : authors) { %>
                		<option value="<%= author.getNomAuteur() %>" selected>
                			<%=author.getNomAuteur() %>
                		</option>
                	<%} %>
                </select>
            	<button type="button" onclick="removeAuthor()" class="border-2 h-1/2 m-auto border-red-700 bg-red-700 text-white ml-4 py-2 w-1/3 rounded-md hover:bg-transparent hover:text-red-700 font-semibold">Supprimer</button>
                </div>
                
            </div>
            <div class="mt-3">
                <label for="dateEdition" class="block text-base mb-2">Date D'Edition</label>
                <input value="<%=livre.getDateEdition() %>" type="date" name="dateEdition" id="dateEdition" class="border w-full text-base px-2 py-2 text-gray-900 rounded-lg focus:outline-none focus:ring-0 focus:border-green-600"/>
            </div>
            <div class="mt-3">
                <label for="keyword" class="block text-base mb-2">Keywords</label>
				<select name="keyword[]" id="keyword" multiple>
				    <%for(Keyword keyword : allkeywords) { 
				    	boolean isSelected = false;
				        for (Keyword kw : keywords) {
				            if (kw.getMotCle().equals(keyword.getMotCle())) {
				                isSelected = true;
				                break;
				            }
				        }
				    %>
				    	<option value="<%=keyword.getIdKeyword()%>" <% if (isSelected) { %> selected <% } %>>
				    	<%=keyword.getMotCle() %>
				    	</option>
				    <%} %>
				</select>
            </div>
            
			<div class="mt-5 text-center">		
					<button type="button" onclick="confirmSubmitForm(this.form)" class="border-2 border-green-700 bg-green-700 text-white py-2 w-1/2 rounded-md hover:bg-transparent hover:text-green-700 font-semibold shadow-lg shadow-red-100/100">&nbsp;&nbsp;Modifier Livre</button>
            </div>
        </form>
		</div>
		</div>
		</div>
		
		
		<script>
        function addAuthor() {
            var authorInput = document.getElementById('auteur');
            var authorList = document.getElementById('authorList');

            // Get the value of the author input
            var author = authorInput.value.trim();

            // Add the author to the select only if it's not empty and not already present
            if (author !== '' && !authorList.querySelector('option[value="' + author + '"]')) {
                var option = document.createElement('option');
                option.value = author;
                option.textContent = author;
                authorList.appendChild(option);

                // Clear the author input after adding
                authorInput.value = '';
                
             // Select the newly added option
                option.selected = true;
            }
        }

        function removeAuthor() {
            var authorList = document.getElementById('authorList');
            var selectedOptions = authorList.selectedOptions;

            // Remove selected authors from the list
            for (var i = selectedOptions.length - 1; i >= 0; i--) {
                authorList.removeChild(selectedOptions[i]);
            }
        }
    </script>
    
    <script>
    new MultiSelectTag('keyword', {
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
        text: "Voulez vous vraiment modifier ce livre?",
        icon: "question",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Oui, Ajouté!"
    }).then((result) => {
        if (result.isConfirmed) {
            // Remove deselected authors
            removeDeselectedAuthors();
            // Submit the form
            form.submit();
        }
    });
}

function removeDeselectedAuthors() {
    var authorList = document.getElementById('authorList');
    var selectedOptions = authorList.selectedOptions;

    // Remove deselected authors from the list
    for (var i = authorList.options.length - 1; i >= 0; i--) {
        if (!authorList.options[i].selected) {
            authorList.removeChild(authorList.options[i]);
        }
    }
}
</script>
</body>
</html>
