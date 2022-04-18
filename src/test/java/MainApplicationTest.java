import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class MainApplicationTest {
  @Test
  public static void main(String[] args) {}

  @Test
  public void validateInputNumberTest() {
    MainApplication mainApplication = new MainApplication();

    char[] successChars = {'1', '2', '3'};
    char[] exceptionChars = {'1', '2', 'a'};
    char[] exceptionChars2 = {'1', '2'};

    assertThat(mainApplication.validateInputNumber(successChars)).isEqualTo(true);
    assertThatIllegalArgumentException().isThrownBy(
            () -> mainApplication.validateInputNumber(exceptionChars));
    assertThatIllegalArgumentException().isThrownBy(
            () -> mainApplication.validateInputNumber(exceptionChars2));
  }

  @Test
  public void convertToListTest() {
    MainApplication mainApplication = new MainApplication();
    char[] test1 = {'1', '2', '3'};
    char[] test2 = {'2', '3', '4'};

    assertThat(mainApplication.convertToList(test1)).hasSize(3).contains('1', '2', '3');
    assertThat(mainApplication.convertToList(test2)).hasSize(3).contains('2', '3', '4');
  }

  @Test
  public void generateRandomNumberTest() {
    MainApplication mainApplication = new MainApplication();
    assertThat(mainApplication.generateRandomNumber(3)).hasSize(3).doesNotHaveDuplicates();
  }

  @Test
  public void validateStrikeTest() {
    List<Character> testRandomNumber1 = new LinkedList<>(Arrays.asList('1', '2', '3'));
    List<Character> testInputNumber1 = new LinkedList<>(Arrays.asList('1', '2', '3'));
    List<Character> testRandomNumber2 = new LinkedList<>(Arrays.asList('1', '2', '3'));
    List<Character> testInputNumber2 = new LinkedList<>(Arrays.asList('1', '2', '4'));
    List<Character> testRandomNumber3 = new LinkedList<>(Arrays.asList('1', '2', '3'));
    List<Character> testInputNumber3 = new LinkedList<>(Arrays.asList('4', '1', '2'));

    MainApplication mainApplication = new MainApplication();
    assertThat(mainApplication.validateStrike(testRandomNumber1, testInputNumber1)).isEqualTo(3);
    assertThat(mainApplication.validateStrike(testRandomNumber2, testInputNumber2)).isEqualTo(2);
    assertThat(mainApplication.validateStrike(testRandomNumber3, testInputNumber3)).isEqualTo(0);
  }

  @Test
  public void validateBallTest() {
    List<Character> testRandomNumber1 = new LinkedList<>(Arrays.asList('1', '2', '3'));
    List<Character> testInputNumber1 = new LinkedList<>(Arrays.asList('2', '3', '1'));
    List<Character> testRandomNumber2 = new LinkedList<>(Arrays.asList('1', '2', '3'));
    List<Character> testInputNumber2 = new LinkedList<>(Arrays.asList('4', '5', '6'));
    List<Character> testRandomNumber3 = new LinkedList<>(Arrays.asList('1', '2', '3'));
    List<Character> testInputNumber3 = new LinkedList<>(Arrays.asList('5', '1', '7'));
    List<Character> testRandomNumber4 = new LinkedList<>(Arrays.asList('1', '2', '3'));
    List<Character> testInputNumber4 = new LinkedList<>(Arrays.asList('1', '2', '3'));

    MainApplication mainApplication = new MainApplication();
    assertThat(mainApplication.validateBall(testRandomNumber1, testInputNumber1)).isEqualTo(3);
    assertThat(mainApplication.validateBall(testRandomNumber2, testInputNumber2)).isEqualTo(0);
    assertThat(mainApplication.validateBall(testRandomNumber3, testInputNumber3)).isEqualTo(1);
    assertThat(mainApplication.validateBall(testRandomNumber4, testInputNumber4)).isEqualTo(3);
  }
}
