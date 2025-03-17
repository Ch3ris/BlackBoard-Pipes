# BlackBoard-Pipes

Request:
  We have multiple reviews given as input. We also have different criterias for filtering. The different criterias can either be ones that eliminate the review from the review list, or ones that transform the review from the reviews list. We must implement this
  request using the two different architectural styles: Pipes-Filters and Blackboard. Transformation criterias must always occur after the elimination ones. 

Implmentation:
  We have a Client class ; This class handles the handling/reading of the input. The input is read from a file with a name as a String hardcoded to be "Testing_input.txt". After this, the input is cloned and passed once through the Pipes and Filters architecture, and twice to the Blackboard architecture. In our client we also declare both a PipeCustom[] variable as well as a KnowledgeSource[] variable. These hold the order of execution of the pipes/knowledge sources for the pipes-filters style and the blackboard style respectively. Since our transformations always need to occur after the eliminations, we also need to account for that.

The Arranger class:
  This class is defined in both the Pipes_Filters class, as well as the Blackboard class. The Arranger class handles the sorting of both the pipes/filters and the sources respectively. The logic for sorting both the pipes and sources is basically the same. 
  
  You take a pipe/source from an array of them, you iterate and check if the length after the request is the same as the length before the request. We basically do this to check if there was a string eliminated from the original input. If there was some strings eliminated, then the request we just checked is one that is an elimination request. Because of this, we add it to the eliminationPipes/eliminationSources arrayList. 
  
  If the length of the input before and after request is the same (no eliminations were done), we use a custom method defined "testTransform" on the value before and after the request. This method first takes each string from both of the list of strings, seperates the string by "," since we have csv values, and the compares the words one by one. If a word was modified, that means that a transformation occured, we return true and add the source/pipe to the transSource/transPipes. If a transformation didnt occur and an elimination also didn't occur, we just add it to the eliminationSources/eliminationPipes.
  
  After iterating throguh all the Sources/Pipes, we create a final list variable and add the list of eliminationSources/Pipes and transSource/pipes, returning the array[KnowledgeSource/Pipe] values of it.

Code Execution:
  Filters:
    The Pipes-Filters class holds a PipeCustom[] pipeline Variable, the input and the arranger. Our pipeline variable value is attributed directly using the "addPipeLine" method .After we sort the pipes using the arranger class mentioned above, we call execute on each of the pipes. The "PipeCustom" class is an interface and each of the requests mentioned above implement this interface and override its execute method. What's important to note here is that the execute method in the pipes-filters class returns a String[] variable. This is because we need to chain these values, our input becoming after each execution the value after a request was performed on it. After iterating through all of the pipes, We print the final result, that being the final value of the input.
  

  Blackboard:
    Similar to the pipes-filters implementation, here we have a KnowledgeSource[] sources variable which holds all of our knowledge sources. We also have an arranger and the input defined like in the pipes-filters implmentation. After the arranger sorts the input,we can move on to the execution of each knowledge source. What's important to distinguish here compared to the pipes-filters implementation is that each knowledge source does not directly return a value. Instead, we directly update the value of the input in the blackboard by passing to each of the execution method of the sources the defined blackboard class. 
