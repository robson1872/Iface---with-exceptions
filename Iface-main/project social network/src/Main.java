import java.util.ArrayList;
import java.util.Scanner;
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
        return Character.isDigit(str.charAt(0));
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
    public static int getIdBylogin(ArrayList<User> users){
        Scanner input22 = new Scanner(System.in);
        System.out.print("Type the login user: ");
        String login = input22.nextLine();
        return IdBylogin(users,login);
    }

    public static boolean getPassword(User x){
        Scanner input22 = new Scanner(System.in);
        System.out.print("Type the password: ");
        String password = input22.next();
        return password.equals(x.getPassword());
    }
    // new methods
    public static int showOptions(){
        int op = 0;
        try{
            Scanner input23 = new Scanner(System.in);
            System.out.println("Press (1) to login/ (2) to create a new user/ (3) to get out");
            System.out.print("Type: ");
            op = input23.nextInt();
        } catch(Exception e){
            System.out.println("You can only type numbers, Exception error: " + e.getMessage());
        }
        return op;
    }
    public static boolean loginUser(ArrayList<User> users,int id){
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
        return ok;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Comunity> comunities = new ArrayList<>();
        System.out.println("WELCOME TO IFACE \n");
        while(true){
            int op = -1;
            while(op == -1){
                op = showOptions();
            }   
            if(op == 1){
                int id = getIdBylogin(users);
                boolean ok = loginUser(users,id);
                if(ok){
                    while(true) {
                        Menu.showMenu();
                        int op2 = input.nextInt();
                        Scanner input3 = new Scanner(System.in), input4 = new Scanner(System.in);
                        if (op2 == 1) {// EDIT USER
                            Menu.showEdit();
                            int choice = input3.nextInt();
                            if(choice == 1){
                                try{
                                    System.out.print("Type the new name: ");
                                    String newName = input4.nextLine();
                                    if(hasDigits(newName)) {
                                        throw new Exception("Number is not allowed.");
                                    }
                                    users.get(id).setName(newName);
                                }catch (Exception e) {
                                    System.out.println("Exception error: " + e.getMessage());
                                }

                            }else if(choice == 2){
                                try{
                                    System.out.print("Type the new login: ");
                                    String newLogin = input4.nextLine();
                                    if (isDigit(newLogin)){
                                        throw new Exception("Starting with number is not allowed.");
                                    }
                                    int x = IdBylogin(users,newLogin);
                                    if(x != -1){
                                        throw new Exception("This login already exist!");
                                    }
                                    users.get(id).setLogin(newLogin);
                                }catch (Exception e) {
                                    System.out.println("Exception error: " + e.getMessage());
                                }
                            }else if(choice == 3){
                                try{
                                    System.out.print("Type the new password: ");
                                    String newPassword = input4.next();
                                    users.get(id).setPassword(newPassword);
                                }catch (Exception e){
                                    System.out.println("Exception error: " + e.getMessage());
                                }
                            }else if(choice == 4){
                                try{
                                    System.out.print("Type the new age: ");
                                    int age = input4.nextInt();
                                    if(age <= 0 || age >= 130){
                                        throw new Exception("This age isn't valid!");
                                    }
                                    users.get(id).setAge(age);
                                }catch (Exception e){
                                    System.out.println("Exception error: " + e.getMessage());
                                }

                            }else if(choice == 5){
                                System.out.print("The the new address: ");
                                String newAddress = input4.next();
                                users.get(id).setAddress(newAddress);
                            }else if(choice == 6){
                                System.out.println("Do you want that your feed can only seen by your friends?(YES) or (NO) ");
                                System.out.print("Type: ");
                                String op3 = input4.next();
                                if(op3.equals("NO")){
                                    users.get(id).setJustFriends(false);
                                }else if(op3.equals("YES")){
                                    users.get(id).setJustFriends(true);
                                }else{
                                    System.out.println("Only type YES or NO!");
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
                                    throw new Exception("USER DOESN'T EXIST!");
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
                                    try{
                                        int id3 = getIdByName(users);
                                        if(id3 == -1){
                                            throw new Exception("USER DOESN'T EXIST!");
                                        }
                                        id3 = comunities.get(id2).searchMember(users.get(id3).getLogin());
                                        if(id3 != -1){
                                            throw new Exception("USER IS ALREADY IN THE COMMUNITIES!");
                                        }
                                        users.get(id3).addCommunity(comunities.get(id2).getName());
                                        Member newUser = new Normal(users.get(id3).getName(),users.get(id3).getLogin());
                                        comunities.get(id2).addUser(newUser);
                                        System.out.println("The user was added! \n ");
                                    }catch (Exception e){
                                        System.out.println("Exception error: " + e.getMessage());
                                    }
                                }else{
                                    try{
                                        Scanner input6 = new Scanner(System.in);
                                        System.out.print("Type the news: ");
                                        String neews = input6.nextLine();
                                        comunities.get(id2).addNews(neews);
                                    }catch (Exception e){
                                        System.out.println("Exception error: " + e.getMessage());
                                    }
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
                                if (!op3.equals("NO")) {
                                    users.remove(id);
                                    System.out.println("User was deleted!");
                                    break;
                                }
                            } catch (Exception e) {
                                System.out.println("Exception error: " + e.getMessage());
                            }
                            
                        } else if (op2 == 8) {
                            try{
                                System.out.println("  Send a news feed  \n");
                                System.out.print("Type: ");
                                String news = input3.nextLine();
                                users.get(id).getNews(news);
                                System.out.println("News feed was add!");
                            }catch (Exception e){
                                System.out.println("Exception error: " + e.getMessage());
                            }

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
                Scanner input2 = new Scanner(System.in), input3 = new Scanner(System.in),input4 = new Scanner(System.in);
                try{
                    System.out.println("  Creating Account  ");
                    String name, login, password;
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
