import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SRCHBIN {

    private static final int TABLE_MAX = 45;
    private static final String ACCOUNT_NUMBER_TO_SEARCH = "18011809";
    private static final String INPUT_FILENAME = "ACCT-REC";

    private static class AccountRecord {
        String acctNo;
        int acctLimit;
        int acctBalance;
        String lastName;
        String firstName;
        String streetAddr;
        String cityCounty;
        String usaState;
        String reserved;
        String comments;
    }

    private static List<AccountRecord> acctTable = new ArrayList<>(TABLE_MAX);
    private static boolean endOfFile = false;

    public static void main(String[] args) {
        openFiles();
        loadTables();
        if (!endOfFile) {
            sortAccountRecords();
            searchRecord();
        }
        closeStop();
    }

    private static void openFiles() {
        // In Java, files are typically opened when they are read.
    }

    private static void loadTables() {
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null && acctTable.size() < TABLE_MAX) {
                AccountRecord record = parseAccountRecord(line);
                acctTable.add(record);
            }
        } catch (IOException e) {
            endOfFile = true;
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void sortAccountRecords() {
        Collections.sort(acctTable, Comparator.comparing(record -> record.acctNo));
    }

    private static AccountRecord parseAccountRecord(String line) {
        AccountRecord record = new AccountRecord();
        record.acctNo = line.substring(0, 8);
        record.acctLimit = Integer.parseInt(line.substring(8, 13).trim());
        record.acctBalance = Integer.parseInt(line.substring(13, 18).trim());
        record.lastName = line.substring(18, 38).trim();
        record.firstName = line.substring(38, 53).trim();
        record.streetAddr = line.substring(53, 78).trim();
        record.cityCounty = line.substring(78, 98).trim();
        record.usaState = line.substring(98, 113).trim();
        record.reserved = line.substring(113, 120).trim();
        record.comments = line.substring(120).trim();
        return record;
    }

    private static void searchRecord() {
        int low = 0;
        int high = acctTable.size() - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            AccountRecord midVal = acctTable.get(mid);
            int cmp = midVal.acctNo.compareTo(ACCOUNT_NUMBER_TO_SEARCH);

            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                System.out.println("User with Acct No " + ACCOUNT_NUMBER_TO_SEARCH + " is found!");
                return; // Account number found
            }
        }
        System.out.println("Not Found"); // Account number not found
    }

    private static void closeStop() {
        // In Java, files are typically closed automatically using try-with-resources.
    }
}
