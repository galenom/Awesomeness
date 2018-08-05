package com.solstice.nomination.Nomination.models;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EmployeeClient {

    private EurekaClient employeeClient;
    private RestTemplate restTemplate;

    @Autowired
    public EmployeeClient(EurekaClient employeeClient) {
        this.employeeClient = employeeClient;
        this.restTemplate = new RestTemplate();
    }

    public Employee getEmployeeById(Long id) {
        Employee employee = restTemplate.getForObject(getEmployeeUrl(id), Employee.class);

        return employee;
    }

    private String getEmployeeUrl(Long id) {
        InstanceInfo instance = employeeClient.getNextServerFromEureka("EMPLOYEESERVICE", false);

        String url = instance.getHomePageUrl() + "/api/employees/" + id;

        return url;
    }

}
