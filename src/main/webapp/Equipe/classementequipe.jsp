<%@ page import="java.util.List" %>
<%@ page import="com.example.testeeval.model.ClassementGeneraleEquipe" %>
<%@ include file="headerClient.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-lg-9 boiteBoucle">
            <div class="row">
                <h3 class="col-lg-9"><b>Etapes</b></h3>
            </div> <br>

            <table id="tableauListe" class="table table-bordered">
                <% List<ClassementGeneraleEquipe> listRetour= (List<ClassementGeneraleEquipe>) request.getAttribute("list"); %>
                <tr id="trTableau">
                    <td class="nom"><b>Equipe</b></td>
                    <td class="nom"><b>Point general</b></td>

                </tr>
                <% for (int i=0; i<listRetour.size();i++){%>
                <tr id="trTableau">
                    <td class="nom"><%= listRetour.get(i).getEquipe().getNom() %></td>
                    <td class="nom"><%= listRetour.get(i).getPointequipe() %></td>
                </tr>
                <% } %>
            </table> <br> <br>
        </div>
        <div class="col-lg-3 categorie">
            <div class="col-lg-1"></div>
            <div class="col-lg-11 categorieVrai">
                <h3 class="text-center">Liste</h3> <hr>

                <div class="row liste">
                    <p>
                        <a href="${pageContext.request.contextPath}/equipe/listetape"> <i class="fas fa-chevron-right "></i> Liste etapes </a> <br>
                        <a href="${pageContext.request.contextPath}/admin/generCateg"> <i class="fas fa-chevron-right "></i> Generer categorie </a> <br>
                        <a href="${pageContext.request.contextPath}/equipe/classementParequipe"><i class="fas fa-chevron-right "></i>Classement par equipe</a> <br>
                        <a href="${pageContext.request.contextPath}/Classementcategorie/categorie"><i class="fas fa-chevron-right "></i>Classement par categorie</a><br>
                        <a href="${pageContext.request.contextPath}/admin/classementCoureurCateg"><i class="fas fa-chevron-right "></i>Clas coureur categorie</a> <br>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>