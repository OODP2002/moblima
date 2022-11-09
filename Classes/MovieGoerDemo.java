public class MovieGoerDemo {
    public static void main(String[] args){
        MovieGoer person = new MovieGoer("zhekai","zhekai@gmail.com",99993333);
        person.listAllMovies();
        person.listBy();
        person.listBy();
        // person.searchMovie();
        // person.searchMovie(69);
        person.buyTicket();
    }
}
