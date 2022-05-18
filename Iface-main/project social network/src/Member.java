public abstract class Member {
    private String name;
    private String login;
    public Member(String name, String login){
        this.name = name;
        this.login = login;
    }
    public String getLogin(){
        return this.login;
    }
    public String getType(){
        return "";
    };

}
