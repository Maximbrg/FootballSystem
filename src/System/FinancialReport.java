package System;

import System.FootballObjects.Team.Team;

import java.util.Date;

public class FinancialReport {

    private Date date;
    private final Team team;
    private int incomeFromGame;
    private int maintenanceFieldCost;
    private int payingSalary;
    private int income;
    private int expanse;

    //<editor-fold desc="constructor">
    public FinancialReport(Team team, Date date){
        this.team = team;
        maintenanceFieldCost = team.getField().getMaintenanceCost();
        income = team.getIncome();
        expanse = team.getExpense();
        payingSalary = team.getPaymentSalary();
        this.date = date;
    }
    //</editor-fold>

    //<editor-fold desc="getters">

    public int getIncomeFromGame() {
        return incomeFromGame;
    }

    public int getMaintenanceFieldCost() {
        return maintenanceFieldCost;
    }

    public int getPayingSalary() {
        return payingSalary;
    }

    public int getIncome() {
        return income;
    }

    public int getExpanse() {
        return expanse;
    }
    //</editor-fold>

    //<editor-fold desc="setter">
    public void setIncomeFromGame(int incomes){
        incomeFromGame=incomes;
    }

    public void setMaintenanceFieldCost(int maintenanceFieldCost) {
        this.maintenanceFieldCost = maintenanceFieldCost;
    }

    public void setPayingSalary(int payingSalary) {
        this.payingSalary = payingSalary;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public void setExpanse(int expanse) {
        this.expanse = expanse;
    }
    //</editor-fold>

}
