package com.stock.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.stock.algo.StockAlgoService;
import com.stock.model.CompanyModel;
import com.stock.model.HistoricalRecord;
import com.stock.model.StockInfoModel;
import com.stock.request.YahooWebRequest;
import com.stock.service.StockManager;

@Controller
@RequestMapping("yahoo")
public class YahooWebController {
    @Autowired
    private YahooWebRequest yahooRequest;
    @Autowired
    private StockManager manager;
    @Autowired
    private StockAlgoService algo;

    @RequestMapping(method = RequestMethod.GET)
    public String initPage(Model model) throws IOException {
        model.addAttribute("message", "Welcome to the Yahoo Page");
        model.addAttribute("lastUpdateTime", manager.getLastUpdateTime());
        model.addAttribute("tsxComp", populateYearlyLowCompany());
        return "yahoo";
    }

    public List<StockInfoModel> populateYearlyLowCompany() throws IOException {
        List<StockInfoModel> model = manager.getAllRecentStockInfo();
        List<StockInfoModel> newModel = new ArrayList<StockInfoModel>();
        for (StockInfoModel s : model) {
            StockInfoModel tmp = algo.getYearLowStock(s);
            if (tmp != null) newModel.add(s);
        }
        return newModel;
    }

    public String upload(@RequestParam String action, Model model) throws IOException {
        if (action.equals("updateExchange")) {
            List<CompanyModel> companyList = new ArrayList<CompanyModel>();
            List<CompanyModel> newCompanyList = new ArrayList<CompanyModel>();
            companyList = manager.findAllCompanies();
            for (int i = 0; i < companyList.size(); i++) {
                StockInfoModel stockModel = yahooRequest.getRecentQuote(companyList
                        .get(i).getSymbol());
                companyList.get(i).setExchange(stockModel.getExchange());
                newCompanyList.add(companyList.get(i));
                if (i % 11 == 10) {
                    manager.updateExchange(newCompanyList);
                    newCompanyList.clear();
                }
            }
            manager.updateExchange(newCompanyList);
            model.addAttribute("message", "Getting recent quote...");
        }
        else if (action.equals("recent")) {
            model.addAttribute("message", "Gettng recent quote...");
        }
        else if (action.equals("historical")) {
            // TODO
            // Parse Date and only do per month
            List<HistoricalRecord> hr = new ArrayList<HistoricalRecord>();
            String symbol = "BUI.TO";
            hr = yahooRequest.getHistoricalQuoteCsv(symbol, "01", "01", "2000", "31",
                    "12", "2014");
            manager.insertHistoricalRecords(symbol, hr);
            model.addAttribute("message", "Uploaded to database..");
        }
        return "yahoo";
    }
}
