import ca.dal.distributed.dpg1.Controllers.AnalyticsModule.Main.AnalyticsProcessor;
import ca.dal.distributed.dpg1.Controllers.AnalyticsModule.Utils.AnalyticsUpdate;
import ca.dal.distributed.dpg1.Controllers.ERDModule.Exceptions.ERDGeneratorException;
import ca.dal.distributed.dpg1.Controllers.ERDModule.Main.ERDGenerator;
import ca.dal.distributed.dpg1.Controllers.ERDModule.Main.ERDGeneratorMain;
import ca.dal.distributed.dpg1.Controllers.ExportModule.Main.ExportData;
import ca.dal.distributed.dpg1.Controllers.QueryProcessingModule.Exceptions.QueryExecutionRuntimeException;
import ca.dal.distributed.dpg1.Controllers.QueryProcessingModule.Exceptions.QueryParseFailureException;
import ca.dal.distributed.dpg1.Controllers.QueryProcessingModule.Main.QueryExecutor;
import ca.dal.distributed.dpg1.Controllers.QueryProcessingModule.Main.QueryManager;
import ca.dal.distributed.dpg1.Controllers.QueryProcessingModule.Model.ExecutionResponse;
import ca.dal.distributed.dpg1.Controllers.TransactionModule.Exceptions.TransactionExceptions;
import ca.dal.distributed.dpg1.Controllers.UserInterfaceModule.Main.Login;
import ca.dal.distributed.dpg1.Controllers.UserInterfaceModule.Main.Register;
import ca.dal.distributed.dpg1.Utils.GlobalUtils;
import ca.dal.distributed.dpg1.Utils.RemoteUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class DistributedDatabase {
    static ERDGeneratorMain erdGenerator = new ERDGenerator();

    static QueryExecutor executor =new QueryExecutor();
    static AnalyticsProcessor analyticsProcessor = new AnalyticsProcessor();
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, ERDGeneratorException, TransactionExceptions , QueryParseFailureException,QueryExecutionRuntimeException{
        if (RemoteUtils.isInternalCommand(args)) {
            RemoteUtils.executeInternalCommand(args);
        } else {
            Scanner c = new Scanner(System.in);
            String userName;
            String pass, passConfirm, securityAnswer;
            boolean loginValidation;
            String Query = "temp";
            Login chk_login = new Login();
            Register reg = new Register();
            Scanner console = new Scanner(System.in);
            Boolean queryStatus;


            System.out.println("*Hostname");
            System.out.println("Welcome");
            String access_option = "0", db_option = "0";

            while (!access_option.equals("3")) {
                System.out.println("Select one of the below options:");
                System.out.println("\nEnter 1 for login");
                System.out.println("\nEnter 2 for Register");
                System.out.println("\nEnter 3 to Exit");
                access_option = console.nextLine();
                switch (access_option) {
                    case "1":
                        System.out.println("\nEnter your name: ");
                        userName = c.nextLine();
                        System.out.println("\nEnter password: ");
                        pass=c.nextLine();
                        System.out.println("\nAnswer the following Security Question:");
                        System.out.println("\nWhat primary school did you attend?");
                        securityAnswer = c.nextLine();
                        loginValidation = chk_login.checkLogin(userName, pass, securityAnswer);
                        if (loginValidation) {
                            System.out.println("\nSucessfully Logged in as:" + userName);
                            // System.out.println(chk_login.loggedUsers.toString()); 
                        } else {
                            System.out.println("\nInvalid Credentials");
                            break;
                        }
                        while (!db_option.equals("5")) {
                            System.out.println("\nSelect one of the below options:");
                            System.out.println("\nEnter 1 for Write Queries");
                            System.out.println("\nEnter 2 for Export");
                            System.out.println("\nEnter 3 for Data Model");
                            System.out.println("\nEnter 4 for Analytics");
                            System.out.println("\nEnter 5 to Exit");
                            db_option = console.nextLine();
                            switch (db_option) {
                                // Write Queries Function
                                case "1":
                                    System.out.println(
                                            "\nEnter Query or Transaction, Enter x to Exit:");
                                        Query = c.nextLine();
                                        if (!Query.equals("x")) {
                                            try{
                                                ExecutionResponse er = executor.processInputQuery(Query);
                                                if(er.isExecutionSuccess()){
                                                    System.out.println("\nQuery Sucessfully Executed "+er.getExecutionResponseMsg());
                                                }else{
                                                    System.out.println("\n Something Went Wrong");
                                                }
                                            }
                                            catch(Exception e)
                                            {
                                                System.out.println(e.getMessage());
                                            }finally {

                                                AnalyticsUpdate.incrementUserCountData(QueryManager.dataBaseInUse, userName);
                                            }
                                        }
                                    Query="temp";
                                    break;
                                // Export Query Function
                                case "2":
                                    System.out.println("\nEnter Database Name to generate dump");
                                    String dbName = c.nextLine();
                                    ExportData export = new ExportData(dbName);
                                    export.exportToFile();
                                    break;
                                // ERD Function
                                case "3":
                                    System.out.println("\nEnter Database Name to generate ERD");
                                    Query = c.nextLine();
                                    System.out.println("\nERD:");
                                    erdGenerator.generateERD(Query);
                                    break;
                                // Analytics Function
                                case "4":
                                    System.out.println("\nAnalytics Function Exectution");
                                    analyticsProcessor.parseQuery();
                                    break;
                                // Exit
                                case "5":
                                    System.out.println("\nExiting......");
                                    break;
                                default:
                                    System.out.println("\nInvalid input");
                            }
                            System.out.print("\n");
                        }
                        db_option="0";
                        break;
                    case "2":
                        System.out.println("\nset your name: ");
                        userName = c.nextLine();
                        System.out.println("\nset your password: ");
//                                        ch = c.nextLine();
                        pass =c.nextLine();
//                                        pass = String.valueOf(ch);
                        System.out.println("\nEnter password once again: ");
//                                        ch1 = c.readPassword();
                        passConfirm=c.nextLine();
//                                        passConfirm = String.valueOf(ch1);
                        System.out.println(pass);
                        System.out.println(passConfirm);
                        while (!pass.equals(passConfirm)) {
                            System.out.println("\nPasswords did not matched");
                            System.out.println("\nset your password: ");
//                                                ch = c.readPassword();
                            pass=c.nextLine();
//                                                pass = String.valueOf(ch);
                            System.out.println("\nEnter password once again: ");
//                                                ch = c.readPassword();
                            passConfirm=c.nextLine();
//                                                passConfirm = String.valueOf(ch);
                        }
                        System.out.println("\nAnswer the following Security Question:");
                        System.out.println("\nWhat primary school did you attend?");
                        securityAnswer = c.nextLine();
                        reg.setRegister(userName, pass, securityAnswer);
                        System.out.println("\nSucessfully Registered, Please Login now...");
                        break;
                    case "3":
                        System.out.println("\nExiting......");
                        break;
                    default:
                        System.out.println("\nInvalid input");
                }
                System.out.println();
            }
        }


    }
}
