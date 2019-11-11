<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html" %>
<%@include file="../base-header.jsp" %>
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>${updatemode?'Update':'Add New'} Assignment</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="/">Home</a></li>
                        <li class="breadcrumb-item">${subject.course.courseTitle}</li>
                        <li class="breadcrumb-item active">${subject.subjectName}</li>
                        <li class="breadcrumb-item active">${updatemode?'Update':'Add'} Assignment</li>
                    </ol>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>


    <sform:form cssClass="content" method="post" modelAttribute="assignmentForm" enctype="multipart/form-data">

        <div class="row ">
            <div class="col-md-6 offset-2">
                <div class="card card-primary">
                    <div class="card-header">
                        <h3 class="card-title">${updatemode?'Update':'New'} Assignment</h3>

                        <div class="card-tools">
                            <button type="button" class="btn btn-tool" data-card-widget="collapse" data-toggle="tooltip"
                                    title="Collapse">
                                <i class="fas fa-minus"></i></button>
                        </div>
                    </div>

                    <div class="card-body">
                        <s:hasBindErrors name="assignmentForm">
                            <c:if test="${not empty errors.globalError}">
                                <div class="alert alert-danger"><sform:errors/></div>
                            </c:if>
                        </s:hasBindErrors>
                        <s:bind path="assignmentTitle">
                            <div class="form-group">
                                <label for="assignmentTitle" class="${status.error?'text-danger':''}">Assignment Title</label>
                                <sform:input path="assignmentTitle"
                                             cssClass="form-control ${status.error?'is-invalid':''}"/>
                                <c:if test="${status.error}">
                                    <small class="text-danger"><sform:errors path="assignmentTitle"/></small>
                                </c:if>
                            </div>
                        </s:bind>

                        <s:bind path="postdate">
                            <div class="form-group">
                                <label for="postdate" class="${status.error?'text-danger':''}">Post Date</label>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text ${status.error?'border-danger':''}"><i class="far fa-calendar-alt"></i></span>
                                    </div>
                                    <sform:input  path="postdate" showPassword="true"
                                                  cssClass="form-control ${status.error?'is-invalid':''}" data-inputmask-alias="datetime"
                                                 data-inputmask-inputformat="dd/mm/yyyy" data-mask="" id="datemask1"/>
                                </div>
                                <c:if test="${status.error}">
                                    <small class="text-danger"><sform:errors path="postdate"/></small>
                                </c:if>
                            </div>
                        </s:bind>

                        <s:bind path="deadlinedate">
                            <div class="form-group">
                                <label for="deadlinedate" class="${status.error?'text-danger':''}">Deadline Date</label>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text ${status.error?'border-danger':''}"><i class="far fa-calendar-alt"></i></span>
                                    </div>
                                    <sform:input path="deadlinedate" showPassword="true"
                                                 cssClass="form-control ${status.error?'is-invalid':''}" data-inputmask-alias="datetime"
                                                 data-inputmask-inputformat="dd/mm/yyyy" data-mask="" id="datemask2"/>
                                </div>
                                <c:if test="${status.error}">
                                    <small class="text-danger"><sform:errors path="deadlinedate"/></small>
                                </c:if>
                            </div>
                        </s:bind>
                        <s:bind path="assignmentStatus">
                            <div class="form-group">
                                <label class="${status.error?'text-danger':''} mr-2">Status</label>
                                <div class="form-check form-check-inline">
                                    <sform:radiobutton path="assignmentStatus" value="ACTIVE"
                                                       cssClass="form-check-input ${status.error?'is-invalid':''}"/>
                                    <label class="${status.error?'text-danger':''} form-check-label">Active</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <sform:radiobutton path="assignmentStatus" value="INACTIVE"
                                                       cssClass="form-check-input ${status.error?'is-invalid':''}"/>
                                    <label class="${status.error?'text-danger':''} form-check-label">Inactive</label>
                                </div>
                                <c:if test="${status.error}">
                                    <small class="text-danger"><sform:errors path="assignmentStatus"/></small>
                                </c:if>
                            </div>
                        </s:bind>
                        <s:bind path="notes">
                            <div class="form-group">
                                <label for="notes" class="${status.error?'text-danger':''}">Notes
                                </label>
                                <sform:textarea path="notes"
                                                cssClass="form-control ${status.error?'is-invalid':''}"/>
                                <c:if test="${status.error}">
                                    <small class="text-danger"><sform:errors path="deadlinedate"/></small>
                                </c:if>
                            </div>
                        </s:bind>

                        <s:bind path="file">
                            <div class="form-group">
                                <label for="file" class="${status.error?'text-danger':''}">File
                                </label>
                                <div class="input-group">
                                    <div class="custom-file">
                                        <sform:input type="file" path="file"
                                                     cssClass="form-control ${status.error?'is-invalid':''}"/>
                                        <label class="custom-file-label" for="file">Choose file</label>
                                    </div>
                                </div>
                                <c:if test="${status.error}">
                                    <small class="text-danger"><sform:errors path="file"/></small>
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
                <a href="/professors" class="btn btn-secondary">Cancel</a>
                <input type="submit" value="${updatemode?'Update':'Add'} Assignment" class="btn btn-success float-right">
            </div>
        </div>
    </sform:form>
</div>
<%@include file="../base-footer.jsp" %>
<script src="${pageContext.request.contextPath}/plugins/inputmask/min/jquery.inputmask.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bs-custom-file-input/bs-custom-file-input.min.js"></script>
<script>
    $('.select2bs4').select2({
        theme: 'bootstrap4'
    });
    $('#datemask1').inputmask('dd/mm/yyyy', {'placeholder': 'dd/mm/yyyy'});
    $('.select2bs4').select2({
        theme: 'bootstrap4'
    });
    $('#datemask2').inputmask('dd/mm/yyyy', {'placeholder': 'dd/mm/yyyy'});
    $(document).ready(function () {
        bsCustomFileInput.init();

    });
</script>

</body>
</html>

