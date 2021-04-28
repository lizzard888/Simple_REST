package data.model;

/**
 * Class that represents data got from GitHubClient, required for further converting into UserResponseData general format.
 */
public class GitHubUserResponseData extends UserResponseData {
    private String created_at;
    private String avatar_url;

    public GitHubUserResponseData(String id, String login, String name, String type, String createdAt, String avatarUrl, String followers, String public_repos, String created_at, String avatar_url) {
        super(id, login, name, type, createdAt, avatarUrl, followers, public_repos);
        this.created_at = created_at;
        this.avatar_url = avatar_url;
    }

    public GitHubUserResponseData() {
    }

    public UserResponseData convert(){
        return new UserResponseData(this.getId(), this.getLogin(), this.getName(), this.getType(), created_at, avatar_url, this.getFollowers(), this.getPublic_repos());
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}
