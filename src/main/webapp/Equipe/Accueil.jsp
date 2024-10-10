<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ include file="headerClient.jsp" %>
<%
    Equipe user = ((Equipe) request.getSession().getAttribute("equipe"));
    List<VResultatCoureur> vResultatCoureurs = (List<VResultatCoureur>) request.getAttribute("vResultatCoureurs");
int idetape=0;
boolean isfirst = true;
%>


<%@ page import="com.example.testeeval.model.Equipe" %>
<%@ page import="com.example.testeeval.model.VResultatCoureur" %>


<%
    String erreur = (String) request.getAttribute("erreur");
    if(erreur != null){ %>
    <script > alert("${erreur}"); </script>
<% } %>

    <div class="container">
        <div>
            <h3><b>Equipe connecter : </b> <span id="equipe"></span>  </h3>
        </div>
        <div class="row" style="margin-top: 20px">
            <div class="col-lg-offset-1 col-lg-8">
                <div class="card" style="border: none">
                    <div class="card-body">
                        <%for (VResultatCoureur vResultatCoureur:vResultatCoureurs){%>
                            <%if(vResultatCoureur.getEtape().getRang()!=idetape){
                                if (!isfirst) { %>
                                        </tbody>
                                    </table>
                                    <div class="col-2">
                                        <a class="btn btn-primary w-120" href="${pageContext.request.contextPath}/equipe/listecoureur?idetape=<%= idetape%>">Ajouter coureur</a>
                                    </div> <br>
                                <% }
                                    idetape=vResultatCoureur.getEtape().getRang();
                                    isfirst = false;
                                %>
                                <div class="page-item">
                                    <h3><%= vResultatCoureur.getEtape().getNom()%><%= vResultatCoureur.getEtape().getRang()%>-<%= vResultatCoureur.getEtape().getNbcoureur()%></h3>
                                </div>
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th >Nom</th>
                                            <th >Temps chrono</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                            <% } %>


                            <!-- Primary Color Bordered Table -->
                                <tr>
                                    <td><%= vResultatCoureur.getCoureur().getNom()%> </td>
                                    <td> <%= vResultatCoureur.getTemps()%></td>
                                </tr>
                            <!-- End Primary Color Bordered Table -->

                            <%
                            }
                            if (!isfirst) {
                            %>
                                </tbody>
                            </table>
                            <div class="col-2">
                                <a class="btn btn-primary w-120" style="color: white" href="${pageContext.request.contextPath}/equipe/listecoureur?idetape=<%= idetape%>">Ajouter coureur</a>
                            </div> <br>
                        <% } %>
                        <!-- End small tables -->
                    </div>

                </div>
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

<style>
    a{
        text-decoration: none;
        color:white;
    }
</style>

<script>
    let equipe = document.getElementById("equipe");
    equipe.innerText = sessionStorage.getItem("nomequipe");
</script>
