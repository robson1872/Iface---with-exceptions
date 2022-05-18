public class Moderator extends Member{
    public Moderator(String name, String login) {
        super(name, login);
    }
    @Override
    public String getType(){
        return "Moderator";
    }
}
