package com.payneteasy.resin;

import com.caucho.resin.HttpEmbed;
import com.caucho.resin.ResinEmbed;
import com.caucho.resin.WebAppEmbed;
import com.payneteasy.Config;

public class MainResin {

    public static void main(String[] args) {
        ResinEmbed resin = new ResinEmbed();

        HttpEmbed http = new HttpEmbed(Config.RESIN);
        resin.addPort(http);


        WebAppEmbed webapp = new WebAppEmbed("/", "src/main/webapp");
        resin.addWebApp(webapp);

        resin.start();
        resin.join();
    }
}
