package kr.codesquad.cafe.post;

import kr.codesquad.cafe.comment.Comment;
import kr.codesquad.cafe.global.exception.InsufficientPermissionException;
import kr.codesquad.cafe.post.exception.DeletionFailedException;
import kr.codesquad.cafe.user.domain.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NamedEntityGraph(
        name = "Post.withComments",
        attributeNodes = @NamedAttributeNode("comments")
)
@Entity
public class Post {
    @OneToMany(mappedBy = "post")
    protected List<Comment> comments = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne
    private User user;
    private String nickname;
    private String title;
    @Lob
    private String textContent;
    private LocalDateTime createdDateTime;
    private boolean isDeleted;


    public Post() {
    }

    private Post(Builder builder) {
        this.id = builder.id;
        this.nickname = builder.nickname;
        this.createdDateTime = builder.createdDateTime;
        this.title = builder.title;
        this.textContent = builder.textContent;
        this.user = builder.user;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTitle() {
        return title;
    }

    public String getTextContent() {
        return textContent;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void disable() {
        isDeleted = true;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    private boolean canDelete() {
        return getComments().stream()
                .filter(comment -> !comment.isSameUserId(user.getId()))
                .allMatch(Comment::isDeleted);
    }

    public void delete() {
        if (canDelete()) {
            disable();
            return;
        }
        throw new DeletionFailedException();
    }

    public void checkPermission(long userId) {
        if (!user.isSameId(userId)) {
            throw new InsufficientPermissionException();
        }
    }

    public void update(String textContent, String title) {
        this.textContent = textContent;
        this.title = title;
    }

    public static class Builder {
        private Long id;

        private String nickname;

        private String title;

        private String textContent;

        private LocalDateTime createdDateTime;
        private User user;

        public Builder() {

        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder textContent(String textContent) {
            this.textContent = textContent;
            return this;
        }

        public Builder createdDateTime(LocalDateTime createdDateTime) {
            this.createdDateTime = createdDateTime;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Post build() {
            return new Post(this);
        }
    }
}
