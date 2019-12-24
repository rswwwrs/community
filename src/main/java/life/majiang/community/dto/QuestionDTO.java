package life.majiang.community.dto;

import life.majiang.community.model.User;
import lombok.Data;

/**
 * 回显问题列表到首页:问题 Question 中creator与User表关联,查询头像等. 但Question对应的数据库中没有相关字段,故建此类形成外键关联关系
 */
@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private User user;
}
