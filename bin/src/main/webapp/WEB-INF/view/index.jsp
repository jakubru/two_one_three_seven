<!DOCTYPE html>
<html>
<head><title>2137 GAME</title></head>
<body>
<h1>2137 GAME</h1>
<div>
    <form method="post" action="/signin">
        <p>Login: <input type="text" value="login" id="log1"></p>
        <p>Password: <input type="text" value="password" id="pass1"></p>
        <input type="submit" value="Sign in" id="sub1">
    </form>
</div>
<div>
    <form method="post" action="/signup">
        <p>Login: <input type="text" value="login" id="log2"></p>
        <p>Password: <input type="text" value="password" id="pass2"></p>
        <p>Repeat password: <input type="text" value="rpassword" id="chpass2"></p>
        <input type="submit" value="Sign up" id="sub2">
    </form>
</div>
<div>
    <form method="post" action="/delacc">
        <p>Login: <input type="text" value="login" id="log3"></p>
        <p>Password: <input type="text" value="password" id="pass3"></p>
        <input type="submit" value="Delete account" id="sub3">
    </form>
</div>
</body>
</html>