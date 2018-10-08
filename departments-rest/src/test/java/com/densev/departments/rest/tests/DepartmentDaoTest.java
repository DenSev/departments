package com.densev.departments.rest.tests;

/*@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)*/
public class DepartmentDaoTest {
/*

    private static final Logger LOG = LoggerFactory.getLogger(DepartmentDaoTest.class);
    @Autowired
    private DepartmentDao departmentDao;

    @Test
    @Transactional
    @Rollback(true)
    public void testMethodChain(){
        Department d1 = new Department();
        d1.setName("test");
        LOG.debug("Calling departmentDao.create(d1)");
        departmentDao.create(d1);
        
        LOG.debug("Call departmentDao.read(d1.getId) and assert that result equals d1"); 
        Department d2 = departmentDao.read(d1.getId());
        assertEquals(d1, d2);
        
        LOG.debug("Call departmentDao.update(d1) then .read() and assert that result equals d1");
        d1.setName("new name");
        departmentDao.update(d1);
        Department d3 = departmentDao.read(d1.getId());
        assertEquals(d1, d3);
        
        LOG.debug("Call departmentDao.readAll and assert that result contains d1");
        List<Department> deps = departmentDao.readAll();
        assertTrue(deps.contains(d1));
        
        LOG.debug("Create DepartmentView that should represend what d1 view wrapper would be and assert that departmentDao.readAllViews contains it");
        DepartmentView dv = new DepartmentView();
        dv.setId(d1.getId());
        dv.setName(d1.getName());
        dv.setSalary(null);
        dv.setCount(0);
        List<DepartmentView> views = departmentDao.readAllViews();
        assertTrue(views.contains(dv));
        
        LOG.debug("Finally, delete d1 and assert that departmentDao.readAll result does not contain it");
        departmentDao.delete(d1.getId());
        deps = departmentDao.readAll();
        assertTrue(!deps.contains(d1));
    }
*/

}
