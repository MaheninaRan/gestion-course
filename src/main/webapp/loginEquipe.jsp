<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Gestion course">
    <meta name="robots" content="index, follow">

    <title>Course-LoginEquipe</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/normalize.css">
    <link rel="stylesheet" href="/style.css">
    <link rel="stylesheet" href="/css/responsive.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-migrate-3.0.0.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
</head>
<body>
<%
    String numeroErreur=(String) request.getAttribute("messageErreur");
    String erreur=(String) request.getAttribute("error");
    if (numeroErreur!=null){%>
        <script>alert(<%= numeroErreur %>);</script>
    <% }
%>
<section style="margin-top: -80px;" class="appointment">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="section-title">
                    <h1>LOGIN EQUIPE</h1>
                    <h2>Course a pied</h2>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-6 col-md-12 order-lg-2 order-1">
                <div class="appointment-image">
                    <img style="margin-top: -60px;" src="/img/contact-img.png" class="img-fluid" alt="#">
                </div>
            </div>

            <div class="col-lg-6 col-md-12 col-12 order-lg-1 order-2">
                <br><br><br><br><br>
                <form class="form" id="form">
                    <h1 style="color: orangered">${error}</h1>
                    <% if (erreur != null){ %>
                        <script> alert(${error}); </script>
                    <%}%>
                    <div class="row">
                        <div class="col-lg-offset-2 col-lg-10 col-md-6 col-12">
                            <div class="col-lg-offset-2 col-lg-10 col-md-6 col-12">
                                <div class="form-group">
                                    <input name="email" type="text" id="email" placeholder="Email" required>
                                </div>
                            </div>
                            <div class="col-lg-10 col-md-6 col-12">
                                <div class="form-group">
                                    <input name="password" type="password" id="password" placeholder="Password" required>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-5 col-md-4 col-12">
                            <div class="form-group">
                                <div class="button">
                                    <button type="submit" class="btn">Connecter</button>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-7 col-md-8 col-12">
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>
</section>

<script>
    $(document).ready(() => {
        $('#form').submit((event) => {
            event.preventDefault();
            let formData = {
                email: $('#email').val(),
                password: $('#password').val()
            };
            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath}/equipe/existe',
                data: formData,
                success: function(response) {
                    if (response.etat ==="succes"){
                        sessionStorage.setItem("nomequipe",JSON.stringify(response.data));
                        window.location.href="${pageContext.request.contextPath}/equipe/equipe";
                    }else{
                        alert(response.data);
                    }
                },
                error: function(xhr, status, error) {
                    console.error('Erreur de login');
                    console.error(xhr.responseText);
                }
            });
        });
    });
</script>


</body>
</html>