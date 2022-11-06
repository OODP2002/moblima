public class MovieSales implements MovieInformation<Integer>{
    private Integer movieSales;

    public MovieSales(){
        this.movieSales = 0;
    }

    public MovieSales(Integer i){
        this.movieSales = i;
    }

    public Integer getDetail(){
        return this.movieSales;
    }

    public void setDetail(Integer i){
        this.movieSales = i;
    }

    public void addSale() {
        this.movieSales++;
    }
}
