package com.example.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BoardDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public int insertBoard(com.example.board.BoardVO vo){
		String sql = "insert into BOARD (category,title, writer, content) values("
				+"'"+vo.getCategory()+"',"
				+"'"+vo.getTitle()+"',"
				+"'"+vo.getWriter()+"',"
				+"'"+vo.getContent()+"')";
		return jdbcTemplate.update(sql);
	}
	public int deleteBoard(int seq){
		String sql = "delete from BOARD where seq= " +seq;
		return jdbcTemplate.update(sql);
	}

	public int updateBoard(com.example.board.BoardVO vo){
		String sql = "update BOARD set title='"+vo.getTitle()+"', "
				+"category= '" + vo.getCategory()+"', "
				+"title= '" + vo.getTitle() +"', "
				+"writer= '" + vo.getWriter() +"', "
				+"content= '" + vo.getContent() +"' where seq="
				+ vo.getSeq();
		return jdbcTemplate.update(sql);
	}

	public com.example.board.BoardVO getBoard(int seq){
		String sql = "select *from BOARD where seq="+seq;
		return jdbcTemplate.queryForObject(sql, new BoardRowMapper());
	}
	public List<com.example.board.BoardVO> getBoardList(){
		String sql = "select * from BOARD order by regdate desc";
		return jdbcTemplate.query(sql,new BoardRowMapper());
	}
}

class BoardRowMapper implements RowMapper<com.example.board.BoardVO>{
	@Override
	public com.example.board.BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException{
		com.example.board.BoardVO vo = new com.example.board.BoardVO();
		vo.setSeq(rs.getInt("seq"));
		vo.setCategory(rs.getString("category"));
		vo.setTitle(rs.getString("title"));
		vo.setContent(rs.getString("content"));
		vo.setWriter(rs.getString("writer"));
		vo.setRegdate(rs.getDate("regdate"));
		return vo;
	}
}