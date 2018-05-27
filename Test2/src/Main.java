import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        ArrayList<Name> nameList = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        while(!line.equals("end")) {

            String[] strArr = line.split(",");

            for (int i = 0; i < strArr.length; i++) {

                strArr[i] = strArr[i].trim();
            }

            nameList.add(new Name(Integer.parseInt(strArr[0]), strArr[1], strArr[2]));

            line = br.readLine();
        }

        String ret = "\"";

        // result #1
        /*
        for (Name name : nameList) {

            if (name.gender == 1) {

                ret += name.name;
                ret += "\",\n";
                ret += "\"";
            }
        }

        */

        // result #2
        for (Name name : nameList) {

            ret += name.name;
            ret += "\": \"";
            ret += name.mean;
            ret += "\",\n";
            ret += "\"";
        }

        System.out.println(ret);
    }
}

class Name {

    int gender;
    String name;
    String mean;

    Name(int gender, String name, String mean) {

        this.gender = gender;
        this.name = name;
        this.mean = mean;
    }

    @Override
    public String toString() {

        return gender + ", " + name + ", " + mean + "\n";
    }
}