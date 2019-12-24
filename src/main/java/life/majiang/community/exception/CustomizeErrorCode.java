package life.majiang.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001, "你找的问题不存在,换个试试吧?"),
    TARGET_PARAM_NOT_FOUND(2002, "未选中任何问题或评论来回复"),
    NO_LOGIN(2003, "当前操作需要登录,请登录后重试"),
    SYS_ERROR(2004, "未知错误,请呼叫网管"),
    TYPE_PARAM_WRONG(2005, "评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006, "回复的评论不存在"),
    COMMENT_CONTENT_IS_EMPTY(2007, "回复的评论不能为空"),
    READ_NOTITICATION_FAIL(2008, "非法操作,你读别人信息呢?"),
    NOTITICATION_NOT_FOUND(2009, "消息不翼而飞了?"),
    ;

    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
