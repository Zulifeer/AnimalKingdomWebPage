

<nav
  class="fixed-top navbar navbar-expand-lg shadow-lg navbar-dark color-primary-light"
>

  <button type="button" id="sidebarCollapse" class="btn btn-info">
    <i class="fas fa-bars BarsIcon"></i>
  </button>


  <ul class="navbar-nav ml-auto">
    <div class="navbar-user"></div>
    <li class="nav-item dropdown">
      
      <i
        class="far fa-user UserIcon"
        id="navbarDropdownMenuLink"
        data-toggle="dropdown"
        aria-haspopup="true"
        aria-expanded="false"
      ></i>
      
      <div
        class="dropdown-menu dropdown-menu-right"
        aria-labelledby="navbarDropdown"
      >
        <% if(request.getAttribute("user") != null){ %>
        <a class="dropdown-item" href="#">My Profile</a>
        <div class="dropdown-divider"></div>
        <form id="logout-form" action="/logout" method="post">
          <button type="submit" class="dropdown-item">LogOut</button>
          <input
            type="hidden"
            name="${_csrf.parameterName}"
            value="${_csrf.token}"
          />
        </form>
        
        <% }else {%>
        <a class="dropdown-item" href="/login">SignIn</a>
        <a class="dropdown-item" href="/SignUp">SignUp</a>
        <% } %>
      </div>
    </li>
  </ul>
</nav>