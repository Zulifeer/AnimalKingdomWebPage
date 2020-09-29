<!DOCTYPE html>
<html lang="en">
  <link rel="stylesheet" href="/src/main/resources/static/CSS/style.css">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login</title>
  </head>
  <body>
    <div class="login-page">
      <div class="login">
        <form method="POST" action="/process_login" class="login_form">
          <div class="heading-form">
            <h2>Login</h2>
          </div>
          <div class="form__group">
            <input type="text" name="username" id="username" placeholder="Username" class="form__input">
          </div>
          <div class="form__group mb-5">
              <input type="password" name="password" id="password" placeholder="Password" class="form__input">
          </div>
          <div class="form__group">
              <button class="btn-submit" type="submit">Login</button>
          </div>
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
      </div>
    </div>
  </body>
  <script type="application/javascript" src="/src/main/resources/static/JS/bundle.js"></script>
</html>