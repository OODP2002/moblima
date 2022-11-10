interface SysPriceHandler {
    default void editPricing(Pricing newPricing){
        PricingStore pricingStore = PricingStore.getInstance();
        pricingStore.addPricing(newPricing);
    }
}
