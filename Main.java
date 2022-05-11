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
     static Scanner scan; 
     static int heap_size;
     public static void main(String[] args)
    {   
    scan = new Scanner(System.in);
    int n, k;
    int[] A;
    
    System.out.println("Welcome");
    System.out.println("Enter the number of elements in the array");
    n = scan.nextInt();
    A = new int[n];
    heap_size = n;
    System.out.println("Enter the k value");
    k = scan.nextInt();
    System.out.println("do you want to choose numbers or let the program choose for you?");
    System.out.println("type 'me' if you want to choose or anything else if you want random");
    scan.nextLine(); //move the cursor to the next line
    String ans = scan.nextLine(); //scan next line
    if(ans.equals("me"))
        Get_Input(A, n);
    
        
    else 
        Random_Numbers(A,n);
    Print_Array(A);
    Print_k_Smallest_Using_Heap(A, k);
    }

    public static void Print_k_Smallest_Using_Heap(int[] A, int k)
    {
        Build_Min_Heap(A);
        System.out.println("\n"+k+" smallest elements are:");
        for(int i = 0; i<k; i++)
            System.out.println(Heap_Extract_Min(A));
        
    }

    public static void Get_Input(int[] A, int n) //user fill array
    {
            for(int i=0;i<n;i++)
            {
                System.out.println("Enter next number from 0 to 999");
                int num = scan.nextInt();
                while(num<0 || num>999) //if number not in bound
                {
                    System.out.println("error! number should be from 0 to 999 \n please choose different number");
                    num = scan.nextInt();
                }
                A[i] = num;
            }

    }

    public static void Random_Numbers(int[] A,int n) //fill array with random integers from 0 to 999
    {
        Random rand = new Random();
        for(int i=0;i<n;i++)
            A[i] = rand.nextInt(1000);
            
        
    }

    public static int Heap_Extract_Min(int[] A)//return min element of heap and reorganize to min-heap
    {
            int min = A[0];
            A[0] = A[heap_size-1];
            heap_size--;
            Min_Heapify(A, 0);
            return min;
    }

    private static void Min_Heapify(int[] A, int i)//organize the heap(array) to be min-heap 
    {
        int l = Left(i);
        int r = Right(i);
        int smallest;
        if(l<heap_size && A[l] < A[i])
            smallest = l;
        else smallest = i;
        if(r<heap_size && A[r] < A[smallest])
            smallest = r;
        if(smallest!=i)
        {
            Swap(A, i , smallest);
            Min_Heapify( A, smallest);
        }
    }

    private static void Build_Min_Heap(int[] A) //build min-heap from array 
    {
        for(int i= (A.length-1)/2; i>=0; i--)
        {
            Min_Heapify(A,i);
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

    private static void Swap(int[] A, int a, int b) //swap between two elements in array
    {
        int temp = A[a];
        A[a] = A[b];
        A[b] = temp;
    }

    private static void Print_Array(int[] A)
    {
        System.out.println("Your Array is:");
        for(int i = 0; i<A.length; i++)
            System.out.print(A[i]+" ");
    }

}
