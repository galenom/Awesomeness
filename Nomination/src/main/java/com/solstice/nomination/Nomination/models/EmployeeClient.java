package com.solstice.nomination.Nomination.models;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class EmployeeClient {

    @Autowired
    private EurekaClient employeeClient;

    @Autowired
    private RestTemplate restTemplate;

    public void getEmployeeById(Long id) {
        Employee employee = restTemplate.getForObject(getEmployeeUrl(), Employee.class);

        return;
    }

    private String getEmployeeUrl() {
        InstanceInfo instance = employeeClient.getNextServerFromEureka("EMPLOYEESERVICE", false);

        String url = instance.getHomePageUrl() + "/api/employees/1";

        return url;
    }

}
