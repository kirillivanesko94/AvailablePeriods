package com.example.availableperiods_task.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ReportServiceTest {
    private final ReportService reportService = new ReportService();
    static Map<Integer, List<String>> EXPECTED_1 = new HashMap<>();
    static Map<Integer, List<String>> EXPECTED_2 = new HashMap<>();
    static Map<Integer, List<String>> EXPECTED_3 = new HashMap<>();


    static {
        EXPECTED_1.put(2019, List.of("31 декабря 2019", "01 января 2020", "01 февраля 2020", "01 марта 2020",
                "01 апреля 2020", "01 мая 2020", "01 июня 2020", "01 июля 2020", "01 августа 2020", "01 сентября 2020",
                "01 октября 2020", "01 ноября 2020", "01 декабря 2020"));
        EXPECTED_1.put(2020, List.of("31 декабря 2020", "01 января 2021", "01 февраля 2021", "01 марта 2021",
                "01 апреля 2021", "01 мая 2021", "01 июня 2021", "01 июля 2021", "01 августа 2021", "01 сентября 2021",
                "01 октября 2021", "01 ноября 2021", "01 декабря 2021"));
        EXPECTED_1.put(2021, List.of("31 декабря 2021", "01 января 2022"));


        EXPECTED_2.put(2020, List.of("31 декабря 2020", "01 января 2021", "01 февраля 2021", "01 марта 2021",
                "01 апреля 2021", "01 мая 2021", "01 июня 2021", "01 июля 2021", "01 августа 2021", "01 сентября 2021",
                "01 октября 2021", "01 ноября 2021", "01 декабря 2021"));
        EXPECTED_2.put(2021, List.of("31 декабря 2021", "01 января 2022", "01 февраля 2022", "01 марта 2022",
                "01 апреля 2022", "01 мая 2022", "01 июня 2022", "01 июля 2022", "01 августа 2022", "01 сентября 2022",
                "01 октября 2022", "01 ноября 2022", "01 декабря 2022"));
        EXPECTED_2.put(2022, List.of("31 декабря 2022", "01 января 2023", "01 февраля 2023", "01 марта 2023",
                "01 апреля 2023", "01 мая 2023", "01 июня 2023", "01 июля 2023", "01 августа 2023", "01 сентября 2023",
                "01 октября 2023"));


        EXPECTED_3.put(2021, List.of("31 декабря 2021", "01 января 2022", "01 февраля 2022", "01 марта 2022",
                "01 апреля 2022", "01 мая 2022", "01 июня 2022", "01 июля 2022", "01 августа 2022", "01 сентября 2022",
                "01 октября 2022", "01 ноября 2022", "01 декабря 2022"));
        EXPECTED_3.put(2022, List.of("31 декабря 2022", "01 января 2023", "01 февраля 2023", "01 марта 2023",
                "01 апреля 2023", "01 мая 2023", "01 июня 2023", "01 июля 2023", "01 августа 2023", "01 сентября 2023",
                "01 октября 2023", "01 ноября 2023", "01 декабря 2023"));
        EXPECTED_3.put(2023, List.of("31 декабря 2023"));
    }


    @ParameterizedTest()
    @MethodSource("getDates")
    void getAvailablePeriodTest(LocalDateTime date, Map<Integer, List<String>> expected) {
        try (MockedStatic<LocalDateTime> mockedStatic = Mockito.mockStatic(LocalDateTime.class)) {
            mockedStatic.when(LocalDateTime::now).thenReturn(date);
            Map<Integer, List<String>> actual = reportService.getAvailablePeriodsFromCurrentDate();

            assertEquals(expected, actual);
        }
    }

    private static Stream<Arguments> getDates() {
        return Stream.of(
                Arguments.of(LocalDateTime.of(2022, 1, 1, 0, 0), EXPECTED_1),
                Arguments.of(LocalDateTime.of(2023, 10, 27, 0, 0), EXPECTED_2),
                Arguments.of(LocalDateTime.of(2023, 12, 31, 0, 0), EXPECTED_3)
        );
    }
}
