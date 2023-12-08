package com.example.availableperiods_task.controller;

import com.example.availableperiods_task.service.ReportService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

@WebMvcTest(ReportController.class)
public class ReportControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ReportService reportService;


    @ParameterizedTest
    @ValueSource(strings = {"token1", "token2", "token3"})
    void testGetAvailablePeriod(String token) throws Exception {
        Map<Integer, List<String>> expected = new HashMap<>();
        expected.put(2019, List.of("31 декабря 2019", "01 января 2020", "01 февраля 2020", "01 марта 2020",
                "01 апреля 2020", "01 мая 2020", "01 июня 2020", "01 июля 2020", "01 августа 2020", "01 сентября 2020",
                "01 октября 2020", "01 ноября 2020", "01 декабря 2020"));

        when(reportService.getAvailablePeriodsFromCurrentDate()).thenReturn(expected);

        mockMvc.perform(MockMvcRequestBuilders.get("/available-period")
                        .header("session-token", token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        "{\"2019\":" +
                                "[\"31 декабря 2019\", " +
                                "\"01 января 2020\", " +
                                "\"01 февраля 2020\", " +
                                "\"01 марта 2020\"," +
                                "\"01 апреля 2020\", " +
                                "\"01 мая 2020\", " +
                                "\"01 июня 2020\", " +
                                "\"01 июля 2020\", " +
                                "\"01 августа 2020\", " +
                                "\"01 сентября 2020\",\n" +
                                "\"01 октября 2020\", " +
                                "\"01 ноября 2020\", " +
                                "\"01 декабря 2020\"]}"));
        verify(reportService, times(1)).getAvailablePeriodsFromCurrentDate();

    }


}
