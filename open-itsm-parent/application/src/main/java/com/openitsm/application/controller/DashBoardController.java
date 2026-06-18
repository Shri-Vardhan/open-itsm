package com.openitsm.application.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashBoardController {

    private static final Logger logger = LogManager.getLogger(DashBoardController.class);

    @GetMapping("/dashboard")
    public String dashboard() {

        logger.info("Dashboard accessed");

        return "dashboard/dashboard";
    }
}