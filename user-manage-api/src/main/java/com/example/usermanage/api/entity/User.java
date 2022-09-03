package com.example.usermanage.api.entity;

/**
 * Author wangjinshuai
 * Date 2022/9/2 10:32
 **/
public class User {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用状态
     */
    private Integer userStatus;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public static final class UserBuilder {
        private Long id;
        private String name;
        private String email;
        private Integer userStatus;

        private UserBuilder() {
        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder withUserStatus(Integer userStatus) {
            this.userStatus = userStatus;
            return this;
        }

        public User build() {
            User user = new User();
            user.name = this.name;
            user.userStatus = this.userStatus;
            user.id = this.id;
            user.email = this.email;
            return user;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", userStatus=" + userStatus +
                '}';
    }
}
