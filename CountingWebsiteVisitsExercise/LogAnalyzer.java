
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         
         for (String line : fr.lines())
         {
             LogEntry logEntry = WebLogParser.parseEntry(line);
             records.add(logEntry);
         }
     }
     
     public int countUniqueIPs ()
     {
         ArrayList<String> uniqueIPsList = new ArrayList<String>();
         
         for (LogEntry le : records) {
            String ip = le.getIpAddress();
             
            if(!uniqueIPsList.contains(ip))
            {
                uniqueIPsList.add(ip);
            }
         }
         
         return uniqueIPsList.size();
     }
     
     public void printAllHigherThanNum (int num)
     {
         
         for (LogEntry le : records) {
            int statusCode = le.getStatusCode();
             
            if(statusCode > num)
            {
                System.out.println(le);
            }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay (String someday)
     {
         ArrayList<String> uniqueIPsOnDay = new ArrayList<String>();
         for (LogEntry le : records) {
            String ip = le.getIpAddress();
             
            if(!uniqueIPsOnDay.contains(ip))
            {
                String date = le.getAccessTime().toString();
                if (date.contains(someday))
                {
                    uniqueIPsOnDay.add(ip);
                }
            }
         }
         return uniqueIPsOnDay;
     }
     
     public int countUniqueIPsInRange (int low, int high)
     {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         int count = 0;
         for (LogEntry le : records) {
            int statusCode = le.getStatusCode();
             
            if(statusCode >= low && statusCode <= high)
            {
                String ip = le.getIpAddress();
                if(!uniqueIPs.contains(ip))
                {   
                    count++;
                    uniqueIPs.add(ip);
                }
            }
         }
         return count;
     }
     
     public HashMap<String, Integer> countVisitsPerIP ()
     {
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         
         for (LogEntry le : records)
         {
            String ip = le.getIpAddress();
             
            if(counts.containsKey(ip))
            {
                counts.put(ip, counts.get(ip) + 1);
            }
            else
            {
                counts.put(ip, 1);
            }
         }
         
         return counts;
     }
     
     public int mostNumberVisitsByIP (HashMap<String, Integer> counts)
     {
         int max = 0;
         
         for (String ip : counts.keySet())
         {
            int count = counts.get(ip);
             
            if(count > max)
            {
                max = count;
            }
         }
         
         return max;
     }
     
     public ArrayList<String> iPsMostVisits (HashMap<String, Integer> counts)
     {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         
         int max = mostNumberVisitsByIP(counts);
         
         for (String ip : counts.keySet())
         {
            int count = counts.get(ip);
             
            if(count == max)
            {
                uniqueIPs.add(ip);
            }
         }
         
         return uniqueIPs;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays ()
     {
         HashMap<String, ArrayList<String>> iPsForDaysMap = new HashMap<String, ArrayList<String>>();
         
         for (LogEntry le : records) {
             
             String[] fullDate = le.getAccessTime().toString().split(" ");
             String date = fullDate[1] + " " + fullDate[2];
             String ip = le.getIpAddress();
             if (iPsForDaysMap.containsKey(date))
             {
                 ArrayList<String> ips = iPsForDaysMap.get(date);
                 ips.add(ip);
                 iPsForDaysMap.put(date, ips);
             }
             else
             {
                 ArrayList<String> ips = new ArrayList<String>();
                 ips.add(ip);
                 iPsForDaysMap.put(date, ips);
             }
         }
         
         return iPsForDaysMap;
     }
     
     public String dayWithMostIPVisits (HashMap<String, ArrayList<String>> iPsForDaysMap)
     {
         int max = 0;
         String maxDay = "";
         
         for (String day : iPsForDaysMap.keySet())
         {
            int count = iPsForDaysMap.get(day).size();
             
            if(count > max)
            {
                max = count;
                maxDay = day;
            }
         }
         
         return maxDay;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay (HashMap<String, ArrayList<String>> iPsForDaysMap, String day)
     {
         ArrayList<String> ips = iPsForDaysMap.get(day);
         
         HashMap<String, Integer> ipsCounts = new HashMap<String, Integer>();
         
         for (String ip : ips)
         {
             
            if(ipsCounts.containsKey(ip))
            {
                ipsCounts.put(ip, ipsCounts.get(ip) + 1);
            }
            else
            {
                ipsCounts.put(ip, 1);
            }
         }
         
         ips = iPsMostVisits(ipsCounts);
         
         return ips;
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
