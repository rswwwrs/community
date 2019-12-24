CREATE TABLE notification
(
    id bigint AUTO_INCREMENT PRIMARY KEY, /*id*/
    notifier bigint NOT NULL,/*发起者*/
    receiver bigint NOT NULL,/*接收者*/
    outerId bigint NOT NULL,/*问题或评论或点赞的目标ID*/
    type int NOT NULL,/*区分恢复,评论,点赞*/
    gmt_create bigint NOT NULL,/*生成时间*/
    status int DEFAULT 0 NOT NULL/*0:未读;1:已读*/
);