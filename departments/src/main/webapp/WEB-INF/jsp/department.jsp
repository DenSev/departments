<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<c:url value="/department/save.html" var="save"/>

<div class="container">
    <div>
        <h3><spring:message code="head.dep.s"/></h3>
    </div>
    <div class="well well-lg">
        <form:form modelAttribute="department" method="POST" action="${save}">
            <form:hidden path="id"/>
            <div class="form-group">
                <form:label path="name"><spring:message code="card.emp.name"/></form:label>
                <form:input class="form-control" path="name"/>
                <form:errors path="name" cssClass="alert alert-danger" />
            </div>
            <input type="submit" class="btn btn-primary" value="save"/>
        </form:form>
    </div>
</div>