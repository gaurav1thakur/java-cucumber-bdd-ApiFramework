package helper;

import java.io.IOException;
import java.util.ArrayList;

public class TestSample {
    public static void main(String[] args) throws IOException {
        DataDriven dataDriven = new DataDriven();
        ArrayList<String> al = dataDriven.getData("UpdatePlace");
        System.out.println(al.get(0));
        System.out.println(al.get(1));
        System.out.println(al.get(2));
        System.out.println(al.get(3));
        System.out.println(al.get(4));
    }
}
