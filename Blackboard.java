/*
Blackboard pattern:
 
The blackboard pattern describes the overall flow of information and control within the blackboard architecture. It typically involves the following steps:

Initialization: The blackboard is set up with the initial problem statement and any available input data.

Activation: The controller selects and activates one or more knowledge sources based on the current state of the problem and the available data on the blackboard.

Execution: The activated knowledge sources independently analyze the problem, apply their specialized algorithms or techniques, and produce partial solutions or hypotheses.

Conflict resolution: If multiple knowledge sources generate conflicting or overlapping solutions, a conflict resolution mechanism is employed to reconcile the differences and select the most 
appropriate solution(s).

Update: The knowledge sources update the blackboard with their outputs, such as new constraints, proposed solutions, or intermediate results.

Iteration: The controller repeats the activation and execution steps until a satisfactory solution is reached, convergence criteria are met, or a predefined time limit is exceeded.
 */

public class Blackboard
{
    public Blackboard(String[] input,String[][] listBuyers)
    {
        //TODO        
    }
}