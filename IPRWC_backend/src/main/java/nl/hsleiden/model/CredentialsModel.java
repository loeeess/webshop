package nl.hsleiden.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class CredentialsModel {
    private String username;
    @NotEmpty
    @Length(min = 0, max = 64)
    private String password;

    public CredentialsModel() {

    }

    public CredentialsModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


