public class MoviePersonnel implements Person {
    private String name;
    private Role role;

    public MoviePersonnel(String name, Role role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
