package pope.two_one_three_seven.controller;

import com.sun.javafx.css.converters.BooleanConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pope.two_one_three_seven.model.Field;
import pope.two_one_three_seven.model.ListOfPlayers;

import java.util.Map;

@Controller
public class MappingController {

    @RequestMapping(value = "/addPlayer", method = RequestMethod.POST)
    @ResponseBody
    public String addPlayer(@RequestParam("nick") String nick, Map<String,Object> model){
        model.put("nick", nick);
        if(ListOfPlayers.getInstance().addPlayer(nick))
            return "ok";
        else
            return "no ok";
    }

    @RequestMapping(value = "/startGame", method = RequestMethod.POST)
    @ResponseBody
    public String startGame(){
        if(ListOfPlayers.getInstance().getSize() == 3)
            return "start";
        else
            return "wait";
    }

    @RequestMapping(value = "/move", method = RequestMethod.POST)
    @ResponseBody
    public String move(@RequestParam("nick") String nick, @RequestParam("pointID") int pointID){
        if(ListOfPlayers.getInstance().getPlayer(nick).isActive()){
            ListOfPlayers.getInstance().getPlayer(nick).deactivate();
            ListOfPlayers.getInstance().getNext(nick).activate();
            return "ok";
        }
        else{
            return "no ok";
        }
    }

    @RequestMapping(value = "/isActive", method = RequestMethod.POST)
    @ResponseBody
    public String isActive(@RequestParam("nick") String nick){
        return Boolean.toString(ListOfPlayers.getInstance().getPlayer(nick).isActive());
    }


    @RequestMapping(value = "/getField", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public Field getField(){
        return Field.getInstance();
    }
}
