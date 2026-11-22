import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;
import java.util.Scanner;

public class HtmlRead {

    public String user_string;
    public String user_link;
    public String programmer_designated_string = "";
    public boolean pass_test = false;

    public static void main(String[] args) {

        HtmlRead html = new HtmlRead();
        while(true) {
            if(html.real_test()) {
                html.read_inputs();
                html.htmlRead();
            } else {
                System.out.println("Wrong answer! lol, try again!");
            }
        }

        //gotta have html. cause its an object of it

    }

    public boolean real_test(){
        Random rand = new Random();
        int num = rand.nextInt(0,100);
        int num2 = rand.nextInt(500, 999);

        Scanner myObj = new Scanner(System.in);
        System.out.println("what is " + num + " + " + num2 + " = ___ ?");
        int answer = myObj.nextInt();
        if(answer == num + num2) {
            pass_test = true;
            return true;
        }
        pass_test = false;
        return false;
    }

    public void read_inputs(){
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("User Designated Link: ");
        user_link = myObj.nextLine();
        if(programmer_designated_string != ""){
            System.out.println("User Designated String Filter: " + programmer_designated_string);
            user_string = programmer_designated_string;
        }else {
            System.out.println("User Designated String Filter: ");
            user_string = myObj.nextLine();  // Read user input
            System.out.println("User Designated String Filter: " + user_string);
        }
        System.out.println("User Designated Link: " + user_link);
          // Output user input
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
            System.out.println("Valid Link!");

            String line;
            boolean found_match = false;
            //todo: add sufficient error messages
            //todo: add programmer specificed option. e.g. if programmer specified string = blank,✅

            while ( (line = reader.readLine()) != null ) {
                //find if there are multiple https in one line
                //use the string challenges
                int end_index = 0;
//                int link_count = 0;
//                int count_index_Of = 0;
//                int index = 0;

                //String href_finder = "href";

            //todo: check filter through multiple lines✅


//                for(int x = 0; x < line.length() - 1; x++) {
//                    //System.out.println("look for link_count");
//                    if (line.charAt(x) == '/' && line.charAt(x+1) == '/') {
//                        link_count = link_count + 1;
//                    }
//                }
// the above commented out code is not needed because the below for-loop

                for (int x = 0; x < line.length(); x++) {
                    //System.out.println("start reading indexes");
                    int start_index = line.indexOf("href=", x);
                    //fix this isssue✅

                    if(start_index == -1){
                        break;
                    }
                    //System.out.println("hi");
                    //the code DID NOT GET to the "hi" line; ergo, the error lies above

                    if(line.charAt(start_index + 5) == '\''){
                        end_index = line.indexOf(" '", start_index + 6);
                    } else if (line.charAt(start_index + 5) == '\"') {
                        end_index = line.indexOf('"', start_index + 6);
                    }
                    //todo: why does the code work when start_index is set to 6 and not when it is set to 5✅
                    //todo: make it look for single quotation marks too✅
                    //todo: add to index the index right after href=, to see it that is single quotes or double quotes, then check end_index accordingly to that✅

                    if(end_index != -1){
                        //System.out.println("end_index ！= -1");
                        String new_substring = line.substring(start_index + 6, end_index);
                        if(new_substring.contains(user_string)) {
                            System.out.println(new_substring);
                            found_match = true;
                        }
                        x = end_index + 1;
                    } else {
                        break;
                    }
                }
                //todo: add a specified String that the user can input into the textbox below to act as a specified String✅
                //todo: fix partial link issue with reference to this website below:❌(no need for this)
                //todo: debug the user_specified_string issue✅
            }
            reader.close();

            if(!found_match){
                System.out.println("Uh Oh! No link matched the chosen String!");
            }

        } catch(Exception ex) {
            System.out.println("Invalid Link or connection failed!");
            System.out.println(ex);

        }
    }
}

/***
 * Correctly handles and prints multiple links in the same HTML line✅
 * The user can input whatever link they want✅
 * The user can dictate the string that filters the links✅
 * All of the links on the page are found and printed✅
 * The user can re run the program with out hitting the play button✅
 * Some other spicy extension!✅
 *
 */
