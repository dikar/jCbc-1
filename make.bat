swig -c++ -java -package src -outdir src jCbc.i  

g++ -c -fopenmp jCbc.cpp jCbc_wrap.cxx -DHAVE_STRUCT_TIMESPEC -static -Wl,--kill-at -I "C:\Java\jdk1.8.0_45\include" -I "C:\Java\jdk1.8.0_45\include\win32" -I "C:\opt\Cbc-2.9.7.0\include\coin" 

g++ -shared -fopenmp jCbc.o jCbc_wrap.o  -o jCbc.dll  -static -Wl,--kill-at -I "C:\Java\jdk1.8.0_45\include" -I "C:\Java\jdk1.8.0_45\include\win32" -I "C:\opt\Cbc-2.9.7.0\include\coin"  -L C:\opt\Cbc-2.9.7.0\lib -lCbcSolver -lCbc  -lClp -lOsiClp -lOsi -lOsiCbc  -lCgl -lCoinUtils  -lcoinmumps -lcoinblas -lcoinlapack  -lcoinmetis  -lgfortran -lpthread

pause 
:END