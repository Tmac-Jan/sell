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
                    <form role="form" method="post" action="/sell/support/ad/save">
                        <div class="form-group">
                            <label>广告名称</label>
                            <input name="adName" type="text" class="form-control" value="${(adInfoForm.adName)!''} "/>
                        </div>
                        <div class="form-group">
                            <label>广告图片</label>
                            <input id="adImage" name="adImage" type="text" hidden="hidden" value="${(adInfoForm.adImage)!''}"/>

                            <div class="file-loading">
                                <input id="input-id" type="file">
                                <p class="help-block">支持jpg、jpeg、png、gif格式，大小不超过1M</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>广告编辑者</label>
                            <input readonly  unselectable="on" name="modifierName" type="text" class="form-control" value="${(adInfoForm.modifierName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>创建时间</label>
                            <P>${(adInfoForm.createTime)!''}</P>
                        </div>
                        <div class="form-group">
                            <label>更新时间</label>
                            <P>${(adInfoForm.updateTime)!''}</P>
                        </div>
                        <input hidden type="text" name="id" value="${(adInfoForm.id)!''}">
                        <button type="submit" class="btn btn-default">提交</button>
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
        if ('${(adInfoForm.adImage)!""}' != '') {
            initialPreview = "<img class='kv-preview-data file-preview-image' src='${(adInfoForm.adImage)!""}'>"
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
        $('#adImage').val(data.response.data.fileName)
    });
</script>
</body>
</html>