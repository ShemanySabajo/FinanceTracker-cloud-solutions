package sr.unasat.bp24.hibernate.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import sr.unasat.bp24.hibernate.configuration.JPAConfiguration;
import sr.unasat.bp24.hibernate.entity.Expense;
import sr.unasat.bp24.hibernate.entity.Income;
import sr.unasat.bp24.hibernate.entity.Transaction;

public class TransactionRepo {


    private EntityManager entityManager;
    private EntityTransaction transaction = null;

    public TransactionRepo() {
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

            //entityManager.flush();

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

    public Transaction updateTransaction(Transaction transaction) {

        try {
            //get a transaction
            this.transaction = entityManager.getTransaction();
            //begin transaction
            if (!this.transaction.isActive()) {
                this.transaction.begin();
            }

            //update the transaction
            entityManager.merge(transaction);

            //commit the transaction
            this.transaction.commit();

            System.out.println("Transaction updated successfully: " + transaction.getTransactionId());

        } catch (Exception e) {
            if (this.transaction != null) {
                this.transaction.rollback();
                System.out.println("rollback transaction");
            }
            e.printStackTrace();
        }
        return transaction;
    }
    public Transaction getTransactionById(Long transactionId) {
        return entityManager.find(Transaction.class, transactionId);
    }
    public void deleteTransaction(Transaction transaction) {
        try {
            // Begin a transaction
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            // Remove the transaction entity
            entityManager.remove(entityManager.contains(transaction) ? transaction : entityManager.merge(transaction));

            // Commit the transaction
            entityTransaction.commit();

            System.out.println("Transaction deleted successfully: " + transaction.getTransactionId());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to delete transaction: " + transaction.getTransactionId());
        }
    }

}
