package cc.bitky.demo.featurelab.service.time;

import cc.bitky.demo.featurelab.util.log.KyLog;
import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @author liMingLiang
 * @date 2019-05-29
 */
@Slf4j
public class KyDurationUtils {

    private static final KyLog KY_LOG = KyLog.of(log);

    public static void main(String[] args) {
        duration();
        KY_LOG.info("------");
        period();
    }

    private static void period() {
        Period period = Period.between(LocalDate.of(2019, 6, 1), LocalDate.now());
        KY_LOG.info("较大的时间间隔：" + period.getDays() + "天");
    }

    private static void duration() {
        LocalDateTime time = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).minusMinutes(1);
        Duration duration1 = Duration.between(time, LocalDateTime.now());
        long seconds1 = duration1.getSeconds();
        KY_LOG.info("较小的时间间隔:" + seconds1 + "秒");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time2 = LocalDateTime.parse("2018-05-29 21:14:01", formatter);
        ZonedDateTime zonedTime2 = ZonedDateTime.of(time2, ZoneId.systemDefault());
        Instant instant = Instant.from(zonedTime2);
        Duration duration2 = Duration.between(instant, Instant.now());
        long hours = duration2.toHours();
        KY_LOG.info("较小的时间间隔:" + hours + "小时");
    }
}
