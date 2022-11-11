import java.time.LocalTime;
interface SysPriceHandler {
    public boolean updateHype(String hype, Float newVal);

    public boolean updateCinemaClass(String cinemaClass, Float newVal);

    public boolean updateDayOfWeek(Integer dayOfWeek, Float newVal);

    public boolean updateCutOff(LocalTime oldCutOff, LocalTime newCutoff);

    public boolean updateFridayRule(LocalTime cutOff, Float newVal);

    public boolean updateView(String view, Float newVal);
}
