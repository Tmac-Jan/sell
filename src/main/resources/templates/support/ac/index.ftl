<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
<#include "../common/nav.ftl">

<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/sell/support/ac/save">
                        <div class="form-group">
                            <label>活动名称</label>
                            <input name="acName" type="text" class="form-control" value="${(acInfoForm.acName)!''} "/>
                        </div>
                        <div class="form-group">
                            <label>活动图片</label>
                            <input id="acImage" name="acImage" type="text" hidden="hidden" value="${(acInfoForm.acImage)!''}"/>

                            <div class="file-loading">
                                <input id="input-id" type="file">
                                <p class="help-block">支持jpg、jpeg、png、gif格式，大小不超过1M</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>编辑者</label>
                            <input readonly  unselectable="on" name="modifierName" type="text" class="form-control" value="${(acInfoForm.modifierName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>创建时间</label>
                            <P>${(acInfoForm.createTime)!''}</P>
                        </div>
                        <div class="form-group">
                            <label>更新时间</label>
                            <P>${(acInfoForm.updateTime)!''}</P>
                        </div>
                        <input hidden type="text" name="id" value="${(acInfoForm.id)!''}">
                        <button type="submit" class="btn btn-default">编辑</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-fileinput/4.4.8/js/fileinput.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-fileinput/4.4.8/js/locales/zh.min.js"></script>
<#--<script src="https://cdn.bootcss.com/bootstrap-fileinput/4.4.8/js/locales/zh.min.js"></script>-->
<script>
    $(function () {
        var initialPreview = [];
        if ('${(acInfoForm.acImage)!""}' != '') {
            initialPreview = "<img class='kv-preview-data file-preview-image' src='${(acInfoForm.acImage)!""}'>"
        }

        $("#input-id").fileinput({
            uploadUrl: '/sell/image/upload',
            language: 'zh',
            browseClass: "btn btn-primary btn-block",
            showCaption: false,
            showRemove: false,
            showUpload: false,
            allowedFileExtensions: [ 'jpg', 'jpeg', 'png', 'gif' ],
            maxFileSize: 1024,
            autoReplace: true,
            overwriteInitial: true,
            maxFileCount: 1,
            initialPreview: initialPreview,
        });
    });
    //上传完成设置表单内容
    $('#input-id').on('fileuploaded', function(event, data, previewId, index) {
        if (data.response.code != 0) {
            alert(data.response.msg)
            return
        }
        $('#acImage').val(data.response.data.fileName)
    });
</script>
</body>
</html>