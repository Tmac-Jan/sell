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
                            <th>商店编号</th>
                            <th>商店名称</th>
                            <th>商店地址</th>
                            <th>商店电话</th>
                            <th>商店图标</th>
                            <th>商店营业状态</th>
                            <th>商店封锁状态</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list shopInfoVoPage.content as shopInfo>
                        <tr>
                            <td>${shopInfo.id}</td>
                            <td>${shopInfo.shopName}</td>
                            <td>${shopInfo.shopAddress}</td>
                            <td>${shopInfo.shopPhone}</td>
                            <td><img height="100" width="100" src="${shopInfo.shopIcon}" alt=""></td>
                            <td>${(shopInfo.getShopOpenStausEnum().message)!''}</td>
                            <td>${(shopInfo.getShopStatusEnum().message)!''}</td>
                            <td>${shopInfo.createTime}</td>
                            <td>${shopInfo.updateTime}</td>
                            <td><a href="/sell/support/shop/blockShop?id=${shopInfo.id}">封锁店铺</a></td>
                            <td><a href="/sell/support/shop/reOpenShop?id=${shopInfo.id}">解封店铺</a></td>
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
                        <li><a href="/sell/support/ad/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                    </#if>

                    <#list 1..shopInfoVoPage.getTotalPages() as index>
                        <#if currentPage == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/sell/support/shop/list?page=${index}&size=${size}">${index}</a></li>
                        </#if>
                    </#list>

                    <#if currentPage gte shopInfoVoPage.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/sell/support/shop/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
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