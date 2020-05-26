<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <h1>
            <c:if test="${blog.id==0}">Nuevo</c:if> 
            <c:if test="${blog.id!=0}" >Editar</c:if>
               Blog
       </h1>
            <form action="inicio" method="post">
                <input type="hidden" name="id" value="${proveedor.id}" />
            <table>
                <tr><td>Fecha:</td>
                    <td><input type="text" name="fecha" value="${proveedor.fecha}"/></td>
                </tr>
                <tr><td>Titulo:</td>
                    <td><input type="text" name="titulo" value="${proveedor.titulo}"/></td>
                </tr>
                <tr><td>Contenido:</td>
                    <td><input type="text" name="contenido" value="${proveedor.contenido}"/></td>
                </tr>
                
                <tr>
                    <td></td>
                    <td> <input type="submit"></td>
                </tr>

            </table>

        </form>
    </body>
</html>
