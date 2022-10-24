import java.util.ArrayList;

public class Cineplex{
    private String name;
    private int id;
    private ArrayList<Cinema> Cinema;
    private int numCinema;

    public Cineplex(String name, int id, ArrayList<Cinema> Cinema){
        this.name = name;
        this.id = id;
        this.Cinema=Cinema;
        this.numCinema=Cinema.size();
    }

    public Cinema getCinema(int i){
        return Cinema.get(i);
    }

    public void setCinema(int i, Cinema Cinema){
        return Cinema.set(i, Cinema);
    }

    public int getNumCinema(){
        return this.numCinema;
    }

}
