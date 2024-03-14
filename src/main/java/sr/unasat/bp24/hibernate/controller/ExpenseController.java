package sr.unasat.bp24.hibernate.controller;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import sr.unasat.bp24.hibernate.dto.ExpenseDTO;
import sr.unasat.bp24.hibernate.entity.Expense;
import sr.unasat.bp24.hibernate.services.ExpenseService;
import sr.unasat.bp24.hibernate.services.IncomeService;

@Path("/expense")
public class ExpenseController {

    private ExpenseService expenseService;
    public ExpenseController() {
        this.expenseService = new ExpenseService();
    }
    @GET
    @Path("/{selectedExpenseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public ExpenseDTO getExpenseById(@PathParam("selectedExpenseId") Long selectedExpenseId) {
        Expense expense = expenseService.getExpenseById(selectedExpenseId);
        return convertToDTO(expense);
    }

    private ExpenseDTO convertToDTO(Expense expense) {
        if (expense == null) {
            return null;
        }
        return new ExpenseDTO(
                expense.getExpenseId(),
                expense.getAmount(),
                expense.getDescription()
        );
    }

}
