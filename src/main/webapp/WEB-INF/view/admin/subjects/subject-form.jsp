<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html" %>
<%@include file="../../base-header.jsp" %>
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>${updatemode?'Update':'Add New'} Subject</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="/">Home</a></li>
                        <li class="breadcrumb-item"><a href="/admin/courses">Courses</a></li>
                        <li class="breadcrumb-item"><a href="/admin/subjects">Subjects</a></li>
                        <li class="breadcrumb-item active">${updatemode?'Update':'Add'} Subjects</li>
                    </ol>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>


    <sform:form cssClass="content" method="post" modelAttribute="subject">

        <div class="row ">
            <div class="col-md-6 offset-2">
                <div class="card card-primary">
                    <div class="card-header">
                        <h3 class="card-title">${updatemode?'Update':'New'} Subject</h3>

                        <div class="card-tools">
                            <button type="button" class="btn btn-tool" data-card-widget="collapse" data-toggle="tooltip"
                                    title="Collapse">
                                <i class="fas fa-minus"></i></button>
                        </div>
                    </div>
                    <s:bind path="subjectTitle">
                        <div class="card-body">
                            <s:hasBindErrors name="subject">
                                <c:if test="${not empty errors.globalError}">
                                    <div class="alert alert-danger"><sform:errors/></div>
                                </c:if>
                            </s:hasBindErrors>
                            <div class="form-group">
                                <label for="subjectTitle" class="${status.error?'text-danger':''}">Subject Title</label>
                                <sform:input path="subjectTitle"
                                             cssClass="form-control ${status.error?'is-invalid':''}"/>
                                <c:if test="${status.error}">
                                    <small class="text-danger"><sform:errors path="subjectTitle"/></small>
                                </c:if>

                                <label for="courseId" class="${status.error?'text-danger':''}">Course</label>
                                <sform:select path="courseId">
                                    <sform:options items="${subject.courseOptions}"/>
                                </sform:select>
                                   <c:if test="${status.error}">
                                    <small class="text-danger"><sform:errors path="courseId"/></small>
                                </c:if>
                                <br/>
                                <label for="professorId" class="${status.error?'text-danger':''}">Professor</label>
                                <sform:select path="professorId">
                                    <sform:options items="${subject.professorOptions}"/>
                                </sform:select>
                                <c:if test="${status.error}">
                                    <small class="text-danger"><sform:errors path="professorId"/></small>
                                </c:if>


                            </div>
                        </div>
                    </s:bind>
                    <!-- /.card-body -->
                </div>
                <!-- /.card -->
            </div>
        </div>
        <div class="row">
            <div class="col-6 offset-2">
                <a href="/admin/subjects" class="btn btn-secondary">Cancel</a>
                <input type="submit" value="${updatemode?'Update':'Add'} Subject" class="btn btn-success float-right">
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

