package br.com.rruffer.crawlers.application.crawlers.gmail;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

import org.apache.commons.lang.Validate;

import br.com.rruffer.crawlers.application.crawlers.Crawlable;

public class GmailCrawler implements Crawlable{

    @Override
    public void crawl(Map<String, Object> config) {
    	
        final Object user = config.get(Crawlable.USER_PARAM);
        final Object pass = config.get(Crawlable.PASSWORD_PARAM);

        //validar
        Validate.notNull(user, "Falta o parâmetro necessário: usuario = " + user);
        Validate.notNull(pass, "Falta o parâmetro necessário: password = " + pass);


        String usuario = (String) user;
        String password = (String) pass;
        
        if(!usuario.contains("@gmail.com")) {
        	usuario = usuario + "@gmail.com";
        }
        
        Properties props = new Properties();
        try {
        	
        	
            props.load(new FileInputStream(new File("src\\main\\resources\\gmail.properties")));

            Session session = Session.getDefaultInstance(props, null);

            Store store = session.getStore("imaps");
            store.connect("smtp.gmail.com", usuario, password);

            Folder inbox = store.getFolder("inbox");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages();
            
            System.out.println("Qtd. mensagens: " + messages.length);

            for (Message message : messages) {
                System.out.println("Tutulo: " + message.getSubject());
                System.out.println("Conteudo: " + message.getContent());
            }
            inbox.close(true);
            store.close();

        }catch (AuthenticationFailedException e) {
//        	https://stackoverflow.com/questions/25341198/javax-mail-authenticationfailedexception-is-thrown-while-sending-email-in-java
			System.out.println("Acesse o link abaixo para permitir o acesso da aplição:");
			System.out.println("https://myaccount.google.com/lesssecureapps?pli=1");
			e.printStackTrace();
		}catch (Exception e) {
            e.printStackTrace();
        }
    }
}