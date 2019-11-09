<%@page language="java" contentType="text/html" %>
<%@include file="../../base-header.jsp" %>
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>${updatemode?'Update':'Add New'} Professor</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="/">Home</a></li>
                        <li class="breadcrumb-item"><a href="/admin/users/professors">Professors</a></li>
                        <li class="breadcrumb-item active">${updatemode?'Update':'Add'} Professor</li>
                    </ol>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>


    <sform:form cssClass="content" method="post" modelAttribute="professorForm">

                <div class="row">
                    <div class="col-md-6">
                        <div class="card card-primary">
                            <div class="card-header">
                                <h3 class="card-title">${updatemode?'Update':'New'} Professor</h3>

                                <div class="card-tools">
                                    <button type="button" class="btn btn-tool" data-card-widget="collapse" data-toggle="tooltip"
                                            title="Collapse">
                                        <i class="fas fa-minus"></i></button>
                                </div>
                            </div>

                            <div class="card-body">
                                <s:hasBindErrors name="professorForm">
                                    <c:if test="${not empty errors.globalError}">
                                        <div class="alert alert-danger"><sform:errors/></div>
                                    </c:if>
                                </s:hasBindErrors>
                                <s:bind path="professorFullName">
                                    <div class="form-group">
                                        <label for="professorFullName" class="${status.error?'text-danger':''}">Full Name</label>
                                        <sform:input path="professorFullName"
                                                     cssClass="form-control ${status.error?'is-invalid':''}"/>
                                        <c:if test="${status.error}">
                                            <small class="text-danger"><sform:errors path="professorFullName"/></small>
                                        </c:if>
                                    </div>
                                </s:bind>
                                <s:bind path="dateOfBirth">
                                    <div class="form-group">
                                        <label for="dateOfBirth" class="${status.error?'text-danger':''}">Date of Birth</label>
                                        <div class="input-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text ${status.error?'border-danger':''}"><i class="far fa-calendar-alt"></i></span>
                                            </div>
                                            <sform:input path="dateOfBirth" showPassword="true"
                                                         cssClass="form-control ${status.error?'is-invalid':''}" data-inputmask-alias="datetime"
                                                         data-inputmask-inputformat="dd/mm/yyyy" data-mask="" id="datemask"/>
                                        </div>
                                        <c:if test="${status.error}">
                                            <small class="text-danger"><sform:errors path="dateOfBirth"/></small>
                                        </c:if>
                                    </div>
                                </s:bind>
                                <s:bind path="contactNumber">
                                    <div class="form-group">
                                        <label for="contactNumber" class="${status.error?'text-danger':''}">Contact
                                            Number</label>
                                        <sform:input path="contactNumber" showPassword="true"
                                                     cssClass="form-control ${status.error?'is-invalid':''}"/>
                                        <c:if test="${status.error}">
                                            <small class="text-danger"><sform:errors path="contactNumber"/></small>
                                        </c:if>
                                    </div>
                                </s:bind>
                                <s:bind path="address">
                                    <div class="form-group">
                                        <div class="form-group">
                                            <label for="address" class="${status.error?'text-danger':''}">Address </label>
                                            <sform:textarea path="address" showPassword="true"
                                                            cssClass="form-control ${status.error?'is-invalid':''}"/>
                                            <c:if test="${status.error}">
                                                <small class="text-danger"><sform:errors path="address"/></small>
                                            </c:if>
                                        </div>
                                    </div>
                                </s:bind>
                            </div>

                            <!-- /.card-body -->
                        </div>
                        <!-- /.card -->
                    </div>
                    <div class="col-md-6">
                        <div class="card card-primary">
                            <div class="card-body">
                                <s:bind path="username">
                                    <div class="form-group">
                                        <label for="username" class="${status.error?'text-danger':''}">Username</label>
                                        <sform:input path="username"
                                                     cssClass="form-control ${status.error?'is-invalid':''}"/>
                                        <c:if test="${status.error}">
                                            <small class="text-danger"><sform:errors path="username"/></small>
                                        </c:if>
                                    </div>
                                </s:bind>
                                <c:if test="${not updatemode}">
                                <s:bind path="password">
                                    <div class="form-group">
                                        <label for="password" class="${status.error?'text-danger':''}">Password</label>
                                        <sform:password path="password" showPassword="true"
                                                        cssClass="form-control ${status.error?'is-invalid':''}"/>
                                        <c:if test="${status.error}">
                                            <small class="text-danger"><sform:errors path="password"/></small>
                                        </c:if>
                                    </div>
                                </s:bind>
                                <s:bind path="confirmPassword">
                                    <div class="form-group">
                                        <label for="confirmPassword" class="${status.error?'text-danger':''}">Confirm
                                            Password</label>
                                        <sform:password path="confirmPassword" showPassword="true"
                                                        cssClass="form-control ${status.error?'is-invalid':''}"/>
                                        <c:if test="${status.error}">
                                            <small class="text-danger"><sform:errors path="confirmPassword"/></small>
                                        </c:if>
                                    </div>
                                </s:bind>
                                </c:if>
                                <s:bind path="status">
                                    <div class="form-group">
                                        <label class="${status.error?'text-danger':''} mr-2">Status</label>
                                        <div class="form-check form-check-inline">
                                            <sform:radiobutton path="status" value="ACTIVE"
                                                               cssClass="form-check-input ${status.error?'is-invalid':''}"/>
                                            <label class="${status.error?'text-danger':''} form-check-label">Active</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <sform:radiobutton path="status" value="INACTIVE"
                                                               cssClass="form-check-input ${status.error?'is-invalid':''}"/>
                                            <label class="${status.error?'text-danger':''} form-check-label">Inactive</label>
                                        </div>
                                        <c:if test="${status.error}">
                                            <small class="text-danger"><sform:errors path="status"/></small>
                                        </c:if>
                                    </div>
                                </s:bind>
                            </div>

                            <!-- /.card-body -->
                        </div>
                        <!-- /.card -->
                    </div>
                </div>
        <div class="row">
            <div class="col-6">
                <a href="/admin/users/professors" class="btn btn-secondary">Cancel</a>
                <input type="submit" value="${updatemode?'Update':'Add'} Professor" class="btn btn-success float-right">
            </div>
        </div>
    </sform:form>
</div>
<%@include file="../../base-footer.jsp" %>
<!-- Select2 -->
<script src="${pageContext.request.contextPath}/plugins/select2/js/select2.full.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/inputmask/min/jquery.inputmask.bundle.min.js"></script>
<script>
    $('.select2bs4').select2({
        theme: 'bootstrap4'
    });
    $('#datemask').inputmask('dd/mm/yyyy', {'placeholder': 'dd/mm/yyyy'});
</script>
</body>
</html>

