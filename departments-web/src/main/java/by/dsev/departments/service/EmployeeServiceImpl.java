package com.densev.departments.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.densev.departments.entity.Employee;
import com.densev.departments.entity.SearchForm;
import com.densev.departments.entity.view.EmployeeView;
import com.densev.departments.web.Constants;
import com.densev.departments.web.ResponseForm;

@SuppressWarnings("unchecked")
@Service
public class EmployeeServiceImpl implements EmployeeService{

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private final String serviceAddress = "http://localhost:8080/departments-rest/";
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public void save(Employee employee) {
        try{
            restTemplate.postForObject( serviceAddress + "emp/save", employee, ResponseForm.class);
        } catch(Exception e){
            LOG.error(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void remove(Long id) {
        ResponseForm response  = restTemplate .exchange(serviceAddress + "emp/remove/" + id, HttpMethod.DELETE, null, 
                                                            new ParameterizedTypeReference<ResponseForm>() {}).getBody();
        if(response.getResponseCode() == Constants.RESPONSE_CODE_ERROR_DELETION){
            throw new DataIntegrityViolationException("Cannot delete employee");
        }
    }

    @Override
    public Employee find(Long id) {
        try{
            ResponseForm<Employee> response  = restTemplate .exchange(serviceAddress + "emp/find/" + id, HttpMethod.GET, null, 
                                                                        new ParameterizedTypeReference<ResponseForm<Employee>>() {}).getBody();
            return response.getResponseData();
        } catch(Exception e){
            LOG.error(e.getMessage(), e.getCause());
            return null;
        }
    }


    @Override
    public List<EmployeeView> findAllViews() {
        try{
            ResponseForm<List<EmployeeView>> response = restTemplate .exchange(serviceAddress + "emp/find/views", HttpMethod.GET, null, 
                                                                        new ParameterizedTypeReference<ResponseForm<List<EmployeeView>>>() {}).getBody();

            return response.getResponseData();
        } catch(Exception e){
            LOG.error(e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public List<EmployeeView> searchByDate(SearchForm searchForm) {
        try{
            HttpEntity<SearchForm> request = new HttpEntity<>(searchForm);
            ResponseForm<List<EmployeeView>> response = restTemplate .exchange(serviceAddress + "emp/search", HttpMethod.POST, request, 
                                                                        new ParameterizedTypeReference<ResponseForm<List<EmployeeView>>>() {}).getBody();
            return response.getResponseData();
        } catch(Exception e){
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
