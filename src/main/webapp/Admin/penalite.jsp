<%@ page import="java.util.List" %>
<%@ page import="com.example.testeeval.model.*" %>
<%@ include file="headerAdmin.jsp" %>

<%
	List<Equipe> equipeList = (List<Equipe>) request.getAttribute("equipe");
	List<Etape>  etapeList = (List<Etape>) request.getAttribute("etape");
	List<Penalite>  penaliteList = (List<Penalite>) request.getAttribute("penalites");

%>

<style>
	/* Ajoutez votre CSS personnalisé ici */
	#modalDelete {
		padding: 20px;
		border-radius: 10px;
	}
	.modal-content {
		padding: 20px;
		border-radius: 10px;
	}
	.legend-text {
		text-align: center;
		font-size: 1.5em;
		font-weight: bold;
	}
	.modal-body {
		padding: 20px;
	}
	.radio-buttons {
		display: flex;
		justify-content: center;
		margin-bottom: 20px;
	}
	.radio-buttons input[type="radio"] {
		margin: 0 10px;
	}
	.btn-validate {
		display: flex;
		justify-content: center;
	}
	.btn-validate button {
		padding: 10px 20px;
		font-size: 1.2em;
	}
</style>

	<div class="container">
		<div class="row">
			<div class="col-lg-9 boiteBoucle"> <br><br>
				<div class="row">
						<b style="text-align: center; font-size: 25px;color: black">Penalite
							<button style="background-color: blue;border: initial;color: white" data-toggle="modal" data-target=".modalEditMarque">
									Ajouteur une resultat  <br>
									<i class="fas fa-plus"></i>
							</button>
						</b>
					<br><br>
					<table id="tableauListe" class="table table-bordered">
						<tr id="trTableau">
							<td class="nom"><b>Etape</b></td>
							<td class="nom"><b>Equipe</b></td>
							<td class="nom"><b>Temps de penalite</b></td>
							<td class="nom">Supprimer</td>
						</tr>
						<% for(int i = 0; i < penaliteList.size();i++){ %>
							<tr id="trTableau">
								<td class="nom"><%= penaliteList.get(i).getEtape().getNom() %></td>
								<td class="nom"> <%= penaliteList.get(i).getEquipe().getNom() %></td>
								<td class="nom"><%= penaliteList.get(i).getPenalite() %></td>
								<td class="nom">
									<button style="border: initial; border:none;background: initial;font-size:16px"
											data-toggle="modal"
											data-target=".modalDelete"
											data-idetape="<%= penaliteList.get(i).getEtape().getId()%>"
											data-idequipe="<%= penaliteList.get(i).getEquipe().getId() %>"
									><br>
										<i class="fas fa-trash"></i>
									</button>
								</td>
							</tr>
						<% } %>

					</table> <br> <br>
				</div> <br> <br>
					<div class="modal fade modalEditMarque" tabindex="-1" aria-hidden="true">
						<div class="modal-dialog modal-dialog-centered">
							<div class="modal-content" id="modalEditMarque">
								<%
									String erreurModifier = (String) request.getAttribute("erreurFinition");
								%>
								<% if (erreurModifier!=null){ %>
								<script>alert("<%= erreurModifier %>");</script>
								<% } %>
								<form action="${pageContext.request.contextPath}/penalite/add" method="post">
									<legend class="text-center">
										<p>___________________________</p> <br><br>
										<b>Penalite</b> </legend> <hr>
									<div class="row">
										<div class="col-lg-offset-1 col-lg-2">Etape</div>
										<div class="col-lg-offset-2 col-lg-6">
											<select name="etape" class="form-control">
												<% for(int i = 0; i < etapeList.size();i++){ %>
													<option value="<%= etapeList.get(i).getId() %>"><%= etapeList.get(i).getNom() %></option>
												<% } %>
											</select>
										</div>
									</div>
									<div class="row">
										<div class="col-lg-offset-1 col-lg-2">Equipe</div>
										<div class="col-lg-offset-2 col-lg-6">
											<select name="equipe" class="form-control">
												<% for(int i = 0; i < equipeList.size();i++){ %>
												<option value="<%= equipeList.get(i).getId() %>"><%= equipeList.get(i).getNom() %></option>
												<% } %>
											</select>
										</div>
									</div>

									<div class="row">
										<div class="col-lg-offset-1 col-lg-2">Time</div>
											<input type="time" name="hm" class="col-lg-offset-2 col-lg-6" />
									</div> <br>
									<div class="row">
										<div class="col-lg-offset-1 col-lg-2">Seconde</div>
										<input type="number" name="ss" class="col-lg-offset-2 col-lg-6" />
									</div>
									<div class="row">
										<div class="boutonValider col-lg-offset-5 col-lg-4"><button type="submit">Valider</button></div>
									</div>
								</form>
							</div>
						</div>
					</div>
							<div class="modal fade modalDelete" tabindex="-1" aria-hidden="true">
								<div class="modal-dialog modal-dialog-centered">
									<div class="modal-content" id="modalDelete">
										<form action="${pageContext.request.contextPath}/penalite/remove" method="post">
											<legend class="text-center">
												<p>___________________________</p> <br><br>
												<b>Confirmation suppression </b> </legend> <hr>
											<div class="modal-body">
												<div class="radio-buttons">
													<label><input type="radio" id="idetape" name="idetape"> Oui</label>
													<label><input type="radio" id="idetape" name="idetape"> Non</label>
													<input type="hidden" id="idequipe" name="idequipe"/>
												</div>
												<div class="btn-validate">
													<button type="submit" class="btn btn-primary">Valider</button>
												</div>
											</div>
										</form>
									</div>
								</div>
							</div>
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


			<script>
				$(document).ready(function(){
					$('.modalEditMarque').on('show.bs.modal', function (event){
						var button = $(event.relatedTarget);
						var idetape = button.data('idetape');
						$('#idetape').val(idetape);
					});
				});

				$(document).ready(function(){
					$('.modalDelete').on('show.bs.modal', function (event){
						var button = $(event.relatedTarget);
						var idetape = button.data('idetape');
						var idequipe = button.data('idequipe');

						var modal = $(this);
						modal.find('#idetape').val(idetape);
						modal.find('#idequipe').val(idequipe);
					});
				});
			</script>

