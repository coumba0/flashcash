package coumba0.flashcash.service.form;

public class SignUpForm {
    private String firstName;
    private String lastName;
    private String mail;
    private String password;
    private String confirmPassword;
    private boolean pswdConfirmed;

    static void pswdConfirm() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isPswdConfirmed() {
        return pswdConfirmed;
    }

    public void setPswdConfirmed(boolean pswdConfirmed) {
        this.pswdConfirmed = pswdConfirmed;
    }

}
