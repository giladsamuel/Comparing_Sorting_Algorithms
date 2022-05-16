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
     private static int CounterHe = 0; //count number of comparisons between elements on HeapSort (solution A)
     private static int CounterSe = 0; //count number of comparisons between elements on SelectSort (solution B)
     private static Scanner scan; 
     private static Random rand;
     private static int heap_size;
     public static void main(String[] args)
    {   
    scan = new Scanner(System.in);
    rand = new Random();
    int n, k;
    int[] Array, copyArray; //two copy arrays to test solutions
    
    System.out.println("Welcome\nEnter the number of elements in the array");
    
    n = scan.nextInt();
    Array = new int[n];
    copyArray = new int[n];
    heap_size = n;
    System.out.println("Enter the k value");
    k = scan.nextInt();
    System.out.println("do you want to choose numbers or let the program choose for you?\n"
                           + "type 'me' if you want to choose or anything else if you want random");
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
    
    System.out.println("-----------------------------------------------"
                             +"\nstarting second paragraph\n");
    
    //initialize arrays and there copies for testing

    int [] A = new int[SIZE_ONE];
    int [] B = new int[SIZE_TWO];
    int [] C = new int[SIZE_THREE];
    int [] D = new int[SIZE_FOUR];

    int [] copyA1 = new int[SIZE_ONE];
    int [] copyB1 = new int[SIZE_TWO];
    int [] copyC1 = new int[SIZE_THREE];
    int [] copyD1 = new int[SIZE_FOUR];
    
    int [] copyA2 = new int[SIZE_ONE];
    int [] copyB2 = new int[SIZE_TWO];
    int [] copyC2 = new int[SIZE_THREE];
    int [] copyD2 = new int[SIZE_FOUR];

    Random_Numbers(A);
    Random_Numbers(B);
    Random_Numbers(C);
    Random_Numbers(D);
    
    //test four different 'n' size
    make_comparison(A,copyA1,copyA2);
    make_comparison(B,copyB1,copyB2);
    make_comparison(C,copyC1,copyC2);
    make_comparison(D,copyD1,copyD2);
   
    }

    /*heap's method*/
    
    private static void Print_k_Smallest_Using_Heap(int[] A, int k) //like HeapSort algorithm on page 113 of the textbook
    {
        
        System.out.println("\nusing heap:");
        Build_Min_Heap(A);
        
        System.out.println(k+" smallest elements are:");
        for(int i = 0; i<k; i++)
            System.out.print(Heap_Extract_Min(A) + " ");
            
        System.out.println("\n" + "there were " + CounterHe +" comparisons");
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
        if(l<heap_size)
        {
            CounterHe++;
            if(A[l] < A[i])
                smallest = l;
        }
        if(r<heap_size)
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

    private static int Left(int i) //return left son
    {
        return 2*i+1;
    }

    private static int Right(int i) //return right son 
    {
        return 2*(i+1);
    }

    /*select's methods*/

    private static void Print_k_Smallest_Using_Select(int[] B, int k) //finds k's smallest element,and QuickSort all k smallest elements 
    {
        Randomized_Select(B, 0, B.length-1, k-1);
        System.out.println("\nusing select:\n"+k+" smallest element are:");
        
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

    private static int Partition(int[] B, int p,int r) //make partition sub-method of 'Randomized_Partition' method
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

    private static void Quick_Sort(int[] B, int p,int r) ////like QuickSort algorithm on page 122 of the textbook
    {
        if(p < r) //check if there are at least two elements in virtual array
        {
            int q = Partition(B, p, r);
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

    private static void Print_Array(int[] A) //prints all array elemnts from the smallest index to the top
    {
        System.out.println("Your Array is:");
        for(int i = 0; i<A.length; i++)
            System.out.print(A[i]+" ");
            System.out.println();
        
    }

    private static void initialize(int[] array,int[] copy1,int[] copy2) //return copyArrys to original state and calibrate counters and heap_size
    {
        Copy_arrays(array,copy1);
        Copy_arrays(array,copy2);
    
        heap_size = array.length;
        CounterHe = 0;
        CounterSe = 0;
    }

    public static void make_comparison(int[] array,int[] copy1,int[] copy2) //makes comparison on arrays for second paragraph
    {
        String result;
        System.out.println("n = "+array.length
                         + "\n----------------"
                         + "\n----------------");

        //testing K_ONE
        initialize( array, copy1, copy2); 
        Print_k_Smallest_Using_Heap(copy1, K_ONE);
        Print_k_Smallest_Using_Select(copy2, K_ONE);
        result = CounterHe < CounterSe ? "\nHEAP" : "\nSELECT";
        System.out.println(result+" made less comparisons for n = "+array.length+", k = "+K_ONE+"\n");

        //testing K_TWO
        initialize( array, copy1, copy2);
        Print_k_Smallest_Using_Heap(copy1, K_TWO);
        Print_k_Smallest_Using_Select(copy2, K_TWO);
        result = CounterHe < CounterSe ? "\nHEAP" : "\nSELECT";
        System.out.println(result+" made less comparisons for n = "+array.length+", k = "+K_TWO+"\n");

        //testing K_THREE
        initialize( array, copy1, copy2);
        Print_k_Smallest_Using_Heap(copy1, K_THREE);
        Print_k_Smallest_Using_Select(copy2, K_THREE);
        result = CounterHe < CounterSe ? "\nHEAP" : "\nSELECT";
        System.out.println("\n"+result+" made less comparisons for n = "+array.length+", k = "+K_THREE+"\n");
    }

}
