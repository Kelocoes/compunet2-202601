package com.example.demo.mappers;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.example.demo.controller.rest.dto.CommentRequest;
import com.example.demo.controller.rest.dto.CommentResponse;
import com.example.demo.model.Comment;

@Mapper(componentModel = "spring", uses = { IGameMapper.class, IUserMapper.class })
public interface ICommentMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "game", ignore = true),
            @Mapping(target = "createdAt", expression = "java(getCurrentTimestamp())")
    })
    Comment commentRequestToComment(CommentRequest request);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "game", ignore = true),
            @Mapping(target = "createdAt", ignore = true)
    })
    Comment commentRequestToCommentForUpdate(CommentRequest request);

    @Named("commentToResponse")
    @Mappings({
            @Mapping(source = "user", target = "userId", qualifiedByName = "userToId"),
            @Mapping(source = "user", target = "username", qualifiedByName = "userToUsername"),
            @Mapping(source = "game.id", target = "gameId"),
            @Mapping(source = "game", target = "gameName", qualifiedByName = "gameToName")
    })
    CommentResponse commentToCommentResponse(Comment comment);

    @IterableMapping(qualifiedByName = "commentToResponse")
    List<CommentResponse> commentsToCommentResponses(List<Comment> comments);

    default Timestamp getCurrentTimestamp() {
        return Timestamp.valueOf(LocalDateTime.now());
    }
}