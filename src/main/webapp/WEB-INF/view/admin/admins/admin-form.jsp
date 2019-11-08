<%@page language="java" contentType="text/html" %>
<%@include file="../../base-header.jsp" %>
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>${updatemode?'Update':'Add New'} Admin</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="/">Home</a></li>
                        <li class="breadcrumb-item"><a href="/admin/users/admins">Admins</a></li>
                        <li class="breadcrumb-item active">${updatemode?'Update':'Add'} Admin</li>
                    </ol>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>


    <sform:form cssClass="content" method="post" modelAttribute="adminForm">

        <div class="row ">
            <div class="col-md-6 offset-2">
                <div class="card card-primary">
                    <div class="card-header">
                        <h3 class="card-title">${updatemode?'Update':'New'} Admin</h3>

                        <div class="card-tools">
                            <button type="button" class="btn btn-tool" data-card-widget="collapse" data-toggle="tooltip"
                                    title="Collapse">
                                <i class="fas fa-minus"></i></button>
                        </div>
                    </div>

                    <div class="card-body">
                        <s:hasBindErrors name="adminForm">
                            <c:if test="${not empty errors.globalError}">
                                <div class="alert alert-danger"><sform:errors/></div>
                            </c:if>
                        </s:hasBindErrors>
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
            <div class="col-6 offset-2">
                <a href="/admin/users/admins" class="btn btn-secondary">Cancel</a>
                <input type="submit" value="${updatemode?'Update':'Add'} Admin" class="btn btn-success float-right">
            </div>
        </div>
    </sform:form>
</div>
<%@include file="../../base-footer.jsp" %>
<script>
    $(function () {

    })
</script>
</body>
</html>

