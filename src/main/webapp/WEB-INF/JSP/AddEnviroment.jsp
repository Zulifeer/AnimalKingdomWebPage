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
          <center><h2>Enviroment info</h2></center>
        </div>
        <div class="wrapper">
          <%@ include file="Partials/sidebar.jsp" %>
          <main>
            <%@ include file="Partials/navbar.jsp" %>
             <div class="main-view">
              <form method="POST" action="/sec/Admin/AddEnviroment" class="input_forms">
                <div class="form__group">
                  <input type="text" name="name" id="name" placeholder="Name" class="form__input">
                  <br>
                  <input type="text" name="type" id="type" placeholder="Water body" class="form__input">
                  <br>
                  <label for="description">Enviroment Description</label>
                  <textarea 
                    class="form-control"
                    id="description"
                    rows="3"
                    placeholder="description"
                    name="description"
                    maxlength="200">
                  </textarea>
                  <br>
                  <button class="btn-submit" type="submit">Enter new enviroment</button>
                  <% if(request.getAttribute("enviromentAdded") != null){%>
                    <p> The enviroment has been added has been added.</p>
                  <%}%>
                  <% if(request.getAttribute("enviromentExists") != null){%>
                    <p> This enviroment exists.</p>
                  <%}%>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
              </form>
             </div>
             

          </main>
        </div>
        <div id="overlay" class="overlay"></div>
      </div>
    </div>
    <script type="application/javascript" src="/JS/bundle.js"></script>
  </body>
  
</html>