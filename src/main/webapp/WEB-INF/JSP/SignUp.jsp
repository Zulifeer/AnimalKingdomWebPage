<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SignUp</title>

    <link rel="stylesheet" href="/src/main/resources/static/CSS/style.css">
</head>
<body>
    <div class="login-page">
        <div class="login">
            <form method="POST" name="SignUp" action="/signup" class="login__form">
                <div class="mb-5">
                    <h2 class="heading-form">
                        SignUp
                    </h2>
                </div>
                <div class="form-group mb-5" id="SignUpName">
                    <input type="text" name="name" id="name" placeholder="Name" class="form-control">
                    <label id="nameerror"></label>
                </div>
                <div class="form-group" id="SignUpusername">
                    <input type="text" name="username" id="username" placeholder="Username" class="form-control">
                    <label id="usernameerror"></label>
                </div>
                <div class="form-group" id="SignUpemail">
                    <input type="text" name="email" id="email" placeholder="email" class="form-control">
                    <label id="emailerror"></label>
                </div>
                <div class="form-group mb-5" id="SignUppassword">
                    <input type="password" name="password" id="password" placeholder="Password" class="form-control">
                    <label id="passworderror"></label>
                </div>
                <div class="form-group">
                    <button class="btn-submit" type="submit" id="SignUpButton">SignUp</button>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
    </div>
</body>
<script type="application/javascript" src="/src/main/resources/static/JS/bundle.js"></script>
</html>