// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Arrays;
// import java.util.Scanner;

// public class vasis {

//     public static void main(String[] args) throws Exception {
//         Scanner scanner = new Scanner(System.in);

//         System.out.print("Enter number of months: ");
//         int n = scanner.nextInt();
//         int[] months = new int[n];
//         int[] expenses = new int[n];
//         for (int i = 0; i < n; i++) {
//             months[i] = i;
//             System.out.print("Enter expense for month " + (i + 1) + ": ");
//             expenses[i] = scanner.nextInt();
//         }

//         System.out.println("Running Python script...");
//         String[] command = { "python", "prediction.py", Arrays.toString(months), Arrays.toString(expenses) };
//         Process process = Runtime.getRuntime().exec(command);
//         BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//         String line;
//         List<String> output = new ArrayList<String>();
//         while ((line = reader.readLine()) != null) {
//             output.add(line);
//         }
//         process.waitFor();

//         int predictedExpense = Integer.parseInt(output.get(0));
//         int nextMonth = months[n - 1] + 1;
//         int[] newMonths = Arrays.copyOf(months, n + 1);
//         int[] newExpenses = Arrays.copyOf(expenses, n + 1);
//         newMonths[n] = nextMonth;
//         newExpenses[n] = predictedExpense;

//         System.out.println("Next month's predicted expense: " + predictedExpense);
//         System.out.println("New array of months: " + Arrays.toString(newMonths));
//         System.out.println("New array of expenses: " + Arrays.toString(newExpenses));
//         scanner.close();
//     }
// }

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class vasis {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of months: ");
        int n = scanner.nextInt();
        int[] months = new int[n];
        int[] expenses = new int[n];
        for (int i = 0; i < n; i++) {
            months[i] = i;
            System.out.print("Enter expense for month " + (i + 1) + ": ");
            expenses[i] = scanner.nextInt();
        }

        System.out.println("Running Python script...");
        String pythonScriptPath = "C:/Users/vasis/Desktop/New folder/trial1/src/prediction.py";
        String[] command = { "python", pythonScriptPath, Arrays.toString(months), Arrays.toString(expenses) };
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process process = processBuilder.start();
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        List<String> output = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            output.add(line);
        }
        process.waitFor();

        if (!output.isEmpty()) {
            int predictedExpense = Integer.parseInt(output.get(0));
            int nextMonth = months[n - 1] + 1;
            int[] newMonths = Arrays.copyOf(months, n + 1);
            int[] newExpenses = Arrays.copyOf(expenses, n + 1);
            newMonths[n] = nextMonth;
            newExpenses[n] = predictedExpense;

            System.out.println("Next month's predicted expense: " + predictedExpense);
            System.out.println("New array of months: " + Arrays.toString(newMonths));
            System.out.println("New array of expenses: " + Arrays.toString(newExpenses));
        } else {
            System.out.println("No output received from the Python script.");
        }

        scanner.close();
    }
}
