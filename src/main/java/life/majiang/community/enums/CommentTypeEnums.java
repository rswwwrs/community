package life.majiang.community.enums;

public enum  CommentTypeEnums {
    QUESTION(1),
    COMMENT(2);

    private Integer type;


    public Integer getType() {
        return type;
    }

    CommentTypeEnums(Integer type) {
        this.type = type;
    }

    public static boolean isExist(Integer type) {
        for (CommentTypeEnums commentTypeEnum : CommentTypeEnums.values()) {
            if (commentTypeEnum.getType()==type){
                return true;
            }
        }
        return false;
    }
}
