package jiprologExamples;

import com.ugos.jiprolog.engine.*;

public class SynchronousQuery {
    // main
    public static void main(String args[])
    {
        // New instance of prolog engine
        JIPEngine jip = new JIPEngine();

        JIPTerm queryTerm = null;
        JIPTerm queryTerm2 = null;

        // parse query
        try
        {
            // consult file
            jip.consultFile("familyrelationships.pl");
            jip.consultFile("Einstein's Riddle.pl");


            queryTerm = jip.getTermParser().parseTerm("father(Father, Child)");
            //queryTerm2 = jip.getTermParser().parseTerm("mother(Mother, Child)");
            queryTerm2 = jip.getTermParser().parseTerm("solve(WhoDrinksWater, WhoOwnsTheZebra)");
        }
        catch(JIPSyntaxErrorException ex)
        {
            ex.printStackTrace();
            System.exit(0); // needed to close threads by AWT if shareware
        }


        // open Query
        JIPQuery jipQuery = jip.openSynchronousQuery(queryTerm);
        JIPQuery jipQuery2 = jip.openSynchronousQuery(queryTerm2);
        JIPTerm solution;

        // Loop while there is another solution
        while (jipQuery.hasMoreChoicePoints())
        {
            solution = jipQuery.nextSolution();
            System.out.println("test");
            System.out.println(solution);
            //tempory solution to prevent nullpointer
            if(solution == null){
                //secondSolution(jip, jipQuery2);
                break;
            }
            JIPVariable[] vars = solution.getVariables();
            for (JIPVariable var : vars) {
                if (!var.isAnonymous()) {
                    System.out.print(var.getName() + " = " + var.toString(jip) + " ");
                    System.out.println();
                }
            }
        }
    }

    public static void secondSolution(JIPEngine jip, JIPQuery jipQuery2){
        JIPTerm secondSolution;
        System.out.println("Second queryTerm:");
        while (jipQuery2.hasMoreChoicePoints())
        {
            secondSolution = jipQuery2.nextSolution();
            System.out.println(secondSolution);
            //tempory solution to prevent nullpointer
            if(secondSolution == null){
                break;
            }
            JIPVariable[] vars = secondSolution.getVariables();
            for (JIPVariable var : vars) {
                if (!var.isAnonymous()) {
                    if(var.getName() == null){
                        System.out.println("Faile");
                    }
                    System.out.print(var.getName() + " = " + var.toString(jip) + " ");
                    System.out.println();
                }
            }
        }
    }
}
