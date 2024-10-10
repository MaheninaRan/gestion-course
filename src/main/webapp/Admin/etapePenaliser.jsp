<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="com.example.testeeval.model.*" %>
<%@ page import="java.sql.Time" %>
<%@ include file="headerAdmin.jsp" %>
<%
	List<Etape> categories = (List<Etape>) request.getAttribute("etapes");
	List<ClassementParEtapeParCoureurPoint> clas = (List<ClassementParEtapeParCoureurPoint>) request.getAttribute("classement");
%>

<!-- ======= Sidebar ======= -->

<style>

	.search-bar {
		width: 100%;
		max-width: 400px; /* Par exemple, une largeur maximale de 400 pixels */

	}

	.search-form {
		display: flex; /* Utiliser le modèle de boîte flexible pour aligner les éléments */
		justify-content: center; /* Centrer les éléments horizontalement */
		align-items: center; /* Centrer les éléments verticalement */
	}


	.search-form input[type="text"] {
		padding: 10px; /* Ajouter un peu d'espace autour du texte de la zone de recherche */
		border: 1px solid #ccc; /* Ajouter une bordure autour de la zone de recherche */
		border-radius: 5px; /* Arrondir les coins de la zone de recherche */
		flex: 1; /* Permettre à la zone de recherche de s'étirer pour remplir l'espace disponible */
	}

	/*.penalite{*/
	/*	background-color: #d4edda; !* Vert clair *!*/
	/*}*/
	.search-form button {
		padding: 10px; /* Ajouter un peu d'espace autour du bouton de recherche */
		border: none; /* Supprimer la bordure du bouton */
		background-color: #007bff; /* Couleur de fond du bouton */
		color: #fff;
		border-radius: 5px;
		cursor: pointer;
		margin-left: 5px;
	}




</style>

<div class="container">
	<div class="row">
		<div class="col-lg-9">
			<br> <br>
			<form class="row needs-validation" data-parsley-validate=""  action="${pageContext.request.contextPath}/Classementcategorie/etapeparid"  method="post">
				<label class="col-lg-2">Categorie</label>
				<div class="col-lg-7">
					<select class="col-lg-7" aria-label="Default select example" name="idetape">
						<%for (Etape c : categories){%>
						<option value="<%= c.getId()%>"><%= c.getNom()%></option>
						<%}%>
					</select>
				</div>
				<div class="col-lg-3">
					<button class="btn btn-primary w-100" type="submit">Entrer</button>
				</div>
			</form>




			<section class="section">
				<div class="row">
					<div class="col-lg-12">
						<div class="card">
							<div class="card-body">
								<!-- Primary Color Bordered Table -->

								<table class="table ">
									<thead>
									<tr>
										<th>Etape</th>
										<th>Coureur</th>
										<th>Genre</th>
										<th>Chrono</th>
										<th>Penalite</th>
										<th>Temps</th>
										<th>Rang</th>

									</tr>
									</thead>
									<tbody>
									<% for( ClassementParEtapeParCoureurPoint u : clas){ boolean hasPenalty = (u.getPenalite() != null && !u.getPenalite().equals(Time.valueOf("00:00:00")));  %>
										<tr class="<%= hasPenalty ? "penalite" : "" %>">
											<td> <%= u.getEtape().getNom() %></td>
											<td> <%= u.getCoureur().getNom() %></td>
											<td> <%= u.getEquipe().getNom()%></td>
											<td><%= u.getCoureur().getGenre().getNom()%></td>
											<td><%= u.getChrono()%></td>
											<td><%= u.getPenalite()%></td>
											<td><%= u.getTempsFinal()%></td>
											<td><%= u.getRang()%></td>
										</tr>
									<%}%>
									</tbody>
								</table>


								<!-- End Primary Color Bordered Table -->


								<!-- End small tables -->

							</div>
						</div>


					</div>
				</div>

			</section>
		</div>
		<div class="col-lg-3 categorie">
			<div class="col-lg-1"></div>
			<div class="col-lg-11 categorieVrai">
				<h3 class="text-center">Liste</h3> <hr>

				<div class="row liste">
					<p>
						<a href="${pageContext.request.contextPath}/lag/listetape"> <i class="fas fa-chevron-right "></i> Liste etapes </a> <br>
						<a href="${pageContext.request.contextPath}/equipe/generer"> <i class="fas fa-chevron-right "></i> Generer categorie </a> <br>
						<a href="${pageContext.request.contextPath}/lag/classementParequipe"><i class="fas fa-chevron-right "></i>Classement par equipe</a> <br>
						<a href="${pageContext.request.contextPath}/Classementcategorie/categorie"><i class="fas fa-chevron-right "></i>Classement par categorie</a><br>
						<a href="${pageContext.request.contextPath}/Classementcategorie/selectall"><i class="fas fa-chevron-right "></i>Clas Etape avec penalite</a><br>
						<a href="${pageContext.request.contextPath}/lag/penalite"><i class="fas fa-chevron-right "></i>Penalite</a> <br>
						<a href="${pageContext.request.contextPath}/lag/importView"><i class="fas fa-chevron-right "></i>Importation</a> <br>
						<a href="${pageContext.request.contextPath}/lag/pdfView"><i class="fas fa-chevron-right "></i>PDF</a> <br>
						<a href="${pageContext.request.contextPath}/lag/delete"><i class="fas fa-chevron-right "></i> Clean BD</a> <br>
					</p>
				</div>
			</div>
		</div>
	</div>
</div>






