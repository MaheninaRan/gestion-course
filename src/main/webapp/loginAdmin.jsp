<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="keywords" content="Site keywords here">
    <meta name="description" content="">
    <meta name='copyright' content=''>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Course-LoginAdmin</title>
    <link rel="icon" href="/img/favicon.png">
    <link href="https://fonts.googleapis.com/css?family=Poppins:200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/nice-select.css">
    <link rel="stylesheet" href="/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/icofont.css">
    <link rel="stylesheet" href="/css/slicknav.min.css">
    <link rel="stylesheet" href="/css/owl-carousel.css">
    <link rel="stylesheet" href="/css/datepicker.css">
    <link rel="stylesheet" href="/css/animate.min.css">
    <link rel="stylesheet" href="/css/magnific-popup.css">
    <link rel="stylesheet" href="/css/normalize.css">
    <link rel="stylesheet" href="/style.css">
    <link rel="stylesheet" href="/css/responsive.css">

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
                    <h1>LOGIN ADMIN</h1>
                </div>
            </div>
        </div>
        <div class="row">

            <div class="col-lg-6 col-md-12 col-12"> <br><br><br><br><br>
                <form class="form" action="${pageContext.request.contextPath}/lag/existe" method="post">
                    <h1 style="color: orangered">${error}</h1>
                        <% if (erreur != null  ) { %>
                            <script> alert(${error}); </script>
                        <%}%>
                    <div class="row">
                        <div class="col-lg-offset-2 col-lg-10 col-md-6 col-12">
                            <div class="col-lg-offset-2 col-lg-10 col-md-6 col-12">
                                <div class="form-group">
                                    <input name="email" type="email" name="email" value="john@example.com" placeholder="Email" required>
                                </div>
                            </div>
                            <div class="col-lg-10 col-md-6 col-12">
                                <div class="form-group">
                                    <input name="password" type="password" name="password" value="password123" placeholder="Password" required>
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
            <div class="col-lg-6 col-md-12 ">
                <div class="appointment-image">
                    <img style="margin-top: -75px;" src="/img/contact-img.png" alt="#">
                </div>
            </div>
        </div>
    </div>
</section>

<script src="js/jquery.min.js"></script>
<script src="js/jquery-migrate-3.0.0.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/easing.js"></script>
<script src="js/colors.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap-datepicker.js"></script>
<script src="js/jquery.nav.js"></script>
<script src="js/slicknav.min.js"></script>
<script src="js/jquery.scrollUp.min.js"></script>
<script src="js/niceselect.js"></script>
<script src="js/tilt.jquery.min.js"></script>
<script src="js/owl-carousel.js"></script>
<script src="js/jquery.counterup.min.js"></script>
<script src="js/steller.js"></script>
<script src="js/wow.min.js"></script>
<script src="js/jquery.magnific-popup.min.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/waypoints/2.0.3/waypoints.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/main.js"></script>
</body>
</html>