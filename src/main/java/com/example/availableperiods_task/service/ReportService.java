package com.example.availableperiods_task.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ReportService {
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMMM yyyy");

    private Map<Integer, List<String>> getAvailablePeriods(LocalDateTime calcDate) {
        int numberOfReportingYear = 3;

        if (isEqualToDecember31(calcDate)) {
            calcDate = calcDate.plusYears(1);
        }

        LocalDateTime finalCalcDate = calcDate;


        return IntStream.rangeClosed(1, numberOfReportingYear)
                .boxed()
                .collect(Collectors.toMap(
                        i -> finalCalcDate.getYear() - i,
                        i -> getDateArray(finalCalcDate.getYear() - i, finalCalcDate)));
    }
    public Map<Integer, List<String>> getAvailablePeriodsFromCurrentDate() {
        LocalDateTime currentTime = LocalDateTime.now();
        return getAvailablePeriods(currentTime);
    }

    private List<String> getDateArray(Integer dateTime, LocalDateTime localDateTime) {
        int numberOfReportingDates = 13;

        if (isEqualToDecember31(localDateTime)) {
            localDateTime = localDateTime.minusYears(1);
        }

        List<String> dates = new ArrayList<>(numberOfReportingDates);
        LocalDate currentYear = LocalDate.of(dateTime, Month.DECEMBER, 31);
        LocalDate date = currentYear;


        for (int i = 0; i < numberOfReportingDates; i++) {
            if (date.isAfter(localDateTime.toLocalDate())) {
                break;
            }
            dates.add(date.format(FORMATTER));
            date = date.plusMonths(1).withDayOfMonth(1);

        }
        dates.set(0, currentYear.format(FORMATTER));


        return dates;
    }


    private boolean isEqualToDecember31(LocalDateTime date) {
        int day = date.getDayOfMonth();
        int month = date.getMonthValue();

        return day == 31 && month == 12;
    }
}
