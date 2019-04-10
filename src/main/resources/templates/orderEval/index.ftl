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
                    <form role="form" method="post" action="/sell/seller/buyerComplain/reply">
                        <input hidden name="buyerId" type="number" value="${(orderEvalForm.buyerId)!''} "/>
                        <input hidden type="text" name="id" value="${(orderEvalForm.id)!''}">
                        <div class="form-group">
                            <label>买家Openid</label>
                            <input readonly  unselectable="on" name="buyerOpenid" type="text" class="form-control" value="${(orderEvalForm.buyerOpenid)!''} "/>
                        </div>
                        <div class="form-group">
                            <label>订单编号</label>
                            <input readonly  unselectable="on"  name="orderId" type="text" class="form-control" value="${(orderEvalForm.orderId)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>评价内容</label>
                            <input readonly  unselectable="on" name="buyerEval" type="text" class="form-control" value="${(orderEvalForm.buyerEval)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>评价类型</label>
                            <input readonly  unselectable="on" name="rateType" type="text" class="form-control" value="${(orderEvalForm.rateType)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>买家评分</label>
                            <input readonly  unselectable="on" name="score" type="number" class="form-control" value="${(orderEvalForm.score)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>买家提及</label>
                            <input readonly  unselectable="on" name="recommend" type="text" class="form-control" value="${(orderEvalForm.recommend)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>订单配送时间</label>
                            <input readonly  unselectable="on" name="deliveryTime" type="text" class="form-control" value="${(orderEvalForm.deliveryTime)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>涉及图1</label>
                            <img class="img-responsive" src="${(orderEvalForm.evalPhoto)!''}">
                            <#--<input id="complainFile" name="complainFile" type="text" hidden="hidden" value="${(productInfo.productIcon)!''}"/>-->

                            <#--<div class="file-loading">-->
                                <#--<input id="input-id-1" type="file">-->
                                <#--<p class="help-block">支持jpg、jpeg、png、gif格式，大小不超过1M</p>-->
                            <#--</div>-->
                        </div>
                        <div class="form-group">
                            <label>店家编号</label>
                            <input readonly  unselectable="on" name="shopId" type="text" class="form-control" value="${(orderEvalForm.shopId)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>店家回复</label>
                            <input  name="shopReply" type="text" class="form-control" value="${(orderEvalForm.shopReply)!''}"
                                  <#if (orderEvalForm.shopReply??)>
                                      readonly  unselectable="on"
                                  </#if>
                            />
                        </div>
                        <div class="form-group">
                            <label>创建时间</label>
                            <P>${orderEvalForm.createTime}</P>
                        </div>
                        <div class="form-group">
                            <label>更新时间</label>
                            <P>${orderEvalForm.updateTime}</P>
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