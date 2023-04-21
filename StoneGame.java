package tangenkadai1;

import java.util.Scanner;

public class StoneGame {
    public static void main(String[] args) {
        //ゲーム開始処理
        initGame();
    }

    //ゲームの初期設定
    public static void initGame() {
        String[] text = {"石の総数を指定してください。(10~100個以内)", "１度に取れる石の数を指定してください。(1~10個以内)",
                        "プレイヤーを指定してください。(2~26文字以内)", "石の記号を指定してください。"};

        System.out.println("------------------------------");
        System.out.println("石取りゲームを開始します。");

        System.out.println("石の総数を指定してください。(10~100個以内)");
        int sum = Integer.parseInt(checkValue(1, text));

        System.out.println("１度に取れる石の数を指定してください。(1~10個以内)");
        int max = Integer.parseInt(checkValue(2, text));

        System.out.println("プレイヤーを指定してください。(2~26文字以内)");
        String name = checkValue(3, text);
        String[] players = name.split(",");

        System.out.println("石の記号を指定してください。");
        String symbol = checkValue(4, text);

        System.out.println("石の総数：" + sum);
        System.out.println("１度にとれる石の数：" + max);
        System.out.print("参加プレイヤー");
        for (String player : players) {
            System.out.print(player + " ");
        }
        System.out.println();
        System.out.println("石の記号：" + symbol);

        System.out.println("------------------------------");

        outputStone(sum, symbol);

        //ゲームメイン処理へ
        stoneGame(sum, max, players, symbol);
    }

    //初期値を入力させる処理(入力値が正しいかどうか制御付き)
    public static String checkValue(int num, String[] text) {
        Scanner sc = new Scanner(System.in);
        String value = sc.nextLine();

        //石の総数チェック
        if (num == 1) {
            if (!(Integer.parseInt(value) >= 10 && Integer.parseInt(value) <= 100)) {
                System.out.println("入力値が違います。");
                System.out.println(text[num - 1]);
                return checkValue(num, text);
            }
        } else if (num == 2) {
            if (!(Integer.parseInt(value) >= 1 && Integer.parseInt(value) <= 10)) {
                System.out.println("入力値が違います。");
                System.out.println(text[num - 1]);
                return checkValue(num, text);
            }
        } else if (num == 3) {
            if (!(value.length() >= 2 && value.length() <= 26)) {
                System.out.println("入力値が違います。");
                System.out.println(text[num - 1]);
                return checkValue(num, text);
            }
        } else if (num == 4) {
            if (value.length() != 1) {
                System.out.println("入力値が違います。");
                System.out.println(text[num - 1]);
                return checkValue(num, text);
            }
        }
        return value;
    }

    //石取ゲームメイン機能
    public static void stoneGame(int sum, int max, String[] players, String symbol) {
        int index = 0;
        //勝敗が決まるまで、ループを繰り返す
        while (true) {
            //プレイヤー配列の末尾に来たら、次は先頭の要素を指定するための処理
            index = index % players.length;
            System.out.println(players[index] + "の番");
            sum = cutStone(sum, max);
            if (sum <= 0 && players.length == 2) {
                index++;
                index = index % players.length;
                System.out.println("勝者：" + players[index]);
                break;
            } else if (sum <= 0 && players.length > 2) {
                String loser = players[index];
                index--;
                //先頭の人が負けた場合、一番最後の人を勝者にするための処理
                if (index == -1) {
                    index = players.length - 1;
                }
                System.out.println("勝者：" + players[index]);
                System.out.println("敗者：" + loser);
                break;
            }
            outputStone(sum, symbol);
            index++;
        }
    }

    //石取り処理
    public static int cutStone(int sum, int max) {
        System.out.println("石の数を入力してください。（1~" + max + "まで入力可能です。）");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        //石の数が1～3かどうかを確認
        if (num >= 1 && num <= max) {
            return sum - num;
        } else {
            return cutStone(sum, max);
        }
    }

    //石の数を出力する処理
    public static void outputStone(int sum, String symbol) {
        System.out.println("残り：" + sum + "個");
        System.out.println(symbol.repeat(sum));
        System.out.println("------------------------------");
    }
}