import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;

public class Meetup {

    private YearMonth yearMonth;

    Meetup(int month, int year) {
        yearMonth = YearMonth.of(year, month);
    }

    LocalDate day(DayOfWeek day, MeetupSchedule schedule) {
        int today = schedule == MeetupSchedule.TEENTH
                ? 13
                : schedule == MeetupSchedule.LAST
                ? yearMonth.lengthOfMonth() - 6
                : Math.max(1, schedule.ordinal() * 7);

        if (today < yearMonth.lengthOfMonth() && today % 7 == 0) {
            today++;
        }

        LocalDate date = LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(), today);
        while (date.getDayOfWeek() != day) {
            date = date.withDayOfMonth(++today);
        }
        return date;
    }
}
