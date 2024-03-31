<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Espace Bibliothecaire</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body>

<!-- component -->
<div class="min-h-screen flex items-center justify-center w-full">
    <div class="bg-slate-100 shadow-2xl shadow-emerald-200 rounded-lg px-8 py-6 w-1/3">
        <h1 class="text-2xl font-bold text-center mb-4 text-emerald-800">Espace Bibliothécaire</h1>
        <form id="loginForm" action="../Login" method="post">
            <div class="mb-4">
                <label for="inputUsername" class="block text-sm font-medium text-emerald-800 mb-2">Nom d'utilisateur</label>
                <input type="text" id="inputUsername" name="uname" class="shadow-sm rounded-md w-full px-3 py-2 border border-gray-300 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500" placeholder="Nom d'utilisateur" required>
            </div>
            <div class="mb-4">
                <label for="inputPassword" class="block text-sm font-medium text-emerald-800 mb-2">Mot de passe</label>
                <input type="password" id="inputPassword" name="pwd" class="shadow-sm rounded-md w-full px-3 py-2 border border-gray-300 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500" placeholder="Mot de passe" required>
            </div>
            <button type="submit" name="loginType" value="Biblio" class="w-full flex justify-center py-2 px-4 border-2 border-emerald-800 rounded-md shadow-sm text-sm font-medium text-white bg-emerald-800 hover:text-emerald-800 hover:bg-transparent">Connexion</button>
            <div style="text-align: center; margin-top: 20px;">
                <a href="../Accueil_page.jsp" class="text-teal-800 font-semibold">Retour à l'accueil</a>
            </div>
            <p id="message" class="mt-3 text-red-700 text-center" style="display:none;"></p>
        </form>
    </div>
</div>

<script>
    document.getElementById('loginForm').addEventListener('submit', function(event) {
        var username = document.getElementById("inputUsername");
        var password = document.getElementById("inputPassword");
        var message = document.getElementById('message');

        username.classList.remove("border-red-500");
        password.classList.remove("border-red-500");

        if (!username.value || !password.value) {
            event.preventDefault(); // Prevent form submission
            if (!username.value) username.classList.add("border-red-500");
            if (!password.value) password.classList.add("border-red-500");
            message.innerText = "Veuillez remplir tous les champs.";
            message.style.display = "block";
        }
    });

    <% if("true".equals(request.getParameter("error"))) { %>
    document.getElementById('message').innerText = "Nom d'utilisateur ou mot de passe incorrect.";
    document.getElementById('message').style.display = "block";
    <% } %>
</script>

</body>
</html>