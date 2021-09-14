import peer.P2PPeer;

import java.util.Scanner;

public class PeerApp {
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        P2PPeer peer = new P2PPeer(getAlias());

        while (true) {
            System.out.print("--> ");
            String cmd = scan.nextLine();

            if (cmd.equalsIgnoreCase("close")) {
                break;
            }

            if (cmd.equalsIgnoreCase("info")) {
                info();
            }

            if(cmd.matches("((send)|(SEND)) @.+ \".+\"")) {
                String toAlias = cmd.split(" ")[1].replace("@", "");
                String text = cmd.substring(cmd.indexOf("\"")+1, cmd.trim().length()-1);
                peer.send(toAlias, text);
            }
        }
    }

    public static String getAlias() {
        System.out.print("Please enter your username: ");
        String alias =  scan.nextLine();
        while (alias == null || alias.trim().equals("") || alias.length() < 3) {
            System.out.println("Username invalid! Username must be longer then 4 characters.");
            System.out.print("Try again! Username: ");
            alias =  scan.nextLine();
        }
        return alias;
    }

    public static void info() {
        System.out.println("Commands\n" +
                "| SEND @username \"message\"" + "| INFO | CLOSE |" );
    }
}
