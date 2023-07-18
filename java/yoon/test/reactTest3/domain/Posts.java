package yoon.test.reactTest3.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="post")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 10000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "post_members")
    private Members member;

    @CreationTimestamp
    private LocalDateTime regdate;

    @ColumnDefault("0")
    private int hit;

    @Builder
    public Posts (String title, String content, Members member){
        this.title = title;
        this.content = content;
        this.member = member;
    }

}
