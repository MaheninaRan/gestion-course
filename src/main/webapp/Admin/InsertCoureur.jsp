<%@ page import="java.util.List" %>
<%@ page import="com.example.testeeval.model.Coureur" %>
<%@ page import="com.example.testeeval.model.Etape" %>



<%
    List<Coureur> listcoureur = (List<Coureur>) request.getAttribute("coureur");
    Etape etape = (Etape) request.getAttribute("etape");
%>

<main id="main" class="main">


    <section class="section">
        <div class="row">
            <div class="col-lg-6">

                <div class="card">
                    <div class="card-body">

                        <h5 class="card-title"><%= etape.getNom()%></h5>

                        <form action="${pageContext.request.contextPath}/lag/insert"  method="post">
                            <input type="hidden" value="<%= etape.getId()%>" name="etape">
                            <div class="row mb-3">
                                <label class="col-sm-2 col-form-label">Coureur</label>

                                <div class="col-sm-10">
                                    <select class="form-select" aria-label="Default select example" name="coureur">
                                        <%for(Coureur c :listcoureur){%>
                                        <option value="<%= c.getId()%>"><%= c.getNom()%></option>
                                        <%}%>
                                    </select>
                                </div>

                            </div>



                            <div class="row mb-3">
                                <label for="inputDate" class="col-sm-2 col-form-label">Date</label>
                                <div class="col-sm-10">
                                    <input type="datetime-local" class="form-control" id="inputDate" name="dates">
                                </div>
                            </div>

                            <div class="card" style="text-align: center;width: 300px; margin: auto">
                                <button type="submit" class="btn btn-warning rounded-pill" >valider</button>
                            </div>

                        </form><!-- End General Form Elements -->

                    </div>
                </div>

            </div>
        </div>
    </section>
</main>

