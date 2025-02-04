package kr.codesquad.cafe.comment;

import kr.codesquad.cafe.post.Post;
import kr.codesquad.cafe.user.domain.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @ManyToOne
    private Post post;
    @ManyToOne
    private User user;
    private String content;
    private boolean isDeleted;

    public Comment() {
    }

    private Comment(Builder builder) {
        this.post = builder.post;
        this.user = builder.user;
        this.content = builder.content;
    }

    public static Builder build() {
        return new Builder();
    }

    public static Comment from(String body, Post post, User user) {
        return new Comment.Builder()
                .content(body)
                .post(post)
                .user(user)
                .build();
    }

    public Long getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public User getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }


    public boolean isDeleted() {
        return isDeleted;
    }

    public void delete(long userId) {
        if (this.isSameUserId(userId)) {
            isDeleted = true;
            return;
        }
        throw new CommentDeletionNotAllowedException();
    }

    public boolean isSameUserId(Long userId) {
        return user.isSameId(userId);
    }

    public static class Builder {
        private Post post;
        private User user;
        private String content;

        public Builder post(Post post) {
            this.post = post;
            return this;
        }


        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Comment build() {
            return new Comment(this);
        }
    }

}
