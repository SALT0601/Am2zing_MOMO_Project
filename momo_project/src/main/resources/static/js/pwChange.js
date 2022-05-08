let original_PwCheck = 0;
const original_Pw = $("#original_Pw");


$("#submit_button").on('click', function  pwCheckAll(){
    originalPwCheck();
    if(original_Pw.val()=="") {
        alert("기존 패스워드를 입력해주세요!");
        original_Pw.focus();
        return false;
    } else if($("#password").val()=="") {
        alert("새로운 패스워드를 입력해주세요!");
        return false;
    }else if (original_PwCheck == 0){
        alert("기존 패스워드를 확인해 주세요!");
        return false;
    } else if (!confirm("패스워드를 수정하시겠습니까?")){
        return false;
    } else{
        alert("수정되었습니다. 다시로그인해주세요");
        return true;
    }
});

$("#original_Pw").on('focusout',function (){
    originalPwCheck();
});

$("#password").on('change',function (){
    pwSame();
});

$("#password_check").on('keyup',function (){
    pwSame();
});

function pwSame() {
    const password = $("#password").val();
    const password_check = $("#password_check").val();
    const pw_check_msg = $("#pw_check_msg");
    //패스워드 입력되었는지 확인하기
    if(original_Pw.val()==""){
        alert("기존 패스워드를 입력해주세요!. 필수항목입니다.");
        original_Pw.focus();
        return false;
    }

    if (password == "") {
        alert("패스워드를 입력해주세요!. 필수항목입니다.");
        $("#password").focus();
        return false;
    }
    //패스워드 조건 확인
    if (password.length < 4) {
        alert('패스워드는 4글자 이상이어야 합니다.');
        $("#password").focus();
        return false;
    }
    //패스워드 일치 확인
    if (password != "" && password_check != "") {
        if (password == password_check) {
            pw_check_msg.html("비밀번호가 일치합니다.");
            pw_check_msg.css("color", "blue");
        } else {
            pw_check_msg.html("비밀번호가 다릅니다. 다시 확인해 주세요.");
            pw_check_msg.css("color", "red");
            return false;
        }
    }
    return true;
}

function originalPwCheck(){
    $.ajax({
        type :"get",
        url :"/member/pwdCheck",
        data : {"original_Pw" : original_Pw.val()},
        success : function(result){
            console.log(result);
            if(result.result == "1"){
				console.log(original_PwCheck);
                original_PwCheck = 1;
            
            }else{
				console.log(original_PwCheck);
                original_PwCheck = 0;
               
            }
        },
        error : function(){
            alert("ajax 실행 실패");
        }
    });
}