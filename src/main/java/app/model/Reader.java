package app.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("READER")
public class Reader extends User {
    @Column(name = "registration_code")
    private String registrationCode;

    protected Reader() {
    }

    public Reader(String name, String email, String registrationCode) {
        super(name, email);
        this.registrationCode = registrationCode;
    }

    public String getRegistrationCode() {
        return registrationCode;
    }
}
