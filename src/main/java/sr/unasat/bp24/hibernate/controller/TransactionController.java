package sr.unasat.bp24.hibernate.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sr.unasat.bp24.hibernate.dto.TransactionDTO;
import sr.unasat.bp24.hibernate.entity.Expense;
import sr.unasat.bp24.hibernate.entity.Income;
import sr.unasat.bp24.hibernate.entity.Transaction;
import sr.unasat.bp24.hibernate.services.ExpenseService;
import sr.unasat.bp24.hibernate.services.IncomeService;


@Path("/transaction")
public class TransactionController {
    private IncomeService incomeService;
    private ExpenseService expenseService = new ExpenseService();

    public TransactionController() {
        this.incomeService = new IncomeService();
    }

    @POST
    @Path("/addIncome")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMonthlyIncome(TransactionDTO transactionDTO) {
        try {
            // Create a new income entity
            Income income = new Income();
            income.setAmount(transactionDTO.getAmount());
            income.setDescription("Salary");

            // Create a new transaction entity
            Transaction transaction = new Transaction();
            transaction.setAmount(transactionDTO.getAmount());
            transaction.setIncome(income);

            // Call IncomeService to add the income
            incomeService.addMonthlyIncome(transaction);

            // Call IncomeService to add the income
            incomeService.addMonthlyIncome(transaction);
            // Persist the transaction
            incomeService.addMonthlyIncome(transaction);

            // Return a response indicating success
            return Response.ok().build();
        } catch (Exception e) {
            // Handle exceptions
            return Response.serverError().entity("Error occurred: " + e.getMessage()).build();
        }
    }
    @POST
    @Path("/addExpense")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMonthlyExpense(TransactionDTO transactionDTO) {
        try {
            // Create a new expense entity
            Expense expense = new Expense();
            expense.setAmount(transactionDTO.getAmount());
            expense.setDescription("Expense");

            // Create a new transaction entity
            Transaction transaction = new Transaction();
            transaction.setAmount(transactionDTO.getAmount());
            transaction.setExpense(expense);

            // Call ExpenseService to add the expense
            expenseService.addMonthlyExpense(transaction);

            // Return a response indicating success
            return Response.ok().build();
        } catch (Exception e) {
            // Handle exceptions
            return Response.serverError().entity("Error occurred: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/updateIncome/{transactionId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateIncome(@PathParam("transactionId") Long transactionId, TransactionDTO transactionDTO) {
        try {
            // Fetch the existing transaction from the database using its ID
            Transaction existingTransaction = incomeService.getTransactionById(transactionId);
            if (existingTransaction == null) {
                // Return a response indicating that the transaction with the given ID was not found
                return Response.status(Response.Status.NOT_FOUND).entity("Transaction not found").build();
            }

            // Update the amount of the existing transaction
            existingTransaction.setAmount(transactionDTO.getAmount());

            // Fetch the associated income from the transaction
            Income existingIncome = existingTransaction.getIncome();

            // Update the amount of the existing income
            existingIncome.setAmount(transactionDTO.getAmount());

            // Call the IncomeService to update both the transaction and income
            incomeService.updateTransactionAndIncome(existingTransaction, existingIncome);

            // Return a response indicating success
            return Response.ok().build();
        } catch (Exception e) {
            // Handle exceptions
            return Response.serverError().entity("Error occurred: " + e.getMessage()).build();
        }

}
    @PUT
    @Path("/updateExpense/{transactionId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateExpense(@PathParam("transactionId") Long transactionId, TransactionDTO transactionDTO) {
        try {
            // Fetch the existing transaction from the database using its ID
            Transaction existingTransaction = expenseService.getTransactionById(transactionId);
            if (existingTransaction == null) {
                // Return a response indicating that the transaction with the given ID was not found
                return Response.status(Response.Status.NOT_FOUND).entity("Transaction not found").build();
            }

            // Update the amount of the existing transaction
            existingTransaction.setAmount(transactionDTO.getAmount());

            // Fetch the associated expense from the transaction
            Expense existingExpense = existingTransaction.getExpense();

            // Update the amount of the existing expense
            existingExpense.setAmount(transactionDTO.getAmount());

            // Call the ExpenseService to update both the transaction and expense
            expenseService.updateTransactionAndExpense(existingTransaction, existingExpense);

            // Return a response indicating success
            return Response.ok().build();
        } catch (Exception e) {
            // Handle exceptions
            return Response.serverError().entity("Error occurred: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/deleteIncome/{transactionId}")
    public Response deleteIncome(@PathParam("transactionId") Long transactionId) {
        try {
            // Fetch the existing transaction from the database using its ID
            Transaction existingTransaction = incomeService.getTransactionById(transactionId);
            if (existingTransaction == null) {
                // Return a response indicating that the transaction with the given ID was not found
                return Response.status(Response.Status.NOT_FOUND).entity("Transaction not found").build();
            }

            // Fetch the associated income from the transaction
            Income existingIncome = existingTransaction.getIncome();

            // Call the IncomeService to delete both the transaction and income
            incomeService.deleteIncomeAndTransaction(existingIncome);

            // Return a response indicating success
            return Response.ok().build();
        } catch (Exception e) {
            // Handle exceptions
            return Response.serverError().entity("Error occurred: " + e.getMessage()).build();
        }
    }
    @DELETE
    @Path("/deleteExpense/{transactionId}")
    public Response deleteExpense(@PathParam("transactionId") Long transactionId) {
        try {
            // Fetch the existing transaction from the database using its ID
            Transaction existingTransaction = expenseService.getTransactionById(transactionId);
            if (existingTransaction == null) {
                // Return a response indicating that the transaction with the given ID was not found
                return Response.status(Response.Status.NOT_FOUND).entity("Transaction not found").build();
            }

            // Call the ExpenseService to delete the expense and transaction
            expenseService.deleteExpenseAndTransaction(existingTransaction);

            // Return a response indicating success
            return Response.ok().build();
        } catch (Exception e) {
            // Handle exceptions
            return Response.serverError().entity("Error occurred: " + e.getMessage()).build();
        }

    }

    private TransactionDTO convertToDTO(Transaction transaction) {
        if (transaction == null) {
            return null;
        }
        return new TransactionDTO(
                transaction.getTransactionId(),
                transaction.getIncome() != null ? transaction.getIncome().getIncomeId() : null,
                transaction.getExpense() != null ? transaction.getExpense().getExpenseId() : null,
                transaction.getAmount()
        );
    }
}
