<%@ page import="java.util.List" %> 
<%@ page import="com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Type" %>
<%@ page import="java.util.List" %> 
<%@ page import="com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Enviroment" %>

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
          <center><h2>Employee info</h2></center>
        </div>
        <div class="wrapper">
          <%@ include file="Partials/sidebar.jsp" %>
          <main>
            <%@ include file="Partials/navbar.jsp" %>
             
             <form method="POST" action="/sec/Admin/AddAnimal" class="input_forms"  enctype="multipart/form-data">
              <div class="form__group">
                <input type="text" name="name" id="name" placeholder="Name" class="form__input">
                <br>
                
                <label for="description">Animal Description</label>
                <textarea 
                  class="form-control"
                  id="description"
                  rows="3"
                  placeholder="description"
                  name="description"
                  maxlength="200">
                </textarea>
                <br>
                
                <input
                type="file"
                accept="img/*"
                class="btn btn-info"
                name="img"
                />
                <br>
                
                <label>Select an Enviroment</label>
                <select class="selectpicker" name="enviroment_search">
                  <% List<Enviroment> env = (List<Enviroment>) request.getAttribute("env");
                    for(int i =0; i < env.size(); i++){
                    %>
                    <option value="<%= env.get(i).getEnvirmonetID() %>"><%= env.get(i).getName() %></option>
                    <% } %>
                </select>
                <br>
                
                <label>Select the Race of the Animal</label>
                <select class="selectpicker" name="type_search">
                  <% List<Type> type = (List<Type>) request.getAttribute("type");
                    for(int i =0; i < type.size(); i++){
                    %>
                    <option value="<%= type.get(i).getTypeID() %>"><%= type.get(i).getName() %></option>
                    <% } %>
                </select>
                <br>
                <button class="btn-submit" type="submit">Add Animal</button>
                
                <% if(request.getAttribute("animalAdded") != null){%>
                  <p> The animal has been added.</p>
                <%}%>

                <% if(request.getAttribute("animalExists") != null){%>
                  <p> The animal allready exists.</p>
                <%}%>
                
              </div>
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
             </form>

          </main>
        </div>
        <div id="overlay" class="overlay"></div>
      </div>
    </div>
    <script type="application/javascript" src="/JS/bundle.js"></script>
  </body>
  
</html>