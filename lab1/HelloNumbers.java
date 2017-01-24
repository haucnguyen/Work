public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        int index = 0;
        while (x < 10) {
            System.out.print(index + " ");
            x = x + 1;
            index += x;
        }
        System.out.println();
    }
}
