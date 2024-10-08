<%@ page import="java.util.List" %>
<%@ page import="com.example.testeeval.model.VEquipeCategorie" %>
<%@ page import="com.example.testeeval.model.Categorie" %>
<%@ page import="com.example.testeeval.model.ClassementGeneraleEquipeCategorie" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ include file="headerClient.jsp" %>
<%
    List<VEquipeCategorie> list = (List<VEquipeCategorie>) request.getAttribute("vEquipeCategories");
    List<Categorie> categories = (List<Categorie>) request.getAttribute("categorie");
    List<ClassementGeneraleEquipeCategorie> v = (List<ClassementGeneraleEquipeCategorie>) request.getAttribute("vequipe");
%>
<%
    List<String> equipes = new ArrayList<>();
    List<String> pointList = new ArrayList<>();
    for(ClassementGeneraleEquipeCategorie c : v){
        equipes.add(c.getEquipe().getNom());
        pointList.add(String.valueOf(c.getTotalpoints()));
    }

    String Data_pt_equipe = "[" + String.join(", ", pointList) + "]";
    String Data_equipe = "[" + Arrays.stream(equipes.toArray()).map(s -> "'" + s + "'").collect(Collectors.joining(", ")) + "]";
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
                <form class="row needs-validation" data-parsley-validate=""  action="${pageContext.request.contextPath}/Classementcategorie/rahamisy"  method="post">
                    <label class="col-lg-2">Categoriee</label>
                    <div class="col-lg-7">
                        <select class="col-lg-7" aria-label="Default select example" name="categorie">
                            <%for (Categorie c : categories){%>
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
                                        <th >Categorie</th>
                                        <th >Equipe</th>

                                        <th >Point total</th>

                                    </tr>
                                    </thead>
                                    <tbody>
                                    <% for( ClassementGeneraleEquipeCategorie u : v){ %>


                                    <tr>
                                        <td> <%= u.getCategorie().getNom()%></td>
                                        <td> <%= u.getEquipe().getNom()%></td>
                                        <td><%= u.getTotalpoints()%></td>
                                    </tr>
                                    <%}%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div id="chart">
                        <canvas id="Camembert"></canvas>
                    </div>
                    <script>
                        var ctx = document.getElementById('Camembert').getContext('2d');
                        var myPieChart = new Chart(ctx, {
                            type: 'pie',
                            data: {
                                labels: <%=Data_equipe%>,
                                datasets: [{
                                    label: 'Graphe De repartition des point',
                                    data: <%=Data_pt_equipe%>,
                                    hoverOffset: 4
                                }]
                            },
                            options: {
                                responsive: true,
                                plugins: {
                                    legend: {
                                        position: 'top',
                                    },
                                    tooltip: {
                                        callbacks: {
                                            label: function(context) {
                                                let label = context.label || '';
                                                if (label) {
                                                    label += ': ';
                                                }
                                                if (context.parsed !== null) {
                                                    label += context.parsed;
                                                }
                                                return label;
                                            }
                                        }
                                    }
                                }
                            }
                        });
                    </script>
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
                        <a href="${pageContext.request.contextPath}/admin/classementCoureurCateg"><i class="fas fa-chevron-right "></i>Clas coureur categorie</a> <br>
                        <a href="${pageContext.request.contextPath}/lag/importView"><i class="fas fa-chevron-right "></i>Importation</a> <br>
                        <a href="${pageContext.request.contextPath}/lag/delete"><i class="fas fa-chevron-right "></i> Clean BD</a> <br>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>






