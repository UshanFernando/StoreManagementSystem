package model;

public class SalesReport {
    private int noOfSales;
    private double totalSales;
    private double totalDiscount;
    private double totalRevenue;

    public SalesReport(int noOfSales, double totalSales, double totalDiscount) {
        this.noOfSales = noOfSales;
        this.totalSales = totalSales;
        this.totalDiscount = totalDiscount;
        this.totalRevenue = totalSales-totalDiscount;
    }

    public int getNoOfSales() {
        return noOfSales;
    }

    public void setNoOfSales(int noOfSales) {
        this.noOfSales = noOfSales;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    @Override
    public String toString() {
        return "SalesReport{" +
                "noOfSales=" + noOfSales +
                ", totalSales=" + totalSales +
                ", totalDiscount=" + totalDiscount +
                ", totalRevenue=" + totalRevenue +
                '}';
    }
}
