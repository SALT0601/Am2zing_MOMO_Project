function Login() {
  let re = /^[a-zA-Z0-9]{4,12}$/; // 아이디와 패스워드가 적합한지 검사할 정규식

  let memEmail = document.getElementById('memEmail');
  let pw = document.getElementById('memPassword');

  //아이디에서 입력 조건
  if (memEmail.value == '') {
    alert('이메일을 입력해 주세요.');
    id.focus(); //포커스를 id박스로 이동.
    return false;
  }

  if (pw.value == '') {
    alert('비밀번호를 입력해 주세요.');
    pw.focus(); //포커스를 Password박스로 이동.
    return false;
  }
  
}

function check(re, what, message) {
  if (re.test(what.value)) {
    return true;
  }
  alert(message);
  what.value = '';
  what.focus();
  //return false;
}
