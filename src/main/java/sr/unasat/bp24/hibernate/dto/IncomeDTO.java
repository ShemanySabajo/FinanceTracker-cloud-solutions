package sr.unasat.bp24.hibernate.dto;

import sr.unasat.bp24.hibernate.entity.User;

import java.time.LocalDate;

public class IncomeDTO {
    private Long incomeId;
    private Double amount;
    private String description;


    // Constructors
    public IncomeDTO() {
    }

    public IncomeDTO(Long incomeId, Double amount, String description) {
        this.incomeId = incomeId;
        this.amount = amount;
        this.description = description;
    }

    // Getters and setters
    public Long getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(Long incomeId) {
        this.incomeId = incomeId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
