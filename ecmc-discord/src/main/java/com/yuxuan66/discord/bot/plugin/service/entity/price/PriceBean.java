/**
  * Copyright 2023 bejson.com 
  */
package com.yuxuan66.discord.bot.plugin.service.entity.price;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PriceBean {

   private int id;
   private long buy;
   private long sell;
}