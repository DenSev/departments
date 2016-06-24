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

<c:url value="/department/edit.html" var="depEdit"/>
<c:url value="/department/new.html" var="depNew"/>
<c:url value="/department/delete.html" var="depDelete"/>

<div class="container">
    <div class="custom-heading">
        <p class="lead"><spring:message code="jsp.title"/></p>
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

    <ul class="nav nav-tabs">
      <li class="active"><a data-toggle="tab" href="#departments"><spring:message code="head.dep"/></a></li>
      <li><a data-toggle="tab" href="#employees"><spring:message code="head.emp"/></a></li>
    </ul>
    <div class="tab-content">
        <div id="departments" class="tab-pane fade in active">
            <div class="tab-content">
                <div class="tab-new-button"><a href="${depNew}"><span class="glyphicon glyphicon-plus"></span></a></div>
                <c:forEach var="department" items="${departments}">
                    <div class="list-group-item dep-sub-item">
                        <div>
                            <ul>
                                <li class="list-group-item-heading"><h4><spring:message code="card.dep.name"/>${department.name}</h4></li>
                                <li><spring:message code="card.dep.emp"/>${department.count}</li>
                                <li><spring:message code="card.dep.sal"/>${department.salary}</li>
                            </ul>
                        </div>
                        <div>
                            <div id="edit"><a href="${depEdit}?id=${department.id}"><span class="glyphicon glyphicon-pencil"></span></a></div>
                            <div id="delete"><a href="${depDelete}?id=${department.id}"><span class="glyphicon glyphicon-remove"></span></a></div>
                        </div>
                    </div>
                </c:forEach>
            </div>
      </div>
      <div id="employees" class="tab-pane fade">
            <div class="tab-content">
                <div class="tab-new-button"><a href="${empNew}"><span class="glyphicon glyphicon-plus"></span></a></div>
                <c:forEach var="employee" items="${employees}">
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
    </div>
</div>