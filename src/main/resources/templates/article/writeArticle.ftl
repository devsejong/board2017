<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.css">
    <style>
        body {
            margin-top: 50px;
        }
    </style>
</head>

<body>
<div class="container">
    <div class="row col-lg-10 col-md-12">
        <h1>글쓰기</h1>

        <form action="/articles/write" id="editForm" method="post">
            <div class="form-group">
                <label for="title">제목</label>
                <input type="text" class="form-control" name="title" id="title" placeholder="제목을 입력하세요">
            </div>

            <div class="form-group">
                <label for="author">작성자</label>
                <input type="text" class="form-control" name="author" id="author" placeholder="작성자">
            </div>

            <div class="form-group">
                <label for="author">본문</label>
                <div id="contentEditor"></div>
                <input type="hidden" id="content" name="content">
            </div>

            <div class="form-group text-right">
                <button type="button" class="btn btn-default" id="btnCancel">취소</button>
                <button type="submit" class="btn btn-primary" id="btnSubmit">제출</button>
            </div>

        </form>
    </div>
</div>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.js"></script>
<script>
    $(function () {
        $('#contentEditor').summernote({
            height: 500
        });

        $("#btnSubmit").on("click", function (e) {
            e.preventDefault();
            var content = $('#contentEditor').summernote('code');
            $("#content").val(content);

            $("#editForm").submit();
        });
    });
</script>
</body>
</html>