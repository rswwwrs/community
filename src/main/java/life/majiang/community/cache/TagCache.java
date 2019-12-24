package life.majiang.community.cache;

import life.majiang.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TagCache {
    public static List<TagDTO> get(){
        List<TagDTO> list = new ArrayList<>();

        TagDTO program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("js","php","css","html","java","node","golang","python"));
        list.add(program);

        TagDTO framework = new TagDTO();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("spring","struts","django","yii","koa","hibernate","flask"));
        list.add(framework);

        TagDTO server = new TagDTO();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("linux","nginx","docker","apache","centOS","ubuntu","tomcat"));
        list.add(server);

        TagDTO dbCache = new TagDTO();
        dbCache.setCategoryName("数据库和缓存");
        dbCache.setTags(Arrays.asList("mysql","redis","mongodb","sql","oracle","nosql","sqlite"));
        list.add(dbCache);

        TagDTO devTool = new TagDTO();
        devTool.setCategoryName("开发工具");
        devTool.setTags(Arrays.asList("git","vim","sublime","idea","eclipse","maven","meacs"));
        list.add(devTool);


        return list;
    }

    /*拿到传进来的标签字段,去验证是否存在并返回不存在的字段*/
    public static String filterInvalid(String tags){
        String[] split = StringUtils.split(tags, ",");
        List<TagDTO> tagDTOList = get();

        List<String> tagList = tagDTOList.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));

        return invalid;
    }

}
