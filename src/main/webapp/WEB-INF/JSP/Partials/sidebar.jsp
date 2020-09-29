<nav id="sidebar">
    <div class="sidebar-header">
      <p class="sidebar-header--text">Hello there...</p>
    </div>
    <hr />
    <ul class="list-unstyled">

        
      <%if(request.getAttribute("isAdmin") !=null){ if((boolean)
      request.getAttribute("isAdmin")){ %>
      <li class="sidebar-item">
        <a class="btn" href="/sec/admin">
          <i class="fas fa-users-cog"></i>
          Administrate App
        </a>
      </li>
      <% } } %>
      <li class="sidebar-item">
        <a class="btn" href="/">
          <i class="fas fa-home"></i>
          Home
        </a>
      </li>
    </ul>
  </nav>