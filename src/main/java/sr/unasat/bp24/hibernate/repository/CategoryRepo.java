package sr.unasat.bp24.hibernate.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import sr.unasat.bp24.hibernate.configuration.JPAConfiguration;
import sr.unasat.bp24.hibernate.entity.Category;

import java.util.List;

public class CategoryRepo {

    private EntityManager entityManager;
    private EntityTransaction transaction = null;

    public CategoryRepo() {
        this.entityManager = JPAConfiguration.getEntityManager();
    }

    public List<Category> getAllCategories() {

        //TODO use transaction instance variable

        entityManager.getTransaction().begin();

        String jpql = "select c from Category c";

        TypedQuery<Category> query = entityManager.createQuery(jpql, Category.class);

        List<Category> categoryList = query.getResultList();

        entityManager.getTransaction().commit();

        return categoryList;
    }

}
