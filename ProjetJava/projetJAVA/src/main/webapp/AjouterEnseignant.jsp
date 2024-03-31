<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajouter un livre</title>
<script src="https://cdn.tailwindcss.com"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/habibmhamadi/multi-select-tag@2.0.1/dist/css/multi-select-tag.css">
<script src="https://cdn.jsdelivr.net/gh/habibmhamadi/multi-select-tag@2.0.1/dist/js/multi-select-tag.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>

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
		<form action="./enseignant/new" method="post"> <!-- Update form action -->
            <h1 class="text-3xl block text-center font-semibold">Ajouter un Enseignant</h1>
            <hr class="mt-3">
            <div class="mt-3">
                <label for="CIN" class="block text-base mb-2">CIN</label>
                <input required type="text" name="CIN" id="CIN" class="border w-full text-base px-2 py-2 text-gray-900 rounded-lg focus:outline-none focus:ring-0 focus:border-green-600" placeholder="Enter CIN..." />
            </div>
            <div class="mt-3">
                <label for="Grade" class="block text-base mb-2">Grade</label>
                <input required type="text" name="Grade" id="Grade" class="border w-full text-base px-2 py-2 text-gray-900 rounded-lg focus:outline-none focus:ring-0 focus:border-green-600" placeholder="Enter Grade..." />
            </div>
            <div class="mt-3">
                <label for="Nom" class="block text-base mb-2">Nom</label>
                <input required type="text" name="Nom" id="Nom" class="border w-full text-base px-2 py-2 text-gray-900 rounded-lg focus:outline-none focus:ring-0 focus:border-green-600" placeholder="Enter Nom..." />
            </div>
            <div class="mt-3">
                <label for="Prenom" class="block text-base mb-2">Prenom</label>
           		<input required type="text" name="Prenom" id="Prenom" class="border w-full text-base px-2 py-2 text-gray-900 rounded-lg focus:outline-none focus:ring-0 focus:border-green-600" placeholder="Enter Prenom..." />
            </div>
            <div class="mt-3">
                <label for="email" class="block text-base mb-2">Email</label>
           		<input required type="email" name="Email" id="email" class="border w-full text-base px-2 py-2 text-gray-900 rounded-lg focus:outline-none focus:ring-0 focus:border-green-600" placeholder="Enter Email..." />
            </div>
            <div class="mt-3">
                <label for="phone" class="block text-base mb-2">Telephone</label>
           		<input required type="tel" name="phone" id="phone" class="border w-full text-base px-2 py-2 text-gray-900 rounded-lg focus:outline-none focus:ring-0 focus:border-green-600" placeholder="Enter Numero Telephone..." />
            </div>
			<div class="mt-5 text-center">		
					<button type="button" onclick="confirmSubmitForm(this.form)" class="border-2 border-green-700 bg-green-700 text-white py-2 w-1/2 rounded-md hover:bg-transparent hover:text-green-700 font-semibold shadow-lg shadow-green-100/100">&nbsp;&nbsp;Ajouter Etudiant</button>
            </div>
        </form>
		</div>
		</div>
		</div>

<script type="text/javascript">
    function confirmSubmitForm(form) {
        Swal.fire({
            text: "Voulez vous vraiment ajouté ce enseignant?",
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
