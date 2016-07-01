import src.jCbc;
import src.SWIGTYPE_p_CbcModel;
import src.SWIGTYPE_p_CoinModel;
import src.SWIGTYPE_p_OsiClpSolverInterface;
import src.SWIGTYPE_p_double;
import src.SWIGTYPE_p_int;
import src.SWIGTYPE_p_std__string;


public class ex2 {
     public static void main(String argv[]) {
	
     System.loadLibrary("jCbc");
	 
	
	SWIGTYPE_p_OsiClpSolverInterface solver = jCbc.new_jOsiClpSolverInterface(); 
	jCbc.readLp(solver,"model.lp");
	SWIGTYPE_p_CbcModel Model = jCbc.new_jCbcModel();
	jCbc.assignSolver(Model,solver); 

	jCbc.solve_2(Model,solver,0);

	int numIntVars = jCbc.getNumIntegers(solver);
	SWIGTYPE_p_std__string names = jCbc.new_jarray_string(numIntVars); 
	SWIGTYPE_p_int values = jCbc.new_jarray_int(numIntVars); 
	SWIGTYPE_p_double sol= jCbc.getColSolution(solver);

	int k=0;
	for (int j = 0; j < jCbc.getNumCols(solver); j++){
		if (jCbc.isInteger(solver,j)==1){
		  jCbc.jarray_string_setitem(names,k,jCbc.getColName(solver,j));
		  jCbc.jarray_int_setitem(values,k,(int)jCbc.jarray_double_getitem(sol,j));
	  k++;
	 }} 

	jCbc.delete_jCbcModel(Model);
	
	//=======================================================================
	
	
	SWIGTYPE_p_OsiClpSolverInterface solver1 = jCbc.new_jOsiClpSolverInterface(); 
	jCbc.readLp(solver1,"model1.lp");
	SWIGTYPE_p_CbcModel Model1 = jCbc.new_jCbcModel();
	jCbc.assignSolver(Model1,solver1); 
	double time_total=0;
	double time1 = System.currentTimeMillis();
	jCbc.solve_whs(Model1,solver1,names,values,k,0);
	double time2 = System.currentTimeMillis() - time1;
	time_total+= time2;

	SWIGTYPE_p_double sol1= jCbc.getColSolution(solver1);
	int nCols = jCbc.getNumCols(solver1);
	

	System.out.println("\nSolution:\n"); 
	System.out.println("Objective Value = " + jCbc.getObjValue(Model1)+"\n"); 

	jCbc.delete_jCbcModel(Model1);
	jCbc.delete_jarray_int(values);
	jCbc.delete_jarray_string(names);

	System.out.println("\nTotal time = "+time_total);

}}