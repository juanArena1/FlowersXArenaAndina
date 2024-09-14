/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Ciudad;
import entidades.EstadoUsuario;
import entidades.Pais;
import entidades.Rol;
import entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Aprendiz
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "FlowersXPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public Usuario buscarPorCorreo (String email) {
        Usuario usuarioCorreo = null;
        try {
            Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.email=?1");
            query.setParameter(1, email);
            List<Usuario> lista = query.getResultList();
            if (!lista.isEmpty()) {
                usuarioCorreo = lista.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return usuarioCorreo;
    }
    
    public Usuario login (Usuario usuario) {
        Usuario usuarioLogin = null;
        try {
            Query query = em.createNativeQuery("SELECT * FROM usuario WHERE email=?1 AND password=MD5(?2)");
            //Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.email=:email AND u.password=MD5(:password)");
            query.setParameter(1, usuario.getEmail());
            query.setParameter(2, usuario.getPassword());
            List<Object[]> lista = query.getResultList();
            List<Usuario> list = new ArrayList();
            for (Object[] ob : lista) {
                Usuario u = new Usuario();
                Rol rol = new Rol();
                Pais pais = new Pais();
                Ciudad ciudad = new Ciudad();
                EstadoUsuario estadoUsuario = new EstadoUsuario();
                u.setId(Integer.parseInt(ob[0].toString()));
                u.setNombreTitular(ob[1].toString());
                u.setApellidoTitular(ob[2].toString());
                u.setRazonSocial(ob[3].toString());
                u.setEmail(ob[4].toString());
                u.setPassword(ob[5].toString());
                rol.setIdRol(Integer.parseInt(ob[6].toString()));
                u.setRol(rol);
                pais.setIdpais(Integer.parseInt(ob[7].toString()));
                u.setPais(pais);
                ciudad.setIdciudad(Integer.parseInt(ob[8].toString()));
                u.setCiudad(ciudad);
                estadoUsuario.setIdestadoUsuario(Integer.parseInt(ob[9].toString()));
                list.add(u);
            }
            if (!list.isEmpty()) {
                usuarioLogin = list.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return usuarioLogin;
    }
    
}
