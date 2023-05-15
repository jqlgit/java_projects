package manufacturingdata_proj;


public class manufacture {
    private String date_, hours_, category_;
    private double fee_, time_, cost_;
    private int quantity_;

    public manufacture(String date, String hours, String category, double fee, int quantity, double time, double cost) {
        date_ = date;
        hours_ = hours;
        category_ = category;
        fee_ = fee;
        time_ = time;
        cost_ = cost;
        quantity_ = quantity;
    }

    public static void unitHigh(manufacture arr[]) {
        int index = getMaxPriceIndex(arr);
        System.out.println("Highest per unit assembling fee:");
        System.out.println("When: " + arr[index].getDate() + " " + arr[index].getTime());
        System.out.println("Category: " + arr[index].getCategory());
        System.out.println("Price: " + arr[index].getCost());
    }

    public static void unitLow(manufacture arr[]) {
        int index = getLowPriceIndex(arr);
        System.out.println("Lowest per unit assembling fee:");
        System.out.println("When: " + arr[index].getDate() + " " + arr[index].getTime());
        System.out.println("Category: " + arr[index].getCategory());
        System.out.println("Price: " + arr[index].getCost());
    }

    public static void phoneStat(manufacture arr[]) {
        int i = 0, totalQuant = 0, totalFee = 0, avgFee, totalTime = 0;
        double netProfit, totalCost = 0;
        while (i < arr.length) {
            if (arr[i].getCategory() == "phone") {
                totalQuant += arr[i].getQuantity();
                totalFee += arr[i].getFee();
                totalTime += arr[i].getTime();
                totalCost += arr[i].getCost();
            }
            i++;
        }
        netProfit = ((totalCost-totalFee-(totalTime*16)) / totalQuant);
        avgFee = totalFee/(arr.length);

        System.out.println("Statistic of phone");
        System.out.println("Quantity: "+ totalQuant);
        System.out.println("Average Assembling fee: " + avgFee);
        System.out.println("Average Net Profit: " + netProfit);
    }

    public static void laptopStat(manufacture arr[]) {
        int i = 0, totalQuant = 0, totalFee = 0, avgFee, totalTime = 0;
        double netProfit, totalCost = 0;

        while (i < arr.length) {
            if (arr[i].getCategory() == "laptop") {
                totalQuant += arr[i].getQuantity();
                totalFee += arr[i].getFee();
                totalTime += arr[i].getTime();
                totalCost += arr[i].getCost();
            }
            i++;
        }
        netProfit = ((totalCost-totalFee-(totalTime*16)) / totalQuant);
        avgFee = totalFee/(arr.length);

        System.out.println("Statistic of laptop");
        System.out.println("Quantity: "+ totalQuant);
        System.out.println("Average Assembling fee: " + avgFee);
        System.out.println("Average Net Profit: " + netProfit);
    }

    public static void smart_watchStat(manufacture arr[]) {
        int i = 0, totalQuant = 0, totalFee = 0, avgFee, totalTime = 0;
        double netProfit, totalCost = 0;

        while (i < arr.length) {
            if (arr[i].getCategory() == "smart_watch") {
                totalQuant += arr[i].getQuantity();
                totalFee += arr[i].getFee();
                totalTime += arr[i].getTime();
                totalCost += arr[i].getCost();
            }
            i++;
        }
        netProfit = ((totalCost-totalFee-(totalTime*16)) / totalQuant);
        avgFee = totalFee/(arr.length);

        System.out.println("Statistic of smart_watch");
        System.out.println("Quantity: "+ totalQuant);
        System.out.println("Average Assembling fee: " + avgFee);
        System.out.println("Average Net Profit: " + netProfit);
    }
    public String getDate() {
        return date_;
    }

    public String getHours() {
        return hours_;
    }

    public String getCategory() {
        return category_;
    }

    public double getFee() {
        return fee_;
    }

    public double getTime() {
        return time_;
    }

    public double getCost() {
        return cost_;
    }

    public int getQuantity() {
        return quantity_;
    }

    public static int getMaxPriceIndex(manufacture arr[]) {
        int i = 1;
        double max = arr[0].getFee();
        int maxIndex = 0;
        while (i < arr.length) {
            if (arr[i].getFee() > max)
                max = arr[i].getFee();
            maxIndex = i;
            i++;
        }
        return maxIndex;
    }

    public static int getLowPriceIndex(manufacture arr[])
    {
        int j = 1;
        double min = arr[0].getFee();
        int minIndex = 0;
        while (j < arr.length) {
            if (arr[j].getFee() > min)
                min = arr[j].getFee();
            minIndex = j;
            j++;
        }
        return minIndex;
    }
}
