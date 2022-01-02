package org.anlue.heartbeat.model;

import lombok.Data;

/**
 * Created by Jaeseong on 2022/01/02
 * Git Hub : https://github.com/AnJaeSeongS2
 */
@Data
public class Person {
   private Long id;
   private String name;
   private Integer age;
   private Boolean alive;
}
