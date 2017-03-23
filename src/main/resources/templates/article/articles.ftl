<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
        body {
            margin-top: 50px;
        }
    </style>
</head>

<body>
<div class="container">
    <div class="board-wrapper row">
        <table class="table table-hover board">
            <thead>
            <tr>
                <th class="col-xs-1 text-center">번호</th>
                <th class="col-xs-9 text-center">제목</th>
                <th class="col-xs-1 text-center">날짜</th>
                <th class="col-xs-1 text-center">지은이</th>
            </tr>
            </thead>

            <tbody>
            <tr>
                <td class="text-center">1</td>
                <td>제목입니다.</td>
                <td>2017.03.21</td>
                <td>Sejong Park</td>
            </tr>
            </tbody>
        </table>
    </div><!--//.board-wrapper-->

    <div class="row text-right">
        <button class="btn btn-default">글쓰기</button>
    </div>

    <div class="pagination-wrapper row text-center">
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <li>
                    <a href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <li class="active"><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li>
                    <a href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div><!-- //.pagination-wrapper-->

</div>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
    $(".board > tbody > tr").on("click", function () {
        console.log("board clicked");
    });
</script>
</body>
</html>