function post() {
    var  questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    comment2target(questionId,1,content);
}
function  comment2target(questionId,type,content) {
    if (!content){
        alert("不能回复空内容....")
        return;
    }
    $.ajax({
        type:"POST",
        url:"/comment",
        contentType: 'application/json',
        data:JSON.stringify({
            "parentId":questionId,
            "content":content,
            "type": type
        }),
        success:function (response) {
            alert(response.msg);
        },
        error:function(){
          alert("接口异常")
        },
        dataType:"json"
    });
}