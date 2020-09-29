<!DOCTYPE html>
<html lang="en">
  <link rel="stylesheet" href="/CSS/style.css">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Animal Kingdom Home</title>
  </head>
  <body>
    <div class="home-page">
      <div class="home">
        <div class="heading-home">
          <center><h2>Welcome to Aquatic Kingdom</h2></center>
        </div>
        <div class="wrapper">
          <%@ include file="Partials/sidebar.jsp" %>
          <main>
            <%@ include file="Partials/navbar.jsp" %>
          </main>
        </div>
        <div id="overlay" class="overlay"></div>
        <!--
        <div class="form__group">
          <center><a href="/login"><button class="btn-submit" type="submit" >Login</button></a>
        </div>
        <div class="form__group">
          <center><a href="/signup"><button class="btn-submit" type="submit" >SignUp</button></a></center>
        </div>
        -->
      </div>
    </div>
    <script type="application/javascript" src="/JS/bundle.js"></script>
  </body>
  
</html>