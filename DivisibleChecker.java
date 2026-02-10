public class DivisibleChecker {

    public static void main(String[] args) {
        int i = 1;
        while (i <= 50) {
            // build a bitmask code: 1 for divisible by 2, 2 for by 3, 4 for by 5
            int code = (i % 2 == 0 ? 1 : 0) | (i % 3 == 0 ? 2 : 0) | (i % 5 == 0 ? 4 : 0);

            switch (code) {
                case 0:
                    // not divisible by 2, 3, or 5 — skip
                    break;
                case 1:
                    System.out.println(i + " i  s divisible by 2");
                    break;
                case 2:
                   System.out.println(i + " is divisible by 3");
                    break;
                case 3:
                    System.out.println(i + " is divisible by 2 and 3");
                    break;
                case 4:
                    System.out.println(i + " is divisible by 5");
                    break;
                case 5:
                    System.out.println(i + " is divisible by 2 and 5");
                    break;
                case 6:
                    System.out.println(i + " is divisible by 3 and 5");
                    break;
                case 7:
                    System.out.println(i + " is divisible by 2, 3 and 5");
                    break;
                default:
                    break;
            }

            i++;
        }
    }
}
