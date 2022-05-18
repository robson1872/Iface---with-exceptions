public class Normal extends Member{
    public Normal(String name, String login){
        super(name,login);
    }
    @Override
    public String getType(){
        return "Normal";
    }
}
