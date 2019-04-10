<nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
    <ul class="nav sidebar-nav">
        <li class="sidebar-brand">
            <a href="#">
                卖家管理系统
            </a>
        </li>
        <li class="sidebar-brand">
            <img alt="100x100" src="/sell/img/waimai.png" height="50" width="50" style="margin-left: 60px;"/>
        </li>
        <li>
            <a href="/sell/seller/order/list"><i class="fa fa-fw fa-list-alt"></i> 订单</a>
        </li>
        <li class="dropdown open">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i class="fa fa-fw fa-plus"></i> 商品 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">操作</li>
                <li><a href="/sell/seller/product/list">列表</a></li>
                <li><a href="/sell/seller/product/index">新增</a></li>
            </ul>
        </li>
        <li class="dropdown open">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i class="fa fa-fw fa-plus"></i> 类目 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">操作</li>
                <li><a href="/sell/seller/category/list">列表</a></li>
                <li><a href="/sell/seller/category/index">新增</a></li>
            </ul>
        </li>
        <li class="dropdown open">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i class="fa fa-fw fa-plus"></i> 商店评价 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">操作</li>
                <li><a href="/sell/seller/orderEval/list">列表</a></li>
                <#--<li><a href="/sell/seller/category/index">回复评价</a></li>-->
            </ul>
        </li>
        <li class="dropdown open">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i class="fa fa-fw fa-plus"></i> 商店投诉 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">操作</li>
                <li><a href="/sell/seller/buyerComplain/list">列表</a></li>
            </ul>
        </li>
        <li class="dropdown open">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i class="fa fa-fw fa-plus"></i> 店铺申请/申诉 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">操作</li>
                <li><a href="/sell/seller/request/list">列表</a></li>
                <li><a href="/sell/seller/request/index">申请</a></li>
            </ul>
        </li>
        <li>
            <a href="/sell/seller/shop/shopInfo"><i class="fa fa-fw fa-list-alt"></i> 首页</a>
            <a href="/sell/seller/shop/index"><i class="fa fa-fw fa-list-alt"></i> 店铺信息管理</a>
        </li>
        <li>
            <a href="/sell/seller/logout"><i class="fa fa-fw fa-list-alt"></i> 登出</a>
        </li>
    </ul>
</nav>