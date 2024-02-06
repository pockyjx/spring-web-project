package practice.board.domain;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
@Getter
@Slf4j
public class PageMaker {
    private int nowPage;
    private int startPage;
    private int endPage;
    private boolean prev;
    private boolean next;
    private int block = 5;

    public PageMaker(Page<?> page) {
        nowPage = page.getNumber() + 1;
        endPage = (int)(Math.ceil((double)(nowPage)/block)) * block;
        startPage = endPage - block + 1;
        endPage = Math.min(endPage, page.getTotalPages());

        log.info("start={}, end={}", startPage, endPage);

        prev = startPage != 1;
        next = ((long)endPage < page.getTotalPages());
    }
}
