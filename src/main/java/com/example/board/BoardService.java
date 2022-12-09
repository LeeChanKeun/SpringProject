package com.example.board;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardService {
    int insertBoard(BoardVO vo);
    int deleteBoard(int seq);
    int updateBoard(BoardVO vo);
    BoardVO getBoard(int seq);
    List<BoardVO> getBoardList();

}
