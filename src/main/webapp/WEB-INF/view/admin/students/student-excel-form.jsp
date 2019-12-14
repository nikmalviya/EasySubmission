<%@page language="java" contentType="text/html" %>
<%@include file="../../base-header.jsp" %>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Add Multiple Students</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="/">Home</a></li>
                        <li class="breadcrumb-item"><a href="/admin/users/students">Students</a></li>
                        <li class="breadcrumb-item active">${updatemode?'Update':'Add'} Student</li>
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
                <h3 class="card-title">Add Multiple Students</h3>
            </div>
            <div class="card-body">

                <div class="row">
                    <div class="col-12 col-md-12 col-lg-8 order-2 order-md-1">
                        </div>

                        <div class="row">
                            <div class="col-12">
                                <h5>Format Of Excell File</h5>
                                <p>To enroll multiple students at a time please open the link below.</p>
                                <b>Eg.</b><a href="https://docs.google.com/spreadsheets/d/16-YO16RelfKia1RDU9EMX9XgBLSfO68dTxbCKT-6EB8/edit?usp=sharing">Click Here</a>
                                <sform:form modelAttribute="studentExcelForm" method="post"
                                            enctype="multipart/form-data"
                                            action="${pageContext.request.contextPath}/admin/users/students/add/excel">
                                    <s:hasBindErrors name="studentExcelForm">
                                        <c:if test="${not empty errors.globalError}">
                                            <div class="alert alert-danger"><sform:errors/></div>
                                        </c:if>
                                    </s:hasBindErrors>
                                    <s:bind path="excelFile">
                                        <div class="form-group">
                                            <label for="excelfile" class="${status.error?'text-danger':''}">Excel File:
                                            </label>
                                            <div class="input-group">
                                                <div class="custom-file">
                                                    <sform:input type="file" path="excelFile"
                                                                 cssClass="form-control ${status.error?'is-invalid':''}"/>
                                                    <label class="custom-file-label" for="excelFile">Choose file</label>
                                                </div>
                                            </div>
                                            <c:if test="${status.error}">
                                                <small class="text-danger"><sform:errors path="excelFile"/></small>
                                            </c:if>
                                        </div>
                                    </s:bind>
                                    <button class="btn btn-success w-100" type="submit">Enroll Students</button>
                                </sform:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.card-body -->
        <!-- /.card -->

    </section>
    <!-- /.content -->
</div>
<%@include file="../../base-footer.jsp" %>
<script src="${pageContext.request.contextPath}/plugins/bs-custom-file-input/bs-custom-file-input.min.js"></script>
<script>
    $(document).ready(function () {
        bsCustomFileInput.init();
    });
</script>
</body>
</html>