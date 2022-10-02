package com.example.mbtiboard.board.dto;

import com.example.mbtiboard.board.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDTO {
    private Long boardNo;
    private String boardTitle;
    private String boardContent;
    private int boardViews;
    private String boardAuthor;
    private LocalDateTime registerTime;

    public BoardResponseDTO(Board board) {
        this.boardNo = board.getBoardNo();
        this.boardTitle = getBoardTitle();
        this.boardContent = getBoardContent();
        this.boardViews = getBoardViews();
        this.boardAuthor = getBoardAuthor();
        this.registerTime = board.getRegisterTime();
    }

    @Override
    public String toString() {
        return "BoardListDTO [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent +", boardViews=" + boardViews + ", boardAuthor=" + boardAuthor
                + ", registerTime=" + registerTime + "]";
    }
}
