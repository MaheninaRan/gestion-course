<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="com.example.testeeval.model.Categorie" %>
<%@ page import="com.example.testeeval.model.ClassementGeneraleEquipeCategorie" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="headerAdmin.jsp" %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<%

    List<Categorie> categories = (List<Categorie>) request.getAttribute("categorie");
    List<ClassementGeneraleEquipeCategorie> v = (List<ClassementGeneraleEquipeCategorie>) request.getAttribute("vequipe");
%>

<%
    List<String> equipes = new ArrayList<>();
    List<String> point = new ArrayList<>();

    for (ClassementGeneraleEquipeCategorie cg : v){
        equipes.add(cg.getEquipe().getNom());
        point.add(String.valueOf(cg.getTotalpoints()));
    }
    String Data_equipe = "[" + Arrays.stream(equipes.toArray()).map(s -> "'" + s + "'").collect(Collectors.joining(", ")) + "]";
    String Data_pt_equipe = "[" + String.join(", ", point) + "]";
%>

<div class="container">
    <div class="content-wrapper">
        <div class="row">
            <div class="col-lg-12 grid-margin stretch-card">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title">Classment par catégorie</h4>
                        </p>
                        <div class="">


                            <form class="row g-3 needs-validation" data-parsley-validate=""  action="${pageContext.request.contextPath}/Classementcategorie/rahamisy"  method="post">
                                <label class="col-sm-2 col-form-label">Categorie</label>
                                <div class="col-sm-4">

                                    <select class="form-select" aria-label="Default select example" name="categorie">
                                        <%for (Categorie c : categories){%>
                                        <option value="<%= c.getId()%>"><%= c.getNom()%></option>
                                        <%}%>
                                    </select>


                                </div>
                                <div class="col-2">
                                    <button class="btn btn-primary w-100" type="submit">Entrer</button>
                                </div>
                            </form>
                        </div> <br><br><br><br>




                        <!-- Default Table -->
                        <!-- End Table with hoverable rows -->




                        <table class=" ">
                            <thead>
                            <tr><th >CategorieAAAAAAA</th>
                                <th >Equipe</th>
                                <th >Classement</th>
                                <th >Point total</th>

                            </tr>
                            </thead>
                            <tbody>
                            <% for (int i = 0; i < v.size(); i++) { %>
                            <% ClassementGeneraleEquipeCategorie u = v.get(i); %>
                            <tr id="row<%= i %>">
                                <td><%= u.getCategorie().getNom() %></td>
                                <td><%= u.getEquipe().getNom() %></td>
                                <td><%= u.getClassement() %></td>
                                <td><%= u.getTotalpoints() %></td>
                            </tr>
                            <%}%>
                            </tbody>
                        </table>


                        <style>
                            #chart {
                                max-width: 300px;
                                margin: 0 auto;
                            }
                        </style>
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

                        <script>
                            // Récupérer toutes les lignes de la table
                            var rows = document.getElementsByTagName('tr');

                            // Créer un objet pour stocker les lignes ayant le même nombre de points
                            var pointsMap = {};

                            // Parcourir chaque ligne de la table (en commençant par la deuxième ligne, car la première est l'en-tête)
                            for (var i = 1; i < rows.length; i++) {
                                var row = rows[i];
                                var cells = row.getElementsByTagName('td');

                                // Récupérer le nombre de points de cette ligne
                                var points = cells[3].innerText;

                                // Vérifier si ce nombre de points a déjà été rencontré
                                if (pointsMap[points] === undefined) {
                                    // Si ce nombre de points n'a pas été rencontré, l'ajouter à la map avec une nouvelle liste contenant la ligne actuelle
                                    pointsMap[points] = [row];
                                } else {
                                    // Si ce nombre de points a déjà été rencontré, ajouter cette ligne à la liste existante
                                    pointsMap[points].push(row);
                                }
                            }

                            // Parcourir chaque entrée de la map
                            for (var points in pointsMap) {
                                // Si cette entrée a plus d'une ligne, cela signifie qu'il y a des lignes avec le même nombre de points
                                if (pointsMap[points].length > 1) {
                                    // Appliquer une couleur de fond à toutes les lignes ayant le même nombre de points
                                    for (var j = 0; j < pointsMap[points].length; j++) {
                                        pointsMap[points][j].style.backgroundColor = 'pink';
                                    }
                                }
                            }
                        </script>



                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

<style>
    table {
        width: 100%;
        border-collapse: collapse;
    }

    th, td {
        border: 1px solid black;
        padding: 8px;
    }

    th {
        background-color: #f2f2f2;
    }
</style>
