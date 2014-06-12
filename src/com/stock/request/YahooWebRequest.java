package com.stock.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.algo.StockAlgoService;
import com.stock.model.HistoricalRecord;
import com.stock.model.StockInfoModel;

@Service
public class YahooWebRequest {

	private String URL_YAHOO = "http://finance.yahoo.com/d/a.csv?s=:symbol&f=abdee7e8jkj1vyk1j6k5xe9gh";
	private String URL_YAHOO_HISTORY = "http://ca.finance.yahoo.com/q/hp?s=:symbol&a=:startMonth&b=:startDay&c=:startYear"
			+ "&d=:endMonth&e=:endDay&f=:endYear&g=d&ignore=.csv";
	private String URL_YAHOO_HISTORY_CSV = "http://ichart.finance.yahoo.com/table.csv?s=:symbol&a=:startMonth&b=:startDay&c=:startYear&d=:endMonth&e=:endDay&f=:endYear&g=d&ignore=.csv";

	@Autowired
	private StockAlgoService service;

	// private String URL_YAHOO_COMPANY_QUERY =
	// "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.industry%20where%20id%20in%20(select%20industry.id%20from%20yahoo.finance.sectors)&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

	public StockInfoModel getRecentQuote(String symbol) throws IOException {
		String url = URL_YAHOO.replace(":symbol", symbol);
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		System.out.println("Calling request with url: " + url);
		HttpResponse response = client.execute(request);
		System.out.println("Parsing response...");
		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		String[] token = result.toString().replaceAll("\"", "").split(",");
		StockInfoModel model = new StockInfoModel();

		try {

			model.setSymbol(symbol);
			model.setAsk(token[0]);
			model.setBid(token[1]);
			model.setDividendPerShare(token[2]);
			model.setEarningPerShare(token[3]);
			model.setEpsEstimateCurrentYear(token[4]);
			model.setEpsEstimateNextYear(token[5]);
			model.setYearLow(token[6]);
			model.setYearHigh(token[7]);
			model.setMarketCap(token[8]);
			model.setVolume(token[9]);
			model.setDividendYield(token[10]);
			model.setLastTradePrice(token[11].replace("<b>", "")
					.replace("</b>", "").replace("N/A - ", ""));
			model.setChangeFromYearLowPercent(token[12]);
			model.setChangeFromYearHighPercent(token[13]);
			model.setExchange(token[14]);
			model.setEpsEstimateNextQuarter(token[15]);
			model.setDayLow(token[16]);
			model.setDayHigh(token[17]);
			return model;

		} catch (Exception e) {
			// e.printStackTrace();
		}
		return null;
	}

	public String getHistoricalQuote(String symbol, String startDay,
			String startMonth, String startYear, String endDay,
			String endMonth, String endYear) throws IOException {

		String url = URL_YAHOO_HISTORY.replace(":symbol", symbol)
				.replace(":startMonth", startMonth)
				.replace(":startDay", startDay)
				.replace(":startYear", startYear).replace(":endDay", endDay)
				.replace(":endMonth", endMonth).replace(":endYear", endYear);

		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = client.execute(request);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		BufferedReader rd = null;
		try {
			rd = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		return result.toString();

	}

	public List<HistoricalRecord> getHistoricalQuoteCsv(String symbol,
			String startDay, String startMonth, String startYear,
			String endDay, String endMonth, String endYear) throws IOException {

		String url = URL_YAHOO_HISTORY_CSV.replace(":symbol", symbol)
				.replace(":startMonth", startMonth)
				.replace(":startDay", startDay)
				.replace(":startYear", startYear).replace(":endDay", endDay)
				.replace(":endMonth", endMonth).replace(":endYear", endYear);

		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = client.execute(request);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		BufferedReader rd = null;
		try {
			rd = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));

		} catch (Exception e) {
			e.printStackTrace();
		}

		List<HistoricalRecord> historicList = new ArrayList<HistoricalRecord>();

		String line = "";
		rd.readLine();
		while ((line = rd.readLine()) != null) {
			String[] token = line.split(",");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			HistoricalRecord historicalRecord = new HistoricalRecord();
			try {
				historicalRecord.setTradeDate(df.parse(token[0]));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			historicalRecord.setOpeningPrice(Double.valueOf(token[1]));
			historicalRecord.setDayHigh(Double.valueOf(token[2]));
			historicalRecord.setDayLow(Double.valueOf(token[3]));
			historicalRecord.setClosingPrice(Double.valueOf(token[4]));
			historicalRecord.setVolume(Double.valueOf(token[5]));
			historicalRecord.setAdjustedClose(Double.valueOf(token[6]));
			historicList.add(historicalRecord);
		}
		return historicList;
	}

}
