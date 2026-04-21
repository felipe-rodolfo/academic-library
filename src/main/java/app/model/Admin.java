package app.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {
    @Column(name = "permission_level")
    private Integer permissionLevel;

    protected Admin() {
    }

    public Admin(String name, String email, Integer permissionLevel) {
        super(name, email);
        this.permissionLevel = permissionLevel;
    }

    public Integer getPermissionLevel() {
        return permissionLevel;
    }
}
