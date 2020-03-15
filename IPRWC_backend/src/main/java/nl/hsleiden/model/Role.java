package nl.hsleiden.model;

/**
 * @Author Loes
 * <p>
 * Represents a Role, using an integer to check whether a role `includes` another.
 * The higher the number, the more it `includes`.
 */
public enum Role {
    USER(0),
    ADMIN(1);

    private Integer bit;

    Role(int bit) {
        this.bit = bit;
    }

    public Integer getBit() {
        return bit;
    }

    public boolean hasRole(Role other) {
        return this.getBit() >= other.getBit();
    }
}
