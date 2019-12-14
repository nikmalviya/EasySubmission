<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html" %>
<%@include file="../base-header.jsp" %>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Submissions of ${subject.subjectName}</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item">Courses</li>
                        <li class="breadcrumb-item">${subject.course.courseTitle}</li>
                        <li class="breadcrumb-item">${subject.subjectName}</li>
<%--                        <li class="breadcrumb-item ">${assignment.assignmentTitle}</li>--%>
                        <li class="breadcrumb-item active">Evaluation</li>
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
                                        <span class="info-box-number text-center mb-0"><fmt:formatDate value="${assignment.postedDate}" type="date"/> </span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-sm-4">
                                <div class="info-box bg-danger">
                                    <div class="info-box-content">
                                        <span class="info-box-text text-center "> Deadline</span>
                                        <span class="info-box-number text-center  mb-0"><fmt:formatDate value="${assignment.deadlineDate}" type="date"/></span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-sm-4">
                                <div class="info-box bg-light">
                                    <div class="info-box-content">
                                        <span class="info-box-text text-center">Submission Status</span>
                                        <span class="info-box-number text-center mb-0 ">
                                            <s:eval expression="submissionService.getStatus(assignment,student).badgeTag"/>

                                            </span>
                                    </div>
                                </div>
                            </div>



                        </div>

                        <div class="row mb-2">
                            <div class="col-12">
                                <h5>Submission File</h5>
                                &nbsp;&nbsp;<a href="${submission.filePath}"><i class="far fa-fw fa-file-pdf"></i>${submission.fileName}</a>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-12">
                                <h5>Set Marks</h5>

                                <sform:form modelAttribute="submissionForm" method="post">
                                    <s:bind path="marks">
                                        <div class="form-group">
                                            <label for="marks" class="${status.error?'text-danger':''}">Marks
                                            </label>
                                            <sform:input path="marks"
                                                            cssClass="form-control ${status.error?'is-invalid':''}"/>
                                            <c:if test="${status.error}">
                                                <small class="text-danger"><sform:errors path="marks"/></small>
                                            </c:if>
                                        </div>
                                    </s:bind>
                                    <button class="btn btn-success w-100" type="submit">Submit Evaluation</button>
                                </sform:form>

                            </div>
                        </div>
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