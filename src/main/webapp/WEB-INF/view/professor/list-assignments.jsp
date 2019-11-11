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
            </div>
            <div class="card-body p-0">
                <table class="table table-striped projects">
                    <thead>
                    <tr>
                        <th style="width: 1%">
                            #
                        </th>
                        <th style="width: 24%">
                            Assignment
                        </th>

                        <th style="width: 24%">
                            Note
                        </th>

                        <th style="width: 15%">
                            Posted On
                        </th>
                        <th style="width: 14%">
                            Deadline
                        </th >
                        <th style="width: 1%" class="text-center">
                            Status
                        </th>
                        <th style="width: 8%;">
                        </th>

                    </tr>
                    </thead>
                    <tbody>
                    <tr>

<%--                        <c:forEach items="${admins}" var="admin">--%>
<%--                    <tr>--%>
<%--                        <td>${admin.userID}</td>--%>
<%--                        <td>${admin.username}</td>--%>
<%--                        <td>${admin.userStatus}</td>--%>
<%--                        <td>${admin.userType}</td>--%>
<%--                        <td>--%>
<%--                            <a href="/admin/users/admins/update/${admin.userID}/"--%>
<%--                               class="btn btn-warning btn-sm">Edit</a>--%>
<%--                            <a href="/admin/users/admins/delete/${admin.userID}"--%>
<%--                               class="btn btn-danger btn-sm">Delete</a>--%>
<%--                        </td>--%>
<%--                    </tr>--%>
<%--                    </c:forEach>--%>

<c:forEach items="${assignments}" var="assignment">
    <tr>
    <td>${assignment.assignmentID}</td>
    <td>${assignment.assignmentTitle}</td>
    <td>${assignment.notes}</td>
    <td>${assignment.postedDate}</td>
    <td>${assignment.deadlineDate}</td>
    <td class="project-state">
        <span class="badge badge-${assignment.status?"success":"warning"}">${assignment.status?"ACTIVE":"INACTIVE"}</span>
    </td>
                        <td class="project-actions">
                            <a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/professors/${subject.subjectID}/assignments/update/${assignment.assignmentID}">
                                <i class="fas fa-edit">
                                </i>
                                Edit
                            </a>
                            <br/>
                            <br/>

                            <a class="btn btn-danger btn-sm" href="${pageContext.request.contextPath}/professors/${subject.subjectID}/assignments/delete/${assignment.assignmentID}">
                                <i class="fas fa-remove">
                                </i>
                                Delete
                            </a>
                        </td>

<%--                        <td class="project-actions">--%>

<%--                        </td>--%>

                    </tr>
</c:forEach>
<%--                        <td>--%>
<%--                            #--%>
<%--                        </td>--%>
<%--                        <td>--%>
<%--                            <a>--%>
<%--                                Assignment 1--%>
<%--                            </a>--%>
<%--                        </td>--%>
<%--                        <td>--%>
<%--                            notes--%>
<%--                        </td>--%>
<%--                        <td class="project_progress">--%>
<%--                            12-12-1212 <br/>12:12PM--%>
<%--                        </td>--%>
<%--                        <td class="project-state">--%>
<%--                            <span class="badge badge-warning">Not Submitted</span>--%>
<%--                        </td>--%>
<%--                                                <td class="project-state">--%>
<%--                                                    <span class="badge badge-${assignmentStatus?"success":"warning"}warning">Not Submitted</span>--%>
<%--                                                </td>--%>
<%--                        <td class="text-bold text-center">--%>
<%--                            -- / 10--%>
<%--                        </td>--%>
<%--                        <td class="project-actions">--%>
<%--                            <a class="btn btn-primary btn-sm" href="#">--%>
<%--                                <i class="fas fa-folder">--%>
<%--                                </i>--%>
<%--                                View--%>
<%--                            </a>--%>
<%--                        </td>--%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <td>--%>
<%--                            #--%>
<%--                        </td>--%>
<%--                        <td>--%>
<%--                            <a>--%>
<%--                                Assignment 2--%>
<%--                            </a>--%>
<%--                        </td>--%>
<%--                        <td>--%>
<%--                            12-12-1212 <br/>12:12PM--%>
<%--                        </td>--%>
<%--                        <td class="project_progress">--%>
<%--                            12-12-1212 <br/>12:12PM--%>
<%--                        </td>--%>
<%--                        <td class="project-state">--%>
<%--                            <span class="badge badge-info">Submitted</span>--%>
<%--                        </td>--%>
<%--                        <td class="text-bold text-center">--%>
<%--                            -- / 10--%>
<%--                        </td>--%>
<%--                        <td class="project-actions">--%>
<%--                            <a class="btn btn-primary btn-sm" href="#">--%>
<%--                                <i class="fas fa-folder">--%>
<%--                                </i>--%>
<%--                                View--%>
<%--                            </a>--%>
<%--                        </td>--%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <td>--%>
<%--                            #--%>
<%--                        </td>--%>
<%--                        <td>--%>
<%--                            <a>--%>
<%--                                Assignment 3--%>
<%--                            </a>--%>
<%--                        </td>--%>
<%--                        <td>--%>
<%--                            12-12-1212 <br/>12:12PM--%>
<%--                        </td>--%>
<%--                        <td class="project_progress">--%>
<%--                            12-12-1212 <br/>12:12PM--%>
<%--                        </td>--%>
<%--                        <td class="project-state">--%>
<%--                            <span class="badge badge-danger">Late Submitted</span>--%>
<%--                        </td>--%>
<%--                        <td class="text-bold text-center">--%>
<%--                            -- / 10--%>
<%--                        </td>--%>
<%--                        <td class="project-actions">--%>
<%--                            <a class="btn btn-primary btn-sm" href="#">--%>
<%--                                <i class="fas fa-folder">--%>
<%--                                </i>--%>
<%--                                View--%>
<%--                            </a>--%>
<%--                        </td>--%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <td>--%>
<%--                            #--%>
<%--                        </td>--%>
<%--                        <td>--%>
<%--                            <a>--%>
<%--                                Assignment 1--%>
<%--                            </a>--%>
<%--                        </td>--%>
<%--                        <td>--%>
<%--                            12-12-1212 <br/>12:12PM--%>
<%--                        </td>--%>
<%--                        <td class="project_progress">--%>
<%--                            12-12-1212 <br/>12:12PM--%>
<%--                        </td>--%>
<%--                        <td class="project-state">--%>
<%--                            <span class="badge badge-success">Submitted</span>--%>
<%--                        </td>--%>
<%--                        <td class="text-bold text-center">--%>
<%--                            10 / 10--%>
<%--                        </td>--%>
<%--                        <td class="project-actions">--%>
<%--                            <a class="btn btn-primary btn-sm" href="/${assignment.assignmentId}/update">--%>
<%--                                <i class="fas fa-edit">--%>
<%--                                </i>--%>
<%--                                View--%>
<%--                            </a>--%>
<%--                        </td>--%>
<%--                    </tr>--%>
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