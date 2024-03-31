<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Accueil de la Gestion de la Bibliothèque</title>
<script src="https://cdn.tailwindcss.com"></script>
<style>
  body, html {
    height: 100%;
    margin: 0;
  }
  .bg-image {
    /* The image used */
    background-image: url('image/libraryBG.jpg');

    height: 100%; 
    
    /* Center and scale the image nicely */
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
  }
  .bg-text {
    background-color: rgb(255, 255, 255); /* Fallback color */
    background-color: rgba(255, 255, 255, 0.5); /* White text with a little bit see-through */
    color: black;
    font-weight: bold;
    border: 3px solid #f1f1f1;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    z-index: 2;
    width: 80%;
    padding: 20px;
    text-align: center;
  }
  .button-container {
    margin: 20px 0;
  }

</style>
</head>
<body>

<div class="bg-image"></div>

<div class="bg-text rounded-xl border-2 border-emerald-900">
  <h2 class="text-emerald-800 py-3 text-xl font-bold">Bienvenue dans le système de gestion de bibliothèque</h2>
  <div class="button-container">
    <a href="Login/loginBiblio.jsp" class="w-44 mx-2 py-4 px-6 font-semibold	 border-2 border-emerald-800 rounded-md shadow-sm text-base text-white bg-emerald-800 hover:text-emerald-800 hover:bg-transparent">Espace Bibliothécaire</a>
    <a href="Login/loginAssistant.jsp" class="w-44 mx-2 py-4 px-6 font-semibold border-2 border-emerald-800 rounded-md shadow-sm text-base text-white bg-emerald-800 hover:text-emerald-800 hover:bg-transparent">Espace Assistant</a>
  </div>
</div>
 

</body>
</html>
