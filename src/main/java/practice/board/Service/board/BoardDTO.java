package practice.board.Service.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import practice.board.domain.board.Post;

import java.time.LocalDateTime;

@Data
public class BoardDTO {
    private Long id;
    private String categoryName;
    private String title;
    private String userName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createDate;

    public BoardDTO(Post post) {
        this.id = post.getId();
        this.categoryName = post.getCategory().getName();
        this.title = post.getTitle();
        this.userName = post.getMember().getUserName();
        this.createDate = post.getCreateDate();
    }
}
