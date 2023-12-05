package com.example.availableperiods_task.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    public Map<Integer, List<String>> getAvailablePeriods1(LocalDateTime calcDate) {
        Map<Integer, List<String>> result = new HashMap<>();

        if (isEqualToDecember31(calcDate)) {
            calcDate = calcDate.plusYears(1);
        }

        for (int i = 1; i <= 3; i++) {
            int yearNo = calcDate.getYear() - i;
            result.put(yearNo, getDateArray(yearNo, calcDate));
        }

        return result;
    }

    private List<String> getDateArray(Integer dateTime, LocalDateTime localDateTime) {
        if (isEqualToDecember31(localDateTime)){
            localDateTime = localDateTime.minusYears(1);
        }
        List<String> dates = new ArrayList<>();
        LocalDate currentYear = LocalDate.of(dateTime, Month.DECEMBER, 31);
        LocalDate date = currentYear;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");

        for (int i = 0; i < 13; i++) {
            if (date.isAfter(localDateTime.toLocalDate())) {
                break;
            }
            dates.add(date.format(formatter));
            date = date.plusMonths(1).withDayOfMonth(1);

        }
        dates.set(0, currentYear.format(formatter));


        return dates;
    }


    private boolean isEqualToDecember31(LocalDateTime date) {
        int day = date.getDayOfMonth();
        int month = date.getMonthValue();

        return day == 31 && month == 12;
    }
}
