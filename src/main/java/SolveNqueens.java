import org.jpl7.*;

public class SolveNqueens {
    public static void main(String[] args) {
        Query q1 =
                new Query(
                        "consult",
                        new Term[] {new Atom("plFiles/nQueens.pl")}
                );
        System.out.println( "consult " + (q1.hasSolution() ? "succeeded" : "failed"));
    }
}