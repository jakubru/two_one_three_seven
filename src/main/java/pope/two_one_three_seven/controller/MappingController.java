package pope.two_one_three_seven.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pope.two_one_three_seven.model.*;

import java.util.Map;

@Controller
public class MappingController {

    private Game mGame = new Game();

    @RequestMapping(value = "/addPlayer", method = RequestMethod.POST)
    @ResponseBody
    public String addPlayer(@RequestParam("nick") String nick, Map<String,Object> model){
        model.put("nick", nick);
        return Boolean.toString(mGame.addPlayer(nick));
    }

    @RequestMapping(value = "/startGame", method = RequestMethod.POST)
    @ResponseBody
    public String startGame(){
        return Boolean.toString(mGame.getSize() == 3);
    }

    @RequestMapping(value = "/move", method = RequestMethod.POST)
    @ResponseBody
    public String move(@RequestParam("nick") String nick, @RequestParam("pointID") int pointID){
        return Boolean.toString(mGame.makeMove(nick, pointID));
    }

    @RequestMapping(value = "/isActive", method = RequestMethod.POST)
    @ResponseBody
    public String isActive(@RequestParam("nick") String nick){
        return Boolean.toString(mGame.getPlayer(nick).isActive());
    }


    @RequestMapping(value = "/getField", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public Field getField(){
        return this.mGame.getField();
    }
}
