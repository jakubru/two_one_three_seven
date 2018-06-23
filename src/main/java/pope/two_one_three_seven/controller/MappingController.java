package pope.two_one_three_seven.controller;

import com.sun.javafx.css.converters.BooleanConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pope.two_one_three_seven.model.*;

import java.util.ArrayList;
import java.util.List;
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
        Point p1 = new Point(1,1,1,true);
        Point p2 = new Point(2,1,2,false);
        Point p3 = new Point(3,1,3,true);
        Point p4 = new Point(4,1,4,true);
        Point p5 = new Point(5,1,5,true);
        Point p6 = new Point(6,1,6,true);
        Point p7 = new Point(8,1,7,false);
        List<Point> points1 = new ArrayList<Point>();
        points1.add(p1);
        points1.add(p2);
        points1.add(p3);
        List<Point> points2 = new ArrayList<Point>();
        points2.add(p4);
        points2.add(p5);
        List<Point> points3 = new ArrayList<Point>();
        points3.add(p6);
        points3.add(p7);
        Line l1 = new Line(points1);
        Line l2 = new Line(points2);
        Line l3 = new Line(points3);
        List<Line> lines = new ArrayList<Line>();
        lines.add(l1);
        lines.add(l2);
        lines.add(l3);
        Circle c = new Circle(p1,7);
        return new Field(c,lines);


    }
}
