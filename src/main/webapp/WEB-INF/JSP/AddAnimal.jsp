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
             
             <form method="POST" action="/sec/Admin/AddEmployee" class="input_forms">
              <div class="form__group">
                <input type="text" name="username" id="username" placeholder="Username" class="form__input">
                <br>
                <input type="text" name="name" id="name" placeholder="Name" class="form__input">
                <br>
                <input type="text" name="email" id="email" placeholder="Email" class="form__input">
                <br>
                <input type="password" name="password" id="password" placeholder="Pasword" class="form__input">
                <br>
                <input
                type="file"
                accept="img/*"
                class="btn btn-info"
                name="img"
                />
                <br>
                <button class="btn-submit" type="submit">Add Animal</button>
                <% if(request.getAttribute("animalExists") != null){%>
                  <p> The animal has been added.</p>
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