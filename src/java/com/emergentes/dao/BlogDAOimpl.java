
package com.emergentes.dao;

import com.emergentes.modelo.Blog;
import com.emergentes.utiles.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BlogDAOimpl extends ConexionBD implements BlogDAO {

    @Override
    public void insert(Blog blog) throws Exception {
         try {
        this.conectar();
        PreparedStatement ps = this.conn.prepareStatement("INSERT into blogs (fecha,titulo,contenido) values (?, ?,?)");
        ps.setString(1, blog.getFecha());
        ps.setString(2, blog.getTitulo());
        ps.setString(3, blog.getContenido());
        ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public void update(Blog blog) throws Exception {
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE blogs set fecha = ?, titulo = ?, contenidoo=? WHERE id = ?");
            ps.setString(1, blog.getFecha());
            ps.setString(2,blog.getTitulo());
            ps.setString(3, blog.getContenido());
            ps.setInt(4, blog.getId());
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM blogs where id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public Blog getById(int id) throws Exception {
        Blog pro=new Blog();
        
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM proveedores where id = ? limit 1");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                pro.setId(id);
                pro.setFecha(rs.getString("fecha"));
                pro.setTitulo(rs.getString("titulo"));
                pro.setContenido(rs.getString("contenido"));
            }
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
        return pro;
    }

    @Override
    public List<Blog> getAll() throws Exception {
     List<Blog> lista = null;
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM blogs");
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Blog>();
            while(rs.next()){
                Blog pro= new Blog();
                pro.setId(rs.getInt("id"));
                pro.setNombre(rs.getString("nombre"));
                pro.setAntiguedad(rs.getInt("antiguedad"));
                pro.setEstado(rs.getBoolean("estado"));
                lista.add(pro);
            }
            rs.close();
            ps.close();
        }catch(Exception e){
            throw e;
        } finally{
            this.desconectar();
        }
        return lista;
    }
    
}
