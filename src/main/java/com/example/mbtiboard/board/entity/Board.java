package com.example.mbtiboard.board.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter
@ToString
@NoArgsConstructor
public class Board extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_no")
    private Long boardNo;

    @Column(name = "board_title")
    private String boardTitle;

    @Column(name = "board_content")
    private String boardContent;

    @Column(name = "board_views")
    private int boardViews;

    @Column(name = "board_author")
    private String boardAuthor;

    @Builder
    public Board(Long boardNo, String boardTitle, String boardContent, int boardViews, String boardAuthor) {
        this.boardNo = boardNo;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardViews = boardViews;
        this.boardAuthor = boardAuthor;
    }
}
