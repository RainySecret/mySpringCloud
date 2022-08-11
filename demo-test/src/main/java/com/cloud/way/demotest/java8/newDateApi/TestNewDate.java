package com.cloud.way.demotest.java8.newDateApi;

import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

/**
 * @author chongwei
 * @Description java8新的日期时间API
 * @date 2022/8/9
 */
@Slf4j
public class TestNewDate {

    public static void main(String[] args) {
        clock();

        zoneId();

        localTime();

        localDate();

        localDateTime();
    }

    /**
     * Clock类提供了访问当前日期和时间的方法，Clock是时区敏感的，<br/>
     * 可以用来取代 System.currentTimeMillis()  来获取当前的微秒数。<br/>
     * 某一个特定的时间点也可以使用**Instant**类来表示，Instant类也可以用来创建老的java.util.Date对象。<br/>
     */
    public static void clock() {
        log.info("Clock 时钟测试方法");
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        System.out.println(millis);
        Instant instant = clock.instant();
        // legacy java.util.Date
        Date legacyDate = Date.from(instant);
        System.out.println(legacyDate);
    }

    /**
     * **Timezones 时区**<br/>
     * 在新API中时区使用ZoneId来表示。时区可以很方便的使用静态方法of来获取到。<br/>
     * 时区定义了到UTS时间的时间差，在Instant时间点对象到本地日期对象之间转换的时候是极其重要的。<br/>
     */
    public static void zoneId() {
        log.info("Timezones 时区测试方法");
        //输出所有可用的区域Id
        System.out.println(ZoneId.getAvailableZoneIds());

        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        //获取此 ID 允许执行计算的时区规则
        System.out.println(zone1.getRules());
        System.out.println(zone2.getRules());
    }

    /**
     * LocalTime 本地时间<br/>
     * LocalTime 定义了一个没有时区信息的时间，例如 晚上10点，或者 17:30:15<br/>
     */
    public static void localTime() {
        log.info("LocalTime 本地时间测试方法");
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);
        System.out.println(now1.isBefore(now2));  // false

        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);

        System.out.println(hoursBetween);       // -3
        System.out.println(minutesBetween);     // -239


        /*
         * LocalTime 提供了多种工厂方法来简化对象的创建，包括解析时间字符串。<br/>
         */
        LocalTime late = LocalTime.of(23, 59, 59);
        System.out.println(late);       // 23:59:59
        DateTimeFormatter germanFormatter = DateTimeFormatter
                .ofLocalizedTime(FormatStyle.SHORT)
                .withLocale(Locale.GERMAN);

        LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
        System.out.println(leetTime);   // 13:37
    }

    /**
     * LocalDate 本地日期<br/>
     * LocalDate 表示了一个确切的日期，比如 2014-03-11。<br/>
     * 该对象值是不可变的，用起来和LocalTime基本一致。<br/>
     */
    public static void localDate () {
        log.info("LocalDate 本地日期测试方法");
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = tomorrow.minusDays(2);
        LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();

        System.out.println(dayOfWeek);    // FRIDAY

        //从字符串解析一个LocalDate类型
        DateTimeFormatter germanFormatter = DateTimeFormatter
                        .ofLocalizedDate(FormatStyle.MEDIUM)
                        .withLocale(Locale.GERMAN);
        LocalDate xmas = LocalDate.parse("24.12.2014", germanFormatter);
        System.out.println(xmas);   // 2014-12-24
    }

    /**
     * LocalDateTime 本地日期时间<br/>
     * LocalDateTime 同时表示了时间和日期，相当于前两节内容合并到一个对象上了。LocalDateTime和LocalTime还有LocalDate一样，都是不可变的。<br/>
     */
    public static void localDateTime () {
        log.info("LocalDateTime 本地日期时间测试方法");
        LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);
        DayOfWeek dayOfWeek = sylvester.getDayOfWeek();
        System.out.println(dayOfWeek);      // WEDNESDAY

        Month month = sylvester.getMonth();
        System.out.println(month);          // DECEMBER

        long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
        System.out.println(minuteOfDay);    // 1439

        /*
         * 只要附加上时区信息，就可以将其转换为一个时间点Instant对象，Instant时间点对象可以很容易的转换为老式的java.util.Date
         */
        Instant instant = sylvester.atZone(ZoneId.systemDefault()).toInstant();
        Date legacyDate = Date.from(instant);
        System.out.println(legacyDate);

        /*
         * 格式化LocalDateTime和格式化时间和日期一样的，除了使用预定义好的格式外，我们也可以自己定义格式
         */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd, yyyy-HH:mm");
        LocalDateTime parsed = LocalDateTime.parse("11-03, 2014-07:13", formatter);
        String string = formatter.format(parsed);
        log.info(string);
    }

}
