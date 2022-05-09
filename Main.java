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
   
    String ans = scan.nextLine();
    if(ans.equals("me"))
        Get_Input(A, n);
    
        
    else 
        Random_Numbers(A,n);
    
    print_k_smallest_using_heap(A);
    }
    public static void print_k_smallest_using_heap(int[] A)
    {
        Build_Min_Heap(A);
        for(int i = 0; i<k, i++)
            Heap_Extract_Min(A);
        
    }
    public static void Get_Input(int[] A, int n)
    {
            for(int i=0;i<n;i++)
            {
                System.out.println("Enter next number");
                int num = scan.nextInt();
                A[i] = num;
            }

    }

    public static void Random_Numbers(int[] A,int n)
    {
        Random rand = new Random();
        for(int i=0;i<n;i++)
            A[i] = rand.nextInt(1000);
            
        
    }

    public static int Heap_Extract_Min(int[] A)
    {
        
        
            int min = A[1];
            A[1] = A[heap_size-1];
            heap_size--;
            Min_Heapify(A, heap_size);
            return min;
        
        
    }
    private static void Min_Heapify(int[] A, int i)
    {
        int l = Left(i);
        int r = Right(i);
        int smallest;
        if(l<A.length && A[l] < A[i])
            smallest = l;
        else smallest = i;
        if(r<A.length && A[r] < A[smallest])
            smallest = r;
        if(smallest!=i)
        {
            Swap(A, A[i], A[smallest]);
            Min_Heapify( A, smallest);
        }
    }

    private static void Build_Min_Heap(int[] A)
    {
        for(int i= (A.length-1)/2; i>=0; i--)
        {
            Min_Heapify(A,i);
        }
    }

    private static int Parent(int i) ////return parent
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

}
