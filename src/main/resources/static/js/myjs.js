function post() {
    var questionId = $("#question_id").val();
    var userId=$("#user_id").val();
    var content = $("#comment_content").val();
    notification(userId,questionId);
    comment2target(questionId,1,content);
}

//添加到通知
function notification(receiver,outerId) {
    $.ajax({
        type:"POST",
        url:"/notification",
        contentType:'application/json',
        dataType:"json",
        data:JSON.stringify({
            "receiver":receiver,
            "outerId":outerId,
        }),
        success:function (data) {
        },
        error:function () {
            alert("通知接口异常");
        },
    });
}

//提交评论
function  comment2target(questionId,type,content) {
    if (!content) {
        alert("不能回复空内容....")
        return;
    }else{
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": questionId,
            "content": content,
            "type": type,
        }),
        success: function (response) {
            window.location.reload();
        },
        error: function () {
            alert("接口异常")
        },
        dataType: "json"
    });
}
}

//二级评论
function collapseComments(e){
    var id=e.getAttribute("data-id");
    var comment=$("#comment-" +id);
    comment.addClass("in");
    var comments=e.getAttribute("data-collapse");
    if(comments){
        //折叠
        comment.removeClass("in");
        e.classList.remove("active");
        e.removeAttribute("data-collapse");
        console.log("id is"+id);
        window.location.reload();
    }else {
        //展开
        f(id,2);
        comment.addClass("in");
        e.setAttribute("data-collapse","in");
        e.classList.add("active");
    }
}
//提交二级回复
function comment(e) {
    var parentId=e.getAttribute("data-id");
    var content=$("#input-"+parentId).val();
    var recevier=e.getAttribute("data-commentator");
    var outerId=e.getAttribute("data-parentId");
    console.log(recevier+"分别是"+outerId);
    notification(recevier,outerId);
    comment2target(parentId,2,content);
}
//二级评论通知



function f(id,type) {
    $.ajax({
        type:"POST",
        url:"/comments",
        dataType:"json",
        contentType: 'application/json',
        data:JSON.stringify({
            "id":id,
            "type": type
        }),
        success:function (response) {
            var subCommentContainer =$("#comment-"+id);
            console.log(response.data+"id is");
            $.each(response.data,function(idx,obj) {
                console.log(obj.id);
                console.log("src is"+obj.user.avatarUrl)
                var mediaLeftElement=$("<div/>",{
                  "class":"media-left"
                }).append($("<img/>",{
                    "class":"media-object img-rounded",
                    "src":obj.user.avatarUrl
                }));
                var mediaBodyElement=$("<div/>",{
                    "class":"media-body"
                }).append($("<h5/>",{
                    "class":"media-heading",
                    "html":obj.user.username
                })).append($("<div/>", {
                    "html":obj.content
                })).append($("<div/>", {
                    "class": "menu",
                }).append($("<span/>", {
                    "class":"pull-right",
                    "html":moment(obj.gmtCreate).format('YY-MM-DD HH:mm:ss')
                })));
                var mediaElement = $("<div/>",{
                    "class":"media"
                }).append(mediaLeftElement)
                    .append(mediaBodyElement);

                var commentElement=$("<div/>", {
                    "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments",
                }).append(mediaElement);
                subCommentContainer.prepend(commentElement);
            });
        },
        error:function(){
            alert("接口异常")
        },
    });
}



function focuss(e) {
    var focused=e.getAttribute("data-id");
    console.log("id is"+focused);
    $("#focuss").hide();
    $("#focus").hide();
    $("#removeFocuss").show();
    $.ajax({
        url:"/focus?focused="+focused,
        success:function (data) {
            console.log(data.msg);
        },
        error:function () {
            console.log("接口异常");
        },
    });
}

function removeFocus(e) {
    var focused=e.getAttribute("data-id");
    console.log("id is"+focused);
    $("#removeFocus").hide();
    $("#removeFocuss").hide();
    $("#focuss").show();
    $.ajax({
        url:"/remove_focus?focused="+focused,
        success:function (data) {
            console.log(data.msg);
        },
        error:function () {
            console.log("接口异常");
        },
    });
}

function tags(e) {
    var previous=$("#tag").val();
    if (previous.indexOf(e)==-1){
        if (previous){
            $("#tag").val(previous+","+e);
        } else {
            $("#tag").val(e);
        }
    } 
}

