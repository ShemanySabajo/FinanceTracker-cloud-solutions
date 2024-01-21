package sr.unasat.bp24.hibernate.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import sr.unasat.bp24.hibernate.configuration.JPAConfiguration;
import sr.unasat.bp24.hibernate.entity.Expense;
import sr.unasat.bp24.hibernate.entity.Income;
import sr.unasat.bp24.hibernate.entity.Transaction;

public class TransactionDao {


    private EntityManager entityManager;
    private EntityTransaction transaction = null;

    public TransactionDao() {
        this.entityManager = JPAConfiguration.getEntityManager();
    }

    public void addMonthlyIncome(Transaction transaction) {

        try {
            //get a transaction
            this.transaction = entityManager.getTransaction();
            //begin transaction
            if (!this.transaction.isActive()) {
                this.transaction.begin();
            }

            Income income = transaction.getIncome();

            entityManager.persist(income);
            entityManager.persist(transaction);

            entityManager.flush();

            //commit the transaction
            this.transaction.commit();

            System.out.println("Persisted Income ID: " + transaction.getTransactionId());

        } catch (Exception e) {
            if (this.transaction != null) {
                this.transaction.rollback();
                System.out.println("rollback transaction");
            }
            e.printStackTrace();
        }
    }


    public void addMonthlyExpense(Transaction transaction) {

        try {
            //get a transaction
            this.transaction = entityManager.getTransaction();
            //begin transaction
            if (!this.transaction.isActive()) {
                this.transaction.begin();
            }

            Expense expense = transaction.getExpense();

            entityManager.persist(expense);
            entityManager.persist(transaction);

            entityManager.flush();

            //commit the transaction
            this.transaction.commit();

            System.out.println("Persisted transaction ID: " + transaction.getTransactionId());

            System.out.println("Expense added successfully: "  + " SRD " + expense.getAmount());

        } catch (Exception e) {
            if (this.transaction != null) {
                this.transaction.rollback();
                System.out.println("rollback transaction");
            }
            e.printStackTrace();
        }
    }
}
