/**
 * This class represents an experiment, in which we test and compare between two solutions for the sorting and
 * finding 'k' smallest elements out of 'n' given elements problem.
 * 
 * @author Hen Golubenko
 * @author Gilad Samuel
 */

import java.util.Random;
import java.util.Scanner;

public class Main 
{
     final static int SIZE_ONE = 100;
     final static int SIZE_TWO = 200;
     final static int SIZE_THREE = 500;
     final static int SIZE_FOUR = 1000;  
     final static int K_ONE = 8;
     final static int K_TWO = 50;
     final static int K_THREE = 100;
     private static int CounterHe = 0;
     private static int CounterSe = 0;
     private static Scanner scan; 
     private static Random rand;
     private static int heap_size;
     public static void main(String[] args)
    {   
    scan = new Scanner(System.in);
    rand = new Random();
    int n, k;
    int[] Array, copyArray; //two copy arrays to test solutions
    int[] A,B,C,D;
    System.out.println("Welcome");
    System.out.println("Enter the number of elements in the array");
    n = scan.nextInt();
    Array = new int[n];
    copyArray = new int[n];
    heap_size = n;
    System.out.println("Enter the k value");
    k = scan.nextInt();
    System.out.println("do you want to choose numbers or let the program choose for you?");
    System.out.println("type 'me' if you want to choose or anything else if you want random");
    scan.nextLine(); //move the cursor to the next line
    String ans = scan.nextLine(); //scan next line
    if(ans.equals("me"))
        Get_Input(Array);
    
        
    else 
        Random_Numbers(Array);
    
    Copy_arrays(Array,copyArray);    
    Print_Array(Array);

    Print_k_Smallest_Using_Heap(Array, k);
    Print_k_Smallest_Using_Select(copyArray, k);
    
    System.out.println("-----------------------------------------------");
    A = new int[SIZE_ONE];
    B = new int[SIZE_TWO];
    C = new int[SIZE_THREE];
    D = new int[SIZE_FOUR];
    Random_Numbers(A);
    Random_Numbers(B);
    Random_Numbers(C);
    Random_Numbers(D);
    make_comparison(A);
    make_comparison(B);
    make_comparison(C);
    make_comparison(D);
   
    }
    
    public static void make_comparison(int[] array)
    {
        String result;
        initialize(array.length);
         System.out.println("n = "+array.length);
         System.out.println("---------------");
        System.out.println("----------------");
        Print_k_Smallest_Using_Heap(array, K_ONE);
        Print_k_Smallest_Using_Select(array, K_ONE);
        result = CounterHe < CounterSe ? "HEAP" : "SELECT";
        System.out.println(result+" made less comparisons for n = "+array.length+", k = "+K_ONE+"\n");
        initialize(array.length);
        Print_k_Smallest_Using_Heap(array, K_TWO);
        Print_k_Smallest_Using_Select(array, K_TWO);
        result = CounterHe < CounterSe ? "HEAP" : "SELECT";
        System.out.println(result+" made less comparisons for n = "+array.length+", k = "+K_TWO+"\n");
        initialize(array.length);
        Print_k_Smallest_Using_Heap(array, K_THREE);
        Print_k_Smallest_Using_Select(array, K_THREE);
        result = CounterHe < CounterSe ? "HEAP" : "SELECT";
        System.out.println("\n"+result+" made less comparisons for n = "+array.length+", k = "+K_THREE+"\n");
        initialize(array.length);
    }
    /*heap's method*/
    
    private static void Print_k_Smallest_Using_Heap(int[] A, int k)
    {
        
        System.out.println("using heap:\n");
        Build_Min_Heap(A);
        
        System.out.println(k+" smallest elements are:");
        for(int i = 0; i<k; i++)
            System.out.print(Heap_Extract_Min(A) + " ");
        
        System.out.println();    
        System.out.println("there were " + CounterHe +" comparisons");
    }

    private static int Heap_Extract_Min(int[] A)//return min element of heap and reorganize to min-heap
    {
            int min = A[0];
            A[0] = A[heap_size-1];
            heap_size--;
            Min_Heapify(A, 0);
            return min;
    }

    private static void Build_Min_Heap(int[] A) //build min-heap from array 
    {
        for(int i= (A.length-1)/2; i>=0; i--)
        {
            Min_Heapify(A,i);
        }
    }

    private static void Min_Heapify(int[] A, int i)//organize the heap(array) to be min-heap 
    {
        int l = Left(i);
        int r = Right(i);
        int smallest = i;
        if(l<heap_size-1)
        {
        CounterHe++;
         if(A[l] < A[i])
             smallest = l;
        }
        if(r<heap_size-1)
        {
            CounterHe++;
            if(A[r] < A[smallest])
                smallest = r;
        }
        if(smallest!=i)
        {
            Swap(A, i , smallest);
            Min_Heapify( A, smallest);
        }
    }

    private static int Parent(int i) //return parent
    {
        return (i-1)/2;
    }

    private static int Left(int i) //return left son
    {
        return 2*i+1;
    }

    private static int Right(int i) //return right son 
    {
        return 2*(i+1);
    }


    /*select's methods*/

    private static void Print_k_Smallest_Using_Select(int[] B, int k)
    {
        Randomized_Select(B, 0, B.length-1, k-1);
        System.out.println("\nusing select:\nthe "+k+" smallest element are:");
        
        Quick_Sort(B,0,k-2);
        
        for(int i = 0; i<k; i++)        //print sorted k smallest elements
            System.out.print(B[i]+" ");
        System.out.println();    
        System.out.println("there were " + CounterSe +" comparisons");
    }

    private static int Randomized_Select(int[] B, int p,int r,int k) //return the 'k' smallest element
    {
        if(p==r)
            return B[p];
        int q = Randomized_Partition(B,p,r);
        int j = q - p; //number of elements on the left side

        if(j==k) //the pivot value is the answer
            return B[q]; 

        else if(k<j)
             return Randomized_Select(B,p,q-1,k);

        else return Randomized_Select(B,q+1,r,k-j-1);
    }

    private static int Randomized_Partition(int[] B,int p,int r) //make prtition around pivot element in 'r' index
    {
        int i = rand.nextInt(r+1-p)+p;
        Swap(B,i,r);
        return Partition(B,p,r);
    }

    private static int Partition(int[] B, int p,int r)//make partition sub-method of 'Randomized_Partition' method
    {
        int x = B[r];
        int i = p - 1;
        for(int j = p; j<r; j++)
            {
            CounterSe++;
                if(B[j]<=x)
                {
                    i++;
                    Swap(B, i, j);
                }
            }
        Swap(B, i+1, r);
        return i + 1;
    }

    private static void Quick_Sort(int[] B, int p,int r)
    {
        if(p < r) //check if there are at least two elements in virtual array
        {
            int q = Randomized_Partition(B, p, r);
            Quick_Sort(B, p, q-1);
            Quick_Sort(B, q+1,r);
        }
    }

    /*general methods*/

    private static void Copy_arrays(int[] A, int[] B) //copy elements from A to B
    {
        for(int i =0;i<A.length;i++)
            B[i] = A[i];
    }

    private static void Get_Input(int[] A) //user fill array
    {
            System.out.println("Enter Your numbers, press ENTER after each number");            for(int i=0;i<A.length;i++)
            {
                System.out.println("Enter next integer");
                int num = scan.nextInt();
                A[i] = num;
            }

    }

    private static void Random_Numbers(int[] A) //fill array with random integers from 0 to 999
    {
        
        for(int i=0;i<A.length;i++)
            A[i] = rand.nextInt(1000);
            
        
    }
   
    private static void Swap(int[] A, int i, int j) //swap between two elements of array in i,j index  
    {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    private static void Print_Array(int[] A)
    {
        System.out.println("Your Array is:");
        for(int i = 0; i<A.length; i++)
            System.out.print(A[i]+" ");
            System.out.println();
        
    }

    private static void initialize(int size)
    {
        heap_size = size;
        CounterHe = 0;
        CounterSe = 0;
    }

}
