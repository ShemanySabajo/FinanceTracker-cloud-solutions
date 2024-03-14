package sr.unasat.bp24.hibernate.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import sr.unasat.bp24.hibernate.configuration.JPAConfiguration;
import sr.unasat.bp24.hibernate.entity.Budget;

import java.time.LocalDate;
import java.time.Month;

public class BudgetRepo {

    private EntityManager entityManager;
    private EntityTransaction transaction = null;

    public BudgetRepo() {
        this.entityManager = JPAConfiguration.getEntityManager();
    }

    public Budget createMonthlyBudget(Budget budget) {

        try {
            //get a transaction
            transaction = entityManager.getTransaction();
            //begin transaction
            if (!transaction.isActive()){
                transaction.begin();
            }

            entityManager.persist(budget);

            //commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("rollback transaction");
            }
            /*e.printStackTrace();*/
        }
        return budget;
    }

    public Budget getMonthlyBudget(Long userId) {

        entityManager.getTransaction().begin();

        Month month = LocalDate.now().getMonth();

        String jpql = "select b from Budget b where b.user.id = ?1 AND MONTH(b.budgetMonth) = ?2 ORDER BY b.id DESC";

        TypedQuery<Budget> query = entityManager.createQuery(jpql, Budget.class);

        query.setParameter(1, userId);
        query.setParameter(2, month.getValue());
        query.setMaxResults(1);

        Budget budget = query.getSingleResult();

        entityManager.getTransaction().commit();

        return budget;
    }
}
