package life.majiang.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO<T> {
    private List<T> data;
    private boolean showPrevious = true;
    private boolean showFirstPage = true;
    private boolean showNext = true;
    private boolean showEndPage = true;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage = 0;

    public void setPagination(Integer totalCount, Integer page, Integer size) {

        if (totalCount % size == 0) {
            this.totalPage = totalCount / size;
        } else {
            this.totalPage = totalCount / size + 1;
        }
        if (page < 1)
            page = 1;
        if (page > totalPage)
            page = totalPage;
        this.page = page;
        //页码条显示内容
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        //是否展示上一页   <
        if (page == 1) {
            showPrevious = false;
        }
        //是否展示下一页   >
        if (page == totalPage) {
            showNext = false;
        }
        //是否展示第一页   <<
        if (pages.contains(1)) {
            showFirstPage = false;
        }
        //是否展示最后一页  >>
        if (pages.contains(totalPage)) {
            showEndPage = false;
        }
    }
}
