package com.worksap.stm.SWARMS.dao;

import java.io.IOException;
import java.util.List;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import com.worksap.stm.SWARMS.dto.TemplatMailDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TemplateMailDao {
	
	@Autowired
	private JdbcTemplate template;

	private static final String INSERT_TEMPLATE = "INSERT INTO template_mail "
			+ " (tag, subject, body)"
			+ " VALUES (?, ?, ?)";
	private static final String UPDATE_TEMPLATE = "UPDATE template_mail set subject = ?, body = ? where tag = ?";
	
	private static final String GET_TEMPLATE = "SELECT * FROM template_mail where tag =? ";
	
	private static final String GET_ALL_TEMPLATE = "SELECT * FROM template_mail";
	
	public void insertTemplate(TemplatMailDto templatMailDto ) throws IOException{
		try {
			template.update(INSERT_TEMPLATE, (ps) -> {
			ps.setString(1, templatMailDto.getTagText());
			ps.setString(2, templatMailDto.getSubjectText());
			ps.setString(3, templatMailDto.getBodyText());
			});
		}
		catch (DataAccessException e) {
			System.out.println("At TemplateMailDao :" +e);
			
			try {
					template.update(UPDATE_TEMPLATE, (ps) -> {
					ps.setString(1, templatMailDto.getSubjectText());
					ps.setString(2, templatMailDto.getBodyText());
					ps.setString(3, templatMailDto.getTagText());
				});
			}
			
			catch (DataAccessException e2) {
				
				System.out.println("At TemplateMailDao :" +e2);
			}
		
	}
		
}
		
	public TemplatMailDto getTemplate(String tag) throws IOException{
		try {
			return template.queryForObject(GET_TEMPLATE, (rs,column) -> {
				TemplatMailDto templatMailDto = new TemplatMailDto(); 
				templatMailDto.setTagText(rs.getString("tag"));
				templatMailDto.setSubjectText(rs.getString("subject"));
				templatMailDto.setBodyText(rs.getString("body"));
				return templatMailDto;
			}, tag);
		}
		catch (DataAccessException e) {
			System.out.println("At TemplateMailDao :" +e);
			return null;
		}
	}

	public List<TemplatMailDto> fetchAllTemplates() {
		// TODO Auto-generated method stub
		try {
			return template.query(GET_ALL_TEMPLATE, (rs,column) -> {
				TemplatMailDto templatMailDto = new TemplatMailDto(); 
				templatMailDto.setTagText(rs.getString("tag"));
				templatMailDto.setSubjectText(rs.getString("subject"));
				templatMailDto.setBodyText(rs.getString("body"));
				return templatMailDto;
			});
		}
		catch (DataAccessException e) {
			System.out.println("At TemplateMailDao :" +e);
			return null;
		}
		
	}
	
	
}
