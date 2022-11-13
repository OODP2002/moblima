public class CinemaStaff implements SysShowtimeHandler, SysPriceHandler, SysSpecialOccasionHandler, SysMovieHandler, SysSettings, Person {

    private String name;
    
    public CinemaStaff(String name){
        setName(name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    // Encapsulation: CinemaStaff responsible for own validation and persistence
    public boolean validate(String password) {
        return password.equals(CredentialStore.getInstance().getPassword(name));
    }
}