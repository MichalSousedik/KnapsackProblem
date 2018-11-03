import com.sun.istack.internal.NotNull;

class KnapsackProblemSolverFactory {
    @NotNull
    static KnapsackProblemSolver getSolver(String algorithm, String parameter) {
        switch (algorithm) {
            case "explicit":
                return new KnapsackProblemSolverExplicit();
            case "heuristic_ratio":
                return new KnapsackProblemSolverHeuristic("heuristic_ratio");
            case "heuristic_value":
                return new KnapsackProblemSolverHeuristic("heuristic_value");
            case "heuristic_weight":
                return new KnapsackProblemSolverHeuristic("heuristic_weight");
            case "bab":
                return new KnapsackProblemSolverBAB();
            case "dynamic_value":
                return new KnapsackProblemSolverDynamic(algorithm);
            case "dynamic_weight":
                return new KnapsackProblemSolverDynamic(algorithm);
            case "fptas":
                return new KnapsackProblemSolverFPTAS(parameter);
            default:
                return new KnapsackProblemSolverExplicit();
        }
    }
}
