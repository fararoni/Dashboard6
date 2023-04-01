<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/auth/sesion.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%! String titulo = "Proyecto Dashboard TESE";%>
        <%@include file="/layout/head.jsp" %>
    </head>
    <body class="hold-transition sidebar-mini">
        <!-- Site wrapper -->
        <div class="wrapper">
            <%@include file="/layout/menu_superior.jsp" %>
            <%@include file="/layout/menu_izquierdo.jsp" %>
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <div class="container-fluid">
                        <div class="row mb-2">
                            <div class="col-sm-6">
                                <h1>Cliente</h1>
                            </div>
                            <div class="col-sm-6">
                                <ol class="breadcrumb float-sm-right">
                                    <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/index.jsp">Inicio</a></li>
                                    <li class="breadcrumb-item active"><a href="<%=request.getContextPath()%>/ClientesGrid">Clientes</a></li></li>
                                </ol>
                            </div>
                        </div>
                    </div><!-- /.container-fluid -->
                </section>

                <!-- Main content -->
                <section class="content">
                    <!-- Inicio de Tabla -->

                    <div class="card card-info">
                        <div class="card-header">
                            <h3 class="card-title">Agregar un nuevo cliente</h3>
                        </div>
                        <c:if test="${accion == null }">
                            <c:if test="${cliente == null}">
                                <form class="form-horizontal" action="<%=request.getContextPath()%>/ClientesNew" method="POST">
                            </c:if>
                            <c:if test="${cliente != null}">
                                <form class="form-horizontal" action="<%=request.getContextPath()%>/ClientesUpdate" method="POST">
                            </c:if>                                
                        </c:if>                                
                        <c:if test="${accion == 'borrar'}">
                            <form class="form-horizontal" action="<%=request.getContextPath()%>/ClientesDelete" method="POST">
                        </c:if>                      
                            <div class="card-body">
                                
                                <c:if test="${cliente != null}">
                                    <input type="hidden" name="id" value="<c:out value='${cliente.id}' />" />
                                </c:if>
                            
                                <div class="form-group row">
                                    <label for="nombre" class="col-sm-2 col-form-label">Nombre</label>
                                    <div class="col-sm-10">
                                        <input type="text" id="nombre" name="nombre" value="<c:out value='${cliente.nombre}' />" 
                                               class="form-control" placeholder="Nombre del cliente...">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="apellidos" class="col-sm-2 col-form-label">Apellidos</label>
                                    <div class="col-sm-10">
                                        <input type="text" id="apellidos" name="apellidos" value="<c:out value='${cliente.apellidos}' />" 
                                               class="form-control" placeholder="Apellidos del cliente...">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="telefono" class="col-sm-2 col-form-label">Teléfono</label>
                                    <div class="col-sm-10">
                                        <input type="number" id="telefono" name="telefono"  value="<c:out value='${cliente.telefono}' />" 
                                               class="form-control" placeholder="Teléfono...">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="domicilio" class="col-sm-2 col-form-label">Domicilio</label>
                                    <div class="col-sm-10">
                                        <input  type="text" id="domicilio" name="domicilio"  value="<c:out value='${cliente.domicilio}' />" 
                                                class="form-control" placeholder="Domicilio...">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="codigo" class="col-sm-2 col-form-label">Código Postal</label>
                                    <div class="col-sm-10">
                                        <input  type="text" id="codigo" name="codigo"  value="<c:out value='${cliente.codigo}' />" 
                                                class="form-control" placeholder="Código Postal...">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="edad" class="col-sm-2 col-form-label">Edad</label>
                                    <div class="col-sm-10">
                                        <input  type="text" id="edad" name="edad"  value="<c:out value='${cliente.edad}' />" 
                                                class="form-control" placeholder="Edad...">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="sexo" class="col-sm-2 col-form-label">Sexo</label>
                                    <div class="col-sm-10">
                                        <input  type="text" id="sexo" name="sexo"  value="<c:out value='${cliente.sexo}' />" 
                                                class="form-control" placeholder="Sexo...">
                                    </div>
                                </div>
                            </div>
                            <div class="card-footer">
                                <a href="<%=request.getContextPath()%>/ClientessGrid" class="btn btn-default" role="button">Cancelar</a>
                                <c:if test="${accion == null }">
                                    <c:if test="${cliente == null}">
                                        <button type="submit" class="btn btn-info  float-right">Agregar</button>
                                    </c:if>
                                    <c:if test="${cliente != null}">
                                        <button type="submit" class="btn btn-info  float-right"><i class="fas fa-pencil-alt"></i> Actualizar</button>
                                    </c:if>
                                </c:if>
                                <c:if test="${accion == 'borrar'}">
                                    <button type="submit" class="btn btn-danger   float-right"><i class="fas fa-trash"></i> Borrar</button>
                                </c:if>     
                            </div>

                        </form>
                    </div>

            </div>

            <!-- Fin de tabla -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <%@include file="/layout/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@include file="/layout/libraries.jsp" %>
</body>
</html>
