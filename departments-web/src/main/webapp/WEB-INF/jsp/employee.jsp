<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:url value="/employee/save.html" var="save"/>

  <script type="text/javascript">
      $(function() {
          $('#dob').datetimepicker({
              viewMode: 'years',
              format: 'YYYY-MM-DD'
          });
      });
  </script>
<div class="container">
    <div>
        <h3><spring:message code="head.emp.s"/></h3>
    </div>
    <div class="well well-lg">
        <form:form modelAttribute="employee" method="POST" action="${save}">
            <form:hidden path="id"/>
            <div class="form-group">
                <form:label path="fullName"><spring:message code="card.emp.name"/></form:label>
                <form:input class="form-control" path="fullName"/>
                <form:errors path="fullName" cssClass="alert alert-danger" />
            </div>
            <form:label path="dob"><spring:message code="card.emp.dob"/></form:label>
            <div class='form-group input-group date' id='dob'>
                
                <form:input type='text' class="form-control" path="dob"/>
                <form:errors path="dob" cssClass="alert alert-danger" />
                <span class="input-group-addon">
                <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
            <div class="form-group">
                <form:label path="salary"><spring:message code="card.emp.sal"/></form:label>
                <form:input class="form-control" path="salary"/>
                <form:errors path="salary" cssClass="alert alert-danger" />
            </div>
            <div class="form-group">
                <form:label path="departmentId"><spring:message code="card.emp.dep"/></form:label>
                <form:select class="form-control" path="departmentId">
                    <form:options items="${departments}" itemLabel="name" itemValue="id"/>
                </form:select>
                <form:errors path="departmentId" cssClass="alert alert-danger" />
            </div>
            <input type="submit" class="btn btn-primary" value="<spring:message code="btn.save"/>"/>
        </form:form>
    </div>

</div>