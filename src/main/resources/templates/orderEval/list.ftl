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
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>评价编号</th>
                            <th>订单编号</th>
                            <th>买家openid</th>
                            <th>买家评价内容</th>
                            <th>买家打分</th>
                            <th>评价类型</th>
                            <th>配送时间</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list orderEvalFormPage.content as orderEvalInfo>
                        <tr>
                            <td>${orderEvalInfo.id}</td>
                            <td>${orderEvalInfo.orderId}</td>
                           <#--<td><img height="100" width="100" src="${productInfo.productIcon}" alt=""></td>-->
                            <td>${orderEvalInfo.buyerOpenid}</td>
                            <td>${orderEvalInfo.buyerEval}</td>
                            <td>${(orderEvalInfo.score)!''}</td>
                            <td>${orderEvalInfo.getOrederEvalEnum().message}</td>
                            <td>${(orderEvalInfo.deliveryTime)!''}</td>
                            <td>${orderEvalInfo.createTime}</td>
                            <td>${orderEvalInfo.updateTime}</td>
                            <#--<td><a href="/sell/seller/product/index?productId=${productInfo.productId}">修改</a></td>-->
                            <td>
                                <#if (orderEvalInfo.getShopReply())??>
                                    <a href="/sell/seller/orderEval/index?id=${orderEvalInfo.id}">商家已回复</a>
                                <#else>
                                    <a href="/sell/seller/orderEval/index?id=${orderEvalInfo.id}">回复评价</a>
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
                        <li><a href="/sell/seller/orderEval/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                    </#if>

                    <#list 1..orderEvalFormPage.getTotalPages() as index>
                        <#if currentPage == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/sell/seller/orderEval/list?page=${index}&size=${size}">${index}</a></li>
                        </#if>
                    </#list>

                    <#if currentPage gte orderEvalFormPage.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/sell/seller/orderEval/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
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