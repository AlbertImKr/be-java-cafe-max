package kr.codesqaud.cafe.account.form;

import kr.codesqaud.cafe.account.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ProfileSettingForm {
    @NotEmpty
    @Size(max = 64, min = 2, message = "{error.nickname.size}")
    private final String nickname;
    @NotEmpty
    @Email
    private final String email;
    @NotEmpty
    @Size(max = 32, min = 8, message = "{error.password.size}")
    @Pattern(regexp = "^(.*[a-z]+.*[1-9]+가.*)|(.*[1-9]+.*[a-z]+.*)$", message = "{error.password.pattern}")
    private final String password;

    public ProfileSettingForm(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    private ProfileSettingForm(Builder builder) {
        this.email = builder.email;
        this.nickname = builder.nickname;
        this.password = builder.password;
    }

    public static ProfileSettingForm from(User user) {
        return new Builder()
                .password(user.getPassword())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .build();
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

    public User setUser(User user) {
        return new User.Builder()
                .id(user.getId())
                .email(email)
                .nickname(nickname)
                .password(user.getPassword())
                .build();
    }

    public static class Builder {
        private String nickname;
        private String email;
        private String password;

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

        public ProfileSettingForm build() {
            return new ProfileSettingForm(this);
        }
    }
}
