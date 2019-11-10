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
                        </th >
                        <th style="width: 15%" class="text-center">
                            Submission Status
                        </th>
                        <th class="text-center" style="width: 15%">
                            Marks
                        </th >
                        <th style="width: 15%;">
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            #
                        </td>
                        <td>
                            <a>
                                Assignment 1
                            </a>
                        </td>
                        <td>
                            12-12-1212 <br/>12:12PM
                        </td>
                        <td class="project_progress">
                            12-12-1212 <br/>12:12PM
                        </td>
                        <td class="project-state">
                            <span class="badge badge-warning">Not Submitted</span>
                        </td>
                        <td class="text-bold text-center">
                            -- / 10
                        </td>
                        <td class="project-actions">
                            <a class="btn btn-primary btn-sm" href="#">
                                <i class="fas fa-folder">
                                </i>
                                View
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            #
                        </td>
                        <td>
                            <a>
                                Assignment 2
                            </a>
                        </td>
                        <td>
                            12-12-1212 <br/>12:12PM
                        </td>
                        <td class="project_progress">
                            12-12-1212 <br/>12:12PM
                        </td>
                        <td class="project-state">
                            <span class="badge badge-info">Submitted</span>
                        </td>
                        <td class="text-bold text-center">
                            -- / 10
                        </td>
                        <td class="project-actions">
                            <a class="btn btn-primary btn-sm" href="#">
                                <i class="fas fa-folder">
                                </i>
                                View
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            #
                        </td>
                        <td>
                            <a>
                                Assignment 3
                            </a>
                        </td>
                        <td>
                            12-12-1212 <br/>12:12PM
                        </td>
                        <td class="project_progress">
                            12-12-1212 <br/>12:12PM
                        </td>
                        <td class="project-state">
                            <span class="badge badge-danger">Late Submitted</span>
                        </td>
                        <td class="text-bold text-center">
                            -- / 10
                        </td>
                        <td class="project-actions">
                            <a class="btn btn-primary btn-sm" href="#">
                                <i class="fas fa-folder">
                                </i>
                                View
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            #
                        </td>
                        <td>
                            <a>
                                Assignment 1
                            </a>
                        </td>
                        <td>
                            12-12-1212 <br/>12:12PM
                        </td>
                        <td class="project_progress">
                            12-12-1212 <br/>12:12PM
                        </td>
                        <td class="project-state">
                            <span class="badge badge-success">Graded</span>
                        </td>
                        <td class="text-bold text-center">
                            10 / 10
                        </td>
                        <td class="project-actions">
                            <a class="btn btn-primary btn-sm" href="#">
                                <i class="fas fa-folder">
                                </i>
                                View
                            </a>
                        </td>
                    </tr>
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