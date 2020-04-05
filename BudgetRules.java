package System;

import java.util.List;

public class BudgetRules {

    private static BudgetRules ourInstance = new BudgetRules();

    public static BudgetRules getInstance() {
        return ourInstance;
    }

    private BudgetRules() {
    }

    private List<String> rules;

}
