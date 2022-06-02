package mbeans;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MiddleTime implements MiddleTimeMBean {
    List<LocalDateTime> times = new ArrayList<>();

    public List<LocalDateTime> getTimes() {
        return times;
    }

    public void setTimes(List<LocalDateTime> times) {
        this.times = times;
    }

    @Override
    public int calculateMiddleTime() {
        int sum_of_hours = 0;
        for (int i = 0; i < times.size() - 1; i++) {
            LocalDateTime time1 = times.get(i);
            LocalDateTime time2 = times.get(i + 1);
            sum_of_hours += Duration.between(time1, time2).toMillis();
        }
        return sum_of_hours / times.size();
    }
}
