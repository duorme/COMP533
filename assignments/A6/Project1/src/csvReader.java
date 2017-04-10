/**
 * Created by Tong on 4/8/17.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;

import static java.lang.System.in;
import static java.lang.System.setOut;

public class csvReader {
    public HashMap<String,List<String[]>> LoadData(String file) {

//        String csvFile = "/Users/user/Desktop/COMP533/assignments/A6/Assignment6_Data_from_ERD.csv";
        HashMap<String,List<String[]>> tables = new HashMap<>();

        String csvFile = file;
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String table="";

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] data = line.split(cvsSplitBy);
                int index = data[0].indexOf("*");
                if (index != -1) {
                    table=data[0].substring(index+1);
                    tables.put(table, new LinkedList<String[]>());

                } else {
                    tables.get(table).add(data);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return  tables;

    }

}