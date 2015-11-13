package com.worksap.stm.SWARMS.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worksap.stm.SWARMS.dao.ProductDetailDao;
import com.worksap.stm.SWARMS.dao.TemplateMailDao;
import com.worksap.stm.SWARMS.dto.CustomerDto;
import com.worksap.stm.SWARMS.dto.ProductDetailDto;
import com.worksap.stm.SWARMS.dto.TemplatMailDto;
import com.worksap.stm.SWARMS.exception.ServiceException;
import com.worksap.stm.SWARMS.service.spec.EmailService;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	ProductDetailDao productDetailDao;
	
	@Autowired
	TemplateMailDao templateMailDao;
	
	@Override
	public List<CustomerDto> getListOfCustomerDtoForAvailableProduct(ProductDetailDto productDetailDto) throws ServiceException {
		
		return productDetailDao.getListOfCustomerDtoForAvailableProduct(productDetailDto);
	}
	
	public void mailing(List<CustomerDto> customerDtoList){
		final String username = "text2manish@gmail.com";
		final String password = "sifqsoqmhaxymuxy";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props,
							new javax.mail.Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(username, password);
							}		
		});
		
		try {
			TemplatMailDto templatMailDto;
			try {
				 templatMailDto = templateMailDao.getTemplate("product_availabilty");
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
			
			for(int i=0; i<customerDtoList.size(); i++){
				String emailId = customerDtoList.get(i).getEmailId();
				String name =  customerDtoList.get(i).getFirstName();
				System.out.println(emailId);
				if(emailId==null || emailId.equals(""))
					continue;
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("text2manish@gmail.com"));
				message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(emailId));
				message.setSubject(templatMailDto.getSubjectText());
				/*message.setText("<b>Dear Mail Crawler</b>,"
						
				+ "\n\n No spam to my email, please!");*/
				String content = "<b>Hello" + name +"</b>" ;
				message.setContent(templatMailDto.getBodyText(), "text/html; charset=utf-8");
				Transport.send(message);
				System.out.println("Done");
		}
			}
		catch (MessagingException e){
				throw new RuntimeException(e);
			}
	}
}