package org.anlue.heartbeat.controller;

import org.anlue.heartbeat.model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jaeseong on 2022/01/02
 * Git Hub : https://github.com/AnJaeSeongS2
 */
@RestController
@RequestMapping("info")
public class InfoController {

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Person> info() {
        Person me = new Person();
        me.setAge(32);
        me.setAlive(true);
        me.setName("anlue");
        me.setId(9100001111111L);
        return ResponseEntity.ok(me);
    }
}
