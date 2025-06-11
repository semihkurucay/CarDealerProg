/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealer;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author semih
 */
public class mail {
    sl_Mail sMail = new sl_Mail();
    sl_Users sUser = new sl_Users();
    
    String mailAddress, mailPassword, mailPort, mailHost;
    boolean mailAuth = false, mailStarttls = false;
    
    public void sendMail(String userID, String processID, String processType, String vin, String carType){
        cl_Users user = sUser.getUserInfo(userID);
        if(!user.getUserMail().equals("")){
            String[] setting = sMail.getMailSetting();
            mailAddress = setting[0]; 
            mailPassword = setting[1]; 
            mailPort = setting[2]; 
            mailHost = setting[3];
        
            if(!mailAddress.equals("") && !mailPassword.equals("") && !mailPort.equals("") && !mailHost.equals("")){
                if(setting[4].equals("true")){
                    mailAuth = true;
                }
                if(setting[5].equals("true")){
                    mailStarttls = true;
                }
        
                String[] mailMessage = sMail.getMailMessage();
            
                Properties prep = new Properties();
                prep.put("mail.smtp.auth", mailAuth);
                prep.put("mail.smtp.starttls.enable", mailStarttls);
                prep.put("mail.smtp.host", mailHost);
                prep.put("mail.smtp.port", mailPort);
        
                Session session = Session.getInstance(prep, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(mailAddress,mailPassword);
                }
                });
        
                try{
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(mailAddress));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getUserMail()));
                    message.setSubject("Bir Araba İşlemi Gerçekleştirdiniz - " + mailMessage[0]);
                    message.setText("Sayın " + user.getUserName_Surname() + ","
                        + "\n\n" + mailMessage[1]
                        + "\n\n--------------------------------------------"
                        + "\nİşlem Numarası : " + processID
                        + "\nİşlem Türü : " + processType
                        + "\nŞase Numarası : " + vin
                        + "\nAraç Tipi - Plaka: " + carType
                        + "\n--------------------------------------------"
                        + "\n\n" + mailMessage[2]);
                
                    Transport.send(message);
                }catch(MessagingException e){
                    JOptionPane.showMessageDialog(null, "Mail gönderilemedi, gönderim sırasında hata ile karşılaşıldı", "Mail Gönderilemedi", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public void sendTestMail(String toMail){
            String[] setting = sMail.getMailSetting();
            mailAddress = setting[0]; 
            mailPassword = setting[1]; 
            mailPort = setting[2]; 
            mailHost = setting[3];
        
            if(!mailAddress.equals("") && !mailPassword.equals("") && !mailPort.equals("") && !mailHost.equals("")){
                if(setting[4].equals("true")){
                    mailAuth = true;
                }
                if(setting[5].equals("true")){
                    mailStarttls = true;
                }
        
                String[] mailMessage = sMail.getMailMessage();
            
                Properties prep = new Properties();
                prep.put("mail.smtp.auth", mailAuth);
                prep.put("mail.smtp.starttls.enable", mailStarttls);
                prep.put("mail.smtp.host", mailHost);
                prep.put("mail.smtp.port", mailPort);
        
                Session session = Session.getInstance(prep, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(mailAddress,mailPassword);
                }
                });
        
                try{
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(mailAddress));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMail));
                    message.setSubject("Test Mail");
                    message.setText("Test Mail");
                
                    Transport.send(message);
                }catch(MessagingException e){
                    JOptionPane.showMessageDialog(null, "Mail gönderilemedi, gönderim sırasında hata ile karşılaşıldı", "Mail Gönderilemedi", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

