package com.yuxuan66.discord.bot.images;

import com.ruiyun.jvppeteer.core.Puppeteer;
import com.ruiyun.jvppeteer.core.browser.Browser;
import com.ruiyun.jvppeteer.core.browser.BrowserFetcher;
import com.ruiyun.jvppeteer.core.page.ElementHandle;
import com.ruiyun.jvppeteer.core.page.Page;
import com.ruiyun.jvppeteer.options.Clip;
import com.ruiyun.jvppeteer.options.LaunchOptions;
import com.ruiyun.jvppeteer.options.LaunchOptionsBuilder;
import com.ruiyun.jvppeteer.options.ScreenshotOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 图片渲染核心类
 *
 * @author Sir丶雨轩
 * @since 2023/1/13
 */
@Slf4j
@Component
public class EcmcImage {

    private Browser browser;
    private boolean isOpen;
    // 截图次数
    private int num = 0;

   // @PostConstruct
    public void initialization() throws Exception {
        BrowserFetcher.downloadIfNotExist(null);
        LaunchOptions options = new LaunchOptionsBuilder()
                .withArgs(Arrays.asList("--disable-gpu", "--disable-dev-shm-usage", "--disable-setuid-sandbox", "--no-first-run", "--no-sandbox", "--no-zygote", "--single-process"))
                .withHeadless(true)
                .build();
        browser = Puppeteer.launch(options);
        browser.on("disconnected", event -> isOpen = false);
        isOpen = true;
        log.info("Browser Init Success...,{}", browser);
    }

    public void render(String path,String savePath){
      try{
          Page page = browser.newPage();
          page.goTo("file://" + path);
          ElementHandle body = page.$("#container");
          ScreenshotOptions options = new ScreenshotOptions();
          options.setClip(new Clip(0,0,800,400));
          options.setPath(savePath);
          body.screenshot(options);
          page.close();
          // 最大截图次数
          int maxNum = 300;
          if(num++ > maxNum){
              num = 0;
              initialization();
          }
      }catch (Exception e){

      }
    }

}
