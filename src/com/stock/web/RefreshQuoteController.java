package com.stock.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stock.model.CompanyModel;
import com.stock.model.StockInfoModel;
import com.stock.request.YahooWebRequest;
import com.stock.service.StockManager;

@Controller
@RequestMapping("refreshQuote")
public class RefreshQuoteController {
    @Autowired
    private YahooWebRequest yahooRequest;
    @Autowired
    private StockManager manager;

    @RequestMapping(method = RequestMethod.GET)
    public String initPage(Model model) throws IOException {
        updateRecentQuote();
        return "redirect:yahoo";
    }

    public void updateRecentQuote() {
        // LOGIC
        // 1. Get the list of companies that are trading in various exchanges
        // 2. Insert into the recent quote table
        // 3. Retrieve companies that are trading at minimum
        // 4. Display on screen
        List<CompanyModel> tsxCompanies = manager.findCompanyByExchange("Toronto");
        List<StockInfoModel> stockModel = new ArrayList<StockInfoModel>();
        int i = 0;
        for (CompanyModel cm : tsxCompanies) {
            if (cm.getSymbol().equals("N/A")) continue;
            StockInfoModel stockInfo;
            try {
                stockInfo = yahooRequest.getRecentQuote(cm.getSymbol());
                stockModel.add(stockInfo);
                if (i++ % 49 == 48) {
                    manager.insertRecentStockInfo(stockModel);
                    stockModel.clear();
                }
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
