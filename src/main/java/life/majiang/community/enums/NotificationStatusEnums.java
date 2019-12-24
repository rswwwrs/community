package life.majiang.community.enums;

public enum NotificationStatusEnums {
    UNREAD(0),
    READ(1)
    ;

    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnums(int status) {
        this.status = status;
    }
}
