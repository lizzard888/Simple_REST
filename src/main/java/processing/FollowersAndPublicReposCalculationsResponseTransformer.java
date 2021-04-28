package processing;

/**
 * Class for concrete followers and public_repos calculation method.
 */
public class FollowersAndPublicReposCalculationsResponseTransformer extends TwoAttributesCalculationsWithArgumentsRemovalResponseTransformer {

    public FollowersAndPublicReposCalculationsResponseTransformer() {
        super("followers", "public_repos");
    }

    protected String calculate(int followers, int publicRepos) {
        if(followers == 0)
            // No mathematical correct result.
            return "Not a number";
        return String.valueOf(6/followers * (2+publicRepos));
    }
}
