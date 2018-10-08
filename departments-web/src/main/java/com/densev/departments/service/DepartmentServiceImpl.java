package com.densev.departments.service;

import com.densev.departments.entity.view.DepartmentView;
import com.densev.departments.rest.api.dto.DepartmentDTO;
import com.densev.departments.web.Constants;
import com.densev.departments.web.ResponseForm;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;


/**
 * Implementation of {@link DepartmentService} for interactions with REST service
 * server and {@link Department} and {@link DepartmentView} objects.
 * Uses "http://localhost:8080/departments-rest/" absolute address to access REST service
 *
 * @author DENIS SEVOSTEENKO
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger LOG = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Value("${departments.api.url}")
    private String departmentsApiUrl;
    private RestClient restClient;


    @Override
    public void save(DepartmentDTO department) {
        try {
            DepartmentDTO savedDepartment = restClient.post(departmentsApiUrl, department, new TypeReference<DepartmentDTO>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public void remove(Long id) {
        /*ResponseForm response = restTemplate.exchange(serviceAddress + "dep/remove/" + id, HttpMethod.DELETE, null,
            new ParameterizedTypeReference<ResponseForm>() {
            }).getBody();*/
        if (response.getResponseCode() == Constants.RESPONSE_CODE_ERROR_DELETION) {
            throw new DataIntegrityViolationException("Cannot delete department");
        }
    }


    @Override
    public DepartmentDTO find(Long id) {
        try {
            ResponseForm<Department> response = restTemplate.exchange(serviceAddress + "dep/find/" + id, HttpMethod.GET, null,
                new ParameterizedTypeReference<ResponseForm<Department>>() {
                }).getBody();
            return response.getResponseData();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public List<DepartmentView> findAllViews() {
        try {
            ResponseForm<List<DepartmentView>> response = restTemplate.exchange(serviceAddress + "dep/find/views", HttpMethod.GET, null,
                new ParameterizedTypeReference<ResponseForm<List<DepartmentView>>>() {
                }).getBody();
            return response.getResponseData();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public List<Department> findAll() {
        try {
            ResponseForm<List<Department>> response = restTemplate.exchange(serviceAddress + "dep/find/all", HttpMethod.GET, null,
                new ParameterizedTypeReference<ResponseForm<List<Department>>>() {
                }).getBody();
            return response.getResponseData();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e.getCause());
            return null;
        }
    }


    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


}
