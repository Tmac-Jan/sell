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
                            <th>投诉编号</th>
                            <th>订单编号</th>
                            <th>买家openid</th>
                            <th>买家投诉内容</th>
                            <th>客服编号</th>
                            <th>客服回复</th>
                            <th>投诉进程</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list buyerComplainPage.content as buyerComplainInfo>
                        <tr>
                            <td>${buyerComplainInfo.id}</td>
                            <td>${buyerComplainInfo.orderId}</td>
                           <#--<td><img height="100" width="100" src="${productInfo.productIcon}" alt=""></td>-->
                            <td>${buyerComplainInfo.buyerOpenid}</td>
                            <td>${buyerComplainInfo.buyerComplain}</td>
                            <td>${(buyerComplainInfo.supportId)!''}</td>
                            <td>${(buyerComplainInfo.supportReply)!''}</td>
                            <td>${buyerComplainInfo.getBuyerComplainEnum().message}</td>
                            <td>${buyerComplainInfo.createTime}</td>
                            <td>${buyerComplainInfo.updateTime}</td>
                            <#--<td><a href="/sell/seller/product/index?productId=${productInfo.productId}">修改</a></td>-->
                            <td>
                                <#if buyerComplainInfo.getBuyerComplainEnum().message == "买家已评价">
                                    <a href="/sell/seller/buyerComplain/index?id=${buyerComplainInfo.id}">回复投诉</a>
                                <#else>
                                    <a href="/sell/seller/buyerComplain/index?id=${buyerComplainInfo.id}">商家已回复</a>
                                </#if>
                            </td>
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
                        <li><a href="/sell/seller/buyerComplain/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                    </#if>

                    <#list 1..buyerComplainPage.getTotalPages() as index>
                        <#if currentPage == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/sell/seller/buyerComplain/list?page=${index}&size=${size}">${index}</a></li>
                        </#if>
                    </#list>

                    <#if currentPage gte buyerComplainPage.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/sell/seller/buyerComplain/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>