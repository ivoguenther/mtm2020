//RACFJOB1  JOB 1
//STEP1       EXEC  PGM=IKJEFT01,DYNAMNBR=20
//SYSPRINT DD  SYSOUT=*                                                                        
//SYSUDUMP DD  SYSOUT=*                                        
//SYSTSIN  DD  *   
 LU
/*
//SYSTSPRT DD  PATHOPTS=(ORDWR,OTRUNC,OCREAT),PATHMODE=SIRWXU, 
//  PATHDISP=(KEEP,DELETE),
//  PATH='/z/z05872/test.out' 