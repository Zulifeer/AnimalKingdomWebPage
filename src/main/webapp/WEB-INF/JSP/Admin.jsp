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
          <main class="admin-main">
            <%@ include file="Partials/navbar.jsp" %>
            <div class = "main-view">
              <a href="/sec/Admin/AddEmployee"><p>Add Employee</p></a>
              
              <a href="/sec/Admin/AddAnimal"><p>Add Animal</p></a>
              
              <a href="/sec/Admin/AddEnviroment"><p>Add Enviroment</p></a>
              
              <a href="/sec/Admin/AddType"><p>Add Type</p></a>  
            </div>
            
            <div class="admin-tables">

              <div class="user-table">

              <label for="user-table"><h1>All Users</h1></label>
                <table class="table table-bordered table-hover" id="user-table">
                  <thead>
                    <tr>
                      <th scope="col">ID</th>
                      <th scope="col">Username</th>
                      <th scope="col">Email</th>
                      <th scope="col">Enabed</th>
                    </tr>
                  </thead> 
                </table>
              </div>
              
              <div class="animal-table">
              <label for="animal-table"><h1>All Animals</h1></label>
                <table class="table table-bordered table-hover table-dark" id="animal-table">
                  <thead>
                    <tr>
                      <th scope="col">ID</th>
                      <th scope="col">Name</th>
                      <th scope="col">Enabled</th>
                    </tr>
                  </thead> 
                </table>
              </div>
            </div>
            
          </main>
        </div>
        <div id="overlay" class="overlay"></div>
      </div>
    </div>
    <script type="application/javascript" src="/JS/bundle.js"></script>
  </body>
  
</html>