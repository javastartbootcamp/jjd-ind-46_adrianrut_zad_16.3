package pl.javastart.task;

import java.time.LocalDateTime;
import java.util.*;

public class TimeCalculator {

    public static final String YEAR = "y";
    public static final String MONTH = "M";
    public static final String DAY = "d";
    public static final String HOUR = "h";
    public static final String MINUTE = "m";
    public static final String SECOND = "s";
    public static final String PLUS = "+";
    public static final String MINUS = "-";

    public static List<DataUnitFromUser> createDateList(String dataInput) {
        List<DataUnitFromUser> timeUnits = new ArrayList<>();
        String[] split = dataInput.split("");
        String sign;
        int multiply;
        
        String timeUnit;
        for (int i = 1; i < split.length; i++) {
            sign = split[i];
            i++;
            multiply = Integer.parseInt(split[i]);
            i++;
            timeUnit = split[i];
            int secondMultiply = 0;
            if (Character.isDigit(timeUnit.charAt(0))) {
                secondMultiply = Integer.parseInt(split[i]);
                i++;
            }
            timeUnit = split[i];
            timeUnits.add(new DataUnitFromUser(sign, multiply, secondMultiply, timeUnit));
        }
        System.out.println(timeUnits);
        return timeUnits;
    }

    public static LocalDateTime createTimeResult(String dataInput) {
        List<DataUnitFromUser> dateList = createDateList(dataInput);
        LocalDateTime localDateTime = null;
        for (DataUnitFromUser unit : dateList) {
            if (Objects.equals(unit.getTimeUnit(), YEAR)) {
                if (Objects.equals(unit.getSign(), PLUS)) {
                    localDateTime = LocalDateTime.now().plusYears(unit.getMultiplyNumber());
                } else {
                    localDateTime = LocalDateTime.now().minusYears(unit.getMultiplyNumber());
                }
            } else if (Objects.equals(unit.getTimeUnit(), MONTH)) {
                if (Objects.equals(unit.getSign(), PLUS)) {
                    localDateTime = LocalDateTime.now().plusMonths(unit.getMultiplyNumber());
                } else {
                    localDateTime = LocalDateTime.now().minusMonths(unit.getMultiplyNumber());
                }
            } else if (Objects.equals(unit.getTimeUnit(), DAY)) {
                if (Objects.equals(unit.getSign(), PLUS)) {

                    localDateTime = LocalDateTime.now().plusDays(unit.getMultiplyNumber());
                } else {
                    localDateTime = LocalDateTime.now().minusDays(unit.getMultiplyNumber());
                }
            } else if (Objects.equals(unit.getTimeUnit(), HOUR)) {
                if (Objects.equals(unit.getSign(), PLUS)) {
                    localDateTime = LocalDateTime.now().plusHours(unit.getMultiplyNumber());
                } else {
                    localDateTime = LocalDateTime.now().minusHours(unit.getMultiplyNumber());
                }
            } else if (Objects.equals(unit.getTimeUnit(), MINUTE)) {
                if (Objects.equals(unit.getSign(), PLUS)) {
                    localDateTime = LocalDateTime.now().plusMinutes(unit.getMultiplyNumber());
                } else {
                    localDateTime = LocalDateTime.now().minusHours(unit.getMultiplyNumber());
                }
            } else if (Objects.equals(unit.getTimeUnit(), SECOND)) {
                if (Objects.equals(unit.getSign(), PLUS)) {
                    localDateTime = LocalDateTime.now().plusSeconds(unit.getMultiplyNumber());
                } else {
                    localDateTime = LocalDateTime.now().minusSeconds(unit.getMultiplyNumber());
                }
            }
        }
        return localDateTime;
    }

    static class DataUnitFromUser {
        String sign;
        int multiplyNumber;
        int secondMultiply;
        String timeUnit;

        public DataUnitFromUser(String sign, int multiplyNumber, int secondMultiply, String timeUnit) {
            this.sign = sign;
            this.multiplyNumber = multiplyNumber;
            this.secondMultiply = secondMultiply;
            this.timeUnit = timeUnit;
        }

        public String getSign() {
            return sign;
        }

        public int getMultiplyNumber() {
            return multiplyNumber;
        }

        public int getSecondMultiply() {
            return secondMultiply;
        }

        public String getTimeUnit() {
            return timeUnit;
        }

        @Override
        public String toString() {
            return sign + multiplyNumber + secondMultiply + timeUnit;
        }
    }

}
