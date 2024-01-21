package sr.unasat.bp24.hibernate.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import sr.unasat.bp24.hibernate.configuration.JPAConfiguration;
import sr.unasat.bp24.hibernate.entity.Expense;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class ExpenseDao {

    private EntityManager entityManager;
    private EntityTransaction transaction = null;

    public ExpenseDao() {
        this.entityManager = JPAConfiguration.getEntityManager();
    }

    public List<Expense> getTotalMonthlyExpenses(Long userId) {

        entityManager.getTransaction().begin();

        LocalDate now = LocalDate.now();

        Month month = now.getMonth();
        int year = now.getYear();

        String jpql = "select e from Expense e where e.user.id = ?1 AND MONTH(e.expenseDate) = ?2 AND YEAR(e.expenseDate) = ?3 ";

        TypedQuery<Expense> query = entityManager.createQuery(jpql, Expense.class);

        query.setParameter(1, userId);
        query.setParameter(2, month.getValue());
        query.setParameter(3, year);

        List<Expense> expenseList = query.getResultList();
        entityManager.getTransaction().commit();

        return expenseList;
    }

    public List<Expense> getTopExpensesForTheMonth(Long userId) {

        entityManager.getTransaction().begin();

        LocalDate now = LocalDate.now();

        Month month = now.getMonth();
        int year = now.getYear();

        String jpql = "select e from Expense e where e.user.id = ?1 AND MONTH(e.expenseDate) = ?2 AND YEAR(e.expenseDate) = ?3 ORDER BY e.amount DESC";

        TypedQuery<Expense> query = entityManager.createQuery(jpql, Expense.class);

        query.setParameter(1, userId);
        query.setParameter(2, month.getValue());
        query.setParameter(3, year);

        query.setMaxResults(10);

        List<Expense> expenseList = query.getResultList();
        entityManager.getTransaction().commit();

        return expenseList;
    }

    public List<Expense> getTotalExpenses(Long userId) {
        entityManager.getTransaction().begin();

        LocalDate now = LocalDate.now();

        Month month = now.getMonth();
        int year = now.getYear();

        String jpql = "select e from Expense e where e.user.id = ?1";

        TypedQuery<Expense> query = entityManager.createQuery(jpql, Expense.class);

        query.setParameter(1, userId);
        query.setParameter(2, month.getValue());
        query.setParameter(3, year);

        query.setMaxResults(10);

        List<Expense> expenseList = query.getResultList();
        entityManager.getTransaction().commit();

        return expenseList;
    }
}
