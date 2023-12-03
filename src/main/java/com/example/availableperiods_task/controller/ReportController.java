package com.example.availableperiods_task.controller;

import com.example.availableperiods_task.service.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ReportController {
    ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/available-period")
    private Map<Integer, String[]> get() {
        return reportService.getAvailablePeriods();
    }
}
