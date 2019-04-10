<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav1.ftl">

    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/sell/seller/shop/save">
                        <div class="form-group">
                            <label>商店编号</label>
                           <P>${(shopInfoForm.id)!''}</P>
                        </div>
                        <div class="form-group">
                            <label>商店名字</label>
                            <input name="shopName" type="text" class="form-control" value="${(shopInfoForm.shopName)!''}"
                                   readonly  unselectable="on"
                            />
                        </div>
                        <div class="form-group">
                            <label>商店地址</label>
                            <input name="shopAddress" type="text" class="form-control" value="${(shopInfoForm.shopAddress)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>商店电话</label>
                            <input name="shopPhone" type="number" class="form-control" value="${(shopInfoForm.shopPhone)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>商店介绍</label>
                            <input name="shopIntro" type="text" class="form-control" value="${(shopInfoForm.shopIntro)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>商店配送费</label>
                            <input name="shopDeliver" type="number" class="form-control" value="${(shopInfoForm.shopDeliver)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>商店图标</label>
                            <input id="shopIcon" name="shopIcon" type="text" hidden="hidden" value="${(shopInfoForm.shopIcon)!''}"/>

                            <div class="file-loading">
                                <input id="input-id" type="file">
                                <p class="help-block">支持jpg、jpeg、png、gif格式，大小不超过1M</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>营业资质图</label>
                           <p align="center"><img alt="" src="${(shopInfoForm.shopFile)!''}" width="500"/></p>
                            <input id="shopFile" name="shopFile" type="text" hidden="hidden" value="${(shopInfoForm.shopFile)!''}"/>
                        </div>
                        <div class="form-group">
                            <select name="shopOpen" class="form-control">
                                <option value="0"
                                        <#if (shopInfoForm.shopOpen)?? && shopInfoForm.shopOpen == 0>
                                            selected
                                        </#if>
                                >打烊
                                </option>
                                <option value="1"
                                        <#if (shopInfoForm.shopOpen)?? && shopInfoForm.shopOpen == 1>
                                            selected
                                        </#if>
                                >营业
                                </option>
                            </select>
                        </div>
                        <input hidden type="text" name="id" value="${(shopInfoForm.id)!''}">
                        <input hidden type="text" name="sellerOpenid" value="${(shopInfoForm.sellerOpenid)!''}">
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-fileinput/4.4.8/js/fileinput.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-fileinput/4.4.8/js/locales/zh.min.js"></script>
<#--<script src="https://cdn.bootcss.com/bootstrap-fileinput/4.4.8/js/locales/zh.min.js"></script>-->
<script>
    $(function () {
        var initialPreview = [];
        if ('${(shopInfoForm.shopIcon)!""}' != '') {
            initialPreview = "<img class='kv-preview-data file-preview-image' src='${(shopInfoForm.shopIcon)!""}'>"
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
        $('#shopIcon').val(data.response.data.fileName)
    });
</script>
</body>
</html>