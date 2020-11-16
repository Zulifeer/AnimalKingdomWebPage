<%@ page import="java.util.List" %> 
<%@ page import="java.lang.String" %> 
<%@ page import="com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Type" %>
<%@ page import="com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Enviroment" %>
<%@ page import="com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Users" %>
<%@ page import="com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Employee" %>
<%@ page import="com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Animal" %>
<%@ page import="com.Capston2.joel_y_seba.AnimalKingdomWebPage.DAO.Entities.Roles" %>
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
          <main  class="admin-main">
            <%@ include file="Partials/navbar.jsp" %>
            <div class = "main-view">
              <a href="/sec/Admin/AddEmployee"><p>Add Employee</p></a>
              
              <a href="/sec/Admin/AddAnimal"><p>Add Animal</p></a>
              
              <a href="/sec/Admin/AddEnviroment"><p>Add Enviroment</p></a>
              
              <a href="/sec/Admin/AddType"><p>Add Categories</p></a>  
            </div>

            <div class = "main-view">
              <a href="/sec/admin/users"><p>See all Users</p></a>
              
              <a href="/sec/admin/animals"><p>See all Animals</p></a>
              
              <a href="/sec/admin/enviroments"><p>See all Enviroments</p></a>
              
              <a href="/sec/admin/types"><p>See all Categories</p></a>  
            </div>

            <div class="admin-tables">
              <%if(request.getAttribute("viewUsers") != null){%>
                  <div class="info-table">
                    <label for="info-table"><h1>All Users</h1></label>
                      <table class="table table-bordered table-hover table-sm dataTable" id="info-table">
                        <thead class="thead-dark th-sm">
                          <tr>
                            <th class="th-sm sorting" scope="col">ID</th>
                            <th class="th-sm sorting" scope="col">Username</th>
                            <th class="th-sm sorting" scope="col">Email</th>
                            <th class="th-sm sorting" scope="col">Role</th>
                            <th class="th-sm sorting" scope="col">Date Added</th>
                            <th class="th-sm sorting" scope="col">Enabed</th>
                          </tr>
                        </thead> 
                        <tbody>
                        <%List<Employee> employees = (List<Employee>) request.getAttribute("employees");
                          List<Users> users = (List<Users>) request.getAttribute("users");
                          List<String> role = (List<String>) request.getAttribute("roles");
                          List<String> dateAdded = (List<String>) request.getAttribute("dateAdded");
                          for(int i = 0; i<users.size(); i++) {%>
                          <tr>
                              <th scope="row" id="selected_id"> <a href="/sec/Admin/Modify/user/<%= users.get(i).getId() %>"> <%= users.get(i).getId() %></a> </th> 
                              <td>  <%= users.get(i).getUsername() %> </td>
                              <td>  <%= users.get(i).getEmail() %> </td>
                              <td>  <%= role.get(i) %> </td>
                              <td>  <%= dateAdded.get(i) %> </td>
                              <% if(role.get(i).equals("USER") || role.get(i).equals("ADMIN")){ %>
                                <% if(users.get(i).getEnabled() == 1){ %>
                                  <td>Yes</td>
                                <% } else { %>
                                  <td>No</td>
                                <% } %>
                              <% }else if(role.get(i).equals("EMPLOYEE")){
                                  for(int x =0; x<employees.size(); x++){
                                    if(users.get(i).getId() == employees.get(x).getUser_id().getId()){
                                       if(employees.get(x).getEnabled() == 1){ %>
                                        <td>Yes</td>
                                        <% } else { %>
                                        <td>No</td>
                                        <% } 
                                        break;
                                    }
                                  }%>
                              <% } %>
                            </tr>
                          <% } %>
                      </tbody>
                    </table>
                  </div >
                  <%if(request.getAttribute("user_selected") != null) { 
                      Employee selected_employee = (Employee) request.getAttribute("employee_selected");
                      Users selected_user = (Users) request.getAttribute("selected_user");
                      String user_role = (String) request.getAttribute("selected_user_role");
                      String date_added = (String) request.getAttribute("selected_user_date_added");%>
                      <div class="selected-info">
                        <form method="POST" action="/sec/Admin/Modify/User/<%=selected_user.getId()%>">
                          <div class="form__group">
                            <h1>Selected User</h1>
                            <p>If something isnt being changed leave it empty.</p>
                            <div class="selected-info-flex">
                              <div class="selected-info-flex-1">
                                <p>Name: <%= selected_user.getName()%> </p>
                                <p>Username: <%= selected_user.getUsername()%> </p>
                                <p>Email: <%= selected_user.getEmail()%> </p>
                                <label>New Email: <input type="text" name="email" id="email" class="form-control"></label>
                                <label>New Password: <input type="password" name="password" id="password"  class="form-control"></label>
                              </div>
                              
                              <div class="selected-info-flex-2">
                                <p>Date Added: <%= date_added%> </p>
                                <p>Role: <%= user_role%></p>
                                <select class="form-control" name = "selected_enabled">
                                <%if(user_role.equals("USER")){%>
                                  <%if(selected_user.getEnabled() == 1){%>
                                    <option value = 1>Enabled</option>
                                    <option value = 0>Disabled</option>
                                  <%} else {%>
                                    <option value = 0>Disabled</option>
                                    <option value = 1>Enabled</option>
                                  <%}%>
                                <%} else if(user_role.equals("EMPLOYEE")){%>
                                  <%if(selected_employee.getEnabled() == 1){%>
                                    <option value = 1>Enabled</option>
                                    <option value = 0>Disabled</option>
                                  <%} else {%>
                                    <option value = 0>Disabled</option>
                                    <option value = 1>Enabled</option>
                                  <%}%>
                                <%}%>
                                  
                                </select>
                              </div>
                              
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-secondary btn-lg">Update User info</button>
                          </div>
                        </form>
                      </div>
                  <% } %>
                  
              <% } %>
              <%if(request.getAttribute("viewAnimals") != null){ %>
                <div class="info-table">
                  <label for="info-table"><h1>All Animals</h1></label>
                    <table class="table table-bordered table-hover table-sm dataTable" id="info-table">
                      <thead class="thead-dark">
                        <tr>
                          <th class="th-sm sorting" scope="col">ID</th>
                          <th class="th-sm sorting" scope="col">Name</th>
                          <th class="th-sm sorting" scope="col">Enviroment</th>
                          <th class="th-sm sorting" scope="col">Category</th>
                        </tr>
                      </thead> 
                      <tbody>
                        <%List<Animal> animals = (List<Animal>) request.getAttribute("animals"); 
                          for(int i = 0; i<animals.size(); i++){%>
                            <tr>
                              <th scope="row"> <a href="/sec/Admin/Modify/animals/<%= animals.get(i).getAnimlalID() %>"> <%= animals.get(i).getAnimlalID() %> </a></th>
                              <td> <%= animals.get(i).getName() %> </td>
                              <td> <%= animals.get(i).getEnviromentId().getName() %> </td>
                              <td> <%= animals.get(i).getTypeID().getName() %> </td>
                            </tr>
                        <% } %>
                      </tbody>
                    </table>
                </div>
                <%if(request.getAttribute("animal_selected") != null) { 
                      Animal selected_animal = (Animal) request.getAttribute("selected_animal");%>
                      <div class="selected-info">
                        <form method="POST" action="/sec/Admin/Modify/Animal/<%=selected_animal.getAnimlalID()%>" enctype="multipart/form-data">
                          <div class="form__group">
                            <h1>Selected Animal</h1>
                            <p>If something isnt being changed leave it empty.</p>
                            <div class="selected-info-flex">
                              <div class="selected-info-flex-1">
                                <img class="animal-img " src="<%= selected_animal.getImagePath() %>" alt="Animal Picture">
                                <p>Name: <%= selected_animal.getName()%> </p>
                                <p>Enviroment: <%= selected_animal.getEnviromentId().getName()%> </p>
                                <p>Category: <%= selected_animal.getTypeID().getName()%> </p>
                              </div>
                              
                              <div class="selected-info-flex-2">
                                <label>Description: <p> <%= selected_animal.getDescription()%> </p></label>
                                <label>New Description:
                                  <textarea 
                                    class="form-control"
                                    id="description"
                                    rows="3"
                                    placeholder="description"
                                    name="description"
                                    maxlength="200">
                                  </textarea>
                                </label>
                                <label>New Image
                                  <input
                                  type="file"
                                  accept="img/*"
                                  class="btn btn-info"
                                  name="img"
                                  />
                                </label>
                              </div>
                              
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-secondary btn-lg">Update Animal info</button>
                          </div>
                        </form>
                      </div>
                  <% } %>
              <% } %>
              <%if(request.getAttribute("viewEnviroments") != null){%>
                <div class="info-table">
                  <label for="info-table"><h1>All Enviroments</h1></label>
                    <table class="table table-bordered table-hover table-sm dataTable" id="info-table">
                      <thead class="thead-dark">
                        <tr>
                          <th class="th-sm sorting" scope="col">ID</th>
                          <th class="th-sm sorting" scope="col">Name</th>
                          <th class="th-sm sorting" scope="col">Body of Water</th>
                        </tr>
                      </thead> 
                      <tbody>
                        <%List<Enviroment> envs = (List<Enviroment>) request.getAttribute("enviroments"); 
                          for(int i = 0; i<envs.size(); i++){%>
                            <tr>
                              <th scope="row"><a href="/sec/Admin/Modify/enviroments/<%= envs.get(i).getEnvirmonetID() %>"> <%= envs.get(i).getEnvirmonetID() %> </a></th>
                              <td> <%= envs.get(i).getName() %> </td>
                              <td> <%= envs.get(i).getType() %> </td>
                            </tr>
                          <% } %>
                      </tbody>
                    </table>
                </div>
                <%if(request.getAttribute("enviroments_selected") != null) { 
                      Enviroment selected_enviroment = (Enviroment) request.getAttribute("selected_enviroment");%>
                      <div class="selected-info">
                        <form method="POST" action="/sec/Admin/Modify/Enviroment/<%=selected_enviroment.getEnvirmonetID()%>">
                          <div class="form__group">
                            <h1>Selected Enviroment</h1>
                            <p>If something isnt being changed leave it empty.</p>
                            <div class="selected-info-flex">
                              <div class="selected-info-flex-1">
                                
                                <p>Name: <%= selected_enviroment.getName()%> </p>
                                <p>Body of Water: <%= selected_enviroment.getType()%> </p>
                              </div>
                              
                              <div class="selected-info-flex-2">
                                <label>Description: <p> <%= selected_enviroment.getDescription()%> </p></label>
                                <label>New Description:
                                  <textarea 
                                    class="form-control"
                                    id="description"
                                    rows="3"
                                    placeholder="description"
                                    name="description"
                                    maxlength="200">
                                  </textarea>
                                </label>
                              </div>
                              
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-secondary btn-lg">Update Enviroment info</button>
                          </div>
                        </form>
                      </div>
                  <% } %>
              <% } %>
              <%if(request.getAttribute("viewTypes") != null){%>
                <div class="info-table">
                  <label for="info-table"><h1>All Categories</h1></label>
                    <table class="table table-bordered table-hover table-sm dataTable" id="info-table">
                      <thead class="thead-dark">
                        <tr>
                          <th class="th-sm sorting" scope="col">ID</th>
                          <th class="th-sm sorting" scope="col">Name</th>
                        </tr>
                      </thead>
                      <tbody>
                      <%List<Type> types = (List<Type>) request.getAttribute("types"); 
                          for(int i = 0; i<types.size(); i++){%>
                            <tr>
                              <th scope="row"> <a href="/sec/Admin/Modify/types/<%= types.get(i).getTypeID() %>"><%= types.get(i).getTypeID() %></a>  </th>
                              <td> <%= types.get(i).getName() %> </td>
                            </tr>
                          <% } %>
                      </tbody> 
                    </table>
                </div>
                <%if(request.getAttribute("type_selected") != null) { 
                      Type selected_type = (Type) request.getAttribute("selected_type");%>
                      <div class="selected-info">
                        <form method="POST" action="/sec/Admin/Modify/Type/<%=selected_type.getTypeID()%>">
                          <div class="form__group">
                            <h1>Selected Category</h1>
                            <p>If something isnt being changed leave it empty.</p>
                            <div class="selected-info-flex">
                              <div class="selected-info-flex-1">
                                <p>Name: <%= selected_type.getName()%> </p>
                              </div>
                              
                              <div class="selected-info-flex-2">
                                <label>Description: <p> <%= selected_type.getDescription()%> </p></label>
                                <label>New Description:
                                  <textarea 
                                    class="form-control"
                                    id="description"
                                    rows="3"
                                    placeholder="description"
                                    name="description"
                                    maxlength="200">
                                  </textarea>
                                </label>
                              </div>
                              
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-secondary btn-lg">Update Category info</button>
                          </div>
                        </form>
                      </div>
                  <% } %>
              <% } %>
              
            </div>
            
            <div class="modify-forms">

            </div>

          </main>
        </div>
        <div id="overlay" class="overlay"></div>
      </div>
    </div>
    <script type="application/javascript" src="/JS/bundle.js"></script>
  </body>
  
</html>