public class Shuffle {
    public static void main (String[] args){

        String[] a = StdIn.readAll().split("\\s+");   
        int N = a.length;

        for (int i = 0; i < N; i++){
            int r = i + (int)(Math.random() * (N - i));
            String swap = a[r];
            a[r] = a[i];
            a[i] = swap;
        }

        for (int i = 0; i < N; i++){
            StdOut.println(a[i]);
        }
    }
}
