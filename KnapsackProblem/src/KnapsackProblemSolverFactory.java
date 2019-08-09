import com.sun.istack.internal.NotNull;

import java.util.Map;

class KnapsackProblemSolverFactory {
    @NotNull
    static KnapsackProblemSolver getSolver(String algorithm, Map<String, String> parameters) {
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
            case Constant.FPTAS:
                return new KnapsackProblemSolverFPTAS(parameters);
            case Constant.GA:
                return new KnapsackProblemSolverGA(parameters);
            default:
                return new KnapsackProblemSolverExplicit();
        }
    }
}
