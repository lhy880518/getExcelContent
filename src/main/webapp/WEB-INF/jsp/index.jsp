<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">
<head>

</head>
<body>
<form id="excel_form" method="post" enctype="multipart/form-data" action="">
    <div class="btn_area" id="excel_upload_btn_div">
        <a href="javascript:void(0);" class="basic-btn02 btn-blue-bg default_popup_confirm" id="excel_upload">일괄 업로드</a>
    </div>
</form>
</body>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">

    var excelResult = new Array();
    var allLiter = 0;
    var allLiterCnt = 0;

    $(document).ready(function () {
        $("#excel_upload").click(function (e){
            e.preventDefault();
            $("#liter_excel_upload").remove();

            var file = "<input type='file' id='liter_excel_upload' style='display: none;' onchange='javascript:selectFile(this);'/>";

            $("#excel_upload_btn_div").append(file);

            $("#liter_excel_upload").click();
        });
    });

    function selectFile(obj){
        var ext = obj.value.split(".").pop().toLowerCase();
        if($.inArray(ext, ["xlsx"]) == -1){
            alert("xlsx 파일만 업로드 해주세요.");
            $("#liter_excel_upload").remove();
            return;
        }else{
            var form = $("#excel_form")[0];
            var formData = new FormData(form);
            formData.append("excel",$("#liter_excel_upload")[0].files[0]);
            $.ajax({
                url: "/excel.json",
                contentType: false,
                processData: false,
                data: formData,
                type: "POST",
                // dataType: "json",
                success: function (data) {
                    $(".popup_close").click();

                    //공통 객체 초기화
                    initCommonVal();

                    excelResult = data;

                    $.each(excelResult, function(idx, value){

                        var tbodyHtml = "<tr>";

                        tbodyHtml += "<td class=\"text-center\">"+(excelResult.length-idx)+"</td>";
                        tbodyHtml += "<td class=\"text-center\">"+value.memberId+"</td>";
                        tbodyHtml += "<td class=\"text-center\">"+value.memberName+"</td>";
                        tbodyHtml += "<td class=\"text-center\">"+value.liter+"</td>";
                        tbodyHtml += "<tr/>";

                        allLiter += value.liter*1;
                        allLiterCnt += 1;

                        $("#literResultTbody").append(tbodyHtml);
                    });

                    $("#allLiter").val(allLiter);
                },
                error : function (data) {
                    $(".popup_close").click();
                    console.log('error ');
                }
            });
        }
    }

    function initCommonVal(){
        excelResult = new Array();
        allLiter = 0;
        allLiterCnt = 0;
    }
</script>