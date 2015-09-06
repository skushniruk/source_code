


public class PhoneNumberValidator {

    public static boolean checkTelNumber(String telNumber) {
        char[] arr = telNumber.toCharArray();
        int countdig = 0;
        int count = 0;
        int dist = 5;
        for (int i = 0;  i < arr.length; i++)
        {
            if (arr[i] == 45)
            {
                if (dist == 1)
                    return false;
                count++;
                dist = 0;
            }
            dist++;
            if (arr[i] >= 48 && arr[i] <= 57)
                countdig++;
        }
        if (count > 2)
            return false;
        if ((arr[0] == 43 && countdig != 12) || (arr[0] != 43 && countdig != 10)){
            return false;
        }


        if (telNumber.matches("^[+][0-9--]+[0-9]") || telNumber.matches("^[+][0-9]+[(]\\d{3}[)][0-9--]+[0-9]") || telNumber.matches("^[0-9]+[(]\\d{3}[)][0-9--]+[0-9]") ||
                telNumber.matches("^[0-9--]+[0-9]") || telNumber.matches("^[(]\\d{3}[)][0-9--]+[0-9]"))
            return true;
        return false;
    }

    public static void main(String[] args)
    {
        System.out.println(checkTelNumber("(050)1834567"));

    }
}
