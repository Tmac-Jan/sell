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
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>申请编号</th>
                            <th>商店所有人OpenId</th>
                            <th>商店名字</th>
                            <th>商店地址</th>
                            <th>商店电话</th>
                            <th>商店类型</th>
                            <th>申请类型</th>
                            <th>申请状态</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list shopRequestFormPage.content as shopRequestInfo>
                        <tr>
                            <td>${shopRequestInfo.id}</td>
                            <td>${shopRequestInfo.sellerOpenid}</td>
                            <td>${shopRequestInfo.shopName}</td>
                            <td>${shopRequestInfo.shopAddress}</td>
                            <td>${shopRequestInfo.shopPhone}</td>
                            <td>${shopRequestInfo.shopType}</td>
                            <td>${shopRequestInfo.getShopRequestTypeEnum().message}</td>
                            <td>${shopRequestInfo.getShopRequestResultEnum().message}</td>
                            <td>${shopRequestInfo.createTime}</td>
                            <td>${shopRequestInfo.updateTime}</td>
                            <td><a href="/sell/support/request/index?id=${shopRequestInfo.id}">详情</a></td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

            <#--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                    <#if currentPage lte 1>
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="/sell/seller/request/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                    </#if>

                    <#list 1..shopRequestFormPage.getTotalPages() as index>
                        <#if currentPage == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/sell/seller/request/list?page=${index}&size=${size}">${index}</a></li>
                        </#if>
                    </#list>

                    <#if currentPage gte shopRequestFormPage.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/sell/seller/request/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>