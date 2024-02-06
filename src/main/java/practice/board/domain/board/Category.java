package practice.board.domain.board;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;
    private String name;

    // 상위 -> 하위
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    // 하위 -> 상위
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "category")
    private List<Post> posts = new ArrayList<>();

    // 하위 등록 시 상위에도 등록
    public void setParent(Category parent) {
        this.parent = parent;
        getChild().add(this);
    }

    // 생성 메서드
    public Category createCategory(String name, Category parent) {
        Category category = new Category();
        if(category != null) {
            setParent(parent);
        }
        return category;
    }



}
