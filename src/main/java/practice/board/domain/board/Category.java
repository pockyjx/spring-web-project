package practice.board.domain.board;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})
public class Category {
    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;
    private String name;

    // 상위 -> 하위
    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
    private List<Category> child = new ArrayList<>();

    // 하위 -> 상위
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "category")
    private List<Post> posts = new ArrayList<>();

    // 하위 등록 시 상위에도 등록
    public void setParent(Category parent) {
        this.parent = parent;
        parent.getChild().add(this);
    }

    @Builder
    public Category(String name, Category parent) {
        this.name = name;
        if(parent != null) setParent(parent);
    }



}
