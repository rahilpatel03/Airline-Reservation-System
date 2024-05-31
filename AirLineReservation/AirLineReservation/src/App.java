import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        // File f=new File("RulesforPhn.txt");

        // System.out.println(f.getAbsolutePath());
        // System.out.println((int)(Math.random()*10000));
        // String s="|                                                 |";
        // System.out.println(s.length());
        // String passengerName = Main.Users.get(Main.logged_userId).UserName;
        // System.out.println(passengerName);
    }


    void LearningLisPrinter(String rtoCode, String uniqueValue, String classOfV, String issuedate, String validty,
            String name, String middlename, String dob) throws Exception {
        FileWriter fw = new FileWriter("GroupTest2.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(" _________________________________________________ ");
        bw.newLine();
        bw.write("|                                                 |");
        bw.newLine();
        bw.write("|                LEARNER'S LICENCE                |");
        bw.newLine();
        bw.write("|                  GUJARAT STATE                  |");
        bw.newLine();
        bw.write("|");
        String s1 = String.format("%50s", "|");
        bw.write(s1);
        bw.newLine();
        bw.write("| LL No. : GJ" + rtoCode + "/" + uniqueValue + "/2023");
        String s2 = String.format("%23s", "|");
        bw.write(s2);
        bw.newLine();
        bw.write("| COV : " + classOfV);
        int space = 50 - (classOfV.length() + 7);
        String s3 = String.format("%" + space + "s", "|");
        bw.write(s3);
        bw.newLine();
        bw.write("| Valid From : " + issuedate + "   Valid To : " + validty + " |");
        bw.newLine();
        bw.write("| Name : " + name);
        int space2 = 50 - (name.length() + 8);
        String s4 = String.format("%" + space2 + "s", "|");
        bw.write(s4);
        bw.newLine();
        bw.write("| S/D/W of : " + middlename);
        int space3 = 50 - (middlename.length() + 12);
        String s5 = String.format("%" + space3 + "s", "|");
        bw.write(s5);
        bw.newLine();
        bw.write("| D.O.B. : " + dob);
        String s6 = String.format("%30s", "|");
        bw.write(s6);
        bw.newLine();
        bw.write("|_________________________________________________|");
        bw.flush();
    }
}
