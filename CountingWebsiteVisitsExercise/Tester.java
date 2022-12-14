
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer lg = new LogAnalyzer();
        //read logs from file
        lg.readFile("short-test_log");
        //print all logs
        lg.printAll();
    }
    
    public void testUniqueIP() {
        LogAnalyzer lg = new LogAnalyzer();
        //read logs from file
        lg.readFile("weblog2_log");
        //print all unqiue ips
        System.out.println("Number of unique addresses is: " + lg.countUniqueIPs ());
    }
    
    public void testAllHigherThanNum() {
        LogAnalyzer lg = new LogAnalyzer();
        //read logs from file
        lg.readFile("weblog1_log");
        
        int num = 400;
        //print all logs that has status code greater than num
        lg.printAllHigherThanNum(num);
    }
    
    public void testuniqueIPVisitsOnDay() {
        LogAnalyzer lg = new LogAnalyzer();
        //read logs from file
        lg.readFile("weblog2_log");
        
        String someday = "Sep 24";
        //print all logs that has status code greater than num
        ArrayList<String> uniqueIPsOnDay = lg.uniqueIPVisitsOnDay(someday);
        
        for (String ip : uniqueIPsOnDay) {
             System.out.println(ip);
        }
        System.out.println("Number of unique addresses on this date ("+ someday +") is: " + uniqueIPsOnDay.size());
    }
    
    public void testcountUniqueIPsInRange() {
        LogAnalyzer lg = new LogAnalyzer();
        //read logs from file
        lg.readFile("weblog2_log");
        
        int low = 200, high = 299;
        //print all logs that has status code greater than num
        System.out.println("Number of unique addresses in the range of ("+ low + " to " + high +") is: " + lg.countUniqueIPsInRange(low, high));
    }
    
    public void testcountVisitsPerIP() {
        LogAnalyzer lg = new LogAnalyzer();
        //read logs from file
        lg.readFile("weblog3-short_log");
        
        HashMap<String, Integer> counts = lg.countVisitsPerIP ();
        //print all unique ips with their visits
        System.out.println("print all unique ips with their visits:");
        System.out.println(counts);
    }
    
    public void testmostNumberVisitsByIP() {
        LogAnalyzer lg = new LogAnalyzer();
        //read logs from file
        lg.readFile("weblog2_log");
        
        HashMap<String, Integer> counts = lg.countVisitsPerIP ();
        //print all unique ips with their visits
        System.out.println("most Number Visits By IP: " + lg.mostNumberVisitsByIP(counts));
    }
    
    public void testiPsMostVisits() {
        LogAnalyzer lg = new LogAnalyzer();
        //read logs from file
        lg.readFile("weblog2_log");
        
        HashMap<String, Integer> counts = lg.countVisitsPerIP ();
        
        ArrayList<String> uniqueIPs = lg.iPsMostVisits (counts);
        //print all unique ips with max visits
        System.out.println("all unique ips with max visits:");
        for (String ip : uniqueIPs) {
             System.out.println(ip);
        }
    }
    
    public void testiPsForDays() {
        LogAnalyzer lg = new LogAnalyzer();
        //read logs from file
        lg.readFile("weblog3-short_log");
        
        HashMap<String, ArrayList<String>> iPsForDaysMap = lg.iPsForDays ();
        
        //print all unique days with their ips
        System.out.println("all iPs For Days:");
        System.out.println(iPsForDaysMap);
    }
    
    public void testdayWithMostIPVisits() {
        LogAnalyzer lg = new LogAnalyzer();
        //read logs from file
        lg.readFile("weblog2_log");
        
        HashMap<String, ArrayList<String>> iPsForDaysMap = lg.iPsForDays ();
        
        String day = lg.dayWithMostIPVisits(iPsForDaysMap);
        
        //print day With Most IP Visits
        System.out.println("day With Most IP Visits: " + day);
    }
    
    public void testiPsWithMostVisitsOnDay() {
        LogAnalyzer lg = new LogAnalyzer();
        //read logs from file
        lg.readFile("weblog2_log");
        
        HashMap<String, ArrayList<String>> iPsForDaysMap = lg.iPsForDays ();
        
        String day = "Sep 29";
        
        ArrayList<String> ips = lg.iPsWithMostVisitsOnDay(iPsForDaysMap, day);
        
        //print iPs With Most Visits On Day (day)
        System.out.println("iPs With Most Visits On Day (" + day + "):");
        for (String ip : ips) {
             System.out.println(ip);
        }
    }
}
