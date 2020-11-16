<%@ page import="java.util.List" %> 
<%@ page import="java.lang.String" %> 
<%@ page import="java.util.ArrayList" %> 
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
            
            
            <div class="user-view">
              <% 
              List<Enviroment> envs = (List<Enviroment>) request.getAttribute("allEnviroments");
              List<Animal> animals = new ArrayList<>();
              if(request.getAttribute("animal_selected") != null){
                animals = (List<Animal>) request.getAttribute("selected_animals");
              }else{
                animals = (List<Animal>) request.getAttribute("allAnimals");
              }%>
              <div class="box-1 rounded">
                <div class="box-2-info">
                  <%if(request.getAttribute("animal_selected") != null){%>
                    <h1><%= animals.get(0).getTypeID().getName()%></h1>
                  <%}else{%>
                    <h1>All Animals</h1>
                  <%}%>
                  
                  <p>Select what category of animals you want to view.</p>
                  <div class="animal-categories">
                    <a href="/Home_1/allanimals"> All animals </a>
                    <%List<Type> types = (List<Type>) request.getAttribute("allTypes");
                    for(int i=0; i<types.size(); i++){%>
                      <a href="/Home_1/<%= types.get(i).getName()%>"> <%= types.get(i).getName()%> </a>
                    <%}%>
                  </div>
                </div>
              </div>
              
              <% 
              for(int i = 0; i<animals.size(); i++){%>
                <div class="box-1 rounded">
                  <div class = "box-1-info">
                    <h1><a href="/animals/<%= animals.get(i).getName()%>"><%= animals.get(i).getName()%></a></h1>
                    <p>Found in <%= animals.get(i).getEnviromentId().getName()%></p>
                    <p><%= animals.get(i).getDescription()%></p>
                  </div>
                  <div class="animal-img-viewer">
                    <img class="animal-img " src="<%= animals.get(i).getImagePath() %>" alt="Animal Picture">
                  </div>
                </div>
              <% } %>
            </div>

            <br>
          </main>
        </div>
        <div id="overlay" class="overlay"></div>
      </div>
    </div>
    <script type="application/javascript" src="/JS/bundle.js"></script>
    <script>
      var animal_size = <%= animals.size() %>
    </script>
  </body>
  
</html>