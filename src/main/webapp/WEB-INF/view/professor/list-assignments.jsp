<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        <li class="breadcrumb-item active">${subject.subjectName}</li>
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
                <h3 class="card-title">Assignments</h3>
                <div class="card-header col-12">
                    <a href="/professors/${subject.subjectID}/assignments/add"
                       class="btn btn-success bg-gradient-success float-right"><i
                            class="fa fa-plus-circle mr-1"></i> Add New Assignment
                    </a>
                </div>
            </div>
            <div class="card-body p-0">
                <table class="table table-striped projects">
                    <thead>
                    <tr>
                        <th style="width: 1%">
                            #
                        </th>
                        <th style="width: 19%">
                            Assignment
                        </th>

                        <th style="width: 20%">
                            Note
                        </th>

                        <th style="width: 17%">
                            Posted On
                        </th>
                        <th style="width: 17%">
                            Deadline
                        </th>
                        <th style="width: 5%" class="text-center">
                            Status
                        </th>
                        <th style="width: 17%;">
                        </th>

                    </tr>
                    </thead>
                    <tbody>
                    <tr>

                        <c:forEach items="${assignments}" var="assignment">
                    <tr>
                        <td>${assignment.assignmentID}</td>
                        <td>${assignment.assignmentTitle}</td>
                        <td>${assignment.notes}</td>
                        <td><fmt:formatDate value="${assignment.postedDate}" pattern="dd-MMM-yyyy" type="date"/></td>
                        <td><fmt:formatDate value="${assignment.deadlineDate}" pattern="dd-MMM-yyyy" type="date"/></td>
                        <td class="project-state">
                            <span class="badge badge-${assignment.status == "ACTIVE"?"success":"danger"}">${assignment.status}</span>
                        </td>
                        <td class="project-actions">
                            <a class="btn btn-primary btn-sm"
                               href="${pageContext.request.contextPath}/professors/${subject.subjectID}/assignments/update/${assignment.assignmentID}">
                                <i class="fas fa-edit">
                                </i>
                                Edit
                            </a>
                            <a class="btn btn-danger btn-sm"
                               href="${pageContext.request.contextPath}/professors/${subject.subjectID}/assignments/delete/${assignment.assignmentID}">
                                <i class="fas fa-remove">
                                </i>
                                Delete
                            </a>
                            <br/>
                            <br/>
                            <a class="btn btn-success btn-sm" href="${pageContext.request.contextPath}/professors/submissions/${assignment.assignmentID}">
                                <i class="fas fa-edit">
                                </i>
                                Check
                            </a>
                        </td>

                            <%--                        <td class="project-actions">--%>

                            <%--                        </td>--%>

                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- /.card-body -->
        </div>
        <!-- /.card -->

    </section>
    <!-- /.content -->
</div>
<%@include file="../base-footer.jsp" %>
</body>
</html>