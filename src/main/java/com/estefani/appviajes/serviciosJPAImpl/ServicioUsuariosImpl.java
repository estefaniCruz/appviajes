package com.estefani.appviajes.serviciosJPAImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estefani.appviajes.model.Usuario;
import com.estefani.appviajes.servicios.ServicioUsuarios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
//Si algo falla, hace ROLLBACK automáticamente
@Transactional
public class ServicioUsuariosImpl implements ServicioUsuarios {

	
	@PersistenceContext
	private EntityManager entityManager;
	//entityManager es parte de JPA

	@Override
	public void registrarUsuario(Usuario usuario) {

		entityManager.persist(usuario);

	}

	@Override
	public List<Usuario> obtenerUsuarios() {
		// ahora en jpa no hay criteria
		// para obtener datos y hacer consulats tenemos estas opciones
		// 1. SQL nativo
		// 2. JPQL--> pseudo SQL, muy sencillo, dev objetos
		// no hay que estar conectando a la bbdd, JPA ya sabe el mapeo
		List<Usuario> usuarios = entityManager.createQuery("select u from Usuario u").getResultList();

		return usuarios;
	}


	@Override
	public void borrarUsuario(int id) {
		// antes de borrar el producto debemos borrar las referencias
		// al mismo en el carrito y pedidos
		// hacer esto mismo en usuario
		
		entityManager.createNativeQuery("delete from CARRITO where USUARIO_ID = :id ").setParameter("id", id)
		.executeUpdate();
		
		entityManager.createNativeQuery(
			    "DELETE FROM producto_pedido " +
			    "WHERE pedido_id IN (SELECT id FROM pedido WHERE usuario_id = :id)"
			)
			.setParameter("id", id)
			.executeUpdate();


		entityManager.createNativeQuery("delete from pedido where USUARIO_ID = :id ").setParameter("id", id)
		.executeUpdate();
		
		entityManager.createNativeQuery("delete from tabla_usuarios where id = :id ").setParameter("id", id)
				.executeUpdate();

	}

	@Override
	public Usuario obtenerUsuarioPorId(int id) {

		return entityManager.find(Usuario.class, id);
		
	}

	@Override
	public void actualizarUsuario(Usuario usuarioEditar) {
		// ese id si no existiera la crearia eso hace el merge
		entityManager.merge(usuarioEditar);

	}

	@Override
	public Usuario obtenerUsuarioPorEmailYpass(String email, String pass) {
		try {
			Usuario usuario = 
					(Usuario)
					entityManager.createQuery(
						"select u from Usuario u where u.email = :email and u.pass = :pass ").
						setParameter("email", email).
						setParameter("pass", pass).getSingleResult();
			return usuario;
		} catch (Exception e) {
			System.out.println("no se encontro un usuario con el email y el pass indicado");
		}
		return null;
	}

	//una vez definido en la interfaz se hace aquí 
	@Override
	public Usuario obtenerUsuarioPorEmail(String email) {
		 try {
			 Usuario usuario = 
						(Usuario)
						entityManager.createQuery(
							"select u from Usuario u where u.email = :email ").
							setParameter("email", email).getSingleResult();
							
				return usuario;
		} catch (Exception e) {
			System.out.println("no hay un usuario con el email indicado");
		}
			return null; 
		}

	@Override
	public boolean existeEmail(String email) {
		// Consulta JPQL: Cuenta cuántos usuarios tienen ese email
	    String hql = "select count(u) from Usuario u where u.email = :email";
	    
	    Long cantidad = (Long) entityManager.createQuery(hql)
	                                        .setParameter("email", email)
	                                        .getSingleResult();
	    
	    // Si la cantidad es mayor que 0, es que ya existe (return true)
	    return cantidad > 0;
	}

}
