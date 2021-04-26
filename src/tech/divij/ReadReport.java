package tech.divij;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ReadReport {

    public static void main(String args[]) throws FileNotFoundException {
        File file = new File("uservisitdata.csv");

        Map<String, ArrayList<String>> resultMap = new HashMap<>();

        try (FileInputStream fileInputStream = new FileInputStream(file)) {

            Scanner scanner  = new Scanner(fileInputStream);
            scanner.nextLine();
            while(scanner.hasNextLine()){
               // System.out.println(scanner.nextLine());
                String str = scanner.nextLine();
                String[] arr = str.split(",");
               // System.out.println(Arrays.toString(arr));
                String customerId = getString(arr[0]);
                int visitCount = Integer.parseInt(getString(arr[1]));

                String range = findRange(visitCount);

                if(null == resultMap.get(range)){
                    ArrayList<String> list = new ArrayList<>();
                    list.add(customerId);
                    resultMap.put(range,list);

                }else{
                    resultMap.get(range).add(customerId);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        for(Map.Entry<String, ArrayList<String>> obj:resultMap.entrySet()){
            System.out.println(obj);
        }

       // System.out.println(resultMap);
   }

    private static String getString(String s) {
        String ss = s.split("\"")[1];

       // System.out.println("ss:"+ss);
        return ss;
    }

    public static String findRange(int num){
        String range =null;


        if(num>0 && num<=1000){
            range = "0...1000";
        }else if (num>1000 && num<=5000){
            range = "1001 - 5000";
        }else if (num>5000 && num<=10000){
            range = "50001 - 10000";
        }else if(num>10000 && num<=25000){
            range = "10001 - 25000";
        }else if(num>25000 && num<=50000){
            range = "25001 - 50000";
        }else if(num >50000 && num<=75000){
            range = "50001 - 75000";
        }else if(num> 75000 && num<=100000){
            range = "75001 - 100000";
        }else if(num>100000){
            range = "100000+";
        }else{
            throw new RuntimeException("Range could not be determined");
        }

        return range;
   }
}
