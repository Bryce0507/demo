package com.dingding.util;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.security.Security;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class SendMailUtil {
    private static final String ALIDM_SMTP_HOST = "smtp.qiye.aliyun.com";

    private static final String ALIDM_SMTP_PORT = "25";// 或80


    /**
     * @param sendAddress 发件人地址
     * @param sendPassword 发件人密码
     * @param host 协议
     * @param port 端口
     * @param subject 标题
     * @param content 内容
     * @param filePath 附件地址
     * @param CC 抄送人
     * @throws Exception
     * @throws AddressException
     */
    public static void sendMail(String sendAddress,String sendPassword,String host,String port,String subject,String content,String internetAddress,String filePath,String CC) throws AddressException, Exception {
        //设置SSL连接、邮件环境
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", host);
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", port);//设置端口
        props.setProperty("mail.debug", "true");//启用调试
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtp.auth", "true");
        //建立邮件会话
        Session session = Session.getDefaultInstance(props, new Authenticator() {   //身份认证
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sendAddress, sendPassword);//发件人账号、密码
            }
        });
        //建立邮件对象
        MimeMessage message = new MimeMessage(session);
        //设置邮件的发件人、收件人、主题
        //附带发件人名字
        //  message.setFrom(new InternetAddress("from_mail@qq.com", "optional-personal"));
        message.setFrom(new InternetAddress(sendAddress));//发件人账号
        message.setRecipients(Message.RecipientType.TO, internetAddress);//收件人账号

        //标题
        message.setSubject(subject);//邮件标题

        //内容
        Multipart multipart = new MimeMultipart();
        BodyPart contentPart = new MimeBodyPart();
        contentPart.setContent(content, "text/html;charset=utf-8");//邮件内容
        multipart.addBodyPart(contentPart);

        //附件部分
        if (StringUtils.isNotBlank(filePath)) {
            BodyPart attachPart = new MimeBodyPart();
            FileDataSource fileDataSource = new FileDataSource(filePath);//附件地址 D:/题库上传模板v1.xlsx
            attachPart.setDataHandler(new DataHandler(fileDataSource));
            attachPart.setFileName(MimeUtility.encodeText(fileDataSource.getName()));
            multipart.addBodyPart(attachPart);
        }

        message.setContent(multipart);

        //抄送地址
        if (StringUtils.isNotBlank(CC)) {
            InternetAddress[] internetAddressCC = new InternetAddress().parse(CC);
            message.setRecipients(Message.RecipientType.CC, internetAddressCC);
        }

        //发送邮件
        Transport.send(message);
    }
    public static void main(String[] args) {
        try {
            sendMail("lingrui-recovery@lr-amm.com", "Admin123", ALIDM_SMTP_HOST, ALIDM_SMTP_PORT, "subject test",
                    "<H1>哈哈哈1111</H1>", "834388682@qq.com", null, "13862258153@163.com,990724665@qq.com");
        } catch (Exception e) {
            System.out.println("出现异常");
        }
    }


}
