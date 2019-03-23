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
                    <form role="form" method="post" action="/sell/seller/buyerComplain/reply">
                        <input hidden name="buyerId" type="number" value="${(buyerComplain.buyerId)!''} "/>
                        <input hidden type="text" name="id" value="${(buyerComplain.id)!''}">
                        <div class="form-group">
                            <label>买家Openid</label>
                            <input readonly  unselectable="on" name="buyerOpenid" type="text" class="form-control" value="${(buyerComplain.buyerOpenid)!''} "/>
                        </div>
                        <div class="form-group">
                            <label>订单编号</label>
                            <input readonly  unselectable="on"  name="orderId" type="text" class="form-control" value="${(buyerComplain.orderId)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>投诉内容</label>
                            <input readonly  unselectable="on" name="buyerComplain" type="text" class="form-control" value="${(buyerComplain.buyerComplain)!''}"/>
                        </div>

                        <div class="form-group">
                            <label>涉及图1</label>
                            <img class="img-responsive" src="${(buyerComplain.complainFile)!''}">
                            <#--<input id="complainFile" name="complainFile" type="text" hidden="hidden" value="${(productInfo.productIcon)!''}"/>-->

                            <#--<div class="file-loading">-->
                                <#--<input id="input-id-1" type="file">-->
                                <#--<p class="help-block">支持jpg、jpeg、png、gif格式，大小不超过1M</p>-->
                            <#--</div>-->
                        </div>

                        <div class="form-group">
                            <label>涉及图2</label>
                            <img class="img-responsive" src="${(buyerComplain.complainPhoto)!''}">
                            <#--<input id="complainPhoto" name="complainPhoto" type="text" hidden="hidden" value="${(productInfo.productIcon)!''}"/>-->

                            <#--<div class="file-loading">-->
                                <#--<input id="input-id-2" type="file">-->
                                <#--<p class="help-block">支持jpg、jpeg、png、gif格式，大小不超过1M</p>-->
                            <#--</div>-->
                        </div>
                        <div class="form-group">
                            <label>受理客服编号</label>
                            <input readonly  unselectable="on" name="shopId" type="number" class="form-control" value="${(buyerComplain.supportId)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>客服回复</label>
                            <input name="supportReply" type="text" class="form-control" value="${(buyerComplain.supportReply)!'未受理且未回复'}"/>
                        </div>
                        <div class="form-group">
                            <label>店家编号</label>
                            <input readonly  unselectable="on" name="shopId" type="text" class="form-control" value="${(buyerComplain.shopId)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>店家回复</label>
                            <input  name="shopReply" type="text" class="form-control" value="${(buyerComplain.shopReply)!''}"
                                  <#if (buyerComplain.shopReply??)>
                                      readonly  unselectable="on"
                                  </#if>
                            />
                        </div>
                        <div class="form-group">
                            <label>创建时间</label>
                            <P>${buyerComplain.createTime}</P>
                        </div>
                        <div class="form-group">
                            <label>更新时间</label>
                            <P>${buyerComplain.updateTime}</P>
                        </div>

                        <button type="submit" class="btn btn-default">回复</button>
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
        <#--var initialPreview1 = [];-->
        <#--if ('${(productInfo.productIcon)!""}' != '') {-->
            <#--initialPreview1 = "<img class='kv-preview-data file-preview-image' src='${(productInfo.productIcon)!""}'>"-->
        <#--}-->

        <#--var initialPreview2 = [];-->
        <#--if ('${(productInfo.productIcon)!""}' != '') {-->
            <#--initialPreview2 = "<img class='kv-preview-data file-preview-image' src='${(productInfo.productIcon)!""}'>"-->
        <#--}-->
        <#--$("#input-id-1").fileinput({-->
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
            <#--initialPreview: initialPreview1,-->
        <#--});-->
        <#--$("#input-id-2").fileinput({-->
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
            <#--initialPreview: initialPreview1,-->
        <#--});-->
    });
    //上传完成设置表单内容
    // $('#input-id-1').on('fileuploaded', function(event, data, previewId, index) {
    //     if (data.response.code != 0) {
    //         alert(data.response.msg)
    //         return
    //     }
    //     $('#productIcon').val(data.response.data.fileName)
    // });
    // $('#input-id-2').on('fileuploaded2', function(event, data, previewId, index) {
    //     if (data.response.code != 0) {
    //         alert(data.response.msg)
    //         return
    //     }
    //     $('#productIcon').val(data.response.data.fileName)
    // });
</script>
</body>
</html>