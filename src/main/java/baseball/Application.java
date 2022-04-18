package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Application {

    private static final String INSERT_NUMBER = "숫자를 입력해주세요 : ";
    private static final String RESTART_QUESTION = "게임을 새로 시작하려면 1, 종료하려면 2을 입력하세요.";
    private static final String END_GAME1 = "3개의 숫자를 모두 맞히셨습니다! ";
    private static final String END_GAME2 = "게임 종료";
    private static final String STRIKE = "스트라이크";
    private static final String BALL = "볼";
    private static final String NOT_THING = "낫싱";
    private static final String SPACE = " ";

    public static void main(String[] args) {
        Application application = new Application();

        List<Integer> randomNumber = null;
        boolean exit = true;
        boolean newGame = true;

        while (exit) {
            if (newGame) {
                randomNumber = application.generateRandomNumber();
                newGame = false;
            }

            String inputNumbers = application.inputNumbers();
            application.validateInputNumber(inputNumbers);
            List<Integer> convertNumbers = application.convertInputNumber(inputNumbers);

            int strike = application.validateStrike(randomNumber, convertNumbers);
            int ball = application.validateBall(randomNumber, convertNumbers);
            application.printResult(strike, ball);

            if (strike == 3) {
                System.out.print(END_GAME1);
                System.out.println(END_GAME2);
                newGame = application.checkRestartGame();
                exit = newGame;
            }
        }
    }

    // 입력값 검증
    public void validateInputNumber(String inputNumber) {

        char[] numbers = inputNumber.toCharArray();

        if (numbers.length != 3) {
            throw new IllegalArgumentException();
        }

        Set<Character> inputNumberSet = new HashSet<>();

        for (char c : numbers) {
            if (c < 48 || c > 57) {
                throw new IllegalArgumentException();
            }

            inputNumberSet.add(c);
        }

        if (inputNumberSet.size() != 3) {
            throw new IllegalArgumentException();
        }
    }

    public List<Integer> convertInputNumber(String inputNumber) {
        List<Integer> convertNumbers = new ArrayList<>();
        for (char c : inputNumber.toCharArray()) {
            convertNumbers.add(Character.getNumericValue(c));
        }

        return convertNumbers;
    }

    // 난수 생성
    public List<Integer> generateRandomNumber() {
        Set<Integer> randomNumbers = new HashSet<>();
        while (randomNumbers.size() != 3) {
            randomNumbers.add(Randoms.pickNumberInRange(0, 9));
        }
        return new ArrayList<>(randomNumbers);
    }

    // Strike 확인
    public int validateStrike(List<Integer> randomNumber, List<Integer> inputNumber) {
        int strike = 0;
        for (int i = 0; i < randomNumber.size(); i++) {
            if (randomNumber.get(i).equals(inputNumber.get(i))) {
                strike++;
            }
        }

        return strike;
    }

    // Ball 확인
    public int validateBall(List<Integer> randomNumber, List<Integer> inputNumber) {
        int ball = 0;
        for (int i = 0; i < randomNumber.size(); i++) {
            if (randomNumber.contains(inputNumber.get(i))
                    && !randomNumber.get(i).equals(inputNumber.get(i))) {
                ball++;
            }
        }

        return ball;
    }

    // 결과 출력
    public void printResult(int strike, int ball) {
        boolean spaceCheck = false;

        if (ball > 0) {
            System.out.print(ball + BALL);
            spaceCheck = true;
        }

        if (strike > 0) {
            String space = spaceCheck ? SPACE : "";
            System.out.print(space + strike + STRIKE);
        }

        if (strike == 0 && ball == 0) {
            System.out.print(NOT_THING);
        }

        System.out.println();
    }

    // 다시 시작 여부 확인
    public boolean checkRestartGame() {
        System.out.println(RESTART_QUESTION);
        String input = Console.readLine();

        return "1".equals(input);
    }

    public String inputNumbers() {
        System.out.print(INSERT_NUMBER);
        return Console.readLine();
    }
}
