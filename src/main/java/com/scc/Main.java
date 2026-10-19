package com.scc;

import com.scc.dao.DatabaseInitializer;
import com.scc.dao.ScenarioDao;
import com.scc.ui.ConsoleUI;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 1. Initialize Database Schema
        DatabaseInitializer.initialize();

        // 2. Instantiate DAO & Console UI
        ScenarioDao scenarioDao = new ScenarioDao();
        Scanner scanner = new Scanner(System.in);

        // 3. Start Interactive Application Loop
        ConsoleUI consoleUI = new ConsoleUI(scenarioDao, scanner);
        consoleUI.start();
    }
}