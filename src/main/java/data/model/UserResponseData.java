package data.model;

/**
 * Class that represents data got from DataProvider, required for further processing.
 */
public class UserResponseData {
    private String id;
    private String login;
    private String name;
    private String type;
    private String avatarUrl;
    private String createdAt;
    private String followers;
    private String public_repos;

    public UserResponseData(String id, String login, String name, String type, String createdAt, String avatarUrl, String followers, String public_repos) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.type = type;
        this.createdAt = createdAt;
        this.avatarUrl = avatarUrl;
        this.followers = followers;
        this.public_repos = public_repos;
    }

    public UserResponseData() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getPublic_repos() {
        return public_repos;
    }

    public void setPublic_repos(String public_repos) {
        this.public_repos = public_repos;
    }
}
