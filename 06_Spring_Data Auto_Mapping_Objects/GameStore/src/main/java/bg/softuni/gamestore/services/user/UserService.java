package bg.softuni.gamestore.services.user;

import bg.softuni.gamestore.domain.entities.User;

public interface UserService {
    String registerUser(String[] args);
    String loginUser(String[] args);

    String logoutUser();

    String addGame(String[] args);

    User getLoggedInUser();
}
