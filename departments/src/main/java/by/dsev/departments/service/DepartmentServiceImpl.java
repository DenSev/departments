package by.dsev.departments.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import by.dsev.departments.entity.Department;
import by.dsev.departments.entity.view.DepartmentView;
import by.dsev.departments.web.Constants;
import by.dsev.departments.web.ResponseForm;


/**
 * Implementation of {@link DepartmentService} for interactions with REST service 
 * server and {@link Department} and {@link DepartmentView} objects.
 * Uses "http://localhost:8080/departments-rest/" absolute address to access REST service
 * @author DENIS SEVOSTEENKO
 */
@SuppressWarnings("unchecked")
@Service
public class DepartmentServiceImpl implements DepartmentService{
    
    private static final Logger LOG = LoggerFactory.getLogger(DepartmentServiceImpl.class);
    private final String serviceAddress = "http://localhost:8080/departments-rest/";
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public void save(Department department) {
        try{
            restTemplate.postForObject(serviceAddress + "department.json", department, ResponseForm.class);
        } catch(Exception e){
            LOG.error(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void remove(Long id) {
        ResponseForm response  = restTemplate.exchange(serviceAddress + "department.json?id=" + id, HttpMethod.DELETE, null, 
                                                        new ParameterizedTypeReference<ResponseForm>() {}).getBody();
        if(response.getResponseCode() == Constants.RESPONSE_CODE_ERROR_DELETION){
            throw new DataIntegrityViolationException("Cannot delete department");
        }
    }


    @Override
    public Department find(Long id) {
        try{
            ResponseForm<Department> response  = restTemplate .exchange(serviceAddress + "department.json?id=" + id, HttpMethod.GET, null, 
                                                                        new ParameterizedTypeReference<ResponseForm<Department>>() {}).getBody();
            return response.getResponseData();
        } catch(Exception e){
            LOG.error(e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public List<DepartmentView> findAllViews() {
        try{
            ResponseForm<List<DepartmentView>> response  = restTemplate .exchange(serviceAddress + "departments.json", HttpMethod.GET, null, 
                                                                                    new ParameterizedTypeReference<ResponseForm<List<DepartmentView>>>() {}).getBody();
            return response.getResponseData();
        } catch(Exception e){
            LOG.error(e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public List<Department> findAll() {
        try{
            ResponseForm<List<Department>> response  = restTemplate .exchange(serviceAddress + "departments_basic.json", HttpMethod.GET, null, 
                                                                                    new ParameterizedTypeReference<ResponseForm<List<Department>>>() {}).getBody();
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
