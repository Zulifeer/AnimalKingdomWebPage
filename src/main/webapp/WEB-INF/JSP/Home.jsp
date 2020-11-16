<%@ page import="java.util.List" %> 
<%@ page import="java.util.Random" %> 
<%@ page import="java.lang.String" %> 
<%@ page import="com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Type" %>
<%@ page import="com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Enviroment" %>
<%@ page import="com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Animal" %>

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
          <main class="content">
            <%@ include file="Partials/navbar.jsp" %>

            <video autoplay muted loop id="home-video">
                <source src="/Videos/home_video.mp4" type="video/mp4">
            </video>
            <div class="home-menu">
                <div id ="3d-model" class = "three-d-model-viewer-1"></div>
                <p>Here you will see the different animals and what enviroments they come from and most of them will be shown on our VR aplication.</p>
                <br>
                <div class="home-buttons">
                    <a  class="btn btn-light" href="/login">Log in</a>
                    <a  class="btn btn-light" href="/signup">Sign Up</a>
                    <a  class="btn btn-light" href="/Home_1/allanimals">Continue as Gest</a>
                </div>
            </div>
            
          </main>
        </div>
        <div id="overlay" class="overlay"></div>
      </div>
    </div>
    <%List<Animal> animals = (List<Animal>) request.getAttribute("allAnimals"); 
      Random rand = new Random();
      int x = rand.nextInt(animals.size());%>
    <script type="application/javascript" src="/JS/bundle.js"></script>
    <script>
      var model_path = "<%= animals.get(x).getModel_Path()%>";
    </script>
  </body>
  
</html>