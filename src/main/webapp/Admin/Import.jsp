<%@ page import="java.util.List" %>
<%@ include file="headerAdmin.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-lg-9 boiteBoucle">
            <div class="row" style="margin-top: 20px;">
                <form action="${pageContext.request.contextPath}/upload/ImportEtapeResultat" method="post" enctype="multipart/form-data">
                    <div class="row">
                        <label for="mt" class="col-sm-2 col-form-label">Etapes</label>
                        <div class="col-sm-10">
                            <input type="file" name="etape" class="form-control" id="mt">
                        </div>
                    </div>
                    <div class="row" style="margin-top: 5px;">
                        <label for="devis" class="col-sm-2 col-form-label">Resultat</label>
                        <div class="col-sm-10">
                            <input type="file" name="resultat" class="form-control" id="devis">
                        </div>
                    </div>
                    <div class="col-lg-3" style="margin-top: 5px;">
                        <button type="submit" class="btn btn-primary">Importer</button>
                    </div>
                </form>
            </div> <br> <br> <br>
            <div class="row">
                <form class="col-lg-offset-3 col-lg-7" action="${pageContext.request.contextPath}/importpoint" enctype="multipart/form-data" method="post">
                    <legend class="text-center">Insertion point</legend>
                    <div class="row">
                        <div class="col-lg-2">Point</div>
                        <input type="file" name="point" class="col-lg-4">
                    </div><br>
                    <div class="row"><button class="col-lg-offset-5 col-lg-2" type="submit" style="background-color: blue;color:white">Valider</button></div>
                </form>
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