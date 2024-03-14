package sr.unasat.bp24.hibernate.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import sr.unasat.bp24.hibernate.configuration.JPAConfiguration;
import sr.unasat.bp24.hibernate.entity.Income;

import java.util.List;

public class IncomeRepo {

    private EntityManager entityManager;
    private EntityTransaction transaction = null;

    public IncomeRepo() {
        this.entityManager = JPAConfiguration.getEntityManager();
    }

    public List<Income> getTotalIncome(Long userId) {

        entityManager.getTransaction().begin();

        String jpql = "select s from Income s where s.user.id = ?1";

        TypedQuery<Income> query = entityManager.createQuery(jpql, Income.class);

        query.setParameter(1, userId);


        List<Income> icomeList = query.getResultList();
        entityManager.getTransaction().commit();

        return icomeList;
    }

    public Income getIncomeById(int selectedIncomeId) {

        Income income = null;

        try {
            entityManager.getTransaction().begin();

            String jpql = "select i from Income i where i.id = ?1";

            TypedQuery<Income> query = entityManager.createQuery(jpql, Income.class)
                    .setParameter(1, selectedIncomeId);

            income = query.getSingleResult();
            entityManager.getTransaction().commit();

        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("rollback transaction");
            }
            //e.printStackTrace();
        }
        return income;
    }

    public Income updateIncome(Income income) {

        try {
            //get a transaction
            transaction = entityManager.getTransaction();
            //begin transaction
            if (!transaction.isActive()){
                transaction.begin();
            }

            entityManager.merge(income);

            //commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("rollback transaction");
            }
            /*e.printStackTrace();*/
        }
        return income;
    }

    public void delete(Income selectedIncome) {
        try {
            //get a transaction
            transaction = entityManager.getTransaction();
            //begin transaction
            if (!transaction.isActive()){
                transaction.begin();
            }

            Income income = entityManager.find(Income.class, selectedIncome.getIncomeId());

            entityManager.remove(income);

            //commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("rollback transaction");
            }
            /*e.printStackTrace();*/
        }
    }
}
