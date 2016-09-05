package de.scout24.financing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.scout24.financing.resource.DashboardResource;
import de.scout24.financing.resource.QuestionDashboardResource;
import de.scout24.financing.service.impl.DashboardService;

@RestController
public class DashboardController {
    
    @Autowired
    protected DashboardService dashboardService;

    @RequestMapping(value = "/dashboard/admin", method = RequestMethod.GET)
    public DashboardResource getDashboard() {
        return dashboardService.getAdminDashboard();
    }

    @RequestMapping(value = "/dashboard/question", method = RequestMethod.GET)
    public QuestionDashboardResource getQuestionDashboard() {
        return dashboardService.getQuestionDashboard();
    }
}
