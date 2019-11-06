import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

class Gigasecond {

    private final static long GIGASECONDS = 1000000000L;
    private LocalDateTime dateInAGigasecond;

    Gigasecond(LocalDate moment) {
            this(moment.atStartOfDay());
    }

    Gigasecond(LocalDateTime moment) {
            this.dateInAGigasecond = moment.plus(GIGASECONDS, ChronoUnit.SECONDS);
    }

    LocalDateTime getDateTime() {
        return dateInAGigasecond;
    }

}
