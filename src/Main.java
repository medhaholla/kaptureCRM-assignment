import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;



    public class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            ZoneId startTime = ZoneId.of("Europe/Kiev");
            LocalTime workStart = LocalTime.parse(scanner.nextLine());
            LocalTime workEnd = LocalTime.parse(scanner.nextLine());
            getEndTime(startTime, workStart,workEnd);

        }
        public static void getEndTime(ZoneId startTime, LocalTime workStart, LocalTime workEnd){
            // consider any time zone

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the start hours");
            int Starthours = sc.nextInt();
            System.out.println("Enter start the min");
            int Startmin = sc.nextInt();
            System.out.println("Enter the end hours");
            int Endhours = sc.nextInt();
            System.out.println("Enter the end min");
            int endmin = sc.nextInt();

            //work start and end hours
            workStart = LocalTime.of(Starthours,Startmin);
            workEnd = LocalTime.of(Endhours,endmin);

            // Start date of the task

            System.out.println("Enter the start year,month,day,hours,mins,seconds, nanoseconds");
            int year = sc.nextInt();
            int month = sc.nextInt();
            int day = sc.nextInt();
            int hour = sc.nextInt();
            int min = sc.nextInt();
            int seconds = sc.nextInt();
            int nanosec = sc.nextInt();
            ZonedDateTime start = ZonedDateTime.of(year,month,day, hour, min, seconds, nanosec,startTime);
            System.out.println("Enter the End year,month,day,hours,mins,seconds, nanoseconds");
            int Endyear = sc.nextInt();
            int Endmonth = sc.nextInt();
            int Endday = sc.nextInt();
            int Endhour = sc.nextInt();
            int Endmin = sc.nextInt();
            int Endseconds = sc.nextInt();
            int Endnanosec = sc.nextInt();
            ZonedDateTime end = ZonedDateTime.of(Endyear,Endmonth,Endday, Endhour, Endmin, Endseconds, Endnanosec,startTime);
            long totaltimeReq = 0;

            ZonedDateTime startHour = start;

            // if start is above 6 and befour 10
            // before 10 AM
            if (start.toLocalTime().isBefore(workStart)) {
                // set time to 10 AM
                startHour = start.with(workStart);
                // after 6 PM
            } else if (start.toLocalTime().isAfter(workEnd)) {
                // set time to 6 PM
                startHour = start.with(workEnd);
            }
            ZonedDateTime endHour = end;
            // if end is before 10AM or 6PM, adjust it
            // after 6 PM
            if (end.toLocalTime().isAfter(workEnd)) {
                //set to 6PM
                endHour = end.with(workEnd);
                //if it is befour 10 AM
            } else if (end.toLocalTime().isBefore(workStart)) {
                //set to 10 AM
                endHour = end.with(workStart);
            }
            while(startHour.isBefore(endHour)) {
                if (startHour.toLocalDate().equals(endHour.toLocalDate())) {
                    totaltimeReq += ChronoUnit.HOURS.between(startHour, endHour);
                    break;
                } else {
                    ZonedDateTime endOfDay = startHour.with(workEnd); //
                    totaltimeReq += ChronoUnit.HOURS.between(startHour, endOfDay);
                    // move to the next day
                    startHour = startHour.plusDays(1).with(workStart);

                }
            }

            System.out.println(totaltimeReq);

            long totalDays = totaltimeReq/8;
            long hours = totaltimeReq % 8;
            System.out.println(totalDays + "days, " + hours + " hours" );


        }
    }
