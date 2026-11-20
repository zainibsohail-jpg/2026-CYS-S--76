package composition;
import java.io.FileWriter;

import java.io.File;
import java.io.IOException;

public class file_hndling {
    static void main(String[] args) throws IOException {
        File fil = new File("abc.txt");
//        fil.write("zainab");
//        fil.close();
        fil.delete();
    }


}
