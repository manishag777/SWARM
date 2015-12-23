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
	
	@Override
	public void mailing(List<CustomerDto> customerDtoList, ProductDetailDto productDetailDto){
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
				String content =  convertTemplateMail(templatMailDto.getBodyText(), customerDtoList.get(i), productDetailDto);
				message.setContent(content, "text/html; charset=utf-8");
				Transport.send(message);
				System.out.println("Done");
		}
			}
		catch (MessagingException e){
				throw new RuntimeException(e);
			}
	}
	
	public void mailingForReffering(String subject, String body, String emailId){
		commonMailService(subject,body,emailId);
	}
	
	private String convertTemplateMail(String mailText, CustomerDto customerDto, ProductDetailDto productDetailDto){
		
		String cust_name = customerDto.getFirstName();
		if(customerDto.getLastName()!=null)
			cust_name += customerDto.getLastName();
		String pdt_name = "productName";
		String color = productDetailDto.getColor();
		String size = productDetailDto.getSize();
		String price = "20";
		System.out.println(mailText.replaceAll("cust_name", cust_name));
		mailText = mailText.replaceAll("%cust_name", cust_name);
		mailText = mailText.replaceAll("%pdt_name", pdt_name);
		mailText = mailText.replaceAll("%color", color);
		mailText = mailText.replaceAll("%size", size);
		mailText = mailText.replaceAll("%price", price);
		System.out.println(mailText);
		return mailText;
	}
	
	
	
	
	private void commonMailService(String subject, String body, String emailId){
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
			
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("text2manish@gmail.com"));
				message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(emailId));
				message.setSubject(subject);
				message.setContent(body, "text/html; charset=utf-8");
				Transport.send(message);
				System.out.println("Done");
		}
		
		catch (MessagingException e){
				throw new RuntimeException(e);
			}

	}
	
	
	

}