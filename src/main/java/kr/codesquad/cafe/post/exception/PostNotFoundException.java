package kr.codesquad.cafe.post.exception;

public class PostNotFoundException extends RuntimeException {
    private static final String MESSAGE = "해당 게시글이 존재하지 않습니다.";

    public PostNotFoundException() {
        super(MESSAGE);
    }
}
