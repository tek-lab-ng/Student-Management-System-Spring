package Universities;


import com.mysql.cj.callback.MysqlCallback;
import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Reading {
    public static void main(String[] args) throws IOException, SQLException {

        Connection con = null;
        String jdbcUrl = "jdbc:mysql://localhost:3306/mydb";
        String jdbcUser = "root";
        String jdbcPassword = "pass123";
        String sql = "Select * From employees";

        

//        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

        try {
            con = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);

            Statement st = con.createStatement();
            ResultSet std = st.executeQuery(sql);

            while(std.next()){
                System.out.println("name " + std.getString("first_name") + " last_name " + std.getString("last_name"));
            }



        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(con != null)
                con.close();
        }

//        FileInputStream sourceStream = null;
//        FileOutputStream targetStream = null;
//
//        try {
//            sourceStream
//                    = new FileInputStream("sourcefile.txt");
//            targetStream
//                    = new FileOutputStream("targetfile.txt");
//
//            // Reading source file and writing content to target file byte by byte
//            int temp;
//            while ((
//                    temp = sourceStream.read())
//                    != -1)
//                targetStream.write((byte)temp);
//        }
//        finally {
//            if (sourceStream != null)
//                sourceStream.close();
//            if (targetStream != null)
//                targetStream.close();}


//        File file = new File("example.txt");
//
//        Path filepath = Paths.get("C:\\Users\\Gabriel.Osaji\\Documents\\Public Class A\\RealLearning\\src\\main\\java\\Universities");
//
//
//       try {
//           long value = Files.walk(filepath)
//                   .filter(Files::isRegularFile)
//                   .count();
//           System.out.println("The value of the files is: " + value);
//           Files.walk(filepath).filter(path-> path.toString().endsWith("java")).forEach(System.out::println);
//       } catch (IOException e) {
//           throw new RuntimeException(e);
       }



//    }
//    public static List<String> readFile(Path file){
//        List<String> outcome = new ArrayList<>();
//        try{
//            BufferedReader bufferedReader = new BufferedReader(new FileReader(file.toFile()));
//
//            int temp;
//            while ((temp = bufferedReader.read() )!= -1){
//                String value = String.valueOf((char)temp);
//                outcome.add(value);
//            }
//
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e){
//            System.out.println("error reading from file");
//        }
//        return outcome;
//
//    }
}
