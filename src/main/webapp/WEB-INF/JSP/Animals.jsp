<%@ page import="java.util.List" %> 
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
  <%Animal animal = (Animal) request.getAttribute("animal");%>
    <div class="home-page">
      <div class="home">
        <div class="heading-home">
          <center><h2><%= animal.getName()%> Model</h2></center>
        </div>
        <div class="wrapper">
          <%@ include file="Partials/sidebar.jsp" %>
          <main class="content">
            <%@ include file="Partials/navbar.jsp" %>
            <div class="user-view">
              <div id="3d-model" class="three-d-model-viewer-2">
                <p>Left click to move the model. Zoom in and out using the scroll wheel. Right click to drag.</p>
              </div>
            </div>
            
          </main>
        </div>
        <div id="overlay" class="overlay"></div>
      </div>
    </div>
    
    <script type="application/javascript" src="/JS/bundle.js"></script>

    <script type="application/javascript">
      let model_path = "<%= animal.getModel_Path() %>";
      console.log(model_path);
    </script>
    
  </body>
  
</html>