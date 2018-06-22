package pope.two_one_three_seven.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pope.two_one_three_seven.model.ListOfPlayers;

import java.util.Map;

@Controller
public class MappingController {

    @RequestMapping(value = "/play", method = RequestMethod.POST)
    public String addPlayer(@RequestParam("nick") String nick, Map<String,Object> model){
        model.put("nick", nick);
        if(ListOfPlayers.getInstance().addPlayer(nick))
            return "game";
        else
            return "wrongnick";
    }

}
