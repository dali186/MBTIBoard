package com.example.mbtiboard.board.service;

import com.example.mbtiboard.board.dto.BoardRequestDTO;
import com.example.mbtiboard.board.entity.Board;
import com.example.mbtiboard.board.dto.BoardResponseDTO;
import com.example.mbtiboard.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardRequestDTO boardRequestDTO) {
        return boardRepository.save(boardRequestDTO.toEntity()).getBoardNo();
    }

    @Transactional(readOnly = true)
    public HashMap<String, Object> findAll(Integer page, Integer size) {

        HashMap<String, Object> resultMap = new HashMap<String, Object>();

        Page<Board> list = boardRepository.findAll(PageRequest.of(page, size));

        resultMap.put("list", list.stream().map(BoardResponseDTO::new).collect(Collectors.toList()));
        resultMap.put("paging", list.getPageable());
        resultMap.put("totalCnt", list.getTotalElements());
        resultMap.put("totalPage", list.getTotalPages());

        return resultMap;
    }

    public BoardResponseDTO findByNo(Long no) {
        return new BoardResponseDTO(boardRepository.findById(no).get());
    }

    public int updateBoard(BoardRequestDTO boardRequestDTO) {
        return boardRepository.updateBoard(boardRequestDTO);
    }

    public int updateBoardViews(Long no) {
        return boardRepository.updateBoardViews(no);
    }

    public void deleteByNo(Long no) {
        boardRepository.deleteById(no);
    }
}
