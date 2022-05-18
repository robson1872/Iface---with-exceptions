import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public abstract class Main {
    public static boolean hasDigits(String str)
    {
        for (int i = 0; i < str.length(); i++){
            if (Character.isDigit(str.charAt(i))){
                return true;
            }
        }
        return false;
    }
    public static boolean isDigit(String str)
    {
        if (Character.isDigit(str.charAt(0))){
            return true;
        }
        return false;
    }
    public static int getIdByName(ArrayList< ? extends General > users) throws Exception {
        Scanner input22 = new Scanner(System.in);
        System.out.print("Type the name: ");
        String name = input22.nextLine();
        if(hasDigits(name)){
            throw new Exception("Number is not allowed."); 
        }
        int id = - 1;
        for(int i = 0; i < users.size(); i++) {
            String curr = users.get(i).getName();
            if (name.equals(curr)) {
                id = i;
            }
        }
        return id;
    }
    public static int getIdBylogin(ArrayList<User> users){
        Scanner input22 = new Scanner(System.in);
        System.out.print("Type the login user: ");
        String login = input22.nextLine();
        int id = - 1;
        for(int i = 0; i < users.size(); i++) {
            String curr = users.get(i).getLogin();
            if (login.equals(curr)) {
                id = i;
            }
        }
        return id;
    }
    public static int IdBylogin(ArrayList<User> users, String login){
        int id = - 1;
        for(int i = 0; i < users.size(); i++) {
            String curr = users.get(i).getLogin();
            if (login.equals(curr)) {
                id = i;
            }
        }
        return id;
    }
    public static boolean getPassword(User x){
        Scanner input22 = new Scanner(System.in);
        System.out.print("Type the password: ");
        String password = input22.next();
        return password.equals(x.getPassword());
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<User>();
        ArrayList<Comunity> comunities = new ArrayList<Comunity>();
        System.out.println("WELCOME TO IFACE \n");
        while(true){
            int op = -1;
            while(op == -1){
                try{
                    Scanner input23 = new Scanner(System.in);
                    System.out.println("Press (1) to login/ (2) to create a new user/ (3) to get out");
                    System.out.print("Type: ");
                    op = input23.nextInt();
                } catch(Exception e){
                    System.out.println("You can only type numbers, Exception error: " + e.getMessage());
                    continue;
                }  
            }   
            if(op == 1){
                int id = getIdBylogin(users);
                boolean ok = false;
                if(id == -1){
                    System.out.println("USER DOESN'T EXIST!");
                }else{
                    ok = getPassword(users.get(id));
                    if(!ok){
                        System.out.println("WRONG PASSWORD!");
                    }else{
                        System.out.println("SUCCESSFUL LOGIN!");
                    }
                }
                if(ok){
                    while(true) {
                        System.out.println("Press (1) to EDIT USER/ (2) to ADD FRIEND/ (3) to SEND A MESSAGE/ (4) to " +
                                "CREATE A COMMUNITY/ (5) COMMUNITIES / (6) SHOW MY USER/ (7) to DELETE MY ACCOUNT/" +
                                " (8) to SEND A NEW FEED/(9) Visit an user /(10) to LOGOUT ");
                        System.out.print("type:");
                        int op2 = input.nextInt();
                        Scanner input3 = new Scanner(System.in);
                        Scanner input4 = new Scanner(System.in);
                        if (op2 == 1) {// EDIT USER
                            System.out.println("   Editing user  \n");
                            System.out.println("Press (1) to change the name/ (2) to change the login/ " +
                                    "(3) to change the password/ (4) to " +
                                    "to change the age/ (5) to change the address/ (6) Privacity ");
                            System.out.print("Type:");
                            int choice = input3.nextInt();
                            if(choice == 1){
                                System.out.print("Type the new name: ");
                                String newName = input4.nextLine();
                                users.get(id).setName(newName);
                            }else if(choice == 2){
                                System.out.print("Type the new login: ");
                                String newName = input4.next();
                                users.get(id).setLogin(newName);
                            }else if(choice == 3){
                                System.out.print("Type the new password: ");
                                String newName = input4.next();
                                users.get(id).setPassword(newName);
                            }else if(choice == 4){
                                System.out.print("Type the new age: ");
                                int age = input4.nextInt();
                                users.get(id).setAge(age);
                            }else if(choice == 5){
                                System.out.print("The the new address: ");
                                String newName = input4.next();
                                users.get(id).setAddress(newName);
                            }else if(choice == 6){
                                System.out.println("Do you want that your feed can only seen by your friends?(YES) or (NO) ");
                                System.out.print("Type: ");
                                String op3 = input4.next();
                                if(op3.equals("NO")){
                                    users.get(id).setJustFriends(false);
                                }else{
                                    users.get(id).setJustFriends(true);
                                }
                            }else{
                                System.out.println("The choice doesn't exist!");
                            }
                        } else if (op2 == 2) {
                            try {
                                System.out.println("   Add a Friend  \n");
                                int id2 = getIdByName(users);
                                if(id2 == -1){
                                    System.out.println("USER DOESN'T EXIST!");
                                    continue;
                                }
                                String name2 = users.get(id2).getName();
                                String name1 = users.get(id).getName();
                                if(users.get(id).searchInvite(name2)){
                                    users.get(id).addUser(name2);
                                    users.get(id2).addUser(name1);
                                    System.out.println(name2 + " now is friend of " + name1);
                                }else{
                                    users.get(id2).inviteFriend(name1);
                                    System.out.println("The invite was send to " + name2);
                                }
                            } catch (Exception e) {
                                System.out.println("Exception error: " + e.getMessage());
                            }
                            
                        } else if (op2 == 3){
                            try {
                                System.out.println("  Sending a message  \n");
                                String msg;
                                int id2 = getIdByName(users);
                                if(id2 == -1){
                                    System.out.println("USER DOESN'T EXIST!");
                                    continue;
                                }
                                System.out.print("Type the message: ");
                                msg = input3.nextLine();
                                users.get(id2).getMessage(msg);
                                System.out.println("The message was sent! \n ");
                            } catch (Exception e) {
                                System.out.println("Exception error: " + e.getMessage());
                            }
                        } else if (op2 == 4) {// create a community
                            try {
                                System.out.println("  Creating a community  \n");
                                String nameCommunity, description;
                                System.out.print("Type the name: ");
                                nameCommunity = input3.nextLine();
                                if(hasDigits(nameCommunity)) {
                                    throw new Exception("Number is not allowed.");
                                }
                                System.out.print("Type the description: ");
                                description = input3.nextLine();
                                Comunity aux = new Comunity(nameCommunity,users.get(id),description);
                                comunities.add(aux);
                                users.get(id).addCommunity(nameCommunity);
                                System.out.println("The community was created! \n ");
                            } catch (Exception e) {
                                System.out.println("Exception error: " + e.getMessage());
                            }
                        } else if (op2 == 5) {// comunities
                            try {
                                System.out.println("Choose one of the communities \n");
                                users.get(id).showCommunities();
                                int id2 = getIdByName(comunities);
                                if(id2 == -1){
                                    System.out.println("COMMUNITY DOESN'T EXIST!");
                                    continue;
                                }
                                int id4 = comunities.get(id2).searchMember(users.get(id).getLogin());
                                String type = comunities.get(id2).showTypeMember(id4);
                                System.out.println("Type: " + type);
                                System.out.println("What do you wanna do? (1) See the News, (2) Add an user, (3) Create a news");
                                int choice2 = input3.nextInt();
                                if(choice2 == 1){
                                    comunities.get(id2).showNews();
                                }else if(choice2 == 2){
                                    if(!comunities.get(id2).isModerator(id4)){
                                        continue;
                                    }
                                    int id3 = getIdByName(users);
                                    if(id3 == -1){
                                        System.out.println("USER DOESN'T EXIST!");
                                        continue;
                                    }
                                    users.get(id3).addCommunity(comunities.get(id2).getName());
                                    Member newuser = new Normal(users.get(id3).getName(),users.get(id3).getLogin());
                                    comunities.get(id2).addUser(newuser);
                                    System.out.println("The user was added! \n ");
                                }else{
                                    Scanner input6 = new Scanner(System.in);
                                    System.out.print("Type the news: ");
                                    String neews = input6.nextLine();
                                    comunities.get(id2).addNews(neews);
                                }
                            } catch (Exception e) {
                                System.out.println("exception: " + e.getMessage());
                            }
                            
                        } else if (op2 == 6) {
                            System.out.println("  Showing my user  \n");
                            System.out.println("Nome: " + users.get(id).getName() +"| Age: "+users.get(id).getAge() +"| Address: "+users.get(id).getAddress());
                            users.get(id).showFriends();
                            users.get(id).showNews();
                            users.get(id).showMessages();
                        } else if (op2 == 7) {
                            try {
                                System.out.println("  Deleting my user  \n");
                                System.out.println("Are you sure?(YES or NO)");
                                System.out.print("Type: ");
                                String op3 = input3.next();
                                if(!op3.equals("NO") && !op3.equals("YES")){
                                    throw new Exception("This is not an option!"); 
                                }
                                if(op3.equals("NO")){
                                }else{
                                    users.remove(id);
                                    System.out.println("User was deleted!");
                                    break;
                                }
                            } catch (Exception e) {
                                System.out.println("Exception error: " + e.getMessage());
                            }
                            
                        } else if (op2 == 8) {
                            System.out.println("  Send a news feed  \n");
                            System.out.print("Type: ");
                            String news = input3.nextLine();
                            users.get(id).getNews(news);
                            System.out.println("News feed was add!");
                        } else if (op2 == 9) {
                            try {
                                System.out.println("  Visiting a user  \n");
                                int id3 = getIdByName(users);
                                if(id3 == -1){
                                    throw new Exception("User doesn't exist!"); 
                                }
                                if(users.get(id3).justFriends && !users.get(id3).searchFriend(users.get(id).getName())){
                                    System.out.println("The user Account is private!");
                                }else{
                                    users.get(id3).showNews();
                                }
                            } catch (Exception e) {
                                System.out.println("Exception error: " + e.getMessage());
                            }
                        }else {
                            System.out.println("SUCCESSFUL LOGOUT!");
                            break;
                        }
                    }
                }
            }else if( op == 2){
                try{
                    System.out.println("  Creating Account  ");
                    Scanner input2 = new Scanner(System.in);
                    Scanner input3 = new Scanner(System.in);
                    Scanner input4 = new Scanner(System.in);
                    String name, login,password;
                    System.out.print("Type the name: ");
                    name = input2.next();
                    if(hasDigits(name)) {
                        throw new Exception("Number is not allowed.");
                    }
                    System.out.print("Type the login: ");
                    login = input3.next();
                    if (isDigit(login)){
                        throw new Exception("Starting with number is not allowed.");
                    }
                    int x = IdBylogin(users,login);
                    if(x != -1){
                        throw new Exception("This login already exist!");
                    }
                    System.out.print("Type the password: ");
                    password = input4.next();
                    //System.out.println(name + " " + login + " " + password);
                    User aux = new User(name,login,password);
                    users.add(aux);
                    System.out.println("REGISTERED USER!\n");
                    //input2.close(); input3.close(); input4.close();
                }catch (Exception e) {
                    System.out.println("Exception error: " + e.getMessage());
                }
            }else if(op == 3){
                System.out.println("BYE!!\n");
                break;
            }else{
                System.out.println("This option doesn't exist!!\n");
            }
        }
    }
}
