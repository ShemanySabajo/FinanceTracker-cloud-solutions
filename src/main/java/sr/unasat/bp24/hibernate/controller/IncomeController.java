package sr.unasat.bp24.hibernate.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sr.unasat.bp24.hibernate.dto.TransactionDTO;
import sr.unasat.bp24.hibernate.entity.Income;
import sr.unasat.bp24.hibernate.services.IncomeService;
import sr.unasat.bp24.hibernate.dto.IncomeDTO;
import sr.unasat.bp24.hibernate.entity.Transaction;

import java.util.List;
import java.util.stream.Collectors;

@Path("/income")
public class IncomeController {
    private IncomeService incomeService;

    public IncomeController() {
        this.incomeService = new IncomeService();
    }

    @GET
    @Path("/list/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<IncomeDTO> getTotalIncome(@PathParam("userId") Long userId) {
        List<Income> incomes = incomeService.getTotalIncome(userId);
        return incomes.stream()
                .map(this::convertToDTO) // Convert each Income to IncomeDTO
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{selectedIncomeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public IncomeDTO getIncomeById(@PathParam("selectedIncomeId") int selectedIncomeId) {
        Income income = incomeService.getIncomeById((long) selectedIncomeId);
        return convertToDTO(income);
    }


    private IncomeDTO convertToDTO(Income income) {
        if (income == null) {
            return null;
        }
        return new IncomeDTO(
                income.getIncomeId(),
                income.getAmount(),
                income.getDescription()
        );
    }

}

