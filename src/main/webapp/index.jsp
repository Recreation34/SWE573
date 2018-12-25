<%@ page import="java.util.List" %>
<%@ page import="objects.Coin" %>
<%@ page import="database.Interactions" %>
<%@ page import="utils.CoinUtils" %>
<%@ page import="objects.CoinTweetDetail" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="utils.TweetFetcher" %>

<%
    TweetFetcher.start();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>CoinTweetCap</title>

    <!-- Bootstrap -->
    <link href="../vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="../vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="../vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- iCheck -->
    <link href="../vendors/iCheck/skins/flat/green.css" rel="stylesheet">
    <!-- Datatables -->
    <link href="../vendors/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <link href="../vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css" rel="stylesheet">
    <link href="../vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
    <link href="../vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css" rel="stylesheet">
    <link href="../vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="../build/css/custom.min.css" rel="stylesheet">
</head>

<body class="nav-md">
<div class="container body">
    <div class="main_container">

        <!-- page content -->
        <div class="" role="main">
            <div class="">

                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_content">
                                <table id="datatable" class="table" data-page-length='50'>
                                    <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Tweet Count</th>
                                        <th>Ticker</th>
                                        <th>Price</th>
                                        <th>Tweet Change (1h)</th>
                                        <th>Tweet Change (24h)</th>
                                    </tr>
                                    </thead>


                                    <tbody>
                                    <%
                                        List<Coin> coins = CoinUtils.getCoins();
                                        for (Coin coin : coins) {
                                            CoinTweetDetail coinTweetDetail = new Gson().fromJson(coin.getInfo(), CoinTweetDetail.class);
                                    %>
                                    <tr>
                                        <td><a href="coin.jsp?id=<% out.print(coin.getId());%>"><%
                                            out.print(coin.getName());%></a></td>
                                        <td><% out.print(coinTweetDetail.getTweetsCount());%></td>
                                        <td><% out.print(coin.getTicker());%></td>
                                        <td><% out.print(coinTweetDetail.getCoinLastPrice());%></td>
                                        <td><% out.print("To Do");%></td>
                                        <td><% out.print("To Do");%></td>
                                    </tr>
                                    <% } %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!-- /page content -->
    </div>
</div>

<!-- jQuery -->
<script src="../vendors/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="../vendors/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="../vendors/fastclick/lib/fastclick.js"></script>
<!-- NProgress -->
<script src="../vendors/nprogress/nprogress.js"></script>
<!-- iCheck -->
<script src="../vendors/iCheck/icheck.min.js"></script>
<!-- Datatables -->
<script src="../vendors/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="../vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<script src="../vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
<script src="../vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
<script src="../vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
<script src="../vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
<script src="../vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
<script src="../vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
<script src="../vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
<script src="../vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
<script src="../vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
<script src="../vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>
<script src="../vendors/jszip/dist/jszip.min.js"></script>
<script src="../vendors/pdfmake/build/pdfmake.min.js"></script>
<script src="../vendors/pdfmake/build/vfs_fonts.js"></script>

<!-- Custom Theme Scripts -->
<script src="../build/js/custom.min.js"></script>

<script>

    var direction = 'desc';
    var index = 1;
    $(datatable).DataTable({
        order: [
            [index, direction]
        ]
    })

</script>

</body>
</html>