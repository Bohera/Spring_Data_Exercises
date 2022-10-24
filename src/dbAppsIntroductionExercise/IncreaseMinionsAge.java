package dbAppsIntroductionExercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class IncreaseMinionsAge {

    final private static String UPDATE_MINIONS_NAME_TO_LOWERCASE = "UPDATE minions AS m SET m.name = LOWER(m.name) WHERE m.id = ?";
    final private static String UPDATE_MINIONS_AGE_PLUS_ONE = "UPDATE minions AS m SET m.age = m.age + 1 WHERE m.id = ?";
    final private static String GET_ALL_MINIONS_WITH_AGE = "SELECT m.name, m.age FROM minions AS m";



    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getSQLConnection();

        int[] minionsID = Arrays.stream(new Scanner(System.in).nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        final PreparedStatement minionsNamesToLowercase = connection.prepareStatement(UPDATE_MINIONS_NAME_TO_LOWERCASE);
        final PreparedStatement minionsAgePlusOne = connection.prepareStatement(UPDATE_MINIONS_AGE_PLUS_ONE);

        connection.setAutoCommit(false);

        for (int index = 0; index < minionsID.length; index++) {
            connection.setAutoCommit(false);
            minionsNamesToLowercase.setInt(1, minionsID[index]);
            minionsNamesToLowercase.executeUpdate();
            minionsAgePlusOne.setInt(1, minionsID[index]);
            minionsAgePlusOne.executeUpdate();
        }

        connection.commit();

        final PreparedStatement allMinionsWithAge = connection.prepareStatement(GET_ALL_MINIONS_WITH_AGE);
        final ResultSet resultSet = allMinionsWithAge.executeQuery();

        while (resultSet.next()) {
            System.out.printf("%s %d%n", resultSet.getString("name"), resultSet.getInt("age"));
        }

        connection.close();
    }
}
