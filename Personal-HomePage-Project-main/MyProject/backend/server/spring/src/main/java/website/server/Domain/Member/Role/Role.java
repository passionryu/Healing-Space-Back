package website.server.Domain.Member.Role;


public enum Role {
    /**
     * Role type = 3
     * Admin > user > visitor
     */
    ADMIN("this is admin"),
    SELLER("this is seller"),
    USER("this is user"),
    VISITOR("this is visitor");


    private final String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
