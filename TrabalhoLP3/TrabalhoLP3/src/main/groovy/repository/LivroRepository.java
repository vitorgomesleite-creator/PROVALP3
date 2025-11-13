package repository;

import model.LivroModel;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class LivroRepository {
    private static LivroRepository instance;
    protected EntityManager entityManager;

    public LivroRepository() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernatePU");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }
        return entityManager;
    }

    public static LivroRepository getInstance() {
        if (instance == null) {
            instance = new LivroRepository();
        }
        return instance;
    }

    public String salvar(LivroModel livro) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(livro);
            entityManager.getTransaction().commit();
            return "Livro salvo com sucesso!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String editar(LivroModel livro) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(livro);
            entityManager.getTransaction().commit();
            return "Livro editado com sucesso!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public List<LivroModel> buscarTodos() {
        try {
            return entityManager.createQuery("from LivroModel").getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public LivroModel buscarPorId(Long id) {
        try {
            return entityManager.find(LivroModel.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    public String remover(LivroModel livro) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(livro);
            entityManager.getTransaction().commit();
            return "Livro removido com sucesso!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
