package com.example.mtm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jcraft.jsch.JSchException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {
    ConnectionHandler ch = new ConnectionHandler();
    Connection con;

    @GetMapping("/logon")
    public String logon(Model model) {
        model.addAttribute("credentials", new Credentials());
        return "logon";
    }

    @PostMapping("/logon")
    public String logonSubmit(@ModelAttribute Credentials credentials, Model model) {

        User user = null;
        ZUser zuser = null;
        try {
            con = ConnectionHandler.connect(credentials.getUsername(), credentials.getPassword(), "192.86.32.153", 22);
            user = getUSSUserDetails(ch, con);
            zuser = getzOSDetails(ch, con, credentials.getUsername());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        model.addAttribute("user", user);
        model.addAttribute("zuser", zuser);
        return "userdetails";
    }

    private ZUser getzOSDetails(ConnectionHandler ch2, Connection con2, String zusername) {
        String response = "";
        ZUser zuser = null;
        try {
            String x = ch2.send(con2, "cat /z/" + zusername.toLowerCase() + "/racflstu.jcl | submit");
            response = ch2.send(con2, "cat /z/" + zusername.toLowerCase() + "/test.out");

            Pattern p = Pattern.compile(
                    "OWNER=([\\w\\.]*)[\\W\\w]*CREATED=([\\w\\.]*)[\\W\\w]*PASSDATE=([\\w\\.]*)[\\W\\w]*PASS-INTERVAL=([\\w\\.]*)[\\W\\w]*LAST-ACCESS=([\\w\\.\\/\\:]*)[\\W\\w]*AUTHORIZATIONS=([\\w\\.]*)[\\W\\w]*INSTALLATION-DATA=([\\w\\W]*)...NO-MODEL[\\W\\w]*");
            Matcher m = p.matcher(response);
            if (m.find()) {
                zuser = new ZUser(m.group(1), m.group(2), m.group(3), m.group(4), m.group(5), m.group(6), m.group(7));
            }
        } catch (JSchException | InterruptedException e) {
            System.out.println("Problems durring command execution");
            e.printStackTrace();
        }
        return zuser;
    }

    private static User getUSSUserDetails(ConnectionHandler ch, Connection con) {
        String cmd = "id";
        User u = null;
        try {
            String response = ch.send(con, cmd);
            Pattern pattern = Pattern
                    .compile("uid=(\\w*)\\((\\w*)\\).gid=(\\w*)\\((\\w*)\\).groups=(\\w*)\\((\\w*)\\)");
            Matcher m = pattern.matcher(response);
            if (m.find()) {
                u = new User(m.group(1), m.group(2), m.group(3), m.group(4));
            }
        } catch (JSchException | InterruptedException e) {
            System.out.println("Problems durring command execution");
            e.printStackTrace();
        }
        return u;
    }

    @GetMapping("/logoff")
    public String logoff(Model model) {
        model.addAttribute("credentials", new Credentials());
        ch.disconnect(con);
        return "logon";
    }

    // @PostMapping("/changepw")
    // public String changePassword(@ModelAttribute Password pw, Model model) {
    // User user = null;
    // Credentials credentials = new Credentials();
    // credentials.setUsername(pw.getUsername());
    // credentials.setPassword(pw.getOldPassword());
    // // String command = "echo -e \"" + pw.getOldPassword() + "\\" +
    // // pw.getNewPassword() + "\"| passwd "
    // // + pw.getUsername();

    // try {
    // // *String customFile = "// PATH='/z/" + pw.getUsername().toLowerCase() +
    // // "/test.out ";
    // // *String template = ch.send(con, "echo -e \"$(cat /z/" +
    // // pw.getUsername().toLowerCase()
    // // + "/racflstu.jcl)\"\\n// PATH='/z/" + pw.getUsername().toLowerCase() +
    // // "/test.out'");
    // String x = ch.send(con, "cat /z/" + pw.getUsername().toLowerCase() +
    // "/racflstu.jcl | submit");
    // /*
    // * System.out.println("echo -e \"$(cat /z/" + pw.getUsername().toLowerCase() +
    // * "/racflstu.jcl)\"\n// PATH='/z/" + pw.getUsername().toLowerCase() +
    // * "/test.out'");
    // */
    // String response = ch.send(con, "cat /z/" + pw.getUsername().toLowerCase() +
    // "/test.out");
    // System.out.println(response);
    // // System.out.println(ch.send(con, "cat /z/" +
    // pw.getUsername().toLowerCase()));
    // // System.out.println(ch.send(con, "onion8"));
    // // ch.disconnect(con);
    // // con = ConnectionHandler.connect(credentials.getUsername(),
    // // credentials.getPassword(), "192.86.32.153", 22);
    // // user = getUSSUserDetails(ch, con);

    // } catch (Exception e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // model.addAttribute("credentials", credentials);
    // model.addAttribute("user", user);
    // model.addAttribute("password", new Password());
    // return "test";

    // }

}
