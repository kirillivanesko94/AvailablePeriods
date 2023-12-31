package com.example.availableperiods_task.controller;

import com.example.availableperiods_task.service.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/available-period")
    private Map<Integer, List<String>> get(@RequestHeader(value = "session-token") String token) {
       return reportService.getAvailablePeriodsFromCurrentDate();
    }
}
