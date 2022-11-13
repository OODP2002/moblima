public class StoreManager {
    static void closeAllStores() {
        CineplexesReaderWriter.getInstance().writeFile();
        CredentialStore.getInstance().closeShowTimeStore();
        MovieStore.getInstance().closeMovieStore();
        SeatStore.getInstance().writeFile();
        SettingStore.getInstance().closeSettingStore();
        ShowTimeStore.getInstance().closeShowTimeStore();
        ShowTimeStore.getInstance().closeShowTimeStore();
        SpecialOccasionStore.getInstance().writeToSpecialOccasionFile(); //working
        TicketStore.getInstance().closeTicketStore();
        PricingStore.getInstance().closePricingStore();
    }
}
