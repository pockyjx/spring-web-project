package practice.board.web.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import practice.board.Service.board.BoardDTO;
import practice.board.Service.board.PostService;
import practice.board.domain.PageMaker;
import practice.board.domain.board.Category;
import practice.board.domain.board.Post;
import practice.board.repository.board.CategoryRepository;

import java.util.List;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final PostService postService;
    private final CategoryRepository categoryRepository;

    @ModelAttribute("categories")
    public List<Category> category() {
        return categoryRepository.findAll();
    }

    @GetMapping("/add")
    public String addForm(@ModelAttribute("post") Post post) {
        return "board/addPostForm";
    }

    @GetMapping
    public String postList(@PageableDefault(size = 5, sort = "id",direction = Sort.Direction.DESC) Pageable pageable,
                           Model model) {

        Page<BoardDTO> page = postService.findAll(pageable);
        PageMaker pm = new PageMaker(page);

        model.addAttribute("pm", pm);
        model.addAttribute("page", page);

        return "board/postList";
    }
}
