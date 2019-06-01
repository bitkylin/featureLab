package cc.bitky.demo.featurelab.service.time;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @author liMingLiang
 * @date 2019-05-29
 */
public class KyDurationUtils {

    public static void main(String[] args) {
        LocalDateTime time = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).minusMinutes(1);
        Duration duration1 = Duration.between(time, LocalDateTime.now());
        long seconds1 = duration1.getSeconds();
        System.out.println("时间间隔:" + seconds1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime time2 = LocalDateTime.parse("20190529211401", formatter);
        ZonedDateTime zonedTime2 = ZonedDateTime.of(time2, ZoneId.systemDefault());
        Instant instant = Instant.from(zonedTime2);
        Duration duration2 = Duration.between(instant, Instant.now());
        long seconds2 = duration2.getSeconds();
        System.out.println("时间间隔:" + seconds2);
    }
}
