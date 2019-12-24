/*提交一级评论*/
function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    comment2target(questionId, 1, content);
}

/*提交二级评论*/
function post2(e) {
    var commentId = e.getAttribute("data-idx");
    var content = $("#input-" + commentId).val();
    comment2target(commentId, 2, content);
}


function comment2target(parentId, type, content) {
    if (!content) {
        alert("不能回复空内容");
        return;
    }
    $.ajax({
        url: "/comment",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": parentId,
            "content": content,
            "type": type
        }),
        success: function (rspn) {
            if (rspn.code == 200) {
                // $("#comment_section").hide();
                window.location.reload();
            } else {
                //当前操作需要登录,请登录后重试
                if (rspn.code == 2003) {
                    var cfm = confirm(rspn.message);
                    if (cfm) {
                        window.open("https://github.com/login/oauth/authorize?client_id=06f996b60ea2461f4f66&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(rspn.message);
                }
            }
        },
        dataType: "json"
    });
}


/*展开二级评论*/
function collapseComments(e) {
    var id = e.getAttribute("data-idx");
    var $commentNode = $("#comment-" + id);
    //收起和展开二级评论
    if ($commentNode.hasClass("in")) {
        $commentNode.removeClass("in");
        e.classList.remove("active");
    } else {
        $.getJSON("/comment/" + id, function (data) {
            // console.log(data);
            var $subCommentContainer = $("#comment-" + id);
            if ($subCommentContainer.children().length != 1) {
                //展开二级评论
                $commentNode.addClass("in");
                //消息图标为蓝色
                e.classList.add("active");
            } else {
                $.each(data.data.reverse(), function (index, comment) {
                    var mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object img-rounded",
                        "src": comment.user.avatarUrl
                    }));

                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        "html": comment.user.name
                    })).append($("<div/>", {
                        "html": comment.content
                    })).append($("<div/>", {
                        "class": "menu"
                    }).append($("<span/>", {
                        "class": "pull-right",
                        "html": moment(comment.gmtCreate).format('YYYY-MM-DD HH:mm')
                    })));

                    var mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement).append(mediaBodyElement);

                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments"
                    }).append(mediaElement);

                    $subCommentContainer.prepend(commentElement);
                });

                //展开二级评论
                $commentNode.addClass("in");
                //消息图标为蓝色
                e.classList.add("active");
            }
        });
    }
    //记录展开状态
    //e.setAttribute('data-collapse','in');
}

/*选中标签*/
function selectTag(value) {
    var previous = $("#tag").val();
    if (previous.indexOf(value) == -1) {
        if (previous) {
            $("#tag").val(previous + "," + value);
        } else {
            $("#tag").val(value);
        }
    }
}

/*展示标签选择区域*/
function showSelectTag() {
    $("#select-tag").show()
}
