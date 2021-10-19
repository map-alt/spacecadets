import java.util.Scanner;
import java.net.*;
import java.io.*;
import java.util.regex.*;

public class EmailToName {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the email id: ");
        String id = scan.next();
        scan.close();

        String urlString = "https://www.ecs.soton.ac.uk/people/" + id;

        URL url;
        InputStream inputStream;
        BufferedReader bufferedReader = null;
        String line;

        try {
            url = new URL(urlString);
            inputStream = url.openStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            //URLConnection connection = url.openConnection();
            //inputStream = connection.getInputStream();

            Pattern pattern = Pattern.compile("(?<=property=\"name\">)[^<]*");
            boolean matchFound = false;
            while ((line = bufferedReader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                matchFound = matcher.find();
                if (matchFound) {
                    System.out.println(matcher.group(0));
                    break;
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}


