package com.densev.departments.rest.tests;

/*
@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
*/

public class EmployeeRestTest {
/*


        @Mock
        private EmployeeService employeeService;

        @InjectMocks
        private EmployeeRest employeeRest;

        private Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();
        private ObjectMapper mapper = new ObjectMapper();

        */
/**
 * set up mockito
 *//*

        @Before
        public void setUp(){
            MockitoAnnotations.initMocks(this);
            Mockito.reset(employeeService);
            dispatcher.getRegistry().addSingletonResource(employeeRest);

        }

        */
/**
 *
 * @throws Exception
 *//*

        @Test
        public void testFindAllViews() throws Exception{

            EmployeeView emp1 = new EmployeeView();
            emp1.setId(1l);
            emp1.setFullName("test 1");
            emp1.setDateOfBirth("1999-01-01");
            emp1.setSalary(1000);
            emp1.setDepartment("department 1");
            
            EmployeeView emp2 = new EmployeeView();
            emp2.setId(2l);
            emp2.setFullName("test 2");
            emp2.setDateOfBirth("1999-02-02");
            emp2.setSalary(2000);
            emp2.setDepartment("department 2");

            ResponseForm<List<EmployeeView>> form = new ResponseForm<List<EmployeeView>>();
            form.setResponseData(Arrays.asList(emp1, emp2));
            
            when(employeeService.findAllViews()).thenReturn(Arrays.asList(emp1, emp2));

            MockHttpRequest request = MockHttpRequest.get("/emp/find/views");
            MockHttpResponse response = new MockHttpResponse();

            //call method to be tested
            dispatcher.invoke(request, response);

            assertEquals(HttpServletResponse.SC_OK, response.getStatus());
            verify(employeeService, times(1)).findAllViews();
            verifyNoMoreInteractions(employeeService);
            assertEquals(mapper.writeValueAsString(form), response.getContentAsString());
        }

        @Test
        public void testFindAllViewsByDepartmentId() throws Exception{
            ArgumentCaptor<Long> arg = ArgumentCaptor.forClass(Long.class);
            
            Department dep = new Department();
            dep.setId(1l);
            dep.setName("department 1");
            
            EmployeeView emp1 = new EmployeeView();
            emp1.setId(1l);
            emp1.setFullName("test 1");
            emp1.setDateOfBirth("1999-01-01");
            emp1.setSalary(1000);
            emp1.setDepartment(dep.getName());
            
            EmployeeView emp2 = new EmployeeView();
            emp2.setId(2l);
            emp2.setFullName("test 2");
            emp2.setDateOfBirth("1999-02-02");
            emp2.setSalary(2000);
            emp2.setDepartment(dep.getName());
            
            ResponseForm<List<EmployeeView>> form = new ResponseForm<List<EmployeeView>>();
            form.setResponseData(Arrays.asList(emp1, emp2));

            when(employeeService.findAllViewsByDepartmentId(dep.getId())).thenReturn(Arrays.asList(emp1, emp2));

            MockHttpRequest request = MockHttpRequest.get("/emp/find/dep/" + dep.getId());
            MockHttpResponse response = new MockHttpResponse();

            //call method to be tested
            dispatcher.invoke(request, response);

            assertEquals(HttpServletResponse.SC_OK, response.getStatus());
            verify(employeeService, times(1)).findAllViewsByDepartmentId(arg.capture());
            assertEquals(mapper.writeValueAsString(form), response.getContentAsString());
            verifyNoMoreInteractions(employeeService);
            assertEquals(dep.getId(), arg.getValue());

        }

        @Test
        public void testFind() throws Exception{
            ArgumentCaptor<Long> arg = ArgumentCaptor.forClass(Long.class);

            Date dob = new Date();
            Employee emp = new Employee();
            emp.setId(1l);
            emp.setFullName("test 1");
            emp.setDateOfBirth(dob);
            emp.setSalary(1000);
            emp.setDepartmentId(1l);

            ResponseForm<Employee> form = new ResponseForm<Employee>();
            form.setResponseData(emp);

            when(employeeService.find(emp.getId())).thenReturn(emp);

            MockHttpRequest request = MockHttpRequest.get("/emp/find/"+ emp.getId());
            MockHttpResponse response = new MockHttpResponse();
            //call method to be tested
            dispatcher.invoke(request, response);

            assertEquals(HttpServletResponse.SC_OK, response.getStatus());

            verify(employeeService, times(1)).find(arg.capture());
            verifyNoMoreInteractions(employeeService);
            assertEquals(emp.getId(), arg.getValue());
            assertEquals(mapper.writeValueAsString(form), response.getContentAsString());

        }
        
        @Test
        public void testSave() throws Exception{
            ArgumentCaptor<Employee> arg = ArgumentCaptor.forClass(Employee.class);
            ResponseForm form = new ResponseForm();

            Employee emp = new Employee();
            emp.setId(1l);
            emp.setFullName("test 1");
            emp.setDateOfBirth(new Date());
            emp.setDepartmentId(1l);
            emp.setSalary(1000);

            MockHttpRequest request = MockHttpRequest.post("/emp/save");
            MockHttpResponse response = new MockHttpResponse();
            request.content(mapper.writeValueAsString(emp).getBytes());
            request.contentType(MediaType.APPLICATION_JSON);
            //call method to be tested
            dispatcher.invoke(request, response);

            verify(employeeService, times(1)).save(arg.capture());
            assertEquals(emp, arg.getValue());
            verifyNoMoreInteractions(employeeService);
            assertEquals(HttpServletResponse.SC_OK, response.getStatus());
            assertEquals(mapper.writeValueAsString(form), response.getContentAsString());

        }

        @Test
        public void testDelete() throws Exception{
            ArgumentCaptor<Long> arg = ArgumentCaptor.forClass(Long.class);
            Long id = 1l;
            ResponseForm form = new ResponseForm();

            MockHttpRequest request = MockHttpRequest.delete("/emp/remove/" + id);
            MockHttpResponse response = new MockHttpResponse();
            //call method to be tested
            dispatcher.invoke(request, response);

            assertEquals(HttpServletResponse.SC_OK, response.getStatus());
            verify(employeeService, times(1)).remove(arg.capture());
            verifyNoMoreInteractions(employeeService);
            assertEquals(id, arg.getValue());
            assertEquals(mapper.writeValueAsString(form), response.getContentAsString());

        }

*/
}
