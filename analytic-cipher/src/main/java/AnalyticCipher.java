

public class AnalyticCipher {

    public static void main(String[] args) {
        String text = "Прямые каналы утечки данных, требуют непосредственного доступа к техническим средствам информационной системы и данным.";
        int[] intChars = text.chars().toArray();
        int[] resultChars = new int[text.length()];

        int[][] matr = new int[][] {{1,5,0}, //Key
                {2,3,1},
                {1,7,8}};

        for(int i=0; i< intChars.length; i++) {
            for(int j=0; j<3; j++) {
                resultChars[i] += matr[j][i%3] * intChars[i];
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
