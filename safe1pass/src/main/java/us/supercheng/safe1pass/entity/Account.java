package us.supercheng.safe1pass.entity;

/**
 * Created by hongyu on 7/16/17.
 */
public class Account {
    private Integer account_id;
    private String username;
    private String password;

    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
