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
                    <form role="form" method="post" action="/sell/seller/request/save">
                        <div class="form-group">
                            <label>商店名称</label>
                            <input readonly  unselectable="on" name="shopName" type="text" class="form-control" value="${(shopRequestForm.shopName)!''} "/>
                        </div>
                        <div class="form-group">
                            <label>商店地址</label>
                            <input readonly  unselectable="on" name="shopAddress" type="text" class="form-control" value="${(shopRequestForm.shopAddress)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>商店电话</label>
                            <input readonly  unselectable="on" name="shopPhone" type="text" class="form-control" value="${(shopRequestForm.shopPhone)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>商店类型</label>
                            <input readonly  unselectable="on" name="shopType" type="text" class="form-control" value="${(shopRequestForm.shopType)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>通知邮箱</label>
                            <input readonly  unselectable="on" name="notifyEmail" type="email" class="form-control" value="${(shopRequestForm.notifyEmail)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>申请类型</label>
                            <input hidden  name="requestType" type="number"  value="${(shopRequestForm.requestType)!''}"/>
                            <#--<input hidden type="text" name="sellerOpenid" value="${(shopRequestForm.sellerOpenid)!''}">-->
                            <p>${(shopRequestForm.getShopRequestTypeEnum().message)!''}</p>
                            <#--<#if (shopRequestForm.getRequestResult())??>-->
                                <#--<P>${(shopRequestForm.getShopRequestTypeEnum().message)!''}</P>-->
                            <#--<#else>-->
                                <#--<select name="requestType" class="form-control">-->
                                    <#--<option value="1">店铺申请</option>-->
                                    <#--<option value="2">店铺申诉</option>-->
                                <#--</select>-->
                            <#--</#if>-->
                        </div>
                        <div class="form-group">
                            <label>处理状态</label>
                            <#--<#if (shopRequestForm.getRequestResult())??>-->
                                <#--<P>${(shopRequestForm.getShopRequestResultEnum().message)!''}</P>-->
                            <#--<#else>-->
                                <#--<p>创建中</p>-->
                            <#--</#if>-->
                            <select name="requestResult" class="form-control">
                                    <option value="1"
                                            <#if (shopRequestForm.requestResult)?? && shopRequestForm.requestResult == 1>
                                                selected
                                            </#if>
                                    >审核通过
                                    </option>
                                <option value="2"
                                        <#if (shopRequestForm.requestResult)?? && shopRequestForm.requestResult == 2>
                                            selected
                                        </#if>
                                >审核不通过
                                </option>
                                <option value="3"
                                        <#if (shopRequestForm.requestResult)?? && shopRequestForm.requestResult == 3>
                                            selected
                                        </#if>
                                >处理中
                                </option>
                                <option value="4"
                                        <#if (shopRequestForm.requestResult)?? && shopRequestForm.requestResult == 4>
                                            selected
                                        </#if>
                                >卖家取消
                                </option>
                                <option value="0"
                                        <#if (shopRequestForm.requestResult)?? && shopRequestForm.requestResult == 0>
                                            selected
                                        </#if>
                                >新建
                                </option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>申请内容</label>
                            <input readonly  unselectable="on" name="requestContent" type="text" class="form-control" value="${(shopRequestForm.requestContent)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>营业资质图</label>
                            <p align="center"><img alt="" src="${(shopRequestForm.licenseImg)!''}" width="500"/></p>
                           <input id="licenseImg" name="licenseImg" type="text" hidden="hidden" value="${(shopRequestForm.licenseImg)!''}"/>

                            <#--<div class="file-loading">-->
                                <#--<input id="input-id" type="file">-->
                                <#--<p class="help-block">支持jpg、jpeg、png、gif格式，大小不超过1M</p>-->
                            <#--</div>-->
                        </div>
                        <div class="form-group">
                            <label>卫生许可证图</label>
                            <p align="center"><img alt="" src="${(shopRequestForm.healthImg)!''}" width="500"/></p>
                           <input id="healthImg" name="healthImg" type="text" hidden="hidden" value="${(shopRequestForm.healthImg)!''}"/>

                            <#--<div class="file-loading">-->
                                <#--<input id="input-id" type="file">-->
                                <#--<p class="help-block">支持jpg、jpeg、png、gif格式，大小不超过1M</p>-->
                            <#--</div>-->
                        </div>
                        <div class="form-group">
                            <label>申请附加图（可不填）</label>
                            <p align="center"><img alt="" src="${(shopRequestForm.requestImg)!''}" width="500"/></p>
                            <input id="requestImg" name="requestImg" type="text" hidden="hidden" value="${(shopRequestForm.requestImg)!''}"/>

                            <#--<div class="file-loading">-->
                                <#--<input id="input-id" type="file">-->
                                <#--<p class="help-block">支持jpg、jpeg、png、gif格式，大小不超过1M</p>-->
                            <#--</div>-->
                        </div>
                        <div class="form-group">
                            <label>创建时间</label>
                            <P>${shopRequestForm.createTime}</P>
                        </div><div class="form-group">
                            <label>更新时间</label>
                            <P>${shopRequestForm.updateTime}</P>
                        </div>
                        <input hidden type="text" name="id" value="${(shopRequestForm.id)!''}">
                        <input hidden type="text" name="sellerId" value="${(shopRequestForm.sellerId)!''}">
                        <input hidden type="text" name="sellerOpenid" value="${(shopRequestForm.sellerOpenid)!''}">
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
<#--<script>-->

    <#--$(function () {-->
        <#--var initialPreview = [];-->
        <#--if ('${(shopRequestForm.productIcon)!""}' != '') {-->
            <#--initialPreview = "<img class='kv-preview-data file-preview-image' src='${(shopRequestForm.productIcon)!""}'>"-->
        <#--}-->

        <#--$("#input-id").fileinput({-->
            <#--uploadUrl: '/sell/image/upload',-->
            <#--language: 'zh',-->
            <#--browseClass: "btn btn-primary btn-block",-->
            <#--showCaption: false,-->
            <#--showRemove: false,-->
            <#--showUpload: false,-->
            <#--allowedFileExtensions: [ 'jpg', 'jpeg', 'png', 'gif' ],-->
            <#--maxFileSize: 1024,-->
            <#--autoReplace: true,-->
            <#--overwriteInitial: true,-->
            <#--maxFileCount: 1,-->
            <#--initialPreview: initialPreview,-->
        <#--});-->
    <#--});-->
    <#--//上传完成设置表单内容-->
    <#--$('#input-id').on('fileuploaded', function(event, data, previewId, index) {-->
        <#--if (data.response.code != 0) {-->
            <#--alert(data.response.msg)-->
            <#--return-->
        <#--}-->
        <#--$('#productIcon').val(data.response.data.fileName)-->
    <#--});-->
<#--</script>-->
</body>
</html>