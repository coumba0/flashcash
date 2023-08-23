package coumba0.flashcash.service.flashcash;

public class TransferToBankForm {
    private  String iban;
    private  Double amount;

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
