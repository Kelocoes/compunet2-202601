package com.example.demo.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.example.demo.controller.rest.dto.GameRequest;
import com.example.demo.controller.rest.dto.GameResponse;
import com.example.demo.model.Game;

@Mapper(componentModel = "spring", uses = { IUserMapper.class })
public interface IGameMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "comments", ignore = true)
    })
    Game gameRequestToGame(GameRequest request);

    @Mappings({
            @Mapping(source = "user.id", target = "userId"),
            @Mapping(source = "user.username", target = "username")
    })
    GameResponse gameToGameResponse(Game game);

    List<GameResponse> gamesToGameResponses(List<Game> games);

    @Named("gameToName")
    default String gameToName(Game game) {
        return game != null ? game.getName() : null;
    }
}