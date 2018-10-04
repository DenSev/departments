$(function(){
    //init datepicker
    $('#dobPicker').datetimepicker({
        viewMode: 'years',
        format: 'YYYY-MM-DD'
    });
    $('#date1').datetimepicker({
        viewMode: 'years',
        format: 'YYYY-MM-DD'
    });
    $('#date2').datetimepicker({
        viewMode: 'years',
        format: 'YYYY-MM-DD'
    });
    
    //load data
    loadDepartments();
    loadDepartmentOptions();
    loadEmployees(1);

    //search logic
    $('body').on('click', '#search', function(){
        var searchQuery = {
                startDate : $('#date1').val(),
                endDate : $('#date2').val()
        };
        search(searchQuery);
    })
    
    //switches active and loads employees
    $('body').on('click', 'div .dep-sub-item', function(){
        $('#dep-list .active').removeClass("active");
        $(this).addClass("active");
        var id = $(this).attr('id');
        loadEmployees(id);
    })

    //load data for edit modals
    $('body').on('click', '#emp-list #edit', function(){
        var id = $(this).parent().parent().attr('id');
        loadEmployee(id);
    })
    $('body').on('click', '#dep-list #edit', function(event){
        var id = $(this).parent().parent().attr('id');
        loadDepartment(id);
    })

    //load data for edit modals
    $('body').on('click', '#emp-list #delete', function(){
        var id = $(this).parent().parent().attr('id');
        console.log(id)
        $('#empDeleteModal .modal-content').attr('id', id);
    })
    $('body').on('click', '#dep-list #delete', function(event){
        var id = $(this).parent().parent().attr('id');
        $('#depDeleteModal .modal-content').attr('id', id);
    })


    //delete modal buttons
    $('body').on('click', '#empDeleteButton', function(){
        var id = $('#empDeleteModal .modal-content').attr('id');
        deleteEmployee(id);
    })
        $('body').on('click', '#depDeleteButton', function(){
        var id = $('#depDeleteModal .modal-content').attr('id');
        deleteDepartment(id);
    })

    
    
    //modal buttons
    $('body').on('click', '#empModalClose', cleanEmployeeInputs)
    $('body').on('click', '#empModalSave', function(){
        var employee = formEmployeeJSON();
        if(validateEmployee(employee)){
            saveEmployee(employee);
        }
    })
    $('body').on('click', '#depModalClose', cleanDepartmentInputs)
    $('body').on('click', '#depModalSave', function(){
        var department = formDepartmentJSON();
        if(validateDepartment(department)){
            saveDepartment(department);
        }
    })

});
//------------------------------------------------------------------------load single functions
function loadDepartment(id){
    console.log("load department called")
    $.ajax({
        contentType : "application/json",
        dataType: 'json',
        type: "GET",
        url: '/departments-rest/dep/find/' + id,
        complete : function (data){
            console.log(data)
            var department = processServerResponse(data.responseJSON, true);
            $('.modal-body').attr('id',department.id)
            $('#depName').val(department.name);
        },
        error: function(response, status, error) {
            toastr.error(error)
        }
    })
}
function loadEmployee(id){
    $.ajax({
        contentType : "application/json",
        dataType: 'json',
        type: "GET",
        url: '/departments-rest/emp/find/' + id,
        complete : function (data){
            var employee = processServerResponse(data.responseJSON, true);
            var dob = moment(employee.dob);
            $('#empModal .modal-body').attr('id',employee.id)
            $('#empFullName').val(employee.fullName);
            $('#empDob').val(dob.format("YYYY-MM-DD"));
            $('#empSalary').val(employee.salary);
            $('#empDepartmentId').val(employee.departmentId);
        },
        error: function(response, status, error) {
            toastr.error(error)
        }
    })
}
//------------------------------------------------------------------------form json functions
function formEmployeeJSON(){
    var employee = {
        id : $('#empModal .modal-body').attr('id'),
        fullName : $('#empFullName').val(),
        dob : $('#empDob').val(),
        salary : $('#empSalary').val(),
        departmentId : $('#empDepartmentId').val()
    };
    return employee;
}
function formDepartmentJSON(){
    var department = {
        id : $('#depModal .modal-body').attr('id'),
        name : $('#depName').val(),
    };
    return department;
}
//------------------------------------------------------------------------clean inputs functions
function cleanEmployeeInputs(){
    $('#empModal .modal-body').attr('id','0');
    $('#empFullName').val('');
    $('#empDob').val('');
    $('#empSalary').val('');
    $('#empDepartmentId').val('');
}
function cleanDepartmentInputs(){
    $('#depModal .modal-body').attr('id','0');
    $('#depName').val('');
}
//------------------------------------------------------------------------validation functions
function validateEmployee(employee){
    var errorsArr = [];
    if(employee.fullName == null || employee.fullName == ''){
        $('#empNameAlert').removeClass("hidden");
        $('#empNameAlert').text("Enter a valid name");
        errorsArr.push("error");
    } else {
        $('#empNameAlert').addClass("hidden");
    }
    if(employee.salary == null || employee.salary == '' || employee.salary == 0){
        $('#empSalAlert').removeClass("hidden");
        $('#empSalAlert').text("Enter a correct salary. Salary can't be 0.");
        errorsArr.push("error");
    } else {
        $('#empSalAlert').addClass("hidden");
    }
    var mDate = moment(employee.dob);
    if(employee.dob == null || employee.dob == '' || !mDate.isValid()){
        $('#empDobAlert').removeClass("hidden");
        $('#empDobAlert').text("Enter a valid date of birth");
        errorsArr.push("error");
    } else {
        $('#empDobAlert').addClass("hidden");
    }
    if(employee.departmentId == null || employee.departmentId == '' ){
        $('#empDepAlert').removeClass("hidden");
        $('#empDepAlert').text("Select a department");
        errorsArr.push("error");
    } else {
        $('#empDepAlert').addClass("hidden");
    }
    if(errorsArr.indexOf("error") != -1){
        return false
    }
    return true;
}
function validateDepartment(department){
    var errorsArr = [];
    if(department.name == null || department.name == ''){
        $('#depNameAlert').removeClass("hidden");
        $('#depNameAlert').text("Enter a valid name");
        errorsArr.push("error");
    } else {
        $('#depNameAlert').addClass("hidden");
    }
    if(errorsArr.indexOf("error") != -1){
        return false
    }
    return true;
}
//------------------------------------------------------------------------delete functions
function deleteDepartment(id){
    $.ajax({
        contentType : "application/json",
        dataType: "json",
        type : "DELETE",
        url : "/departments-rest/dep/remove/" + id,
        complete : function(data){
            processServerResponse(data.responseJSON, false);
            loadDepartments(1);
            loadEmployees(1);
            $('#depDeleteModal').modal('hide')
        }, 
        error : function(response, status, error){
            toastr.error(error);
        }
    })
}
function deleteEmployee(id){
    $.ajax({
        contentType : "application/json",
        dataType: "json",
        type : "DELETE",
        url : "/departments-rest/emp/remove/" + id,
        complete  : function(data){
            processServerResponse(data.responseJSON, false);
            loadDepartments(1);
            loadEmployees(1);
            $('#empDeleteModal').modal('hide')
        }, 
        error : function(response, status, error){
            toastr.error(error);
        }
    })
}
//------------------------------------------------------------------------save functions
function saveEmployee(employee){
    $.ajax({
        contentType : "application/json",
        dataType : 'json',
        type : "POST",
        url : '/departments-rest/emp/save',
        data : JSON.stringify(employee),
        complete : function(data){
            processServerResponse(data.responseJSON, false);
            cleanEmployeeInputs();
            loadDepartments(employee.departmentId);
            loadEmployees(employee.departmentId);
            $('#empModal').modal('hide')
        }, 
        error : function(response, status, error){
            toastr.error(error);
        }
    })
}
function saveDepartment(department){
    $.ajax({
        contentType : "application/json",
        dataType : 'json',
        type : "POST",
        url : '/departments-rest/dep/save',
        data : JSON.stringify(department),
        complete : function(data){
            processServerResponse(data.responseJSON, false);
            cleanDepartmentInputs();
            loadDepartments(department.id);
            loadEmployees(department.id);
            $('#depModal').modal('hide')
        }, 
        error : function(response, status, error){
            toastr.error(error);
        }
    })
}
//------------------------------------------------------------------------list build functions
function buildEmployeeList(employees){
    var empCardTemplate = $('#emp-card-template').children()[0];
    empCardTemplate = $(empCardTemplate).clone();

    var empList = $('#emp-list');
    for(var i = 0; i < employees.length; i++){
        var template = $(empCardTemplate).clone();
        $(template).attr('id', employees[i].id);
        $(template).find('#name').append(" " + employees[i].fullName);
        $(template).find('#dob').append(" " + employees[i].dob);
        $(template).find('#salary').append(" " + employees[i].salary);
        $(template).find('#department').append(" " + employees[i].department);
        empList.append(template);
    }
}

//------------------------------------------------------------------------load lists functions




function loadEmployees(id){
    var employees = [];
    var empList = $('#emp-list');
    $(empList).html('')

    $.ajax({
        contentType : "application/json",
        dataType: 'json',
        type: "GET",
        url: '/departments-rest/emp/find/dep/' + id,
        complete : function (data){
            employees = processServerResponse(data.responseJSON, true);
            //populate employee list
            buildEmployeeList(employees);
        },
        error: function(response, status, error) {
            toastr.error(error)
        }
    })
}

function loadDepartmentOptions(){
    var departments = [];
    var selectInput = $('#empDepartmentId');
    
    $.ajax({
        contentType: "application/json",
        dataType : "json",
        type: "GET",
        url : '/departments-rest/dep/find/all',
        complete : function (data){
            departments = processServerResponse(data.responseJSON, true);
            
            for(var i = 0; i < departments.length; i++){
                selectInput.append("<option value='" + departments[i].id + "'>" + departments[i].name + "</option>");
            }
        },
        error : function (response, status, error) { 
            toastr.error(error)
        }
    })
}

function loadDepartments(activeId){
    var departments = [];
    var depCardTemplate = $('#dep-card-template').children()[0];
    depCardTemplate = $(depCardTemplate).clone();
    var depList = $('#dep-list');
    $(depList).html('')
    
    $.ajax({
        contentType : "application/json",
        dataType: 'json',
        type: "GET",
        url: '/departments-rest/dep/find/views',
        complete : function (data){
            departments = processServerResponse(data.responseJSON, true);
            //populate department list
            for(var i = 0; i < departments.length; i++){
                var template = $(depCardTemplate).clone();
                $(template).attr('id', departments[i].id);
                $(template).find('#name').append(" " + departments[i].name);
                $(template).find('#count').append(" " + departments[i].count);
                $(template).find('#salary').append(" " + departments[i].salary);
                depList.append(template);
            }
            if(activeId != undefined){
                $('#dep-list #' + activeId).addClass("active");
            } else {
                $('#dep-list #1').addClass("active");
            }
        },
        error: function(response, status, error) { 
            toastr.error(error)
        }
    })
}
//------------------------------------------------------------------------search function
function search(searchQuery){
    $.ajax({
        contentType : "application/json",
        dataType: 'json',
        type: "POST",
        url: '/departments-rest/emp/search',
        data: JSON.stringify(searchQuery),
        complete : function (data){
            employees = processServerResponse(data.responseJSON, false);
            var empList = $('#emp-list');
            $(empList).html('')
            toastr.info(employees.length + " entries found.")
            buildEmployeeList(employees);
        },
        error: function(response, status, error) { 
            toastr.error(error)
        }
    })

}
