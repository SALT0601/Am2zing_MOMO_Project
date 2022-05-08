function withdrawal() {
    if (!confirm("정말로 탈퇴 하시겠습니까?")) {
        return false
    } else if (!confirm("게시글과 댓글 등이 삭제되며 이후 취소 처리가 불가 합니다. 탈퇴 하시겠습니까?")) {
        return false
    }
    reConverter();
    alert("회원탈퇴가 완료되었습니다.")
}
