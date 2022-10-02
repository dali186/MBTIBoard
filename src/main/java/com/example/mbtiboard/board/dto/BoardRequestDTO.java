package com.example.mbtiboard.board.dto;

import com.example.mbtiboard.board.entity.Board;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
public class BoardRequestDTO {
    private Long boardNo;
    private String boardTitle;
    private String boardContent;
    private String boardAuthor;

    public Board toEntity() {
        return Board.builder()
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .boardAuthor(boardAuthor)
                .build();
    }
}
