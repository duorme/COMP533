import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 4/8/17.
 */
public class InsertDataFromFile {
    Connection conn=null;
    public InsertDataFromFile(Connection conn){

        this.conn=conn;
    }
    public void InsertOrg(String id,String name,Boolean is_univ){

        try {
            CallableStatement InsertOrg = conn.prepareCall("{call InsertToOrg(?,?,?)}");
            InsertOrg.setString(1,id);
            InsertOrg.setString(2,name);
            InsertOrg.setBoolean(3,is_univ);
            InsertOrg.execute();
            InsertOrg.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void CheckAndInsertOrg(List<String[]> value){
        for(String[] list :value) {
            InsertOrg(list[0], list[1], Boolean.parseBoolean(list[2]));
        }
    }


    public void InsertMeet(String name, Date start_date, int num_days, String org_id){
        try {
            CallableStatement InsertMeet = conn.prepareCall("{call InsertToMeet(?,?,?,?)}");
            InsertMeet.setString(1,name);
            InsertMeet.setDate(2,start_date);
            InsertMeet.setInt(3,num_days);
            InsertMeet.setString(4,org_id);
            InsertMeet.execute();
            InsertMeet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void CheckAndInsertMeet(List<String[]>value){
        for(String[] list :value) {
            Date date=java.sql.Date.valueOf(list[1]);
            InsertMeet(list[0], date, Integer.parseInt(list[2]), list[3]);
        }
    }
    public void InsertParticipant(String id, String gender, String org_id , String name){
        try {
            CallableStatement Insert = conn.prepareCall("{call InsertToParticipant(?,?,?,?)}");
            Insert.setString(1,id);
            Insert.setString(2,gender);
            Insert.setString(3,org_id);
            Insert.setString(4,name);
            Insert.execute();
            Insert.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void  CheckAndInsertParticipant(List<String[]>value){
        for(String[] list :value) {
            if(list[2].matches("[UO]\\d+")) {
                InsertParticipant(list[0], list[1], list[2], list[3]);
            }
        }
    }
    public void InsertLeg(int leg){
        try {
            CallableStatement Insert = conn.prepareCall("{call InsertToLeg(?)}");
            Insert.setInt(1,leg);
            Insert.execute();
            Insert.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void  CheckAndInsertLeg(List<String[]>value){
        for(String[] list :value) {

                InsertLeg(Integer.valueOf(list[0]));
        }
    }
    public  void InsertStroke(String stroke){
        try {
            CallableStatement Insert = conn.prepareCall("{call InsertToStroke(?)}");
            Insert.setString(1,stroke);
            Insert.execute();
            Insert.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public  void  CheckAndInsertStroke(List<String[]>value){
        for(String[] list :value) {
            if(list[0].matches("[a-zA-Z]+")) {
                InsertStroke(list[0]);
            }
        }
    }

    public  void InsertDistance(int distance){
        try {
            CallableStatement Insert = conn.prepareCall("{call InsertToDistance(?)}");
            Insert.setInt(1,distance);
            Insert.execute();
            Insert.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void  CheckAndInsertDistance(List<String[]>value){
        for(String[] list :value) {
            if(list[0].matches("\\d+")) {
                InsertDistance(Integer.valueOf(list[0]));
            }
        }
    }
    public  void InsertEvent(String id,String gender,int distance){
        try {
            CallableStatement Insert = conn.prepareCall("{call InsertToEvent(?,?,?)}");
            Insert.setString(1,id);
            Insert.setString(2,gender);
            Insert.setInt(3,distance);
            Insert.execute();
            Insert.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void  CheckAndInsertEvent(List<String[]>value){
        for(String[] list :value) {
            if(list[0].matches("E\\d+") && list[1].matches("^[FM]$") && list[2].matches("\\d+")) {
                InsertEvent(list[0],list[1],Integer.valueOf(list[2]));
            }
        }
    }
    public void InsertToStrokeOf(String event_id,int leg,String stroke){
        try {
            CallableStatement Insert = conn.prepareCall("{call InsertToStrokeOf(?,?,?)}");
            Insert.setString(1,event_id);
            Insert.setInt(2,leg);
            Insert.setString(3,stroke);
            Insert.execute();
            Insert.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void  CheckAndInsertStrokeOf(List<String[]>value){
        for(String[] list :value) {
            if(list[0].matches("E\\d+") && list[1].matches("\\d+") && list[2].matches("[a-zA-Z]+")) {
                InsertToStrokeOf(list[0],Integer.valueOf(list[1]),list[2]);
            }
        }
    }
    public void InsertToHeat(int id,String event_id,String meet_name){
        try {
            CallableStatement Insert = conn.prepareCall("{call InsertToHeat(?,?,?)}");;
            Insert.setInt(1,id);
            Insert.setString(2,event_id);
            Insert.setString(3,meet_name);
            Insert.execute();
            Insert.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void  CheckAndInsertHeat(List<String[]>value){
        for(String[] list :value) {
            if(list[0].matches("\\d+") && list[1].matches("E\\d+") && list[2].matches("\\w+")) {
                InsertToHeat(Integer.valueOf(list[0]),list[1],list[2]);
            }
        }
    }
    public void InsertToSwim(int heat_id,String event_id,String meet_name,String partition_id,int leg,double _time){
        try {
            CallableStatement Insert = conn.prepareCall("{call InsertToSwim(?,?,?,?,?,?)}");;
            Insert.setInt(1,heat_id);
            Insert.setString(2,event_id);
            Insert.setString(3,meet_name);
            Insert.setString(4,partition_id);
            Insert.setInt(5,leg);
            Insert.setDouble(6,_time);
            Insert.execute();
            Insert.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void  CheckAndInsertSwim(List<String[]>value){
        for(String[] list :value) {
            InsertToSwim(Integer.valueOf(list[0]), list[1], list[2], list[3], Integer.valueOf(list[4]), Double.valueOf(list[5]));
        }
    }




    public void LoadData(String file){
        csvReader reader = new csvReader();
        HashMap<String,List<String[]>> tables = reader.LoadData(file);

        if(tables.containsKey("Org")){
            CheckAndInsertOrg(tables.get("Org"));}
        if(tables.containsKey(("Meet"))){
            CheckAndInsertMeet(tables.get("Meet"));}
        if(tables.containsKey(("Participant"))) {
            CheckAndInsertParticipant(tables.get("Participant"));
        }
        if(tables.containsKey("Leg")){
            CheckAndInsertLeg(tables.get("Leg"));
        }
        if(tables.containsKey("Stroke")){
            CheckAndInsertStroke(tables.get("Stroke"));
        }
        if(tables.containsKey("Distance")){
//            List<String[]>value= tables.get("Distance");
//            for(String[] a : value){
//                System.out.println(Arrays.toString(a));
//            }
            CheckAndInsertDistance(tables.get("Distance"));
        }
        if(tables.containsKey("Event")){
            CheckAndInsertEvent(tables.get("Event"));
        }
        if(tables.containsKey("StrokeOf")){
            CheckAndInsertStrokeOf(tables.get("StrokeOf"));
        }
        if(tables.containsKey("Heat")){
            CheckAndInsertHeat(tables.get("Heat"));
        }
        if(tables.containsKey("Swim")){
            CheckAndInsertSwim(tables.get("Swim"));
        }
    }


}
