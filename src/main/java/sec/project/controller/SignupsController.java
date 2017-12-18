package sec.project.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;

@Controller
public class SignupsController {

    @Autowired
    private SignupRepository signupRepository;

    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/signups", method = RequestMethod.GET)
    public String signUps(Model model, @RequestParam(required = false) boolean admin) {
        List<Signup> signups = signupRepository.findAll();
        model.addAttribute("signups", signups);

        model.addAttribute("name", session.getAttribute("name"));
        model.addAttribute("address", session.getAttribute("address"));

        if (admin) model.addAttribute("admin", true);

        return "signups";
    }

    @RequestMapping(value = "/signups/login", method = RequestMethod.GET)
    public String admin(Model model, @RequestParam String user, @RequestParam String pass) {
        if (user.equals("admin") && pass.equals("admin")) {
            return "redirect:/signups?admin=true";
        }

        return "redirect:/signups?admin=false";
    }
}
