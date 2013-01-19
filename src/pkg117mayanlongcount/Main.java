package pkg117mayanlongcount;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Alexander Lopez
 */
public class Main {

    public Main(){
        convertToMayanCalendarFormat(21,12,2012);
    }

    int daysInYear = 365;
    //Start date
    int startDay = 1;
    int startMonth = 1;
    int startYear = 1970;
    //Start mayan date
    MayanDate startingMayanDate = new MayanDate(12,17,16,7,5);
    GregorianCalendar standardCalendar = new GregorianCalendar();
    
    
    //Entered date must be on or after jan 1 1970
    private MayanDate convertToMayanCalendarFormat(int day, int month, int year){
        MayanDate mayanDate = startingMayanDate;
        int tempDay = startDay;
        int tempMonth = startMonth;
        int tempYear = startYear;
        int totalDays = 0;
        boolean countDays = true; 
        while(countDays){
            standardCalendar.set(tempYear, tempMonth, tempDay);
            if(tempYear == year){
                if(tempMonth == month){
                    if(tempDay == day){
                        countDays = false;
                    }else{
                        totalDays++;
                        tempDay++;
                    }
                }else{
                    totalDays += standardCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                    tempMonth++;
                }
            }else{
                if((tempYear % 4) == 0){
                    daysInYear = 366;
                }
                else {
                    daysInYear = 365;
                }
                totalDays += daysInYear;
                tempYear++;
            }                
        }
        //Add the total amount of days to the mayan date 1 at a time so it can internally convert
        for(int x=0;x<totalDays;x++){
            mayanDate.addKin(1);
        }
        System.out.println(mayanDate.toString());
        return mayanDate;
    }
    
    
    private class MayanDate{   
        int kin;
        int uinal;
        int tun;
        int katun;
        int baktun;      
        public MayanDate(int baktun,int katun,int tun,int uinal,int kin){
            this.kin = kin;
            this.uinal = uinal;
            this.tun = tun;
            this.katun = katun;
            this.baktun = baktun;
        }       
        public void addKin(int amt){
            kin += amt;
            if(kin >= 20){
                addUinal(1);
                kin = 0;
            }
        }
        public void addUinal(int amt){
            uinal += amt;
            if(uinal >= 18){
                addTun(1);
                uinal = 0;
            }
        }
        public void addTun(int amt){
            tun += amt;
            if(tun >= 20){
                addKatun(1);
                tun = 0;
            }
        }
        public void addKatun(int amt){
            katun += amt;
            if(katun >= 20){
                addBaktun(1);
                katun = 0;
            }
        }
        public void addBaktun(int amt){
            baktun += amt;
        }
        
        
        public String toString(){
            return baktun + "." + katun + "." + tun + "." + uinal + "." + kin;
        }
    }
    

    public static void main(String[] args) {
        new Main();       
    }
}
