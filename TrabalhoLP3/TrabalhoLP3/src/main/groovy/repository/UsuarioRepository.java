package repository;

import model.UsuarioModel;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
    private static UsuarioRepository instance;
    protected EntityManager entityManager;

    public UsuarioRepository() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernatePU");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }
        return entityManager;
    }

    public static UsuarioRepository getInstance() {
        if (instance == null) {
            instance = new UsuarioRepository();
        }
        return instance;
    }

    public String salvar(UsuarioModel usuario) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(usuario);
            entityManager.getTransaction().commit();
            return "Usuário salvo com sucesso!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String editar(UsuarioModel usuario) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(usuario);
            entityManager.getTransaction().commit();
            return "Usuário editado com sucesso!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public List<UsuarioModel> buscarTodos() {
        try {
            return entityManager.createQuery("from UsuarioModel").getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public UsuarioModel buscarPorId(Long id) {
        try {
            return entityManager.find(UsuarioModel.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    public String remover(UsuarioModel usuario) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(usuario);
            entityManager.getTransaction().commit();
            return "Usuário removido com sucesso!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
