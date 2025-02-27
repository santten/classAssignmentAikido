import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class AikidoTracker {
    private final String studentName;
    private final LocalDate startDate;
    private int sessions;

    public AikidoTracker(String studentName, LocalDate startDate){
        this.studentName = studentName;
        this.startDate = startDate;
        this.sessions = 0;
    }

    public String getStudentName(){ return this.studentName; }
    public LocalDate getStartDate(){ return this.startDate; }
    public int getSessions(){ return this.sessions; }

    public void incrementSessions() {
        this.sessions++;
        System.out.println("Added a training session! Current amount of sessions: " + getSessions());
    }

    public Boolean checkKyuEligibility() {
        long monthsBetween = ChronoUnit.MONTHS.between(
                getStartDate().withDayOfMonth(1),
                LocalDate.now().withDayOfMonth(1));

        System.out.println("Trained for " + monthsBetween + " months, done " + getSessions() + " training sessions");

        return (monthsBetween >= 6 || getSessions() >= 60);
    }

    public static void main(String[] args) {
        System.out.println("Aikido Tracker");
        Scanner s = new Scanner(System.in);

        System.out.println("Enter student name:");
        String name = s.nextLine();

        System.out.println("Enter start date (DD-MM-YYYY):");
        LocalDate date = LocalDate.parse(s.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        boolean executing = true;
        AikidoTracker tracker = new AikidoTracker(name, date);

        while (executing) {
            System.out.printf(
                    "\n[Aikido Tracker]\n" +
                            "Tracking " + tracker.getStudentName() + "'s Aikido Process \n" +
                            "Training started on: " + date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "\n" +
                            "What would you like to do? \n" +
                            "=====================================\n" +
                            "[1] Log a new training session \n" +
                            "[2] Check eligibility \n" +
                            "[3] Quit \n");

            int input;

            try {
                input = parseInt(s.nextLine());
            } catch (final NumberFormatException e) {
                System.out.println("Invalid input!");
                break;
            }

            switch (input){
                case 1: tracker.incrementSessions(); break;
                case 2: {
                    boolean bool = tracker.checkKyuEligibility();
                    if (bool) {
                        System.out.println(tracker.getStudentName() + " is eligible for Kyu examination!");
                    } else {
                        System.out.println(tracker.getStudentName() + " is not eligible for Kyu examination!");
                    }
                    break;
                }
                case 3: executing = false; break;
                default: System.out.println("Invalid input!"); break;
            }
        }
    }
}
