import java.io.*;
import java.util.*;

public class Project3
{
    static int max;
    static int[] mem;
    static int ip, bp, sp, rv, hp, numPassed, gp;

    static String fileName;

    public static void main(String[] args) throws Exception
    {
        BufferedReader keys = new BufferedReader(
                new InputStreamReader( System.in));
        if( args.length != 2 ){
            System.out.println("Usage: jcr Project3 <vpl program> <memory size>" );
            System.exit(1);
        }
        fileName = args[0];
        max = Integer.parseInt( args[1] );
        mem = new int[max];

        // load the program into the front part of
        // memory
        BufferedReader input = new BufferedReader( new FileReader( fileName ));
        String line;
        StringTokenizer st;
        int opcode;

        ArrayList<IntPair> labels, holes;
        labels = new ArrayList<IntPair>();
        holes = new ArrayList<IntPair>();
        int label;

        int k=0;
        do {
            line = input.readLine();
            System.out.println("parsing line [" + line + "]");
            if( line != null )
            {// extract any tokens
                st = new StringTokenizer( line );
                if( st.countTokens() > 0 )
                {// have a token, so must be an instruction (as opposed to empty line)

                    opcode = Integer.parseInt(st.nextToken());

                    // load the instruction into memory:

                    if( opcode == labelCode )
                    {// note index that comes where label would go
                        label = Integer.parseInt(st.nextToken());
                        labels.add( new IntPair( label, k ) );
                    }
                    else if( opcode == noopCode ){
                    }
                    else
                    {// opcode actually gets stored
                        mem[k] = opcode;  ++k;

                        if( opcode == callCode || opcode == jumpCode ||
                                opcode == condJumpCode )
                        {// note the hole immediately after the opcode to be filled in later
                            label = Integer.parseInt( st.nextToken() );
                            mem[k] = label;  holes.add( new IntPair( k, label ) );
                            ++k;
                        }

                        // load correct number of arguments (following label, if any):
                        for( int j=0; j<numArgs(opcode); ++j )
                        {
                            mem[k] = Integer.parseInt(st.nextToken());
                            ++k;
                        }

                    }// not a label

                }// have a token, so must be an instruction
            }// have a line
        } while( line != null );

        //System.out.println("after first scan:");
        //showMem( 0, k-1 );

        // fill in all the holes:
        int index;
        for( int m=0; m<holes.size(); ++m )
        {
            label = holes.get(m).second;
            index = -1;
            for( int n=0; n<labels.size(); ++n )
                if( labels.get(n).first == label )
                    index = labels.get(n).second;
            mem[ holes.get(m).first ] = index;
        }

        // System.out.println("after replacing labels:");
        // showMem( 0, k-1 );

        // initialize registers:
        bp = k;  sp = k+2;  ip = 0;  rv = -1;  hp = max;
        numPassed = 0;

        int codeEnd = bp-1;

        System.out.println("Code is " );
        showMem( 0, codeEnd );

        gp = codeEnd + 1;

        // start execution:
        boolean done = false;
        int op, a=0, b=0, c=0;
        int actualNumArgs;

        int step = 0;

        int oldIp = 0;

        do{
/*
      System.out.println("--------------------------");
      System.out.println("Step of execution with IP = " + ip + " opcode: " +
          mem[ip] +
         " bp = " + bp + " sp = " + sp + " hp = " + hp + " rv = " + rv );
      System.out.println(" chunk of code: " +  mem[ip] + " " +
                            mem[ip+1] + " " + mem[ip+2] + " " + mem[ip+3] );
      System.out.println("--------------------------");
      System.out.println( " memory from " + (codeEnd+1) + " up: " );
      showMem( codeEnd+1, sp+3 );
      System.out.println("hit <enter> to go on" );
      keys.readLine();
*/

//System.out.println("ip=" + ip + " hp: " + hp + "--------------------------------------");
//showMem(0,max-1);

            // INSERT YOUR CODE HERE TO IMPLEMENT ALL THE COMMANDS
            switch(mem[ip])
            {
                case 0: // 0 no op
                    ip++;
                    break;
                case 2:  // 2 L call
                    mem[sp]=ip+2; // set new return ip
                    mem[sp+1]=bp; // set new return bp
                    bp=sp; // set new bp
                    sp=sp+2+numPassed; // move sp to end of new frame
                    numPassed=0; //reset numPassed
                    ip=mem[ip+1]; // move ip to L                 
                    break;
                case 3: // 3 a pass
                    mem[sp+2+numPassed]=mem[bp+2+mem[ip+1]]; // put cell a contents on the stack
                    numPassed+=1; //increment numPassed
                    ip+=2; // move ip to next instruction
                    break;
                case 4: // 4 n locals
                    sp+=mem[ip+1]; // increase sp by n  
                    ip+=2; // move ip to next instruction
                    break;
                case 5: // 5 a return
                    rv=mem[bp+2+mem[ip+1]]; // copy value in cell a to rv                     
                    ip=mem[bp]; // set ip to return ip 
                    bp=mem[bp+1]; // set bp to return bp
                    break;
                case 6: // 6 a get retval
                    mem[bp+2+mem[ip+1]]=rv; // copy rv to cell a
                    ip+=2; // move ip to next instruction
                    break;
                case 7: // 7 L jump
                    ip=mem[ip+1]; //change ip to L
                    break;
                case 8: // 8 L a cond
                    if(mem[bp+2+mem[ip+2]]!=0) ip=mem[ip+1]; // if cell a is non-zero, jump to L
                    else ip+=3; // else move ip to next instruction
                    break;
                case 9: // 9 a b c add
                    mem[bp+2+mem[ip+1]]=mem[bp+2+mem[ip+2]]+mem[bp+2+mem[ip+3]]; // b + c, store in a
                    ip+=4; // move ip to next instruction
                    break;
                case 10: // 10 a b c subtract
                    mem[bp+2+mem[ip+1]]=mem[bp+2+mem[ip+2]]-mem[bp+2+mem[ip+3]]; // b - c, store in a
                    ip+=4; // move ip to next instruction
                    break;
                case 11: // 11 a b c multiply
                    mem[bp+2+mem[ip+1]]=mem[bp+2+mem[ip+2]]*mem[bp+2+mem[ip+3]]; // b * c, store in a
                    ip+=4; // move ip to next instruction
                    break;
                case 12: // 12 a b c divide
                    mem[bp+2+mem[ip+1]]=mem[bp+2+mem[ip+2]]/mem[bp+2+mem[ip+3]]; // b / c, store in a
                    ip+=4; // move ip to next instruction
                    break;
                case 13: // 13 a b c remainder
                    mem[bp+2+mem[ip+1]]=mem[bp+2+mem[ip+2]]%mem[bp+2+mem[ip+3]]; // b % c, store in a
                    ip+=4; // move ip to next instruction
                    break;
                case 14: // 14 a b c equal 
                    if(mem[bp+2+mem[ip+2]]==mem[bp+2+mem[ip+3]]) mem[bp+2+mem[ip+1]]=1; // if b == c, store 1 in a
                    else mem[bp+2+mem[ip+1]]=0; // else store 0 in a
                    ip+=4; // move ip to next instruction
                    break;
                case 15: // 15 a b c not equal 
                    if(mem[bp+2+mem[ip+2]]!=mem[bp+2+mem[ip+3]]) mem[bp+2+mem[ip+1]]=1; // if b != c, store 1 in a
                    else mem[bp+2+mem[ip+1]]=0; // else store 0 in a
                    ip+=4; // move ip to next instruction
                    break;
                case 16: // 16 a b c less than 
                    if(mem[bp+2+mem[ip+2]]<mem[bp+2+mem[ip+3]]) mem[bp+2+mem[ip+1]]=1; // if b < c, store 1 in a
                    else mem[bp+2+mem[ip+1]]=0; // else store 0 in a
                    ip+=4; // move ip to next instruction
                    break;
                case 17: // 17 a b c less than or equal
                    if(mem[bp+2+mem[ip+2]]<=mem[bp+2+mem[ip+3]]) mem[bp+2+mem[ip+1]]=1; // if b <= c, store 1 in a
                    else mem[bp+2+mem[ip+1]]=0; // else store 0 in a
                    ip+=4; // move ip to next instruction
                    break;
                case 18: // 18 a b c and 
                    if(mem[bp+2+mem[ip+2]]==1&&mem[bp+2+mem[ip+3]]==1) mem[bp+2+mem[ip+1]]=1; // if b && c, store 1 in a
                    else mem[bp+2+mem[ip+1]]=0; // else store 0 in a
                    ip+=4; // move ip to next instruction
                    break;
                case 19: // 19 a b c or 
                    if(mem[bp+2+mem[ip+2]]==1||mem[bp+2+mem[ip+3]]==1) mem[bp+2+mem[ip+1]]=1; // if b || c, store 1 in a
                    else mem[bp+2+mem[ip+1]]=0; // else store 0 in a
                    ip+=4; // move ip to next instruction
                    break;
                case 20: // 20 a b not 
                    if(mem[bp+2+mem[ip+2]]==0) mem[bp+2+mem[ip+1]]=1; // if b==0 sore 1 in a
                    else mem[bp+2+mem[ip+1]]=0; // else store 0 in a
                    ip+=3; // move ip to next instruction
                    break;
                case 21: // 21 a b opposite
                    mem[bp+2+mem[ip+1]]=-1*mem[bp+2+mem[ip+2]];
                    ip+=3;
                    break;
                case 22: // 22 a n literal 
                    mem[bp+2+mem[ip+1]]=mem[ip+2]; // put n in cell a
                    ip+=3; // move ip to next instruction
                    break;
                case 23: // 23 a b copy
                    mem[bp+2+mem[ip+1]]=mem[bp+2+mem[ip+2]];
                    ip+=3;
                    break;
                case 24: // a b c get
                    mem[bp+2+mem[ip+1]]= mem[mem[bp+2+mem[ip+2]] + mem[bp+2+mem[ip+3]]];
                    ip+=4;
                    break;
                case 25: // 25 a b c put
                    mem[mem[bp+2+mem[ip+1]] + mem[bp+2+mem[ip+2]]] = mem[bp+2+mem[ip+3]];
                    ip+=4;
                    break;
                case 26: // 26 halt
                    done = true;
                    break;
                case 27: // 27 a input
                    Scanner scan = new Scanner(System.in); // create a Scanner
                    System.out.print("? "); // prompt user for input
                    mem[bp+2+mem[ip+1]]= scan.nextInt(); // store input in cell a
                    ip+=2; // move ip to next instruction
                    break;
                case 28: // 28 a output
                    System.out.print(mem[bp+2+mem[ip+1]]); // output cell a
                    ip+=2; // move ip to next instruction
                    break;
                case 29: // 29 newline
                    System.out.println(); // newline
                    ip++; // move ip to next instruction
                    break;
                case 30: // 30 a symbol
                    if (32 <= mem[bp+2+mem[ip+1]] && mem[bp+2+mem[ip+1]] <= 126)
                        System.out.print((char)mem[bp+2+mem[ip+1]]);
                    ip+=2;
                    break;
                case 31: // 31 a b new
                    hp = hp - mem[bp+2+mem[ip+2]];
                    mem[bp+2+mem[ip+1]] = hp;
                    ip+=3;
                    break;
                case 32: // 32 n allocate global space
                    gp = bp;
                    bp+=mem[ip+1];
                    sp=bp+2;
                    ip+=2;
                    break;
                case 33: // 33 n a copy to global
                    mem[gp+mem[ip+1]]=mem[bp+2+mem[ip+2]];
                    ip+=3;
                    break;
                case 34: // 34 a n copy from global
                    mem[bp+2+mem[ip+1]]=mem[gp+mem[ip+2]];
                    ip+=3;
                    break;
            }
            //End of my code


        }while( !done );

    }// main

    // use symbolic names for all opcodes:

    // op to produce comment
    private static final int noopCode = 0;

    // ops involved with registers
    private static final int labelCode = 1;
    private static final int callCode = 2;
    private static final int passCode = 3;
    private static final int allocCode = 4;
    private static final int returnCode = 5;  // return a means "return and put
    // copy of value stored in cell a in register rv
    private static final int getRetvalCode = 6;//op a means "copy rv into cell a"
    private static final int jumpCode = 7;
    private static final int condJumpCode = 8;

    // arithmetic ops
    private static final int addCode = 9;
    private static final int subCode = 10;
    private static final int multCode = 11;
    private static final int divCode = 12;
    private static final int remCode = 13;
    private static final int equalCode = 14;
    private static final int notEqualCode = 15;
    private static final int lessCode = 16;
    private static final int lessEqualCode = 17;
    private static final int andCode = 18;
    private static final int orCode = 19;
    private static final int notCode = 20;
    private static final int oppCode = 21;

    // ops involving transfer of data
    private static final int litCode = 22;  // litCode a b means "cell a gets b"
    private static final int copyCode = 23;// copy a b means "cell a gets cell b"
    private static final int getCode = 24; // op a b means "cell a gets
    // contents of cell whose
    // index is stored in b"
    private static final int putCode = 25;  // op a b means "put contents
    // of cell b in cell whose offset is stored in cell a"

    // system-level ops:
    private static final int haltCode = 26;
    private static final int inputCode = 27;
    private static final int outputCode = 28;
    private static final int newlineCode = 29;
    private static final int symbolCode = 30;
    private static final int newCode = 31;

    // global variable ops:
    private static final int allocGlobalCode = 32;
    private static final int toGlobalCode = 33;
    private static final int fromGlobalCode = 34;

    // debug ops:
    private static final int debugCode = 35;

    // return the number of arguments after the opcode,
    // except ops that have a label return number of arguments
    // after the label, which always comes immediately after
    // the opcode
    private static int numArgs( int opcode )
    {
        // highlight specially behaving operations
        if( opcode == labelCode ) return 1;  // not used
        else if( opcode == jumpCode ) return 0;  // jump label
        else if( opcode == condJumpCode ) return 1;  // condJump label expr
        else if( opcode == callCode ) return 0;  // call label

            // for all other ops, lump by count:

        else if( opcode==noopCode ||
                opcode==haltCode ||
                opcode==newlineCode ||
                opcode==debugCode
        )
            return 0;  // op

        else if( opcode==passCode || opcode==allocCode ||
                opcode==returnCode || opcode==getRetvalCode ||
                opcode==inputCode ||
                opcode==outputCode || opcode==symbolCode ||
                opcode==allocGlobalCode
        )
            return 1;  // op arg1

        else if( opcode==notCode || opcode==oppCode ||
                opcode==litCode || opcode==copyCode || opcode==newCode ||
                opcode==toGlobalCode || opcode==fromGlobalCode

        )
            return 2;  // op arg1 arg2

        else if( opcode==addCode ||  opcode==subCode || opcode==multCode ||
                opcode==divCode ||  opcode==remCode || opcode==equalCode ||
                opcode==notEqualCode ||  opcode==lessCode ||
                opcode==lessEqualCode || opcode==andCode ||
                opcode==orCode || opcode==getCode || opcode==putCode
        )
            return 3;

        else
        {
            System.out.println("Fatal error: unknown opcode [" + opcode + "]" );
            System.exit(1);
            return -1;
        }

    }// numArgs

    private static void showMem( int a, int b )
    {
        System.out.println("-------------------------------");
        int index = a;

        while ( index <= b ) {

            // draw next group of indices
            for ( int k=index; k<index+10 && k<=b; k++ ) {
                System.out.printf("[%6d] ", k );
            }
            System.out.println();

            // draw next group of cells
            for ( int k=index; k<index+10 && k<=b; k++ ) {
                System.out.printf(" %6d  ", mem[k] );
            }
            System.out.println();
            System.out.println();

            index += 10;
        }

    }// showMem

}// Project3