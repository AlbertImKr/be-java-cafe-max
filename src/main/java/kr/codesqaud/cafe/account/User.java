package kr.codesqaud.cafe.account;

public class User {

    public static final String BLANK = "";
    private final Long id;
    private final String nickname;
    private final String email;
    private final String password;

    private final Role role;

    private User(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.password = builder.password;
        this.nickname = builder.nickname;
        this.role = builder.role;
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public boolean isSamePassword(String targetPassword) {
        return targetPassword.equals(password);
    }

    public boolean isSameEmail(String email) {
        return this.email.equals(email);
    }

    public static class Builder {
        private Long id = Long.MIN_VALUE;
        private String nickname = BLANK;
        private String email = BLANK;
        private String password = BLANK;
        private Role role = Role.USER;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;
            return this;
        }


        public Builder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

}
