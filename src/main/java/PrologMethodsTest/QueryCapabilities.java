package PrologMethodsTest;

import com.ugos.jiprolog.engine.*;

public class QueryCapabilities {
    //main
    public static void main(String[] args) {
        //new PrologIntepreter instance
        //TODO: Fix fail output on engine creation
        JIPEngine jip = new JIPEngine();
        //instantiate queryVariable
        JIPTerm queryTerm = null;
        JIPTerm testqueryTerm = null;
        //open prologfile and parse query
        try {
            //prologFile
            jip.consultFile("plFiles/test2.pl");
            //actual query: who does dave hate?
            queryTerm = jip.getTermParser().parseTerm("hate(dave, X).");
            //actual query: who is a woman?
            //queryTerm = jip.getTermParser().parseTerm("woman(X).");
            //actual query: is alice a man?
           testqueryTerm = jip.getTermParser().parseTerm("man(alice).");
        }
        catch (JIPSyntaxErrorException e){
            e.printStackTrace();
            //needed for some reason
            System.exit(0);
        }
        // open Query
        JIPQuery jipQuery = jip.openSynchronousQuery(queryTerm);
        //variable to store result
        JIPTerm solution;

        //loop to traverse each matching node
        while (jipQuery.hasMoreChoicePoints()){
            //get actual next matching node
            solution = jipQuery.nextSolution();
            //complete result term
            //System.out.println(solution.toString(jip));
            if(solution!=null){
                //get an array of prolog variables inside the solution
                JIPVariable[] vars = solution.getVariables();
                for (JIPVariable var : vars) {
                    if (!var.isAnonymous()) {
                        //print each variable
                        System.out.print(var.getName() + " = " + var.toString(jip) + " ");
                        System.out.println();
                    }
                }
            }else{
                System.out.println("No more solutions available");
                jip.closeAllQueries();
                testTrueFalse(jip, testqueryTerm);
            }
        }

    }

    public static void testTrueFalse(JIPEngine jip, JIPTerm queryTerm){
        System.out.println("TESTING");
        JIPQuery jipQuery = jip.openSynchronousQuery(queryTerm);
        JIPTerm solution;
        while (jipQuery.hasMoreChoicePoints()){
            solution = jipQuery.nextSolution();
            if(solution!=null){
                JIPVariable[] vars = solution.getVariables();
                for (JIPVariable var : vars) {
                    if (!var.isAnonymous()) {
                        System.out.print(var.getName() + " = " + var.toString(jip) + " ");
                        System.out.println();
                    }
                }
            }else{
                System.out.println("No more solutions available");
            }
        }
    }
}
