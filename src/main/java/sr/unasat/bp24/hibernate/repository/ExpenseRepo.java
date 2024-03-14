package sr.unasat.bp24.hibernate.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import sr.unasat.bp24.hibernate.configuration.JPAConfiguration;
import sr.unasat.bp24.hibernate.entity.Expense;
import sr.unasat.bp24.hibernate.entity.Transaction;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class ExpenseRepo {

    private EntityManager entityManager;
    private EntityTransaction transaction = null;

    public ExpenseRepo() {
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

    public List<Expense> getTotalExpenses(Long userId ) {
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
    public Expense getExpenseById(Long selectedExpenseId) {
        return entityManager.find(Expense.class, selectedExpenseId);
    }
    public Transaction getTransactionById(Long transactionId) {
        try {
            entityManager.getTransaction().begin();
            Transaction transaction = entityManager.find(Transaction.class, transactionId);
            entityManager.getTransaction().commit();
            return transaction;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("rollback transaction");
            }
            return null;
        }
    }

        public Expense updateExpense(Expense expense) {
            try {
                entityManager.getTransaction().begin();
                Expense updatedExpense = entityManager.merge(expense);
                entityManager.getTransaction().commit();
                return updatedExpense;
            } catch (Exception e) {
                if (entityManager.getTransaction().isActive()) {
                    entityManager.getTransaction().rollback();
                }
                e.printStackTrace();
                return null; // Or handle the exception as needed
            }
        }
    public void deleteExpenseAndTransaction(Expense expense) {
        try {
            transaction = entityManager.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }

            entityManager.remove(expense.getTransaction());
            entityManager.remove(expense);

            entityManager.flush();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    }

