<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/js" var="jsPath" scope="request" />
<script src="${jsPath}/script.js"></script>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="row">
    <div class="col-sm-12 custom-heading">
        <p class="lead"><spring:message code="jq.title"/></p>
        <div class="input-group">
            <input type="text" id="date1" class="form-control" placeholder="Date 1">
            <input type="text" id="date2" class="form-control" placeholder="Date 2">
            <span class="input-group-btn">
                <button class="btn btn-default" type="button" id="search">
                    <span class="glyphicon glyphicon-search"></span>
                </button>
            </span>
        </div>
    </div>
    <div class="col-sm-6">
        <div class="dep-container">
            <div class="dep-item">
                <div id="new" class="dep-new"><span class="glyphicon glyphicon-plus"  data-toggle="modal" data-target="#depModal"></span></div>
            </div>
            <div class="dep-item list-group" id="dep-list">
                <!-- gets populated by department cards -->
            </div>
        </div>
    </div>

    <div class="col-sm-6">
        <div class="emp-container">
            <div class="emp-item">
                <div id="new" class="emp-new"><span class="glyphicon glyphicon-plus"  data-toggle="modal" data-target="#empModal"></span></div>
            </div>
            <div class="emp-item list-group" id="emp-list">
                <!-- gets populated by employee cards -->
            </div>
        </div>
    </div>
</div>

<!-- temlpates -->
<div style="display:none" id="dep-card-template">
    <div class="list-group-item dep-sub-item">
        <div>
            <ul>
                <li class="list-group-item-heading"><h4 id="name"><spring:message code="card.dep.name"/></h4></li>
                <li id="count"><spring:message code="card.dep.emp"/></li>
                <li id="salary"><spring:message code="card.dep.sal"/></li>
            </ul>
        </div>
        <div>
            <div id="edit" data-toggle="modal" data-target="#depModal"><span class="glyphicon glyphicon-pencil"></span></div>
            <div id="delete" data-toggle="modal" data-target="#depDeleteModal"><span class="glyphicon glyphicon-remove"></span></div>
        </div>
    </div>
</div>
<div style="display:none" id="emp-card-template">
    <div class="list-group-item emp-sub-item">
        <div>
            <ul>
                <li class="list-group-item-heading"><h4 id="name"><spring:message code="card.emp.name"/></h4></li>
                <li id="dob"><spring:message code="card.emp.dob"/></li>
                <li id="salary"><spring:message code="card.emp.sal"/></li>
                <li id="department"><spring:message code="card.emp.dep"/></li>
            </ul>
        </div>
        <div>
            <div id="edit" data-toggle="modal" data-target="#empModal"><span class="glyphicon glyphicon-pencil"></span></div>
            <div id="delete" data-toggle="modal" data-target="#empDeleteModal"><span class="glyphicon glyphicon-remove"></span></div>
        </div>
    </div>
</div>

<!-- edit dialogue modals -->
<div class="modal fade" id="empModal" tabindex="-1" role="dialog" aria-labelledby="empModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="empModalLabel"><spring:message code="head.emp.s"/></h4>
            </div>
            <div class="modal-body" id="null">
                <div>
                    <label class="control-label"><spring:message code="card.emp.name"/></label>
                    <input id="empFullName" type="text" class="form-control"/>
                    
                </div>
                <div class="alert alert-danger hidden" id="empNameAlert"></div>
                <label class="control-label"><spring:message code="card.emp.dob"/></label>
                <div class='input-group date' id='dobPicker'>
                    <input id="empDob" type='text' class="form-control" />
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
                <div class="alert alert-danger hidden" id="empDobAlert"></div>
                <div>
                    <label class="control-label"><spring:message code="card.emp.sal"/></label>
                    <input id="empSalary" type="text" class="form-control"/>
                </div>
                <div class="alert alert-danger hidden" id="empSalAlert"></div>
                <div>
                    <label class="control-label"><spring:message code="card.emp.dep"/></label>
                    <select id="empDepartmentId" class="form-control">
                    </select>
                </div>
                <div class="alert alert-danger hidden" id="empDepAlert"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="empModalClose"><spring:message code="btn.cancel"/></button>
                <button type="button" class="btn btn-primary" id="empModalSave"><spring:message code="btn.save"/></button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="depModal" tabindex="-1" role="dialog" aria-labelledby="depModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="depModalLabel"><spring:message code="head.dep.s"/></h4>
            </div>
            <div class="modal-body" id="null">
                <div>
                    <label class="control-label"><spring:message code="card.dep.name"/></label>
                    <input id="depName" type="text" class="form-control"/>
                </div>
                <div class="alert alert-danger hidden" id="depNameAlert"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="depModalClose"><spring:message code="btn.cancel"/></button>
                <button type="button" class="btn btn-primary" id="depModalSave"><spring:message code="btn.save"/></button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="depDeleteModal" tabindex="-1" role="dialog" aria-labelledby="depDeleteLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content" id="null">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="depDeleteLabel"><spring:message code="confirm.del.dep"/></h4>
            </div>
            <div class="modal-footer delete-modal-buttons">
                <button type="button" class="btn btn-primary" id="depDeleteButton"><spring:message code="btn.delete"/></button>
                <button type="button" class="btn btn-default delete-modal-close" data-dismiss="modal"><spring:message code="btn.cancel"/></button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="empDeleteModal" tabindex="-1" role="dialog" aria-labelledby="empDeleteLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content" id="null"> 
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="empDeleteLabel"><spring:message code="confirm.del.emp"/></h4>
            </div>
            <div class="modal-footer delete-modal-buttons">
                <button type="button" class="btn btn-primary" id="empDeleteButton"><spring:message code="btn.delete"/></button>
                <button type="button" class="btn btn-default delete-modal-close" data-dismiss="modal"><spring:message code="btn.cancel"/></button>
            </div>
        </div>
    </div>
</div>