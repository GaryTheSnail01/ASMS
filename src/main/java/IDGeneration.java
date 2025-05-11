import java.util.HashSet;
import java.util.Set;
import java.util.Random;

public class IDGeneration {
    private static Set<String> exsistingIDs = new HashSet<>();

    public static String GenerateID(String classType){
        Random random = new Random();
        String idPrefix = "";
        String idNumber;

        switch (classType){
            case "Student":
                idPrefix = "S";
                break;
            case "Teacher":
                idPrefix = "T";
                break;
            case "Course":
                idPrefix = "C";
                break;
        }

        do {
            int randomNum = 1000 + random.nextInt(9000);
            idNumber = idPrefix + " - " + randomNum;
        } while (exsistingIDs.contains(idNumber));

        exsistingIDs.add(idNumber);
        return idNumber;
    }
}
