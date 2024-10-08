<%@ page import="java.util.List" %>
<%@ page import="com.example.testeeval.model.ClassementGeneraleEquipe" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.testeeval.model.ClassementParEtape" %>
<%@ page import="com.example.testeeval.model.Alea" %>
<%@ include file="headerAdmin.jsp" %>


<% List<Alea> listRetour= (List<Alea>) request.getAttribute("liste"); %>


<div class="container">
    <div class="row">
        <div class="col-lg-9 boiteBoucle">
            <div class="row">
                <h3 class="col-lg-9"><b>Etapes</b></h3>
            </div> <br>

            <table id="tableauListe" class="table table-bordered">
                <tr id="trTableau">
                    <td class="nom"><b>Coureur</b></td>
                    <td class="nom"><b>Point</b></td>

                </tr>
                <% for (int i=0; i<listRetour.size();i++){%>
                <tr id="trTableau">
                    <td class="nom"> <a href="${pageContext.request.contextPath}/equipe/lien?idequipe=<%= listRetour.get(i).getEquipe().getId() %>"> <%= listRetour.get(i).getCoureur().getNom() %> </a></td>
                    <td class="nom"><%= listRetour.get(i).getTotal() %></td>
                </tr>
                <% } %>
            </table> <br> <br>

            <style>
                #chart {
                    max-width: 300px;
                    margin: 0 auto;
                }
            </style>
            <div class="row">
                <div id="chart">
                    <canvas id="Camembert"></canvas>
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

</body>
</html>