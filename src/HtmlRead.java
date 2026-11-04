import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HtmlRead {

    public static void main(String[] args) {
        HtmlRead html = new HtmlRead();
    }

    public HtmlRead() {

        try {
            System.out.println();
            System.out.print("hello \n");
            URL url = new URL("https://www.britannica.com/topic/America-television-series");

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
                    if (line.charAt(x) == '/' && line.charAt(x+1) == '/') {
                        link_count = link_count + 1;
                    }
                }

                    for (int x = 0; x < line.length(); x++) {
                        int start_index = line.indexOf("https:", x);

                        if(start_index == -1){
                            break;
                        }

                        end_index = line.indexOf('"',start_index);

                        if(end_index != -1){
                            String new_substring = line.substring(start_index, end_index);
                            System.out.println(new_substring);
                            x = end_index;
                        }
                    }

                //todo: add end index and account for the last element in the link url
            }
            reader.close();
        } catch(Exception ex) {
            System.out.println(ex);
        }

    }

}
