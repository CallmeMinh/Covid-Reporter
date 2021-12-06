/**
 *Covid Data Reporter
 *
 * @author (Minh Nguyen, Caelan Neumann, John Paul Fermin)
 * @version (07-25-21)
 * A program that filters through global covid data from the csv file owid-covid-data.
 * The program chooses a file to export the report to based on user input.
 * The program will print either print a case or vaccination report based on user input on the country the user has choosen.
 * The program can print consecutive reports and will exit when the user inputs the exit key.
 */





import java.util.*;
import java.io.*;
import java.text.*;
import java.util.ArrayList;

public class assignment2
{
    public static void main (String[] args) throws ParseException, IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Covid Filename: ");                                                       //Enter the file that you wish to be read for the program.
        String covidFilename = in.nextLine();
        System.out.print('\u000C');
        System.out.println("0) Set report filename ");                                                      //Enter the file that you wish to print out from the Data.
        System.out.println("1) Report of daily new cases ");                                                //The reports of daily new cases by country.
        System.out.println("2) Daily vaccination report for a specific country ");                          //The reports of daily vaccination by country.
        System.out.println("9) Exit ");                                                                     //User wish to exit the program.
        System.out.print("Enter selection: ");

        boolean a=true;
        String filename="";

        while(true){
            int yourselection=in.nextInt();
            PrintWriter output;
            if(yourselection==0){                                                                           //User selects 0, user must enter a file where the data will be printed out.
                Scanner input = new Scanner(System.in);
                System.out.println("Set report filename: ");
                filename = input.nextLine();
                FileWriter f = new FileWriter(filename, true); 
                BufferedWriter b = new BufferedWriter(f); 
                output = new PrintWriter(b);
                System.out.print('\u000C');
                System.out.println("0) Set report filename ");                                              //User wish to change file where the data will be printed out.
                System.out.println("1) Report of daily new cases ");                                        //The reports of daily new cases by country.
                System.out.println("2) Daily vaccination report for a specific country ");                  //The reports of daily vaccination by country.
                System.out.println("9) Exit ");                                                             //User wish to exit the program.
                System.out.print("Enter selection: ");
                int choice = in.nextInt();
                while(choice==1 || choice==2 || choice==9){
                    //output.close();
                    if(choice==9){                                                                          //User chooses 9, the program exit.
                        output.close();
                        System.exit(0);
                    }

                    if(choice==1){                                                                          //User chooses 1, it will print out the daily reports of new cases by country.
                        try{
                            BufferedReader br = new BufferedReader(new FileReader(covidFilename));
                            br.readLine();
                            String line1= null;
                            System.out.println("Enter Your Country: ");                                     //User must enter the country that wishes to be collected.
                            String country=input.nextLine();
                            output.println("New Case Report for "+ country);
                            output.println("      Date \t New Cases \t NewCases/1M");
                            while((line1=br.readLine()) != null){

                                String[] answer = line1.split(",");
                                Date d=new SimpleDateFormat("yyyy-mm-dd").parse(answer[3]);
                                CovidData line;
                                long totalVaccinations;
                                long peopleVaccinated;
                                long peopleFullyVaccinated;
                                long newVaccinations;
                                long newVaccinationsSmoothed;
                                double totalVaccinationsPerHundred;
                                double peopleVaccinatedPerHundred;
                                double peopleFullyVaccinatedPerHundred;
                                double newVaccinatedSmoothedPerMillion;
                                long newCases;
                                long population;
                                if (answer.length > 44) {
                                    // answers[5] newCases
                                    if (!answer[5].equals("")) 
                                        newCases = (long)Double.parseDouble (answer[5]);
                                    else newCases = 0;
                                    // answers[34] totalVaccinations

                                    if (!answer[34].equals("")) 
                                        totalVaccinations = (long)Double.parseDouble (answer[34]);
                                    else totalVaccinations = 0;

                                    // answers[35] people vaccinated

                                    if (!answer[35].equals("")) 
                                        peopleVaccinated = (long)Double.parseDouble (answer[35]);
                                    else peopleVaccinated = 0;   

                                    // answers[36] people fully vaccinated

                                    if (!answer[36].equals("")) 
                                        peopleFullyVaccinated = (long)Double.parseDouble (answer[36]);
                                    else peopleFullyVaccinated = 0;
                                    // answers[37] new vaccinations

                                    if (!answer[37].equals("")) 
                                        newVaccinations = (long)Double.parseDouble (answer[37]);
                                    else newVaccinations = 0;     

                                    // answers[38] 

                                    if (!answer[38].equals("")) 
                                        newVaccinationsSmoothed = (long)Double.parseDouble (answer[38]);
                                    else newVaccinationsSmoothed = 0;                 

                                    // answers[39] 

                                    if (!answer[39].equals("")) 
                                        totalVaccinationsPerHundred = Double.parseDouble (answer[39]);
                                    else totalVaccinationsPerHundred = 0;  

                                    // answers[40] 

                                    if (!answer[40].equals("")) 
                                        peopleVaccinatedPerHundred = Double.parseDouble (answer[40]);
                                    else peopleVaccinatedPerHundred = 0;              

                                    // answers[41] 

                                    if (!answer[41].equals("")) 
                                        peopleFullyVaccinatedPerHundred = Double.parseDouble (answer[41]);
                                    else peopleFullyVaccinatedPerHundred = 0;     

                                    // answers[42] 

                                    if (!answer[42].equals("")) 
                                        newVaccinatedSmoothedPerMillion = Double.parseDouble (answer[42]);
                                    else newVaccinatedSmoothedPerMillion = 0;            

                                    // answers[44] totalVaccinations

                                    if (!answer[44].equals("")) 
                                        population = (long)Double.parseDouble (answer[44]);
                                    else population = 0;   

                                    // Create new object to hold the data
                                    line = new CovidData(answer[2], d, population, newCases, totalVaccinations, peopleVaccinated,
                                        peopleFullyVaccinated, newVaccinations, newVaccinationsSmoothed, totalVaccinationsPerHundred,
                                        peopleVaccinatedPerHundred, peopleFullyVaccinatedPerHundred, newVaccinatedSmoothedPerMillion);
                                    // Add new object to arraylist, each time you go through the loop you would add
                                    // the line to the list
                                    //list.add(line);

                                    // Calculate some statistics on this entry
                                    double numNewCases = line.getNewCases();
                                    // calculate new cases per 1 million
                                    double newCasesPer1M = (double)newCases/line.getPopulation() * 1E6; 
                                    // calculate new vaccinations per 100
                                    double newVaccPerHundred = (double)line.getNewVaccinations()/line.getPopulation() * 100;
                                    //double ttlVaccPerHunderd = (double)line.getTotalVaccinations()/line.getPopulation() * 100;
                                    // convert the date to a readable format
                                    DateFormat df = new SimpleDateFormat ("mm/dd/YYYY");
                                    String outputDate = df.format (line.getDate());

                                    if(answer[2].equalsIgnoreCase(country)){
                                        output.printf("%s      %,10.0f  %, 16.4f %n",outputDate, (double)line.getNewCases(), newCasesPer1M);
                                    }
                                }

                            }
                            System.out.print('\u000C');                                                                 //after the data has been printed the menu will be prompt.
                            System.out.println("0) Set report filename ");                                              //User wishes to change the file where the data will be printed out but the previous printed out data remains.
                            System.out.println("1) Report of daily new cases ");                                        //the reports of daily new cases by country.
                            System.out.println("2) Daily vaccination report for a specific country ");                  //the reports of daily vaccination by country.
                            System.out.println("9) Exit ");                                                             //it will exit the program.
                            System.out.print("Enter selection: ");
                            choice = in.nextInt();
                            

                        } catch (FileNotFoundException e){
                            e.printStackTrace();
                        }

                    }

                    if(choice==2){                                                                                      //User chooses 2, it will prints out the reports of daily vaccination by country.
                        try{
                            output = new PrintWriter(b);
                            BufferedReader br = new BufferedReader(new FileReader(covidFilename));
                            br.readLine();
                            String line1= null;
                            System.out.println("Enter Your Country: ");                                                 //User must enter a country that wishes to be collected.
                            String country=input.nextLine();
                            output.println("New Vaccinations/Day Report for "+country);
                            output.println("      Date \t  New Vacc \t New Vacc/100 \t  Ttl Vacc \t  Ttl Vacc/100");
                            while((line1=br.readLine()) != null){

                                String[] answer = line1.split(",");
                                Date d=new SimpleDateFormat("yyyy-mm-dd").parse(answer[3]);
                                CovidData line;
                                long totalVaccinations;
                                long peopleVaccinated;
                                long peopleFullyVaccinated;
                                long newVaccinations;
                                long newVaccinationsSmoothed;
                                double totalVaccinationsPerHundred;
                                double peopleVaccinatedPerHundred;
                                double peopleFullyVaccinatedPerHundred;
                                double newVaccinatedSmoothedPerMillion;
                                long newCases;
                                long population;
                                if (answer.length > 44) {
                                    // answers[5] newCases
                                    if (!answer[5].equals("")) 
                                        newCases = (long)Double.parseDouble (answer[5]);
                                    else newCases = 0;
                                    // answers[34] totalVaccinations

                                    if (!answer[34].equals("")) 
                                        totalVaccinations = (long)Double.parseDouble (answer[34]);
                                    else totalVaccinations = 0;

                                    // answers[35] people vaccinated

                                    if (!answer[35].equals("")) 
                                        peopleVaccinated = (long)Double.parseDouble (answer[35]);
                                    else peopleVaccinated = 0;   

                                    // answers[36] people fully vaccinated

                                    if (!answer[36].equals("")) 
                                        peopleFullyVaccinated = (long)Double.parseDouble (answer[36]);
                                    else peopleFullyVaccinated = 0;
                                    // answers[37] new vaccinations

                                    if (!answer[37].equals("")) 
                                        newVaccinations = (long)Double.parseDouble (answer[37]);
                                    else newVaccinations = 0;     

                                    // answers[38] 

                                    if (!answer[38].equals("")) 
                                        newVaccinationsSmoothed = (long)Double.parseDouble (answer[38]);
                                    else newVaccinationsSmoothed = 0;                 

                                    // answers[39] 

                                    if (!answer[39].equals("")) 
                                        totalVaccinationsPerHundred = Double.parseDouble (answer[39]);
                                    else totalVaccinationsPerHundred = 0;  

                                    // answers[40] 

                                    if (!answer[40].equals("")) 
                                        peopleVaccinatedPerHundred = Double.parseDouble (answer[40]);
                                    else peopleVaccinatedPerHundred = 0;              

                                    // answers[41] 

                                    if (!answer[41].equals("")) 
                                        peopleFullyVaccinatedPerHundred = Double.parseDouble (answer[41]);
                                    else peopleFullyVaccinatedPerHundred = 0;     

                                    // answers[42] 

                                    if (!answer[42].equals("")) 
                                        newVaccinatedSmoothedPerMillion = Double.parseDouble (answer[42]);
                                    else newVaccinatedSmoothedPerMillion = 0;            

                                    // answers[44] totalVaccinations

                                    if (!answer[44].equals("")) 
                                        population = (long)Double.parseDouble (answer[44]);
                                    else population = 0;   

                                    // Create new object to hold the data
                                    line = new CovidData(answer[2], d, population, newCases, totalVaccinations, peopleVaccinated,
                                        peopleFullyVaccinated, newVaccinations, newVaccinationsSmoothed, totalVaccinationsPerHundred,
                                        peopleVaccinatedPerHundred, peopleFullyVaccinatedPerHundred, newVaccinatedSmoothedPerMillion);
                                    // Add new object to arraylist, each time you go through the loop you would add
                                    // the line to the list
                                    //list.add(line);

                                    // Calculate some statistics on this entry
                                    double numNewCases = line.getNewCases();
                                    // calculate new cases per 1 million
                                    double newCasesPer1M = (double)newCases/line.getPopulation() * 1E6; 
                                    // calculate new vaccinations per 100
                                    double newVaccPerHundred = (double)line.getNewVaccinations()/line.getPopulation() * 100;
                                    //double ttlVaccPerHunderd = (double)line.getTotalVaccinations()/line.getPopulation() * 100;
                                    // convert the date to a readable format
                                    DateFormat df = new SimpleDateFormat ("mm/dd/YYYY");
                                    String outputDate = df.format (line.getDate());

                                    if(answer[2].equalsIgnoreCase(country)){
                                        output.printf("%s      %,10.0f   %, 16.4f   %,10.0f    %, 16.4f %n",outputDate, (double)line.getNewVaccinations(), newVaccPerHundred, (double)line.getTotalVaccinations(), (double)line.getTotalVaccinationsPerHundred());
                                    }}

                            }
                            System.out.print('\u000C');                                                                 //after the data is printed the menu will be prompt.
                            System.out.println("0) Set report filename ");                                              //User wishes to change the file where it will printed out but the previous printed out data remains.
                            System.out.println("1) Report of daily new cases ");                                        //The report of daily new cases by country.
                            System.out.println("2) Daily vaccination report for a specific country ");                  //The report of daily vaccination by country
                            System.out.println("9) Exit ");                                                             //exit the program.
                            System.out.print("Enter selection: ");
                            choice = in.nextInt();
                            //output.close();

                        } catch (FileNotFoundException e){
                            e.printStackTrace();
                        }

                    }
                }

            }
            if(yourselection==1){                                                                                       //user must select 0 first before you can proceed.
                System.out.println("You need to specific a report filename before requesting this option");
                //System.out.println("press ENTER to continue....");
                System.out.println("Press Enter key to continue...");
                try                                                                                                     //the menu will prompt
                {
                    System.in.read();
                    System.out.print('\u000C');
                    System.out.println("0) Set report filename ");
                    System.out.println("1) Report of daily new cases ");
                    System.out.println("2) Daily vaccination report for a specific country ");
                    System.out.println("9) Exit ");
                    System.out.print("Enter selection: ");
                }  
                catch(Exception e)
                {}
            }
            if(yourselection==2){                                                                                        //user must select 0 first before you can proceed.
                System.out.println("You need to specific a report filename before requesting this option");
                //System.out.println("press ENTER to continue....");
                System.out.println("Press Enter key to continue...");
                try                                                                                                      //the menu will prompt
                {
                    System.in.read();
                    System.out.print('\u000C');
                    System.out.println("0) Set report filename ");
                    System.out.println("1) Report of daily new cases ");
                    System.out.println("2) Daily vaccination report for a specific country ");
                    System.out.println("9) Exit ");
                    System.out.print("Enter selection: ");
                }  
                catch(Exception e)
                {}
            }
            if(yourselection==9){                                                                                      //User selects 9 the program exit.
                System.exit(0);
            }
        }
    }
}