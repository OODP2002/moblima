public class StoreManager {
    static void closeAllStores() {
        CineplexesReaderWriter.getInstance().writeFile();
        CredentialStore.getInstance().closeShowTimeStore();
        // To-do: write file for layout store (MY)
        MovieStore.getInstance().closeMovieStore();
        SeatStore.getInstance().writeFile();
        // To-do: write file for setting store (MY)
        ShowTimeStore.getInstance().closeShowTimeStore();
        // To-do: write file for pricing store (DZ)
        ShowTimeStore.getInstance().closeShowTimeStore();
        SpecialOccasionStore.getInstance().writeToSpecialOccasionFile();
        TicketStore.getInstance().closeTicketStore();
    }
}
