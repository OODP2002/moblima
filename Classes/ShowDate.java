import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the date of the show and accounts if the show is on a weekend or not
 * @author Koh Mingyang
 * @version 1.0.0 Nov 13, 2022
 */
public class ShowDate {
    /**
     * Constant, unchangeable variable of current date
     */
    private final LocalDateTime date;

    /**
     * Boolean variable indicating if it is the weekend or not. True = Weekend, False = weekday
     */
    private boolean weekend;

    /**
     * Creates a new ShowDate which reflects the current date and if it is a weeekend or not
     * @param showTime ShowDate accounts for the showTime of a particular movie
     */
    public ShowDate(LocalDateTime showTime) {
        this.date = LocalDateTime.parse(showTime.format(DateTimeFormatter.ofPattern("")));
        this.weekend = isWeekend(date);
    }

    /**
     * Checks if the given date is on a weekend
     * @param date the date to be checked in LocaDateTime datatype
     * @return boolean True if weekend, False if weekday
     */
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

    /**
     * Gets this ShowTime's weekend attribute
     * @return True if weekday, Flase if weekend
     */
    public boolean getWeekend() {
        return weekend;
    }
}
