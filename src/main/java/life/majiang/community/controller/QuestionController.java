package life.majiang.community.controller;

import life.majiang.community.dto.CommentDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.enums.CommentTypeEnums;
import life.majiang.community.service.CommentService;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model
    ) {
        //根据Id查询单个问题
        QuestionDTO questionDTO = questionService.getById(id);
        //查询相关问题
        List<QuestionDTO> relatedQuestions = questionService.getRelatedQuestions(questionDTO);
        //每次查看,查看数加一
        questionService.incView(id);
        //根据id查询该问题下的评论列表
        List<CommentDTO> commentDTOList = commentService.listByParentId(id, CommentTypeEnums.QUESTION);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",commentDTOList);
        model.addAttribute("relatedQuestions",relatedQuestions);

        return "question";
    }
}
