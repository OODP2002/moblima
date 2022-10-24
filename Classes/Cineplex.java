import java.util.ArrayList;

public class Cineplex{
    private String name;
    private int id;
    private ArrayList<Cinema> cinema;
    private int numCinema;

    public Cineplex(String name, int id, ArrayList<Cinema> cinema){
        this.name = name;
        this.id = id;
        this.cinema=cinema;
        this.numCinema=cinema.size();
    }

    
    public ArrayList<Cinema> getCinema(){
        return cinema;
    }

    public void setCinema(int i, Cinema cinema){
        return cinema.set(i, cinema);
    }

    public int getNumCinema(){
        return this.numCinema;
    }

}
