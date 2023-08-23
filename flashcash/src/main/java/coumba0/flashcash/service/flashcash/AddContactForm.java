package coumba0.flashcash.service.flashcash;

public class AddContactForm {
    private String mail;

    public AddContactForm(String mail) {
        this.mail = mail;
    }

    public AddContactForm() {
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
