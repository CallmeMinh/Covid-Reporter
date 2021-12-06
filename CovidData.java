import java.util.*;
import java.text.*;
/**
 * CovidData - Write a description here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
// Standard import for the Scanner class
import java.util.*;
public class CovidData extends assignment2 {
    private String location; // Country Name
    private Date d;
    private long newCases;
    private long population;
    private long totalVaccinations;
    private long peopleVaccinated;
    private long peopleFullyVaccinated;
    private long newVaccinations;
    private long newVaccinationsSmoothed;
    private double totalVaccinationsPerHundred;
    private double peopleVaccinatedPerHundred;
    private double newVaccinationsSmoothedPerMillion;
    public CovidData (String _location, Date _d, long _population, long _newCases,
        long _totalVaccinations, long _peopleVaccinated, long _peopleFullyVaccinated,
        long _newVaccinations, long _newVaccinationsSmoothed, double _totalVaccinationsPerHundred,
        double _peopleVaccinatedPerHundred, double peopleFullyVaccinatedPerHundred, double _newVaccinationsSmoothedPerMillion) {
            this.location = _location;
            this.population = _population;
            this.newCases = _newCases;
            this.d = _d;
            this.totalVaccinations = _totalVaccinations;
            this.peopleVaccinated = _peopleVaccinated;
            this.peopleFullyVaccinated = _peopleFullyVaccinated;
            this.newVaccinations = _newVaccinations;
            this.newVaccinationsSmoothed = _newVaccinationsSmoothed;
            this.totalVaccinationsPerHundred = _totalVaccinationsPerHundred;
            this.peopleVaccinatedPerHundred = _peopleVaccinatedPerHundred;
            this.newVaccinationsSmoothedPerMillion = _newVaccinationsSmoothedPerMillion;           
    }
    /** getLocation
     * This method returns the location for the line of data. The location is a country name.
     * @return the location where the data was collected
     */
    public String getLocation () {
        return this.location;
    }
    /** getDate
     * Return the date when this data was collected.
     * @return  the date when this data was collected
     */
    public Date getDate () {
        return this.d;
    }
    /** getPopulation
     * Returns the population of the location (country). Useful in calculating cases/100,000 etc.
     * @return the population of the location (country)
     */
    public long getPopulation() {
        return this.population;
    }
    /** getNewCases
     * The number of new cases on this date.
     * @return number of new cases
     */
    public long getNewCases () {
        return this.newCases;
    }
    /** getTotalVaccinations
     * The total number of DOSES of vaccinate administered in the location from
     * the beginning of the panademic. Remember it make take multiple doses to fully vaccinate an 
     * individual, e.g. two for pfizer, Moderna, and Astra Zeneca.
     * @return total number of DOSES of vaccinate administered
     */
    public long getTotalVaccinations() {
        return this.totalVaccinations;
    }
    /**
     * getPeopleVaccinated
     * This is the number of people that have received at least ONE doses of a covid vaccine.
     * @return number of people that have received at least ONE doses of a covid vaccine
     */
    public long getPeopleVaccinated () {
        return this.peopleVaccinated;
    }
    /** 
     * getPeopleFullyVaccinated
     * This is the number of people fully vaccinated against Covid.
     * @return number of people fully vaccinated
     */
    public long getPeopleFullyVaccinated() {
        return peopleFullyVaccinated;
    }
    /** getNewVaccinations
     * This is the number of doses of vaccine administered today
     * @return number of doses of vaccine administered
     */
    public long getNewVaccinations() {
        return this.newVaccinations;
    }
    /** getNewVaccinationsSmoothed
     * This is the average number of doses of vaccine administered
     * over the last 7 day period
     * @return average number of doses administered over last 7 days
     */
    public long getNewVaccinationsSmoothed () {
        return this.newVaccinationsSmoothed;
    }
    /** getNewVaccinationsSmoothedPerMillion
     * This is the average number of doses of vaccine administered per one million people
     * over the last 7 day period
     * @return average number of doses of vaccine administered per one million people
     */
    public double getNewVaccinationsSmoothedPerMillion () {
        return this.newVaccinationsSmoothedPerMillion;
    }
    /**
     * getTotalVaccinationsPerHundred
     * This is the total number of doses administered in the location per one hundred
     * people. Remember individuals may require 1 - 2 doses depend on the vaccined being used.
     * @return total number of doses administered in the location per one hundred people
     * 
     */
    public double getTotalVaccinationsPerHundred() {
        return this.totalVaccinationsPerHundred;
    }
    /** getPeopleVaccinatedPerHundred
     * This is the total number of people that have received at least ONE doses of a covid vaccine
     * in the location.
     * @return total number of people that have received at least ONE dose
     */
    public double getPeopleVaccinatedPerHundred () {
        return this.peopleVaccinatedPerHundred;
    }

    /** toString
     * This method returns a string representation of SOME of the attributes associated with
     * the object.
     */
    public String toString () {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");  
        String strDate = dateFormat.format(d);  
        String r = String.format ("[%10.10s %10s\nNew Cases: %7d\nVaccinations: %7d]", location, strDate, newCases, newVaccinations);
        return r;
    }

}