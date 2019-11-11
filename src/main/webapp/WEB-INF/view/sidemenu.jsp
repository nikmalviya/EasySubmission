<%@page language="java" contentType="text/html" %>

<c:set var="url" value="${requestScope['javax.servlet.forward.request_uri']}"/>
<aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="${pageContext.request.contextPath}/index3.html" class="brand-link">
        <img src="${pageContext.request.contextPath}/dist/img/AdminLTELogo.png" alt="AdminLTE Logo"
             class="brand-image img-circle elevation-3"
             style="opacity: .8">
        <span class="brand-text font-weight-light">Easy Submission</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
        <!-- Sidebar user panel (optional) -->
        <div class="user-panel mt-3 pb-3 mb-3 d-flex">
            <div class="image">
                <img src="${pageContext.request.contextPath}/dist/img/user2-160x160.jpg" class="img-circle elevation-2"
                     alt="User Image">
            </div>
            <div class="info">
                <a href="#" class="d-block"><security:authentication property="principal.username"/></a>
            </div>
        </div>

        <!-- Sidebar Menu -->
        <nav class="mt-2">
            <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                <!-- Add icons to the links using the .nav-icon class
                     with font-awesome or any other icon font library -->
                <li class="nav-item has-treeview">
                    <a href="#" class="nav-link ">
                        <i class="nav-icon fas fa-tachometer-alt"></i>
                        <p>
                            Dashboard
                            <i class="right fas fa-angle-left"></i>
                        </p>
                    </a>
                    <ul class="nav nav-treeview">
                        <li class="nav-item">
                            <a href="#" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Active Page</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="#" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Inactive Page</p>
                            </a>
                        </li>
                    </ul>
                </li>
                <security:authorize access="hasRole('ADMIN')">
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/admin/courses"
                           class="nav-link <c:if test="${f:startsWith(url,'/admin/courses')}">active</c:if>">
                            <i class="nav-icon fas fa-university"></i>
                            <p>
                                Courses
                            </p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/admin/subjects"
                           class="nav-link <c:if test="${f:startsWith(url,'/admin/subjects')}">active</c:if>">
                            <i class="nav-icon fas fa-book-open"></i>
                            <p>
                                Subjects
                            </p>
                        </a>
                    </li>
                    <li class="nav-item has-treeview ${f:startsWith(url, "/admin/users/")?'menu-open':''}">
                        <a href="#" class="nav-link ${f:startsWith(url, "/admin/users/")?'active':''}">
                            <i class="nav-icon fa fa-users"></i>
                            <p>
                                Users
                                <i class="right fas fa-angle-left"></i>
                            </p>
                        </a>
                        <ul class="nav nav-treeview">
                            <li class="nav-item">
                                <a href="/admin/users/admins"
                                   class="nav-link ${f:startsWith(url, "/admin/users/admins")?'active':''}">
                                    <i class="fa fa-user-lock nav-icon"></i>
                                    <p>Admins</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="/admin/users/students"
                                   class="nav-link ${f:startsWith(url, "/admin/users/students")?'active':''}">
                                    <i class="fa fa-user-graduate nav-icon"></i>
                                    <p>Students</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="/admin/users/professors"
                                   class="nav-link ${f:startsWith(url, "/admin/users/professors")?'active':''}">
                                    <i class="fa fa-chalkboard-teacher nav-icon"></i>
                                    <p>Professors</p>
                                </a>
                            </li>
                        </ul>
                    </li>
                </security:authorize>
                <security:authorize access="hasRole('PROFESSOR')">
                    <li class="nav-header">SUBJECTS</li>

                    <c:forEach items="${professor_subjects}" var="subject">
                        <li class="nav-item">
                            <a href="/professors/${subject.subjectID}/assignments"
                               class="nav-link ${f:startsWith(url, "/professors/*/assignments")?'active':''}">
                                <i class="fa fa-book nav-icon"></i>
                                <p>${subject.subjectName}</p>
                            </a>
                        </li>
                    </c:forEach>

                    </li>
                </security:authorize>
                <security:authorize access="hasRole('STUDENT')">
                    <li class="nav-header">COURSES</li>
                    <c:forEach items="${student_courses}" var="course">
                    <li class="nav-item has-treeview ${f:startsWith(url, "/student/")?'menu-open':''}">
                        <a href="#" class="nav-link">
                            <i class="nav-icon fa fa-book-open"></i>
                            <p>
                                ${course.courseTitle}
                                <i class="right fas fa-angle-left"></i>
                            </p>
                        </a>
                        <ul class="nav nav-treeview">
                            <li class="nav-header ml-3">SUBJECTS</li>
                            <c:forEach items="${course.subjects}" var="subject">
                                <li class="nav-item">
                                    <a href="/student/assignments/${subject.subjectID}/"
                                       class="nav-link ${f:startsWith(url, "/student/assignments/${subject.subjectID}/")?'active':''}">
                                        <i class="fa fa-book nav-icon"></i>
                                        <p>${subject.subjectName}</p>
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </li>
                    </c:forEach>
                </security:authorize>
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/logout"
                       class="nav-link">
                        <i class="nav-icon fas fa-sign-out-alt"></i>
                        <p>
                            Logout
                        </p>
                    </a>
                </li>
            </ul>
        </nav>
        <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
</aside>