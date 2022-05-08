import java.util.Random;
import java.util.Scanner;

public class Main 
{
     static Scanner scan = new Scanner(System.in);
     public static void main(String[] args)
    {
    int n, k;
    int[] A;
    
    System.out.println("Welcome");
    System.out.println("Enter the number of elements in the array");
    n = scan.nextInt();
    A = new int[n];
    System.out.println("Enter the k value");
    k = scan.nextInt();
    System.out.println("do you want to choose numbers or let the program choose for you?");
    System.out.println("type 'me' if you want to choose or 'pro' if you want random");
   
    String ans = scan.nextLine();
    if(ans.equals("me"))
        getInput(A, n);
    
        
    else //if(ans.equals("pro"))
        random_number(A,n);
    }

    public static void getInput(int[] A, int n)
    {
            for(int i=0;i<n;i++)
            {
                System.out.println("Enter next number");
                int num = scan.nextInt();
                A[i] = num;
            }

    }

    public static void random_number(int[] A,int n)
    {
        Random rand = new Random();
        for(int i=0;i<n;i++)
            A[i] = rand.nextInt(1000);
            
        
    }

    private static void heapify(int[] A, int i)
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
            then
        
    }

    private static int Parent(int i)
    {
        return i/2;
    }

    private static int Left(int i)
    {
        return 2*i;
    }

    private static int Right(int i)
    {
        return 2*i+1;
    }

    private static void swap(int[] A, int a, int b)
    {
        int temp = A[a];
        A[a] = A[b];
        A[b] = temp;
    }
}
