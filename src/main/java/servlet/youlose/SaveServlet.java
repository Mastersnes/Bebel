package servlet.youlose;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller permettant d'effectuer une sauvegarde youlose
 * 
 */
@Controller
public class SaveServlet {

	@RequestMapping("/save")
	@ResponseBody
	public String save() {
		return "Save !";
	}

	@RequestMapping("/getSave")
	@ResponseBody
	public String getSave() {
		return "getSave !";
	}

}
