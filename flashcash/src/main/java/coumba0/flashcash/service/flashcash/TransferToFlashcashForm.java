package coumba0.flashcash.service.flashcash;

public class TransferToFlashcashForm {
    private Double amount;

    public TransferToFlashcashForm(Double amount) {
        this.amount = amount;
    }

    public TransferToFlashcashForm() {
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
