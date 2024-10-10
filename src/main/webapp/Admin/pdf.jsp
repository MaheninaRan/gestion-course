
<%@ page import="com.example.testeeval.model.ClassementGeneraleEquipe" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Certificate</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f0f0f0;
        }
        .certificate {
            border-radius: 30% 0% 30% 0%;
            background-color: #101631;
            width: 700px;
            padding: 40px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            position: relative;
        }
        .certificate .header {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-bottom: 20px;
        }
        .certificate .header img {
            width: 100px;
            height: auto;
        }
        .certificate .title {
            font-size: 50px;
            font-weight: bold;
            margin: 20px 0;
            text-align: center;
        }
        .certificate .name {
            font-size: 22px;
            font-weight: bold;
            color: #e3b131;
            text-align: center;
        }
        .certificate .body-text {
            margin: 20px 0;
            text-align: center;
            color: #777;
        }
        .certificate .date {
            text-align: center;
            margin: 20px 0;
            color: #aaa;
        }
        .certificate .signatures {
            display: flex;
            justify-content: space-between;
            margin-top: 40px;
        }
        .certificate .signatures .signature {
            text-align: center;
        }
        .certificate .signatures .signature .name {
            font-size: 16px;
            font-weight: bold;
        }
        .certificate .signatures .signature .title {
            font-size: 14px;
            color: #aaa;
        }
        #field{
            border:1px solid white;
            width: 300px;
            margin-left: 200px;
        }
    </style>
</head>
<body>
<%
    List<ClassementGeneraleEquipe> classementGeneraleEquipeList = (List<ClassementGeneraleEquipe>) request.getAttribute("meilleur");
%>
<div class="certificate" id="certificate">
    <div class="title"> <b style="color:white;font-size:50px">RUNNING</b> <br> <b style="color:yellow">CHAMPION</b> </div>
    <div class="name" id="field">Field Certificate</div>
    <div class="body-text">
        This certificate is awarded to <b style="font-size:20px" ><%= classementGeneraleEquipeList.get(0).getEquipe().getNom() %></b> for outstanding performance and determination in completing the multi-stage running event. Your endurance and dedication have been exemplary, setting a high standard for future participants. Congratulations on your remarkable achievement!
    </div>
    <% for (int i = 0;i<1;i++){ %>
    <div class="signatures">
        <div class="signature">
            <div class="name">Equipe</div>
            <div class="title"><%= classementGeneraleEquipeList.get(0).getEquipe().getNom() %></div>
        </div>
        <div class="signature">
            <div class="name">Point Gagner</div>
            <div class="title"><%= classementGeneraleEquipeList.get(0).getPointequipe() %></div>
        </div>
    </div>
    <% }  %>
</div>

<div><button onclick="addPdf('certificate')">Export to PDF</button><div>

<script>
    function addPdf(id) {
        var element = document.getElementById(id);
        html2pdf().from(element).save();
    }
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.3/html2pdf.bundle.min.js"></script>
</body>
</html>
