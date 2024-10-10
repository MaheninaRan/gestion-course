<%@ page import="java.util.List" %>
<%@ page import="com.example.testeeval.model.Coureur" %>
<%@ page import="com.example.testeeval.model.Etape" %>
<%@ include file="headerClient.jsp" %>

<%
    List<Coureur> listcoureur = (List<Coureur>) request.getAttribute("coureur");
    Etape etape = (Etape) request.getAttribute("etape");
%>


<div class="container">
    <div class="row">

        <div class="col-lg-9 boiteBoucle">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title"><%= etape.getNom()%></h5>
                    <form action="${pageContext.request.contextPath}/equipe/insert"  method="post">
                        <input type="hidden" value="<%= etape.getId()%>" name="etape">
                        <div class="row mb-3">
                            <label class="col-sm-2 col-form-label">Select</label>

                            <div class="col-sm-10">
                                <select class="form-select" aria-label="Default select example" name="coureur">
                                    <%for(Coureur c :listcoureur){%>
                                    <option value="<%= c.getId()%>"><%= c.getNom()%></option>
                                    <%}%>
                                </select>
                            </div>
                        </div>
                        <div class="card" style="text-align: center;width: 300px; margin: auto">
                            <button type="submit" class="btn btn-warning rounded-pill" >valider</button>
                        </div>

                    </form><!-- End General Form Elements -->
                </div>
            </div>
        <div/>


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










