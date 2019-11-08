<%@page language="java" contentType="text/html" %>
<%@include file="../../base-header.jsp" %>


<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1 class="m-0 text-dark hell">Admins</h1>
                </div><!-- /.col -->
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="/">Home</a></li>
                        <li class="breadcrumb-item active">Admin Users</li>
                    </ol>
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-header col-12">
                        <a href="/admin/users/admins/add" class="btn btn-success bg-gradient-success float-right"><i
                                class="fa fa-plus-circle mr-1"></i> Add New Admin
                        </a>
                    </div>
                    <!-- /.card-header -->
                    <div class="card-body">
                        <table id="table" class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Username</th>
                                <th>Status</th>
                                <th>Role</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${admins}" var="admin">
                                <tr>
                                    <td>${admin.userID}</td>
                                    <td>${admin.username}</td>
                                    <td>${admin.userStatus}</td>
                                    <td>${admin.userType}</td>
                                    <td>
                                        <a href="/admin/users/admins/update/${admin.userID}/"
                                           class="btn btn-warning btn-sm">Edit</a>
                                        <a href="/admin/users/admins/delete/${admin.userID}"
                                           class="btn btn-danger btn-sm">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                            <tfoot>
                            <tr>
                                <th>ID</th>
                                <th>Username</th>
                                <th>Status</th>
                                <th>Role</th>
                                <th></th>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                    <!-- /.card-body -->
                </div>
                <!-- /.card -->
            </div>
            <!-- /.col -->
        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

<%@include file="../../base-footer.jsp" %>
<script src="/plugins/datatables/jquery.dataTables.js"></script>
<script src="/plugins/datatables-bs4/js/dataTables.bootstrap4.js"></script>
<script>
    $(function () {
        $('#table').DataTable({
            "paging": true,
            "lengthChange": true,
            "searching": true,
            "ordering": true,
            "info": false,
            "autoWidth": true,
        });
    });
    ${message}
    <c:if test="${not empty success_message}">
    $(function () {
        $(document).ready(function () {
            toastr.success("${success_message}")
        });
    });
    </c:if>


</script>
</body>
</html>

