package coumba0.flashcash.service.flashcash;

public class AddIbanForm {
    private String iban;

    public AddIbanForm(String iban) {
        this.iban = iban;
    }

    public AddIbanForm() {
        super();
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}
