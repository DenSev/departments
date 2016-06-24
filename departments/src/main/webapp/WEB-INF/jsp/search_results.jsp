<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
  <script type="text/javascript">
      $(function() {
          $('#date1').datetimepicker({
              viewMode: 'years',
              format: 'YYYY-MM-DD'
          });
          $('#date2').datetimepicker({
              viewMode: 'years',
              format: 'YYYY-MM-DD'
          });
      });
  </script>
<c:url value="/employee/search.html" var="search"/>

<c:url value="/employee/edit.html" var="empEdit"/>
<c:url value="/employee/new.html" var="empNew"/>
<c:url value="/employee/delete.html" var="empDelete"/>

<div class="container">
    <div class="custom-heading">
        <form:form modelAttribute="searchForm" action="${search}" method="POST">
            <div class="input-group">
                <form:input id="date1" path="startDate" class="form-control" placeholder="Date 1"/>
                <form:input id="date2" path="endDate" class="form-control" placeholder="Date 2"/>
                <span class="input-group-btn">
                    <button class="btn btn-default" type="submit">
                        <span class="glyphicon glyphicon-search"></span>
                    </button>
                </span>
            </div>
        </form:form>
    </div>
    <div>
        <div class="tab-new-button"><a href="${empNew}"><span class="glyphicon glyphicon-plus"></span></a></div>
            <c:forEach var="employee" items="${searchResults}">
                <div class="list-group-item emp-sub-item">
                    <div>
                        <ul>
                            <li class="list-group-item-heading"><h4><spring:message code="card.emp.name"/>${employee.fullName}</h4></li>
                            <li><spring:message code="card.emp.dob"/>${employee.dob}</li>
                            <li><spring:message code="card.emp.sal"/>${employee.salary}</li>
                            <li><spring:message code="card.emp.dep"/>${employee.department}</li>
                        </ul>
                    </div>
                    <div>
                            <div><a href="${empEdit}?id=${employee.id}"><span class="glyphicon glyphicon-pencil"></span></a></div>
                            <div><a href="${empDelete}?id=${employee.id}"><span class="glyphicon glyphicon-remove"></span></a></div>
                    </div>
                </div>
            </c:forEach>
        </div>
</div>