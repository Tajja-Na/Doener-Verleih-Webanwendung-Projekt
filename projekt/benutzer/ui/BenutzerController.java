import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/benutzer/{n}")
public class BenutzerController {
    
    @GetMapping()
    public String bearbeiten_get(@PathVariable("n") String benutzer, Model m){
        BenutzerFormular formular = new BenutzerFormular();
        m.addAttribute("benutzer", benutzer);
        m.addAttribute("formular", formular);
        return "bearbeiten";
    }

    @PostMapping()
    public String formular_post(){

    }
}
