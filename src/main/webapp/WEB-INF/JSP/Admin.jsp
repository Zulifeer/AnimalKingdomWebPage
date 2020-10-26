<!DOCTYPE html>
<html lang="en">
  <link rel="stylesheet" href="/CSS/style.css">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Animal Kingdom Administration</title>
  </head>
  <body>
    <div class="home-page">
      <div class="home">
        <div class="heading-home">
          <center><h2>Aquatic Kingdom Administration</h2></center>
        </div>
        <div class="wrapper">
          <%@ include file="Partials/sidebar.jsp" %>
          <main>
            <%@ include file="Partials/navbar.jsp" %>

            <a href="/sec/Admin/AddEmployee"><h1>Add Employee</h1></a>
            <a href="/sec/Admin/AddAnimal"><h1>Add Animal</h1></a>
            <a href="/sec/Admin/AddEnviroment"><h1>Add Enviroment</h1></a>
            <a href="/sec/Admin/AddType"><h1>Add Type</h1></a>
          </main>
        </div>
        <div id="overlay" class="overlay"></div>
      </div>
    </div>
    <script type="application/javascript" src="/JS/bundle.js"></script>
  </body>
  
</html>