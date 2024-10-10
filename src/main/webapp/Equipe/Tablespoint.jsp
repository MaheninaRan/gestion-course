<%@ page import="java.util.List" %>
<%@ page import="com.example.testeeval.model.ClassementParEtape" %>

<%
    List<ClassementParEtape> point = (List<ClassementParEtape>) request.getAttribute("point");
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

<main id="main" class="main">


    <!---->




    <section class="section">
        <div class="row">
            <div class="col-lg-12">



                <!-- Default Table -->
                <!-- End Table with hoverable rows -->




                <div class="card">
                    <div class="card-body">
                        <!-- Primary Color Bordered Table -->
                        <table class="table ">
                            <thead>
                            <tr>
                                <th >Etape</th>
                                <th >Coureur</th>
                                <th >Equipe</th>
                                <th >Heure depart</th>
                                <th >Heure arrivé</th>
                                <th >Temps total </th>
                                <th >Classement </th>
                                <th >Point </th>

                            </tr>
                            </thead>
                            <tbody>
                            <% for( ClassementParEtape u : point){ %>


                            <tr>
                                <td> <%= u.getCompositionetape().getEtape().getNom()%></td>
                                <td> <%= u.getCoureur().getNom()%></td>
                                <td> <%= u.getEquipe().getNom()%></td>
                                <td><%= u.getDebut()%></td>
                                <td><%= u.getFin()%></td>
                                <td><%= u.getTemps()%></td>
                                <td><%= u.getClassementParEtape()%></td>
                                <td><%= u.getPoint()%></td>

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

    <!-- Modal -->

</main><!-- End #main -->


<!-- ======= Footer ======= -->

<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

<!-- Vendor JS Files -->


<!-- Template Main JS File -->



