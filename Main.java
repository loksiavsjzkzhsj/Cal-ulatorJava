import java.util.*;
import java.util.stream.Collectors;

public class Main {
     static boolean isRoman (String value){
        List<RomanNumeral> romanNumeralList = RomanNumeral.getRomanNumeralList();
        for (int i = 0; i < romanNumeralList.size(); i++){
            if (romanNumeralList.get(i).name().equalsIgnoreCase(String.valueOf(value.charAt(0)))){
                return true;
            }
        }
        return false;
    }

    enum RomanNumeral {
        C(100),
        XC(90),
        L(50),
        XL(40),
        X(10),
        IX(9),
        V(5),
        IV(4),
        I(1);

        int value;

        RomanNumeral(int value) {
            this.value = value;
        }

         int getValue() {
            return value;
        }

         static List<RomanNumeral> getRomanNumeralList() {
            return Arrays.stream(values())
                    .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                    .collect(Collectors.toList());
        }
    }

    static String signExpression (String expression){
         if (expression.contains("+")) return "+";
         else if(expression.contains("-")) return "-";
         else if (expression.contains("*")) return "*";
         else if (expression.contains("/")) return "/";
         else return null;
    }

        static int convertInArabian(String value) throws Exception {
            String romanNumeral = value.toUpperCase();
            List<RomanNumeral> romanNumeralsList = RomanNumeral.getRomanNumeralList();
            int i = 0;
            int result = 0;
            while (value.length() > 0 && i < romanNumeralsList.size()) {
                RomanNumeral symbol = romanNumeralsList.get(i);
                if (romanNumeral.startsWith(symbol.name())) {
                    result += symbol.getValue();
                    romanNumeral = romanNumeral.substring(symbol.name().length());
                } else
                    i++;
            }
            if (romanNumeral.length() > 0)
                throw new Exception("");

            return result;
        }

         static String convertInRoman (String value) throws Exception {
            if (Integer.parseInt(value) <=0 || Integer.parseInt(value) >= 4000){
                throw new Exception("Недопустимое значение для римского числа");
            }
            StringBuilder stringBuilder = new StringBuilder();
            List<RomanNumeral> romanNumeralList = RomanNumeral.getRomanNumeralList();
            int num = Integer.parseInt(value);
            int i = 0;
            while (num > 0 && i < romanNumeralList.size()){
                    if (romanNumeralList.get(i).getValue() <= num){
                        stringBuilder.append(romanNumeralList.get(i).name());
                        num -= romanNumeralList.get(i).getValue();
                    }
                    else
                        i++;
                }
                    return stringBuilder.toString();
        }



         static String calc (String input) throws Exception {
            String result;
            int number1;
            int number2;
            boolean isRoman;
            String[] strArray = input.split("[+\\-*/]");
            if (strArray.length != 2){
                throw new Exception("Должно быть два числа");
            }
            if (isRoman(strArray[0]) && isRoman(strArray[1])){
                number1 = convertInArabian(strArray[0]);
                number2 = convertInArabian(strArray[1]);
                isRoman = true;
            }
            else if (!isRoman(strArray[0]) && !isRoman(strArray[1])){
                number1 = Integer.parseInt(strArray[0]);
                number2 = Integer.parseInt(strArray[1]);
                isRoman = false;
            }
            else throw new Exception("Формат чисел должен совпадать друг с другом");

            if ((number1 <= 0 || number1 > 10) || (number2 <= 0 || number2 > 10)){
                throw new Exception("Допустимые значения чисел от 1 до 10");
            }
                switch (signExpression(input)) {
                    case "+":
                        result = String.valueOf(number1 + number2);
                        break;
                    case "-":
                        result = String.valueOf(number1 - number2);
                        break;
                    case "/":
                        result = String.valueOf(number1 / number2);
                        break;
                    case "*":
                        result = String.valueOf(number1 * number2);
                        break;
                    default:
                        throw new Exception("Неверная операция");
                }
            if (isRoman){
                if (Integer.parseInt(result) <= 0){
                    throw new Exception("Недопустимое значение для римского числа");
                }
                result = convertInRoman(result);
            }
            return result;
        }

        public static void main(String[] args) throws Exception {
            Scanner scanner = new Scanner(System.in);
            String console;
            while (!(console = scanner.nextLine()).equals("exit")) {
                System.out.println(calc(console));
            }
        }
}


