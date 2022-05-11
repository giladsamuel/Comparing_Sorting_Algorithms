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
    int[] A, B; //two copy arrays to test solutions
    
    System.out.println("Welcome");
    System.out.println("Enter the number of elements in the array");
    n = scan.nextInt();
    A = B = new int[n];
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
    
    Copy_arrays(A,B);    
    Print_Array(A);
    
    Print_k_Smallest_Using_Heap(A, k);
    Print_k_Smallest_Using_Select( B, k); 
    }

    public static void Copy_arrays(int[] A, int[] B) //copy elements from A to B
    {
        for(int i =0;i<A.length;i++)
            B[i] = A[i];
    }

    public static void Print_k_Smallest_Using_Heap(int[] A, int k)
    {
        Build_Min_Heap(A);
        System.out.println("\n"+k+" smallest elements are:");
        for(int i = 0; i<k; i++)
            System.out.println(Heap_Extract_Min(A));
        
    }

    public static void Get_Input(int[] A,int n) //user fill array
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
    }

    private static int Randomized_Select(int[] B, int p,int r,int k) //return the 'k' smallest element
    {
        if(p==r)
            return A[p];
        int q = Randomized_Partition(A,p,r);
        int j = q - p + 1; //number of elements on the left side

        if(j==k) //the pivot value is the answer
            return A[q]; 

        else if(k<j)
             return Randomized_Select(A,p,q-1,k);

             else return Randomized_Select(A,q+1,r,k-j);
    }

    private static int Randomized_Partition(int B,int p,int r) //make prtition around pivot element in 'r' index
    {
        int i = Get_Random_Number(p , r +1);
        Swap(A,i,r);
        return Partition(A,p,r);
    }

    public static int Get_Random_Number(int min, int max) //return random number greater than or equal to 'min' and less than 'max'
    {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private static int Partition(int[] B, int p,int r)//make prtition sum-method of 'Randomized_Partition' method
    {
        int x = A[r];
        int i = p - 1;
        for(int j = p; j<=r-1; j++)
            {
                if(A[j]<=x)
                    i++;
                    Swap(A, i, j);
            }
        Swap(A, i+1, r);
        return i + 1;
    }

    private static void Quick_Sort(int[] B, int p,int r)
    {
        if(p < r) //check if there are at least two elements in virtual array
        {
            int q = Partition(A, p, r);
            Quick_Sort(A, p, q-1);
            Quick_Sort(A, q+1,r);
        }
    }

    Print_k_Smallest_Using_Select(int[] B, int k)
    {
        System.out.println("\nthe "+k+" smallest element is:");
        System.out.println(Randomized_Select(A, 0, A.length-1, k));
        Quick_Sort(A,0,k-1);

        for(int i = 0; i<k; i++)        //print sorted k smallest elements
            System.out.print(A[i]+" ");
        
    }
}
