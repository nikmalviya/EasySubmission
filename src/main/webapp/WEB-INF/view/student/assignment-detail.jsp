<%@page language="java" contentType="text/html" %>
<%@include file="../base-header.jsp" %>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Assignments for ${subject.subjectName}</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item">Courses</li>
                        <li class="breadcrumb-item">${subject.course.courseTitle}</li>
                        <li class="breadcrumb-item">${subject.subjectName}</li>
                        <li class="breadcrumb-item active">${assignment.assignmentTitle}</li>
                    </ol>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">

        <!-- Default box -->
        <div class="card">
            <div class="card-header">
                <h3 class="card-title">${assignment.assignmentTitle}</h3>
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col-12 col-md-12 col-lg-8 order-2 order-md-1">
                        <div class="row">
                            <div class="col-12 col-sm-4">
                                <div class="info-box bg-info">
                                    <div class="info-box-content">
                                        <span class="info-box-text text-center ">Posted On</span>
                                        <span class="info-box-number text-center mb-0"><fmt:formatDate
                                                value="${assignment.postedDate}" type="date"/> </span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-sm-4">
                                <div class="info-box bg-danger">
                                    <div class="info-box-content">
                                        <span class="info-box-text text-center "> Deadline</span>
                                        <span class="info-box-number text-center  mb-0"><fmt:formatDate
                                                value="${assignment.deadlineDate}" type="date"/></span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-sm-4">
                                <div class="info-box bg-light">
                                    <div class="info-box-content">
                                        <span class="info-box-text text-center">Submission Status</span>
                                        <span class="info-box-number text-center mb-0 ">
                                            <s:eval var="status"
                                                    expression="submissionService.getStatus(assignment,sessionScope.student)"/>
                                            <s:eval expression="status.badgeTag"/>

                                            <c:choose>
                                                <c:when test="${status.status == 'GRADED'}">
                                                    <span class="info-box-text text-bold text-center">
                                                        Marks:&nbsp; <s:eval
                                                            expression="submissionService.getMarks(assignment,sessionScope.student)"/> / 10
                                                    </span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="info-box-text text-bold text-center">
                                                        Marks:&nbsp;  -- / 10
                                                    </span>
                                                </c:otherwise>
                                            </c:choose>
                                            </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%--                        <div class="row">--%>
                        <%--                            <div class="col-12">--%>
                        <%--                                <h5>Questions</h5>--%>
                        <%--                                <ol>--%>
                        <%--                                    <li>Question 1</li>--%>
                        <%--                                    <li>Question 2</li>--%>
                        <%--                                    <li>Question 3</li>--%>
                        <%--                                    <li>Question 4</li>--%>
                        <%--                                </ol>--%>
                        <%--                            </div>--%>
                        <%--                        </div>--%>
                        <div class="row mb-2">
                            <div class="col-12">
                                <h5>Assignment File</h5>
                                &nbsp;&nbsp;<a href="/files?filePath=${assignment.filePath}"><i
                                    class="far fa-fw fa-file-pdf"></i><s:eval
                                    expression="assignment.getFileName()"/></a>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <h5>Notes</h5>
                                <p> ${assignment.notes}</p>
                            </div>
                        </div>
                        <c:if test="${status.status != 'NOT SUBMITTED'}">
                            <div class="row">
                                <div class="alert alert-success">
                                    <h5><i class="fas fa-check"></i>&nbsp;&nbsp;Assignment Submitted!</h5>
                                    <h6>Submitted File :
                                        <i class="far fa-fw fa-file-pdf"></i>
                                        <s:eval expression="submissionService.getFileName(assignment,sessionScope.student)"/>
                                    </h6>
                                    <a href="/files?filePath=<s:eval expression="submissionService.getFilePath(assignment,sessionScope.student)"/>" class="btn btn-info btn-sm w-100" style="text-decoration: none">
                                    <i class="fas fa-eye"></i> View Submitted File
                                    </a>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${status.status != 'GRADED'}">
                        <div class="row">
                            <div class="col-12">
                                <h5>${status.status == 'NOT SUBMITTED'?'Upload':'Update'} Submission File</h5>
                                <c:set var="updateUrl" value="/student/assignments/${subject.subjectID}/${assignment.assignmentID}/update"/>
                                <sform:form modelAttribute="submissionForm" method="post"
                                            enctype="multipart/form-data"
                                            action="${status.status == 'NOT SUBMITTED'?'':updateUrl}">
                                    <s:bind path="file">
                                        <div class="form-group">
                                            <label for="file" class="${status.error?'text-danger':''}"> Submission File
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
                                    <button class="btn btn-success w-100" type="submit">${status.status == 'NOT SUBMITTED'?'Submit':'Update'} Assignment</button>
                                </sform:form>
                            </div>
                        </div>
                        </c:if>
                    </div>
                </div>
            </div>
            <!-- /.card-body -->
        </div>
        <!-- /.card -->

    </section>
    <!-- /.content -->
</div>
<%@include file="../base-footer.jsp" %>
<script src="${pageContext.request.contextPath}/plugins/bs-custom-file-input/bs-custom-file-input.min.js"></script>
<script>
    $(document).ready(function () {
        bsCustomFileInput.init();
    });
</script>
</body>
</html>