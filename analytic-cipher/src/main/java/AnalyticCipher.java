

public class AnalyticCipher {

    private static final String MESSAGE = "После приема подписанного сообщения получатель должен проверить, соответствует ли подпись сообщению.";
    private static final int[][] KEY = new int[][] {   {14,8,3}, //Key
                                                        {8,5,2},
                                                        {3,2,1}};

    public static void main(String[] args) {
        int[] intChars = MESSAGE.chars().toArray();
        int[] resultChars = new int[MESSAGE.length()];

        for(int i=0; i< intChars.length; i++) {
            for(int j=0; j<3; j++) {
                resultChars[i] += KEY[j][i%3] * intChars[i];
            }
        }

        for(int i : intChars) {
            System.out.print(i + " ");
        }

        System.out.println("");

        for(int i : resultChars) {
            System.out.print(i + " ");
        }
    }

}
