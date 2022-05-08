

const memNum = $("#memNum");
const memEmail = $("#memEmail");
const memNickname = $("#memNickname");

let memEmailCheck = 1;

function withdrawalSubmit() {
    if(!confirm("탈퇴 하시겠습니까?")){
        return false;
    }else {
        $.ajax({
            type: "delete",
            url: "/api/admin/withdrawal",
            data: {"id": id.val()},
            success: function () {
                alert("회원 탈퇴처리가 완료되었습니다.");
                window.location.href='/admin';

            },
            error: function (request, status, error) {
                alert("ajax 실행 실패");
                alert("code:" + request.status + "\n" + "error :" + error);

            }
        });
    }

}

function checkBirthEdit() {
    const birth_check_msg = $("#birth_check_msg");
    if ($("#year").val() == "") {
        birth_check_msg.html("태어난 년도를 선택해주세요");
        birth_check_msg.css("color", "red");
        return false;
    } else if ($("#month").val() == "") {
        birth_check_msg.html("태어난 월을 선택해주세요");
        birth_check_msg.css("color", "red");
        return false;
    } else if ($("#day").val() == "") {
        birth_check_msg.html("태어난 날짜(일)를 선택해주세요");
        birth_check_msg.css("color", "red");
        return false;
    } else {
        birth_check_msg.html("");
    }
    return true;
}


function modifyCheckAll(){
    
    if(nickname.val()==""){
        alert("닉네임을 입력해주세요!. 필수항목입니다.");
        nickname.focus();
        return false;
  
    } else if (nicknameCheck == 0){
        alert("닉네임을 확인해 주세요!");
        return false;
    } else if(!blankModify()){
        return false;
    } else if (!confirm("정보를 수정하시겠습니까?")){
        return false;
    } alert("수정되었습니다. 다시로그인해주세요");
    location.href="/account/logout";
    return true;
}

// 닉네임 중복 체크
function memNicknameCheck() {
	const memNickname = $('#memNickname').val();
	if (memNickname == '') {
		alert('닉네임을 입력해주세요!');
		$('#memNickname').focus();
		return false;
	}
	$.ajax({
		type: 'get',
		url: '/member/nicknameChk',
		data: { "memNickname": memNickname },
		dataType: 'JSON',

		success: function(result) {
			if (result.result == '0') {
				if (confirm('이 이름은 사용 가능합니다. \n사용하시겠습니까?')) {
					memNicknameCheckCnt = 1;
					$('#memNickname').attr('readonly', true);
					$('#memNicknameOverlay').attr('disabled', true);
					$('#memNicknameOverlay').css('display', 'none');
					$('#resetMemNickname').attr('disabled', false);
					$('#resetMemNickname').css('display', 'inline-block');
				}
				return false;
			} else {
				alert('이미 사용중인 이름입니다.');
				$('#memNickname').focus();
			}
		},
		error: function(request, status, error) {
			alert('ajax 실행 실패');
			alert('code:' + request.status + '\n' + 'error :' + error);
		},
	});
}

function reMemNickname() {
	nicknameOverlapCheck = 0;
	$('#memNickname').attr('readonly', false).focus();
	$('#memNickname').val('');
	$('#memNicknameOverlay').attr('disabled', false);
	$('#memNicknameOverlay').css('display', 'inline-block');
	$('#resetMemNickname').attr('disabled', true);
	$('#resetMemNickname').css('display', 'none');
}

function memNicknameEdit(){

    $.ajax({
        type :"get",
        url :"/member/nicknameEdit",
        data : {"memId" : $('#memId').val(), "memNickname" : $("memNickname").val()},
        dataType : "JSON",
        success : function(result){
            if(result.result == true){
                $('.nickname_ok').css({"display" : "inline-block","color" : "blue"});
                $('.nickname_already').css("display", "none");
                nicknameCheck = 1;
                console.log(result.result);
            }else{
                $('.nickname_ok').css("display","none");
                $('.nickname_already').css({"display" :"inline-block", "color" : "red"});
                nicknameCheck = 0;
            }
        },
        error : function(){
            alert("ajax 실행 실패");
        }
    });
}


	


