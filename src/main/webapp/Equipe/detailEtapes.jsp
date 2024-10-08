<%@ page import="java.util.List" %>
<%@ page import="com.example.testeeval.model.Etape" %>
<%@ page import="com.example.testeeval.model.Coureur" %>
<%@ page import="com.example.testeeval.model.ClassementParEtape" %>
<%@ include file="headerClient.jsp" %>

<%
	Etape etape = (Etape) request.getAttribute("listetape");
	List<Coureur> coureursList = (List<Coureur>) request.getAttribute("listCoureur");
	List<ClassementParEtape> classementList = (List<ClassementParEtape>) request.getAttribute("classement");

%>

<div class="container">
	<div class="row">
		<div class="col-lg-9 boiteBoucle"> <br><br>
			<div class="row">
					<b style="text-align: center; font-size: 25px;color: black">Detail etape  </b> <br>
				<table id="tableauListe" class="table table-bordered">
					<tr id="trTableau">
						<td class="nom"><b>Rang</b></td>
						<td class="nom"><b>Nom</b></td>
						<td class="nom"><b>Longueur</b></td>
						<td class="nom"><b>Coureur</b></td>

					</tr>
					<tr id="trTableau">
						<td class="nom"><%= etape.getRang() %></td>
						<td class="nom"> <a href="${pageContext.request.contextPath}/equipe/detailEtapes?id=<%= etape.getId() %>"> <%= etape.getNom() %></a> </td>
						<td class="nom"><%= etape.getLongueur()%> Km</td>
						<td class="nom"><%= etape.getNbcoureur() %></td>
					</tr>
				</table> <br> <br>
			</div> <br> <br>

			<button style ="background: initial;border: initial;"
					data-toggle="modal"
					data-target=".modalEditMarque"
					data-idetape="<%= etape.getId() %>"
			>
				Ajouteur une coureur  <br>
				<i class="fas fa-plus"></i>
			</button>

			<h3>Resultat </h3>
			<table id="tableauListe" class="table table-bordered">
				<tr id="trTableau">
					<td class="nom"><b>Coureur</b></td>
					<td class="nom"><b>Chrono</b></td>
					<td class="nom"><b>Point</b></td>

				</tr>
				<% for (int i = 0; i<classementList.size();i++) { %>

				<tr id="trTableau">
					<td class="nom"><%= classementList.get(i).getCoureur().getNom() %></td>
					<td class="nom"><%= classementList.get(i).getTemps() %></td>
					<td class="nom"><%= classementList.get(i).getPoint() %></td>
				</tr>
				<% } %>
			</table>
		</div>

		<div class="row">
			<div class="modal fade modalEditMarque" tabindex="-1" aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered">
					<div class="modal-content" id="modalEditMarque">
						<%
							String erreurModifier = (String) request.getAttribute("erreurFinition");
						%>
						<% if (erreurModifier!=null){ %>
						<script>alert("<%= erreurModifier %>");</script>
						<% } %>
						<form action="${pageContext.request.contextPath}/equipe/entrerCoureur" method="post">
							<legend class="text-center"> <b>Coureur</b> </legend> <hr>
							<div class="row">
								<div class="col-lg-offset-1 col-lg-2">Coureur </div>
								<div class="col-lg-offset-2 col-lg-6">
									<select name="idcoureur">
										<% for(int i = 0; i < coureursList.size();i++){ %>
											<option value="<%= coureursList.get(i).getId() %>"><%= coureursList.get(i).getNom() %></option>
										<% } %>
									</select>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-offset-2 col-lg-6"><input id="idetape" name="idetape" type="hidden"></div>
							</div>
							<div class="row">
								<div class="boutonValider col-lg-offset-5 col-lg-4"><button type="submit">Valider</button></div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<script>
			$(document).ready(function(){
				$('.modalEditMarque').on('show.bs.modal', function (event){
					var button = $(event.relatedTarget);
					var idetape = button.data('idetape');
					$('#idetape').val(idetape);
				});
			});
		</script>

		<div class="col-lg-3 categorie">
			<div class="col-lg-1"></div>
			<div class="col-lg-11 categorieVrai">
				<h3 class="text-center">Liste</h3> <hr>

				<div class="row liste">
					<p>
						<a href="${pageContext.request.contextPath}/equipe/listetape"> <i class="fas fa-chevron-right "></i> Liste etapes </a> <br>
						<a href="${pageContext.request.contextPath}/admin/generCateg"> <i class="fas fa-chevron-right "></i> Generer categorie </a> <br>
						<a href="${pageContext.request.contextPath}/equipe/classementParequipe"><i class="fas fa-chevron-right "></i>Classement par equipe</a> <br>
						<a href="${pageContext.request.contextPath}/admin/classementParcategorie"><i class="fas fa-chevron-right "></i>Classement par categorie</a><br>
						<a href="${pageContext.request.contextPath}/admin/classementCoureurCateg"><i class="fas fa-chevron-right "></i>Clas coureur categorie</a> <br>
					</p>
				</div>

			</div>
		</div>
	</div>
</div>

</body>
</html>