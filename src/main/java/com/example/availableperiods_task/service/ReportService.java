package com.example.availableperiods_task.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {
    private final LocalDateTime DATE_TIME = LocalDateTime.now();

    
    public Map<Integer, String[]> getAvailablePeriods() {
        Map<Integer, String[]> result = new HashMap<>();

        result.put((DATE_TIME.getYear()) - 1, getDateArray((DATE_TIME.getYear()) - 1));
        result.put((DATE_TIME.getYear()) - 2, getDateArray((DATE_TIME.getYear()) - 2));
        result.put((DATE_TIME.getYear()) - 3, getDateArray((DATE_TIME.getYear()) - 3));
        return result;
    }
    
    public static String[] getDateArray(Integer dateTime) {
        String[] dates = new String[13];
        LocalDate currentYear = LocalDate.of(dateTime,Month.DECEMBER,31);
        LocalDate date = currentYear;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");

        for (int i = 0; i < 13; i++) {
            dates[i] = date.format(formatter);
            date = date.plusMonths(1).withDayOfMonth(1);
        }
        dates[0] = currentYear.format(formatter);


        return dates;
    }
}
