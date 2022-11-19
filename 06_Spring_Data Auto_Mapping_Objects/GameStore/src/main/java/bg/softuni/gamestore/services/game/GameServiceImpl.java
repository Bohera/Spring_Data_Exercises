package bg.softuni.gamestore.services.game;

import bg.softuni.gamestore.domain.dtos.GameDTO;
import bg.softuni.gamestore.domain.entities.Game;
import bg.softuni.gamestore.repositories.GameRepository;
import bg.softuni.gamestore.services.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class GameServiceImpl implements GameService {

    private final ModelMapper modelMapper = new ModelMapper();

    private final GameRepository gameRepository;

    private final UserService userService;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, UserService userService) {
        this.gameRepository = gameRepository;
        this.userService = userService;
    }

    @Override
    public String addGame(String[] args) {
        if(this.userService.getLoggedInUser() != null && this.userService.getLoggedInUser().getIsAdmin()) {

            final String title = args[1];

            final BigDecimal price = new BigDecimal(args[2]);

            final float size = Float.parseFloat(args[3]);

            final String trailerUrl = args[4];

            final String imageUrl = args[5];

            final String description = args[6];

            String str = args[7];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            final LocalDate releaseDate = LocalDate.parse(str, formatter);

            GameDTO gameDTO = new GameDTO(title, trailerUrl, imageUrl, size, price, description, releaseDate);

            Game gameToSave = this.modelMapper.map(gameDTO, Game.class);

            this.gameRepository.save(gameToSave);

            return "Added " + title;
        }

    return "Impossible command";

    }

    @Override
    public String editGame(String[] args) {
        return null;
    }

    @Override
    public String deleteGame(Long id) {
        return null;
    }


}
