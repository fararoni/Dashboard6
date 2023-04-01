<!DOCTYPE html>
<html lang="en">
    <head>
        <%! String titulo = "Iniciar sesi�n";
        %>
        <%@include file="/layout/head.jsp" %>
    </head>
    <body class="hold-transition login-page">
        <div class="login-box">
            <div class="card card-outline card-primary">
                <div class="card-header text-center">
                    <a href="../../index2.html" class="h1"><b>Tese</b>Admin</a>
                </div>
                <div class="card-body">
                    <p class="login-box-msg">Olvid� mi contrase�a. Correo electr�nico de recuperaci�n.</p>
                    <form action="recover-password.html" method="post">
                        <div class="input-group mb-3">
                            <input type="email" class="form-control" placeholder="Email">
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="fas fa-envelope"></span>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <button type="submit" class="btn btn-primary btn-block">Solicitar nueva contrase�a</button>
                            </div>

                        </div>
                    </form>
                    <p class="mt-3 mb-1">
                        <a href="login.html">Ingresar</a>
                    </p>
                </div>

            </div>
        </div>
        <%@include file="/layout/libraries.jsp" %>
    </body>
</html>
