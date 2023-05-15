package manufacturingdata_proj;

import java.util.Scanner;

public class console {

    public static void main (String[] args){
        int i = 0;
        Scanner scan = new Scanner (System.in);
        System.out.print("Enter number of items: ");
        int quant = scan.nextInt();
        manufacture[] arr = new manufacture[quant];
        while (quant > i)
        {
            String date = scan.next();
            String hours = scan.next();
            String category = scan.next();
            double fee = scan.nextDouble();
            int quantity = scan.nextInt();
            double time = scan.nextDouble();
            double cost = scan.nextDouble();
            manufacture obj = new manufacture(date, hours, category, fee, quantity, time, cost);
            arr[i] = obj;
            i++;
        }
        manufacture.unitHigh(arr);
        manufacture.unitLow(arr);
        manufacture.phoneStat(arr);
        manufacture.laptopStat(arr);
        manufacture.smart_watchStat(arr);
    }
}