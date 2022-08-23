package com.rada.projectboard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
/*equals 두 객체의 내용이 같은지, 동등성을 비교하는 연산자
hashcode 두 객체가 같은 객체인지, 동일성을 비교하는 연산자
자동으로 이 메소드를 생성할 수 있다*/
/*@EqualsAndHashCode*/
@ToString
/*매핑할 테이블을 지정*/
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")

})
@EntityListeners(AuditingEntityListener.class)
/*jpa 가 관리. db의 테이블과 class를 매핑*/
@Entity
public class Article {

    /*jpa가 객체를 관리할 때 식별할 기본키*/
    @Id
    /*주키의 값을 위한 자동 생성 전략을 명시*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @Column(nullable = false) private String title; //제목
    @Setter  @Column(nullable = false, length =10000)private String content; //내용

    @Setter private String hashtag; //해시태그

    @OrderBy("id")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    /*순환참조 이슈로 ToString 제외*/
    @ToString.Exclude
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

    @CreatedDate @Column(nullable = false) private LocalDateTime createdAt; //생성일시
    @CreatedBy @Column(nullable = false, length =100) private String createdBy; //생성자
    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedAt; //수정일시
    @LastModifiedBy @Column(nullable = false, length =100) private String modifiedBy; //수정자

    protected Article(){
    }

    private Article(String title, String content, String hashtag){
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static Article of(String title, String content, String hashtag){
        return new Article(title, content, hashtag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        /*if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return id.equals(article.id);*/

        /*java 14 pattern matching*/
       if (!(o instanceof Article article)) return false;
        return id != null && id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
