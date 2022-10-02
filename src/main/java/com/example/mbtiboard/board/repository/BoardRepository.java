package com.example.mbtiboard.board.repository;

import com.example.mbtiboard.board.dto.BoardRequestDTO;
import com.example.mbtiboard.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    static final String UPDATE_BOARD = "UPDATE Board " +
            "SET BOARD_TITLE = :#{#boardRequestDTO.boardTitle}, " +
            "BOARD_CONTENT = :#{#boardRequestDTO.boardContent}, " +
            "UPDATE_TIME = NOW() " +
            "WHERE ID = :#{#boardRequestDTO.boardNo}";

    static final String UPDATE_BOARD_VIEWS = "UPDATE Board "
            + "SET BOARD_VIEWS = BOARD_VIEWS + 1"
            + "WHERE ID = :no";

    @Transactional
    @Modifying
    @Query(value = UPDATE_BOARD, nativeQuery = true)
    public int updateBoard(@Param("boardRequestDTO")BoardRequestDTO boardRequestDTO);

    @Transactional
    @Modifying
    @Query(value = UPDATE_BOARD_VIEWS, nativeQuery = true)
    public int updateBoardViews(@Param("no") Long no);
}
