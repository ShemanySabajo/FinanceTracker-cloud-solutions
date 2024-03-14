package sr.unasat.bp24.hibernate.dto;

public class ExpenseDTO {
    private Long expenseId;
    private Double amount;
    private String description;

    public ExpenseDTO() {
    }

    public ExpenseDTO(Long expenseId, Double amount, String description) {
        this.expenseId = expenseId;
        this.amount = amount;
        this.description = description;
    }

    // Getters and setters

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
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
