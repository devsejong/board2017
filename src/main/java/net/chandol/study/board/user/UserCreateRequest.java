package net.chandol.study.board.user;

@lombok.Data
public class UserCreateRequest {
    private String username;
    private String email;
    private String password;

    @lombok.experimental.Tolerate
    public UserCreateRequest(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
