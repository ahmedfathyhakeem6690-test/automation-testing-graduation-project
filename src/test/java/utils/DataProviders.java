package utils;

import org.testng.annotations.DataProvider;

import java.util.List;

public class DataProviders {

    private static final String DIR = "src/test/resources/testdata/";

    @DataProvider(name = "validLogin")
    public static Object[][] validLogin() {
        return toData(DIR + "valid-login.json");
    }

    @DataProvider(name = "invalidLogin")
    public static Object[][] invalidLogin() {
        return toData(DIR + "invalid-login.json");
    }

    @DataProvider(name = "emptyLogin")
    public static Object[][] emptyLogin() {
        return toData(DIR + "empty-login.json");
    }

    private static Object[][] toData(String path) {
        List<User> list = JsonUtils.readUsers(path);
        Object[][] data = new Object[list.size()][1];
        for (int i = 0; i < list.size(); i++) {
            data[i][0] = list.get(i);
        }
        return data;
    }
}
