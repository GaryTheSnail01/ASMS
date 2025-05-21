package SysUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.Random;

public class IDGeneration {
    private static Set<String> existingIDs = new HashSet<>();

    public static String GenerateID(){
        Random random = new Random();
        String idPrefix = "S";
        String idNumber;

        do {
            int randomNum = 1000 + random.nextInt(9000);
            idNumber = idPrefix + randomNum;
        } while (existingIDs.contains(idNumber));

        existingIDs.add(idNumber);
        return idNumber;
    }
}
