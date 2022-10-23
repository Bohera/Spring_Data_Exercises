package dbAppsIntroductionExercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class PrintAllMinionNames {
    private static final String COUNT_ALL_MINIONS = "SELECT COUNT(name) AS minion_count FROM minions";
    private static final String GET_MINION_BY_ID = "SELECT m.name FROM minions AS m WHERE id = ?";

    private static final String COLUMN_LABEL_MINION_COUNT = "minion_count";

    public static void main(String[] args) throws SQLException {

        final Connection connection = Utils.getSQLConnection();

        final PreparedStatement selectAllMinions = connection.prepareStatement(COUNT_ALL_MINIONS);
        final ResultSet countAllMinionsSet = selectAllMinions.executeQuery();
        countAllMinionsSet.next();

        int countAllMinions = countAllMinionsSet.getInt(COLUMN_LABEL_MINION_COUNT);

        ArrayList<String> orderedNamesList = new ArrayList<>();

        for (int i = 1; i <= countAllMinions / 2; i++) {

            addNextNameToList(connection, orderedNamesList, i);
            addNextNameToList(connection, orderedNamesList, (countAllMinions + 1 - i));

        }

        System.out.println(orderedNamesList.stream().collect(Collectors.joining(System.lineSeparator())));
        connection.close();
    }

    private static void addNextNameToList(Connection connection, ArrayList<String> orderedNamesList, int i) throws SQLException {
        final PreparedStatement selectMinion = connection.prepareStatement(GET_MINION_BY_ID);
        selectMinion.setInt(1, i);

        final ResultSet selectMinionSet = selectMinion.executeQuery();
        selectMinionSet.next();

        final String name = selectMinionSet.getString(Constants.COLUM_LABEL_NAME);

        orderedNamesList.add(name);
    }
}
