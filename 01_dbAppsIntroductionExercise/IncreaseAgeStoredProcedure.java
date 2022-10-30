package dbAppsIntroductionExercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class IncreaseAgeStoredProcedure {

    private static final String INCREASE_MINION_AGE_BY_ONE_FIND_BY_ID= "call minions_db.usp_get_older(?)";
    private static final String SELECT_MINION_THAT_GOT_OLD= "SELECT m.name, m.age FROM minions AS m WHERE m.id = ?";

    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getSQLConnection();

        final int minionId = new Scanner(System.in).nextInt();

        final PreparedStatement makeMinionOlder = connection.prepareStatement(INCREASE_MINION_AGE_BY_ONE_FIND_BY_ID);
        makeMinionOlder.setInt(1, minionId);
        makeMinionOlder.executeUpdate();

        final PreparedStatement getOlderMinionNameAndAge = connection.prepareStatement(SELECT_MINION_THAT_GOT_OLD);
        getOlderMinionNameAndAge.setInt(1, minionId);
        final ResultSet resultSet = getOlderMinionNameAndAge.executeQuery();
        resultSet.next();

        System.out.printf("%s %d", resultSet.getString("name"), resultSet.getInt("age"));

        connection.close();
    }
}
