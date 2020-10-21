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
             
             <form method="POST" action="/sec/Admin/AddType" class="input_forms">
              <div class="form__group">
                <input type="text" name="name" id="name" placeholder="Category Name" class="form__input">
                <br>
                <textarea 
                  class="form-control"
                  id="description"
                  rows="3"
                  placeholder="Description"
                  name="Description"
                  maxlength="200">
                </textarea>
                <br>
                <button class="btn-submit" type="submit">Add Animal Category</button>
                <% if(request.getAttribute("categoryExists") != null){%>
                  <p> The category has been added.</p>
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