Unzip attached file DistributedSystems - Lab 2 - Devan Shah 100428864.zip 

Task 1:
	Define Interface for the Election Service in RMI
		1. ElectionInterface can be found at: DistributedSystems - Lab 2 - Task 1 Define Election Interface\src\ElectionInterface.java after unzip
		2. ElectionResults can be found at: DistributedSystems - Lab 2 - Task 1 Define Election Interface\src\ElectionResults.java after unzip
		Note: Information on each of the file can be found in the report.

Task 2:
	Implement the Election service in Java RMI
		1. Open cmd
		2. cd to <path where zipped file was extracted>\DistributedSystems - Lab 2 - Task 2 Implement Election\src
		3. Run "javac *" to compile the source code
		4. Open another cmd and cd to the same path mentioned in step 2
		5. In one of the cmd windows start the server with command "java ElectionServer localhost"
		6. In the other cmd window run the client
			i. Run command "java ElectionClient vote localhost 1099" to cast a vote
		   ii. Run command "java ElectionClient results localhost 1099" to retrieve the results of the election
		7. Repeat step 6 to run multiple clients to perform a vote (open a new cmd window for each client)	
		8. Close the ElectionServer

Task 3:
	Save results
		1. Open cmd
		2. cd to <path where zipped file was extracted>\DistributedSystems - Lab 2 - Task 3 Save Results\src
		3. Run "javac *" to compile the source code
		4. Open another cmd and cd to the same path mentioned in step 2
		5. In one of the cmd windows start the server with command "java ElectionServer localhost"
		6. In the other cmd window run the client
			i. Run command "java ElectionClient vote localhost 1099" to cast a vote
		   ii. Run command "java ElectionClient results localhost 1099" to zipped the results of the election
		7. Repeat step 6 to run multiple clients to perform a vote (open a new cmd window for each client)
		8. Using the steps above cast some votes
		9. On the cmd window where the ElectionServer is running type: ctrl + c to terminate the ElectionServer
	   10. Now in a cmd window run the ElectionServer again with command: "java ElectionServer localhost"	
	   11. The ElectionServer this time will ask if you would like to restore results from the generated file "ElectionResultsRawData.ser"
	   12. Type "yes" here to have the restore performed, can type no if you like to start the server new.
	   13. Run 6.ii to retrieve results, this will grab the results that were restored
	   13. Close the ElectionServer

Task 4:
	Ensure records are consistent when concurrently accessed by multiple clients
		1. Open cmd
		2. cd to <path where zipp file was extracted>\DistributedSystems - Lab 2 - Task 4 Consistent\src
		3. Run "javac *" to compile the source code
		4. Open another cmd and cd to the same path mentioned in step 2
		5. In one of the cmd windows start the server with command "java ElectionServer localhost"
		6. In the other cmd window run the client
			i. Run command "java ElectionClient vote localhost 1099" to cast a vote
		   ii. Run command "java ElectionClient results localhost 1099" to retrieve the results of the election
		7. Repeat step 6 to run multiple clients to perform a vote (open a new cmd window for each client)
		8. Try to run all the votes at the same time, try to retrieve results from some of the clients also.
		9. You will notice that there are no issues, all the results are recorded correctly and in order of occurrence.
	   13. Close the ElectionServer