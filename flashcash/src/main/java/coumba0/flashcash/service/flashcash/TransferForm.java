package coumba0.flashcash.service.flashcash;

public class TransferForm {

    private String contactEmail;
    private Double amount;

    public TransferForm() {
    }

    public TransferForm(String contactEmail, Double amount) {

        this.contactEmail = contactEmail;
        this.amount = amount;
    }
    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
