package sr.unasat.bp24.hibernate.dto;

public class TransactionDTO {
    private Long transactionId;
    private Long incomeId;
    private Long expenseId;
    private Double amount;

    public TransactionDTO() {
    }

    public TransactionDTO(Long transactionId, Long incomeId, Long expenseId, Double amount) {
        this.transactionId = transactionId;
        this.incomeId = incomeId;
        this.expenseId = expenseId;
        this.amount = amount;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(Long incomeId) {
        this.incomeId = incomeId;
    }

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
}
