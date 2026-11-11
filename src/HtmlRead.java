import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class HtmlRead {

    public String user_string;
    public String user_link;

    public static void main(String[] args) {

        HtmlRead html = new HtmlRead();
        while(true) {
            html.read_inputs();
            html.htmlRead();
        }

        //gotta have html. cause its an object of it

    }

    public void read_inputs(){
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("User Designated Link: ");
        user_link = myObj.nextLine();
        System.out.println("User Designated String Filter: ");
        user_string = myObj.nextLine();  // Read user input
        System.out.println("User Designated Link: " + user_link);
        System.out.println("User Designated String Filter: " + user_string);  // Output user input
    }

    public void htmlRead() {

        try {
            System.out.println();
            System.out.print("hello \n");
            URL url = new URL(user_link);

            URLConnection urlc = url.openConnection();
            urlc.setRequestProperty("User-Agent", "Mozilla 5.0 (Windows; U; " + "Windows NT 5.1; en-US; rv:1.8.0.11) ");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlc.getInputStream())
            );
            String line;

            while ( (line = reader.readLine()) != null ) {
                //find if there are multiple https in one line
                //use the string challenges
                int end_index = 0;
                int link_count = 0;

                for(int x = 0; x < line.length() - 1; x++) {
                    //System.out.println("look for link_count");
                    if (line.charAt(x) == '/' && line.charAt(x+1) == '/') {
                        link_count = link_count + 1;
                    }
                }

                for (int x = 0; x < line.length(); x++) {
                    //System.out.println("start reading indexes");
                    int start_index = line.indexOf("href=" + "\"", x);
                    //fix this isssue

                    if(start_index == -1){
                        break;
                    }
                    //System.out.println("hi");
                    //the code DID NOT GET to the "hi" line; ergo, the error lies above

                    end_index = line.indexOf('"',start_index);

                    if(end_index != -1){
                        //System.out.println("end_index ！= -1");
                        String new_substring = line.substring(start_index, end_index);
                        if(new_substring.contains(user_string)) {
                            System.out.println(new_substring);
                        } else{
                            continue;
                        }
                        x = end_index;
                    }
                }

                //todo: add a specified String that the user can input into the textbox below to act as a specified String
                //todo: how to make partial links not partial anymore, do some research!
                //todo: debug the user_specified_string issue
            }
            reader.close();

        } catch(Exception ex) {
            System.out.println(ex);
        }

    }
}