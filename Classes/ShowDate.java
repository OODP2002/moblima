import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ShowDate {
    private final LocalDateTime date;
    private boolean weekend;

    public ShowDate(LocalDateTime showTime) {
        this.date = LocalDateTime.parse(showTime.format(DateTimeFormatter.ofPattern("")));
        this.weekend = isWeekend(date);
    }

    private boolean isWeekend(LocalDateTime date) {
        switch (date.getDayOfWeek()) {
            case FRIDAY:
            case SATURDAY:
            case SUNDAY:
                return true;
            default:
                return false;
        }
    }

    public boolean getWeekend() {
        return weekend;
    }
}
