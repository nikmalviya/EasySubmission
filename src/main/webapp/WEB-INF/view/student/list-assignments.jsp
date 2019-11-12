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
                        <th style="width: 15%">
                            Posted On
                        </th>
                        <th style="width: 15%">
                            Deadline
                        </th>
                        <th style="width: 15%" class="text-center">
                            Submission Status
                        </th>
                        <th class="text-center" style="width: 15%">
                            Marks
                        </th>
                        <th style="width: 15%;">
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${subject.assignments}" var="assignment">
                        <tr>
                            <td>
                                #
                            </td>
                            <td>
                                <a>
                                        ${assignment.assignmentTitle}
                                </a>
                            </td>
                            <td>
                                <fmt:formatDate value="${assignment.postedDate}" pattern="dd-MMM-yyyy"/>
                            </td>
                            <td class="project_progress">
                                <fmt:formatDate value="${assignment.deadlineDate}" pattern="dd-MMM-yyyy"/>
                            </td>
                            <td class="project-state">

                                    <%--                            <span class="badge "><s:eval expression="submissionService.getStatus(assignment,sessionScope.student).status"/></span>--%>

                                <s:eval var="status"
                                        expression="submissionService.getStatus(assignment,sessionScope.student)"/>
                                <s:eval expression="status.badgeTag"/>
                            </td>
                            <c:choose>
                                <c:when test="${status.status == 'GRADED'}">
                                    <td class="text-bold text-center">
                                        <s:eval expression="submissionService.getMarks(assignment,sessionScope.student)"/> / 10
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td class="text-bold text-center">
                                        -- / 10
                                    </td>
                                </c:otherwise>
                            </c:choose>
                            <td class="project-actions">
                                <a class="btn btn-primary btn-sm"
                                   href="<c:url value="/student/assignments/${subject.subjectID}/${assignment.assignmentID}/"/> ">
                                    <i class="fas fa-folder">
                                    </i>
                                    View
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
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
                    <%--                            <span class="badge badge-success">Graded</span>--%>
                    <%--                        </td>--%>
                    <%--                        <td class="text-bold text-center">--%>
                    <%--                            10 / 10--%>
                    <%--                        </td>--%>
                    <%--                        <td class="project-actions">--%>
                    <%--                            <a class="btn btn-primary btn-sm" href="#">--%>
                    <%--                                <i class="fas fa-folder">--%>
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