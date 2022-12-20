package pl.javastart.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Main main = new Main();
        main.run(new Scanner(System.in));

    }

    public void run(Scanner scanner) {
        LocalDateTime dateTime = getLocalDateTime(scanner);
        if (dateTime == null) {
            System.out.println("Nie wczytano daty");
            return;
        }
        printResult(dateTime);

    }

    private LocalDateTime getLocalDateTime(Scanner scanner) {
        String[] dateTimeFormats = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "dd.MM.yyyy HH:mm:ss"};
        String[] dateFormats = {"yyyy-MM-dd"};
        System.out.println("Podaj datę");
        String dataInput = scanner.nextLine();
        for (String format : dateTimeFormats) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            try {
                return LocalDateTime.parse(dataInput, formatter);
            } catch (DateTimeParseException ignore) {
                //ignore
            }
        }
        for (String format : dateFormats) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            try {
                return LocalDate.parse(dataInput, formatter).atStartOfDay();
            } catch (DateTimeParseException ignore) {
                //ignore
            }
        }
        if (Objects.equals(dataInput, "t")) {
            return LocalDateTime.now();
        } else {
            return TimeCalculator.createTimeResult(dataInput);
        }

    }

    private void printResult(LocalDateTime dateTime) {
        DateTimeFormatter dataAndTimePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZonedDateTime localTime = dateTime.atZone(ZoneId.systemDefault());
        List<DisplayDate> displayDates = List.of(
                new DisplayDate("Czas lokalny", localTime.getZone()),
                new DisplayDate("UTC", ZoneId.of("UTC")),
                new DisplayDate("Londyn", ZoneId.of("Europe/London")),
                new DisplayDate("Los Angeles", ZoneId.of("America/Los_Angeles")),
                new DisplayDate("Sydney", ZoneId.of("Australia/Sydney"))
        );

        for (DisplayDate displayDate : displayDates) {
            System.out.println(displayDate.getName() + ": "
                    + localTime.withZoneSameInstant(displayDate.getZone()).format(dataAndTimePattern));
        }
    }

    static class DisplayDate {
        String name;
        ZoneId zone;

        public DisplayDate(String name, ZoneId zone) {
            this.name = name;
            this.zone = zone;
        }

        public String getName() {
            return name;
        }

        public ZoneId getZone() {
            return zone;
        }
    }

}
