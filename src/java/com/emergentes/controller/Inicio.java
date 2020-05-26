
package com.emergentes.controller;

import com.emergentes.dao.BlogDAO;
import com.emergentes.dao.BlogDAOimpl;
import com.emergentes.modelo.Blog;
import com.emergentes.utiles.Blog;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Inicio", urlPatterns = {"/inicio"})
public class Inicio extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try {
            BlogDAO dao = new BlogDAOimpl();
            int id;
            Blog bl = new Blog();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch (action) {
                case "add":
                    request.setAttribute("blog", bl);
                    request.getRequestDispatcher("frmblog.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    bl = dao.getById(id);
                    System.out.println(bl);
                    request.setAttribute("proveedor", bl);
                    request.getRequestDispatcher("frmblog.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath() + "/inicio");
                    break;
                case "view":
                    List<Blog> lista=dao.getAll();
                    request.setAttribute("blog", lista);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                default:
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Error  "+ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
       String nombre=request.getParameter("fecha");
      int antiguedad=Integer.parseInt(request.getParameter("titulo"));
      boolean estado=Boolean.parseBoolean(request.getParameter("contenido"));
       
       Blog bl = new Blog();
       bl.setId(id);
       bl.setFecha(fecha);
       bl.setTitulo(titulo);
       bl.setContenido(contenido);
       
       
       if(id==0){
           try {
               BlogDAO dao=new BlogDAOimpl();
               dao.insert(bl);
               response.sendRedirect(request.getContextPath()+"/inicio");
           } catch (Exception ex) {
               System.out.println("Error   "+ex.getMessage());
           }
           
       }else{
           try {
               BlogDAO dao=new BlogDAOimpl();
               dao.update(bl);
               response.sendRedirect(request.getContextPath()+"/inicio");
           } catch (Exception ex) {
               System.out.println("Error  "+ex.getMessage());
           }
       }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
